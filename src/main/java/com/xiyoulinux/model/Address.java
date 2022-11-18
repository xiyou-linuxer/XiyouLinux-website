package com.xiyoulinux.model;
public class Address {
  private int id;
  private String address;
  private String postcode;

  public Address() {
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getAddress() {
    return this.address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getPostcode() {
    return this.postcode;
  }

  public void setPostcode(String postcode) {
    this.postcode = postcode;
  }
}
