package nh.examples.kstreams.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class OrderItemPayload extends AbstractDebeziumPayload {
  private final Integer id;
  private final Integer orderId;
  private final String product;
  private final Integer quantity;
  private final BigDecimal price;

  public OrderItemPayload(
    @JsonProperty("__op") String op,
    @JsonProperty("__deleted") boolean deleted,

    @JsonProperty("id") Integer id,
    @JsonProperty("order_id") Integer orderId,
    @JsonProperty("product") String product,
    @JsonProperty("quantity") Integer quantity,
    @JsonProperty("price") BigDecimal price
  ) {
    super(op, deleted);
    this.id = id;
    this.orderId = orderId;
    this.product = product;
    this.quantity = quantity;
    this.price = price;
  }

  public Integer getId() {
    return id;
  }

  public Integer getOrderId() {
    return orderId;
  }

  public String getProduct() {
    return product;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public BigDecimal getPrice() {
    return price;
  }

  @Override
  public String toString() {
    return "OrderItemPayload{" +
      "super=" + super.toString() +
      "id=" + id +
      ", orderId=" + orderId +
      ", product='" + product + '\'' +
      ", quantity=" + quantity +
      ", price=" + price +
      "} ";
  }
}
