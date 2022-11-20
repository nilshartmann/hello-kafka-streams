package nh.examples.kstreams.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderPayload extends AbstractDebeziumPayload {
  private final Integer id;
  private final Integer customerId;
  private final Integer discount;

  public OrderPayload(
    @JsonProperty("__op") String op,
    @JsonProperty("__deleted") boolean deleted,
    @JsonProperty("id") Integer id,
    @JsonProperty("customer_id") Integer customerId,
    @JsonProperty("discount") Integer discount) {
    super(op, deleted);
    this.id = id;
    this.customerId = customerId;
    this.discount = discount;
  }

  public Integer getId() {
    return id;
  }

  public Integer getCustomerId() {
    return customerId;
  }

  public Integer getDiscount() {
    return discount;
  }

  @Override
  public String toString() {
    return "OrderPayload{" +
      "super=" + super.toString() +
      "id=" + id +
      ", customerId=" + customerId +
      ", discount=" + discount +
      "} ";
  }
}
