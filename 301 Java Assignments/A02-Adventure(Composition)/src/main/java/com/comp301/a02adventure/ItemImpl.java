package com.comp301.a02adventure;

public class ItemImpl implements Item {
  private final String name;

  public ItemImpl(String name) {
    if (name == null) {
      throw new IllegalArgumentException("Name cannot be null");
    }
    this.name = name;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (other == null || getClass() != other.getClass()) {
      return false;
    }
    ItemImpl Other = (ItemImpl) other;
    return name.equals(Other.name);
  }

  @Override
  public String toString() {
    return this.name;
  }

  @Override
  public boolean equals(Item other) {
    if (other == null) {
      return false;
    }
    return name.equals(other.getName());
  }
}
