package com.xiyoulinux.idao;

import com.xiyoulinux.model.Community;

public interface Icommunity {
  boolean insert(Community var1);

  boolean delete(int var1);

  boolean update(Community var1);

  Community getCommunityByID(int var1);

  Community getCommunityByName(String var1);
}
