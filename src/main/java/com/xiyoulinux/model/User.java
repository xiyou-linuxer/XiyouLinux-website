package com.xiyoulinux.model;
public class User {
  private int id;
  private String name;
  private String passwd;

  public User() {
  }

  public int getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getPasswd() {
    return this.passwd;
  }

  public void setPasswd(String passwd) {
    this.passwd = passwd;
  }

  public void setName(String name) {
    this.name = name;
  }
}
