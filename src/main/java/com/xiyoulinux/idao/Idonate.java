package com.xiyoulinux.idao;

import com.xiyoulinux.model.Donate;

public interface Idonate {
  boolean insert(Donate var1);

  boolean delete(int var1);

  boolean update(Donate var1);

  Donate getDonateByID(int var1);
}
