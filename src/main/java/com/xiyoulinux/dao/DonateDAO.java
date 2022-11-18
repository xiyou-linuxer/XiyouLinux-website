package com.xiyoulinux.dao;

import com.xiyoulinux.idao.Idonate;
import com.xiyoulinux.model.Donate;
import com.xiyoulinux.util.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DonateDAO implements Idonate {
  public DonateDAO() {
  }

  public boolean insert(Donate donate) {
    boolean rtu = false;
    if (donate == null) {
      return rtu;
    } else {
      Connection conn = ConnectionManager.getInstance().getConnection();
      PreparedStatement ps = null;

      try {
        String sql = "insert into donate(wei_name,weixin,alipay_namep,alipay values(?,?,?)";
        ps = conn.prepareStatement(sql);
        ps.setString(1, donate.getWei_name());
        ps.setString(2, donate.getWeixin());
        ps.setString(3, donate.getAlipay_namep());
        ps.setString(4, donate.getAlipay());
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

  public boolean delete(int about_id) {
    boolean rtu = false;
    if (about_id == 0) {
      return rtu;
    } else {
      Connection conn = ConnectionManager.getInstance().getConnection();
      PreparedStatement ps = null;

      try {
        String sql = "delete from donate where id = ?";
        ps = conn.prepareStatement(sql);
        ps.setInt(1, about_id);
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

  public boolean update(Donate donate) {
    boolean rtu = false;
    if (donate == null) {
      return rtu;
    } else {
      Connection conn = ConnectionManager.getInstance().getConnection();
      PreparedStatement ps = null;

      try {
        String sql = "update donate set wei_name=?,weixin=?,alipay_namep=?,alipay= ? ";
        ps = conn.prepareStatement(sql);
        ps.setString(1, donate.getWei_name());
        ps.setString(2, donate.getWeixin());
        ps.setString(3, donate.getAlipay_namep());
        ps.setString(4, donate.getAlipay());
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

  public Donate getDonateByID(int donate_id) {
    Donate rtu = null;
    if (donate_id <= 0) {
      return rtu;
    } else {
      ResultSet rs = null;
      Connection conn = ConnectionManager.getInstance().getConnection();
      PreparedStatement ps = null;

      try {
        String sql = "select  set wei_name,weixin,alipay_namep,alipay, from community where id = ?";
        ps = conn.prepareStatement(sql);
        ps.setInt(1, donate_id);
        rs = ps.executeQuery();

        while(rs.next()) {
          rtu = new Donate();
          rtu.setWei_name(rs.getString("wei_name"));
          rtu.setWeixin(rs.getString("weixin"));
          rtu.setAlipay_namep(rs.getString("setAlipay_namep"));
          rtu.setAlipay(rs.getString("setAlipay"));
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
