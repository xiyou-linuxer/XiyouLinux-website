package com.xiyoulinux.dao;

import com.xiyoulinux.idao.Ifollow;
import com.xiyoulinux.model.Follow;
import com.xiyoulinux.util.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FollowDAO implements Ifollow {
  public FollowDAO() {
  }

  public boolean insert(Follow follow) {
    boolean rtu = false;
    if (follow == null) {
      return rtu;
    } else {
      Connection conn = ConnectionManager.getInstance().getConnection();
      PreparedStatement ps = null;

      try {
        String sql = "insert into follow (id,weixin) values(?,?)";
        ps = conn.prepareStatement(sql);
        ps.setInt(1, follow.getId());
        ps.setString(2, follow.getWenxin());
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
        String sql = "delete from follow where id = ?";
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

  public boolean update(Follow follow) {
    boolean rtu = false;
    if (follow == null) {
      return rtu;
    } else {
      Connection conn = ConnectionManager.getInstance().getConnection();
      PreparedStatement ps = null;

      try {
        String sql = "update follow set id= ? , weixin = ?";
        ps = conn.prepareStatement(sql);
        ps.setInt(1, follow.getId());
        ps.setString(2, follow.getWenxin());
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

  public Follow getFollowByID(int follow_id) {
    Follow rtu = null;
    if (follow_id <= 0) {
      return rtu;
    } else {
      ResultSet rs = null;
      Connection conn = ConnectionManager.getInstance().getConnection();
      PreparedStatement ps = null;

      try {
        String sql = "select id,weixin from follow where follow_id = ?";
        ps = conn.prepareStatement(sql);
        ps.setInt(1, follow_id);
        rs = ps.executeQuery();

        while(rs.next()) {
          rtu = new Follow();
          rtu.setId(rs.getInt("id"));
          rtu.setWenxin(rs.getString("weixin"));
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
