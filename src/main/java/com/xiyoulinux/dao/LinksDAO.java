package com.xiyoulinux.dao;

import com.xiyoulinux.idao.Ilinks;
import com.xiyoulinux.model.Links;
import com.xiyoulinux.util.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LinksDAO implements Ilinks {
  public LinksDAO() {
  }

  public boolean insert(Links links) {
    boolean rtu = false;
    if (links == null) {
      return rtu;
    } else {
      Connection conn = ConnectionManager.getInstance().getConnection();
      PreparedStatement ps = null;

      try {
        String sql = "insert into links(id,name,url) values(?,?,?)";
        ps = conn.prepareStatement(sql);
        ps.setInt(1, links.getId());
        ps.setString(2, links.getName());
        ps.setString(3, links.getUrl());
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

  public boolean update(Links links) {
    boolean rtu = false;
    if (links == null) {
      return rtu;
    } else {
      Connection conn = ConnectionManager.getInstance().getConnection();
      PreparedStatement ps = null;

      try {
        String sql = "update limks set id= ? , name = ? ,url = ?";
        ps = conn.prepareStatement(sql);
        ps.setInt(1, links.getId());
        ps.setString(2, links.getName());
        ps.setString(3, links.getUrl());
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

  public boolean delete(int link_id) {
    boolean rtu = false;
    if (link_id == 0) {
      return rtu;
    } else {
      Connection conn = ConnectionManager.getInstance().getConnection();
      PreparedStatement ps = null;

      try {
        String sql = "delete from links where id = ?";
        ps = conn.prepareStatement(sql);
        ps.setInt(1, link_id);
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

  public Links getLinksByID(int link_id) {
    Links rtu = null;
    if (link_id <= 0) {
      return rtu;
    } else {
      Connection conn = ConnectionManager.getInstance().getConnection();
      PreparedStatement ps = null;
      ResultSet rs = null;

      try {
        String sql = "select id,name,uurl from links where id =?";
        ps = conn.prepareStatement(sql);
        ps.setInt(1, link_id);
        rs = ps.executeQuery();

        while(rs.next()) {
          rtu = new Links();
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

  public Links getLinksByName(String name) {
    Links rtu = null;
    if (name != null && !name.equals("")) {
      ResultSet rs = null;
      Connection conn = ConnectionManager.getInstance().getConnection();
      PreparedStatement ps = null;

      try {
        String sql = "select id,name,url from links where name = ?";
        ps = conn.prepareStatement(sql);
        ps.setString(1, name);
        rs = ps.executeQuery();

        while(rs.next()) {
          rtu = new Links();
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
}
