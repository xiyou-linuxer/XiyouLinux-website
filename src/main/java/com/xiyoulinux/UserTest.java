package com.xiyoulinux;

import com.xiyoulinux.dao.BlogDAO;
import com.xiyoulinux.model.Blog;
import java.util.ArrayList;

public class UserTest {
  public UserTest() {
  }

  public static void main(String[] args) {
    BlogDAO blogDAO = new BlogDAO();
    ArrayList<Blog> list = new ArrayList();
    blogDAO.getBlogByPage(1, "lala");
    System.out.println(list.size());
  }
}
