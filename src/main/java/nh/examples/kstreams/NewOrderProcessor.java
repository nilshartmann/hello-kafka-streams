package nh.examples.kstreams;

import nh.examples.kstreams.model.*;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.state.KeyValueStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class NewOrderProcessor {

  private static final Logger log = LoggerFactory.getLogger(NewOrderProcessor.class);

  @Autowired
  void buildPipeline(StreamsBuilder streamsBuilder,
                     Serde<DebeziumKey> debeziumKeySerde,
                     Serde<OrderPayload> orderSerde,
                     Serde<OrderItemPayload> orderItemSerde,
                     Serde<OrderItemsPerOrder> orderItemsPerOrderSerde,
                     Serde<OrderWithOrderItems> orderWithOrderItemsSerde,
                     @Value("${order-service.topics.orders}") String ordersTopicName,
                     @Value("${order-service.topics.order-items}") String orderItemsTopicName) {

    KTable<DebeziumKey, OrderPayload> orderStream = streamsBuilder
      .table(ordersTopicName,
        Consumed.with(debeziumKeySerde, orderSerde));

    KTable<DebeziumKey, OrderItemsPerOrder> orderItemsTable = streamsBuilder
      .stream(orderItemsTopicName,
        Consumed.with(debeziumKeySerde, orderItemSerde))
      .filter((k, orderItemPayload) -> orderItemPayload.isCreatedEvent())
      .map((id, orderItemPayload) -> new KeyValue<>(new DebeziumKey(orderItemPayload.getOrderId()), orderItemPayload))
      .groupByKey(Grouped.with(debeziumKeySerde, orderItemSerde))
      .aggregate(
        OrderItemsPerOrder::new,
        (orderId, orderItem, orderItemsPerOrder) -> {
          orderItemsPerOrder.setOrderId(orderId.getId());
          orderItemsPerOrder.addOrderItemPayload(orderItem);
          return orderItemsPerOrder;
        },
        Materialized.<DebeziumKey, OrderItemsPerOrder, KeyValueStore<Bytes, byte[]>>
            as("orders-per-order-item")
          .withKeySerde(debeziumKeySerde)
          .withValueSerde(orderItemsPerOrderSerde)
      );

    KTable<DebeziumKey, OrderWithOrderItems> orderWithOrderItemsTable =
      orderStream.join(orderItemsTable,
        v -> new DebeziumKey(v.getId()),
        (order, orderItems) -> new OrderWithOrderItems(order, orderItems.getOrderItems()));

    orderWithOrderItemsTable.toStream().foreach((k, v) -> {
      log.info("Received new Event with Key '{}' and Value: '{}'", k, v);
    });

    orderWithOrderItemsTable.toStream().to("new_order",
      Produced.with(debeziumKeySerde, orderWithOrderItemsSerde));
  }
}