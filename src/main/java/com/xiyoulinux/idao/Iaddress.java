package com.xiyoulinux.idao;

import com.xiyoulinux.model.Address;

public interface Iaddress {
  boolean insert(Address var1);

  boolean delete(int var1);

  boolean update(Address var1);

  Address getAddressByID(int var1);
}
