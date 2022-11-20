package nh.examples.kstreams.model;

import java.util.LinkedList;
import java.util.List;

public class OrderItemsPerOrder {

  private Integer orderId;
  private final List<OrderItemPayload> orderItems = new LinkedList<>();

  public OrderItemsPerOrder() {
  }

  public void setOrderId(Integer orderId) {
    this.orderId = orderId;
  }

  public Integer getOrderId() {
    return orderId;
  }

  public List<OrderItemPayload> getOrderItems() {
    return orderItems;
  }

  public void addOrderItemPayload(OrderItemPayload orderItemPayload) {
    this.orderItems.add(orderItemPayload);
  }

  @Override
  public String toString() {
    return "OrderItemsPerOrder{" +
      "orderId=" + orderId +
      ", orderItems=" + orderItems +
      '}';
  }
}
