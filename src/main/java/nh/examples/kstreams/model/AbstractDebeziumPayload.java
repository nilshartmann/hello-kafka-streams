package nh.examples.kstreams.model;

public class AbstractDebeziumPayload {

  private final String op;
  private final boolean deleted;

  protected AbstractDebeziumPayload(
    String op, boolean deleted) {
    this.op = op;
    this.deleted = deleted;
  }

  public String getOp() {
    return op;
  }

  public boolean isDeleted() {
    return deleted;
  }

  public boolean isCreatedEvent() {
    return "c".equals(op);
  }

  @Override
  public String toString() {
    return "AbstractDebeziumPayload{" +
      "op='" + op + '\'' +
      ", deleted=" + deleted +
      '}';
  }
}
