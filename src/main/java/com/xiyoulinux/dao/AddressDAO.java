package com.xiyoulinux.dao;


import com.xiyoulinux.idao.Iaddress;
import com.xiyoulinux.model.Address;
import com.xiyoulinux.util.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressDAO implements Iaddress {
  public AddressDAO() {
  }

  public boolean insert(Address address) {
    boolean rtu = false;
    if (address == null) {
      return rtu;
    } else {
      Connection conn = ConnectionManager.getInstance().getConnection();
      PreparedStatement ps = null;

      try {
        String sql = "insert into address(address,postcode) values(?,?)";
        ps = conn.prepareStatement(sql);
        ps.setString(1, address.getAddress());
        ps.setString(2, address.getPostcode());
        ps.executeUpdate();
        rtu = true;
      } catch (SQLException var10) {
        var10.printStackTrace();
      } catch (Exception var11) {
        var11.printStackTrace();
      } finally {
        ConnectionManager.close((ResultSet)null, ps, conn);
        return rtu;
      }
    }
  }

  public boolean delete(int address_id) {
    boolean rtu = false;
    if (address_id == 0) {
      return rtu;
    } else {
      Connection conn = ConnectionManager.getInstance().getConnection();
      PreparedStatement ps = null;

      try {
        String sql = "delete from address where id = ?";
        ps = conn.prepareStatement(sql);
        ps.setInt(1, address_id);
        ps.executeUpdate();
        rtu = true;
      } catch (Exception var9) {
        var9.printStackTrace();
      } finally {
        ConnectionManager.close((ResultSet)null, ps, conn);
        return rtu;
      }
    }
  }

  public boolean update(Address address) {
    boolean rtu = false;
    if (address == null) {
      return rtu;
    } else {
      Connection conn = ConnectionManager.getInstance().getConnection();
      PreparedStatement ps = null;

      try {
        String sql = "update address set address= ? , postcode = ?";
        ps = conn.prepareStatement(sql);
        ps.setString(1, address.getAddress());
        ps.setString(2, address.getPostcode());
        rtu = true;
        return rtu;
      } catch (Exception var9) {
        var9.printStackTrace();
        return rtu;
      } finally {
        ;
      }
    }
  }

  public Address getAddressByID(int address_id) {
    Address rtu = null;
    if (address_id <= 0) {
      return rtu;
    } else {
      Connection conn = ConnectionManager.getInstance().getConnection();
      PreparedStatement ps = null;
      ResultSet rs = null;

      try {
        String sql = "select id,name,passwd from user where id =?";
        ps = conn.prepareStatement(sql);
        ps.setInt(1, address_id);
        rs = ps.executeQuery();

        while(rs.next()) {
          rtu = new Address();
          rtu.setId(rs.getInt("id"));
          rtu.setAddress(rs.getString("address"));
          rtu.setPostcode(rs.getString("postcode"));
        }

        return rtu;
      } catch (Exception var10) {
        var10.printStackTrace();
        return rtu;
      } finally {
        ;
      }
    }
  }
}
