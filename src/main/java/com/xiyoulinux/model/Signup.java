package com.xiyoulinux.model;
public class Signup {
  private int id;
  private int events_id;
  private String name;
  private String email;

  public Signup() {
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setEvents_id(int events_id) {
    this.events_id = events_id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public int getId() {
    return this.id;
  }

  public int getEvents_id() {
    return this.events_id;
  }

  public String getName() {
    return this.name;
  }

  public String getEmail() {
    return this.email;
  }
}
