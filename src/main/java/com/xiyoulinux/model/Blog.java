package com.xiyoulinux.model;

public class Blog {
  private int id;
  private String title;
  private String author;
  private String date;
  private String time;
  private String summary;
  private String url;
  private int status;

  public Blog() {
  }

  public int getStatus() {
    return this.status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public int getId() {
    return this.id;
  }

  public String getTitle() {
    return this.title;
  }

  public String getAuthor() {
    return this.author;
  }

  public String getDate() {
    return this.date;
  }

  public String getTime() {
    return this.time;
  }

  public String getSummary() {
    return this.summary;
  }

  public String getUrl() {
    return this.url;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }

  public void setUrl(String url) {
    this.url = url;
  }
}
