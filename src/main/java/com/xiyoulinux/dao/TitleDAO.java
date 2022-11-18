package com.xiyoulinux.dao;

import com.xiyoulinux.idao.Ititle;
import com.xiyoulinux.model.Title;
import com.xiyoulinux.util.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TitleDAO implements Ititle {
  public TitleDAO() {
  }

  public boolean update(Title title) {
    boolean rtu = false;
    if (title == null) {
      return rtu;
    } else {
      Connection conn = ConnectionManager.getInstance().getConnection();
      PreparedStatement ps = null;

      try {
        String sql = "update title set title = ?,subtitle = ?,url=? where id = ?";
        ps = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ps.setString(1, title.getTitle());
        ps.setString(2, title.getSubtitle());
        ps.setString(3, title.getUrl());
        ps.setInt(4, title.getId());
        ps.executeUpdate();
        rtu = true;
      } catch (SQLException var10) {
        var10.printStackTrace();
      } catch (Exception var11) {
        var11.printStackTrace();
      } finally {
        ConnectionManager.close((ResultSet)null, ps, conn);
      }

      return rtu;
    }
  }

  public Title getTitleById(int title_id) {
    if (title_id <= 0) {
      return null;
    } else {
      Connection conn = ConnectionManager.getInstance().getConnection();
      PreparedStatement ps = null;

      try {
        try {
          String sql = "select id,title,subtitle,url from title where id = ?";
          ps = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
          ps.setInt(1, title_id);
          ResultSet rs = ps.executeQuery();
          rs.last();
          Title title;
          if (rs.getRow() == 0) {
            title = null;
            return title;
          }

          rs.beforeFirst();
          title = new Title();

          while(rs.next()) {
            title.setId(rs.getInt(1));
            title.setTitle(rs.getString(2));
            title.setSubtitle(rs.getString(3));
            title.setUrl(rs.getString(4));
          }

          Title var7 = title;
          return var7;
        } catch (SQLException var12) {
          var12.printStackTrace();
        } catch (Exception var13) {
          var13.printStackTrace();
        }

        return null;
      } finally {
        ConnectionManager.close((ResultSet)null, ps, conn);
      }
    }
  }
}
