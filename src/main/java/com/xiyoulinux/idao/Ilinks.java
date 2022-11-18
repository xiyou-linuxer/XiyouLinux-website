package com.xiyoulinux.idao;

import com.xiyoulinux.model.Links;

public interface Ilinks {
  boolean insert(Links var1);

  boolean delete(int var1);

  boolean update(Links var1);

  Links getLinksByID(int var1);

  Links getLinksByName(String var1);
}
