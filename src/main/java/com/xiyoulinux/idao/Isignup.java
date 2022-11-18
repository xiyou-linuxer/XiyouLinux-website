package com.xiyoulinux.idao;

import com.xiyoulinux.model.Signup;

public interface Isignup {
  boolean insert(Signup var1);

  boolean delete(int var1);

  boolean update(Signup var1);

  Signup getSignupByID(int var1);

  Signup getSignupByName(String var1);

  Signup getSignupByEventsID(int var1);
}
