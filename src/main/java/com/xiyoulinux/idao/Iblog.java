package com.xiyoulinux.idao;


import com.xiyoulinux.model.Blog;
import java.util.ArrayList;
public interface Iblog {
  boolean insert(Blog var1);

  boolean delete(int var1);

  boolean update(Blog var1);

  Blog getBlogByID(int var1);

  ArrayList<Blog> getBlogByTitle(String var1);

  ArrayList getBlogByPage(int var1, String var2);

  ArrayList getBlogByPage(int var1, String var2, int var3);

  ArrayList getBlogByNumber(int var1);

  boolean alterBlogStatus(int var1);
}
