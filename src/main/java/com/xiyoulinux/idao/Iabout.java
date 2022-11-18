package com.xiyoulinux.idao;

import com.xiyoulinux.model.About;
import java.util.ArrayList;

public interface Iabout {
  boolean insert(About var1);

  boolean delete(int var1);

  boolean update(About var1);

  About getAboutByID(int var1);

  About getAboutByTitle(String var1);

  ArrayList<About> getAboutByPage(int var1, String var2);
}
