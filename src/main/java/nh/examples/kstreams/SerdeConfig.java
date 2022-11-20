package nh.examples.kstreams;

import nh.examples.kstreams.model.*;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

@Configuration
public class SerdeConfig {

  @Bean
  public static Serde<OrderPayload> orderSerde() {
    JsonSerializer<OrderPayload> serializer = new JsonSerializer<>();
    JsonDeserializer<OrderPayload> deserializer = new JsonDeserializer<>(OrderPayload.class);
    return Serdes.serdeFrom(serializer, deserializer);
  }

  @Bean
  public static Serde<OrderItemPayload> orderItemSerde() {
    JsonSerializer<OrderItemPayload> serializer = new JsonSerializer<>();
    JsonDeserializer<OrderItemPayload> deserializer = new JsonDeserializer<>(OrderItemPayload.class, false);
    return Serdes.serdeFrom(serializer, deserializer);
  }
  @Bean
  public static Serde<OrderItemsPerOrder> orderItemsPerOrderSerde() {
    JsonSerializer<OrderItemsPerOrder> serializer = new JsonSerializer<>();
    JsonDeserializer<OrderItemsPerOrder> deserializer = new JsonDeserializer<>(OrderItemsPerOrder.class, false);
    return Serdes.serdeFrom(serializer, deserializer);
  }

  @Bean
  public static Serde<OrderWithOrderItems> orderWithOrderItemsSerde() {
    JsonSerializer<OrderWithOrderItems> serializer = new JsonSerializer<>();
    JsonDeserializer<OrderWithOrderItems> deserializer = new JsonDeserializer<>(OrderWithOrderItems.class, false);
    return Serdes.serdeFrom(serializer, deserializer);
  }

  @Bean
  public static Serde<DebeziumKey> debeziumKeySerde() {
    JsonSerializer<DebeziumKey> serializer = new JsonSerializer<>();
    JsonDeserializer<DebeziumKey> deserializer = new JsonDeserializer<>(DebeziumKey.class, false);
    return Serdes.serdeFrom(serializer, deserializer);
  }
}
