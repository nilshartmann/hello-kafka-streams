package nh.examples.kstreams.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DebeziumKey {
  private final Integer id;

  public DebeziumKey(@JsonProperty("id") Integer id) {
    this.id = id;
  }

  public Integer getId() {
    return id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    DebeziumKey defaultId = (DebeziumKey) o;

    return id != null ? id.equals(defaultId.id) : defaultId.id == null;
  }

  @Override
  public int hashCode() {
    return id != null ? id.hashCode() : 0;
  }

  @Override
  public String toString() {
    return "DefaultId{" +
      "id=" + id +
      '}';
  }
}
