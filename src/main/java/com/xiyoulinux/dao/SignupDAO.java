package com.xiyoulinux.dao;

import com.xiyoulinux.idao.Isignup;
import com.xiyoulinux.model.Signup;
import com.xiyoulinux.util.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SignupDAO implements Isignup {
  public SignupDAO() {
  }

  public boolean insert(Signup signup) {
    boolean rtu = false;
    if (signup == null) {
      return rtu;
    } else {
      Connection conn = ConnectionManager.getInstance().getConnection();
      PreparedStatement ps = null;

      try {
        String sql = "insert into signup (id,events_id,name,email) values(?,?,?,?)";
        ps = conn.prepareStatement(sql);
        ps.setInt(1, signup.getId());
        ps.setInt(2, signup.getEvents_id());
        ps.setString(4, signup.getEmail());
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

  public boolean delete(int sign_id) {
    boolean rtu = false;
    if (sign_id == 0) {
      return rtu;
    } else {
      Connection conn = ConnectionManager.getInstance().getConnection();
      PreparedStatement ps = null;

      try {
        String sql = "delete from signup where sign_id = ?";
        ps = conn.prepareStatement(sql);
        ps.setInt(1, sign_id);
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

  public boolean update(Signup signup) {
    boolean rtu = false;
    if (signup == null) {
      return rtu;
    } else {
      Connection conn = ConnectionManager.getInstance().getConnection();
      PreparedStatement ps = null;

      try {
        String sql = "update signup set events_id = ? ,name= ? , email = ?";
        ps = conn.prepareStatement(sql);
        ps.setInt(1, signup.getEvents_id());
        ps.setString(2, signup.getName());
        ps.setString(3, signup.getEmail());
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

  public Signup getSignupByID(int signup_id) {
    Signup rtu = null;
    if (signup_id <= 0) {
      return rtu;
    } else {
      ResultSet rs = null;
      Connection conn = ConnectionManager.getInstance().getConnection();
      PreparedStatement ps = null;

      try {
        String sql = "select id,events_id,name,email from user where signup_id = ?";
        ps = conn.prepareStatement(sql);
        ps.setInt(1, signup_id);
        rs = ps.executeQuery();

        while(rs.next()) {
          rtu = new Signup();
          rtu.setId(rs.getInt("id"));
          rtu.setEvents_id(rs.getInt("events_id"));
          rtu.setName(rs.getString("name"));
          rtu.setEmail(rs.getString("email"));
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

  public Signup getSignupByName(String name) {
    Signup rtu = null;
    if (name != null && !name.equals("")) {
      ResultSet rs = null;
      Connection conn = ConnectionManager.getInstance().getConnection();
      PreparedStatement ps = null;

      try {
        String sql = "select id,evens_id,name,emil from user where name = ?";
        ps = conn.prepareStatement(sql);
        ps.setString(1, name);
        rs = ps.executeQuery();

        while(rs.next()) {
          rtu = new Signup();
          rtu.setName(rs.getString("name"));
          rtu.setId(rs.getInt("id"));
          rtu.setEvents_id(rs.getInt("events_id"));
          rtu.setEmail(rs.getString("email"));
        }

        return rtu;
      } catch (Exception var10) {
        var10.printStackTrace();
        return rtu;
      } finally {
        ;
      }
    } else {
      return rtu;
    }
  }

  public Signup getSignupByEventsID(int events_id) {
    Signup rtu = null;
    if (events_id <= 0) {
      return rtu;
    } else {
      ResultSet rs = null;
      Connection conn = ConnectionManager.getInstance().getConnection();
      PreparedStatement ps = null;

      try {
        String sql = "select id,events_id,name,email from user where events_id = ?";
        ps = conn.prepareStatement(sql);
        ps.setInt(1, events_id);
        rs = ps.executeQuery();

        while(rs.next()) {
          rtu = new Signup();
          rtu.setEvents_id(rs.getInt("events_id"));
          rtu.setId(rs.getInt("id"));
          rtu.setName(rs.getString("name"));
          rtu.setEmail(rs.getString("email"));
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

  public static void main(String[] args) {
    SignupDAO a = new SignupDAO();
    Signup b = new Signup();
    b.setEmail("32335322");
    b.setEvents_id(1);
    b.setId(1);
    b.setName("xiaom");
    a.insert(b);
  }
}
