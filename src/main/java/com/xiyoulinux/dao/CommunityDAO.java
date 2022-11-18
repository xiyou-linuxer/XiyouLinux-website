package com.xiyoulinux.dao;


import com.xiyoulinux.idao.Icommunity;
import com.xiyoulinux.model.Community;
import com.xiyoulinux.util.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CommunityDAO implements Icommunity {
  public CommunityDAO() {
  }

  public boolean insert(Community community) {
    boolean rtu = false;
    if (community == null) {
      return rtu;
    } else {
      Connection conn = ConnectionManager.getInstance().getConnection();
      PreparedStatement ps = null;

      try {
        String sql = "insert into community(id,name,url) values(?,?,?)";
        ps = conn.prepareStatement(sql);
        ps.setString(1, community.getName());
        ps.setString(2, community.getUrl());
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

  public boolean delete(int community_id) {
    boolean rtu = false;
    if (community_id == 0) {
      return rtu;
    } else {
      Connection conn = ConnectionManager.getInstance().getConnection();
      PreparedStatement ps = null;

      try {
        String sql = "delete from community where id = ?";
        ps = conn.prepareStatement(sql);
        ps.setInt(1, community_id);
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

  public boolean update(Community community) {
    boolean rtu = false;
    if (community == null) {
      return rtu;
    } else {
      Connection conn = ConnectionManager.getInstance().getConnection();
      PreparedStatement ps = null;

      try {
        String sql = "update community set name= ? , passwd = ?";
        ps = conn.prepareStatement(sql);
        ps.setString(1, community.getName());
        ps.setString(2, community.getUrl());
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

  public Community getCommunityByName(String community_name) {
    Community rtu = null;
    if (community_name != null && !community_name.equals("")) {
      ResultSet rs = null;
      Connection conn = ConnectionManager.getInstance().getConnection();
      PreparedStatement ps = null;

      try {
        String sql = "select id,name, from community where name = ?";
        ps = conn.prepareStatement(sql);
        ps.setString(1, community_name);
        rs = ps.executeQuery();

        while(rs.next()) {
          rtu = new Community();
          rtu.setName(rs.getString("name"));
          rtu.setId(rs.getInt("id"));
          rtu.setUrl(rs.getString("url"));
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

  public Community getCommunityByID(int community_id) {
    Community rtu = null;
    if (community_id <= 0) {
      return rtu;
    } else {
      Connection conn = ConnectionManager.getInstance().getConnection();
      PreparedStatement ps = null;
      ResultSet rs = null;

      try {
        String sql = "select id,name,url from community where id =?";
        ps = conn.prepareStatement(sql);
        ps.setInt(1, community_id);
        rs = ps.executeQuery();

        while(rs.next()) {
          rtu = new Community();
          rtu.setId(rs.getInt("id"));
          rtu.setName(rs.getString("name"));
          rtu.setUrl(rs.getString("url"));
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
