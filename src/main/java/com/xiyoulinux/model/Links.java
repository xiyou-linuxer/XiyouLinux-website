package com.xiyoulinux.model;
public class Links {
  private int id;
  private String name;
  private String url;

  public Links() {
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public int getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public String getUrl() {
    return this.url;
  }
}
