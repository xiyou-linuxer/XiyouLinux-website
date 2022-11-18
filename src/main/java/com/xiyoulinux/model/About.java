package com.xiyoulinux.model;

public class About {
  private int id;
  private String title;
  private String content;
  private String markdown;
  private String picture_url;
  private int status;

  public About() {
  }

  public int getStatus() {
    return this.status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public String getMarkdown() {
    return this.markdown;
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

  public void setPicture_url(String picture_url) {
    this.picture_url = picture_url;
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

  public String getPicture_url() {
    return this.picture_url;
  }
}
