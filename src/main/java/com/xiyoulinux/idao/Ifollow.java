package com.xiyoulinux.idao;


import com.xiyoulinux.model.Follow;

public interface Ifollow {
  boolean insert(Follow var1);

  boolean delete(int var1);

  boolean update(Follow var1);

  Follow getFollowByID(int var1);
}
