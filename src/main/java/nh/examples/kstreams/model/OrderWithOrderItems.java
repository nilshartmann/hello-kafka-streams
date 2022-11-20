package nh.examples.kstreams.model;


import java.util.List;

public class OrderWithOrderItems {

  private final OrderPayload order;
  private final List<OrderItemPayload> orderItems;

  public OrderWithOrderItems(OrderPayload order, List<OrderItemPayload> orderItems) {
    this.order = order;
    this.orderItems = orderItems;
  }

  public OrderPayload getOrder() {
    return order;
  }

  public List<OrderItemPayload> getOrderItems() {
    return orderItems;
  }

  @Override
  public String toString() {
    return "OrderWithOrderItems{" +
      "order=" + order +
      ", orderItems=" + orderItems +
      '}';
  }
}
