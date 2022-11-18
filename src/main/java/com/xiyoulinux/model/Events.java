package com.xiyoulinux.model;

public class Events {
  private int id;
  private String title;
  private String content;
  private String markdown;
  private String poster;
  private String date;
  private String time;
  private String address;
  private String tips;
  private int status;
  private int reader;

  public Events() {
  }

  public String getTips() {
    return this.tips;
  }

  public void setTips(String tips) {
    this.tips = tips;
  }

  public int getStatus() {
    return this.status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public void setMarkdown(String markdown) {
    this.markdown = markdown;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public void setPoster(String poster) {
    this.poster = poster;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public void setLabel(String tips) {
    this.tips = tips;
  }

  public void setReader(int reader) {
    this.reader = reader;
  }

  public String getMarkdown() {
    return this.markdown;
  }

  public int getId() {
    return this.id;
  }

  public String getTitle() {
    return this.title;
  }

  public String getContent() {
    return this.content;
  }

  public String getPoster() {
    return this.poster;
  }

  public String getDate() {
    return this.date;
  }

  public String getTime() {
    return this.time;
  }

  public String getAddress() {
    return this.address;
  }

  public String getLabel() {
    return this.tips;
  }

  public int getReader() {
    return this.reader;
  }
}
