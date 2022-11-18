package com.xiyoulinux.idao;
import com.xiyoulinux.model.User;

public interface Iuser {
  boolean insert(User var1);

  boolean delete(int var1);

  boolean update(User var1);

  User getUserByName(String var1);

  User getUserByID(int var1);

  boolean check(String var1, String var2);
}

