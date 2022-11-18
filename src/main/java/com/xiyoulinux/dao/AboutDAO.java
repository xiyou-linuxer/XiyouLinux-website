package com.xiyoulinux.dao;


import com.xiyoulinux.idao.Iabout;
import com.xiyoulinux.model.About;
import com.xiyoulinux.util.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AboutDAO implements Iabout {
  private static final int PAGE_SIZE = 10;
  private int allCount;
  private int allPageCount;
  private int currentPage;

  public AboutDAO() {
  }

  public static int getPageSize() {
    return 10;
  }

  public int getAllCount() {
    return this.allCount;
  }

  public void setAllCount(int allCount) {
    this.allCount = allCount;
  }

  public int getAllPageCount() {
    return this.allPageCount;
  }

  public void setAllPageCount(int allPageCount) {
    this.allPageCount = allPageCount;
  }

  public int getCurrentPage() {
    return this.currentPage;
  }

  public void setCurrentPage(int currentPage) {
    this.currentPage = currentPage;
  }

  public boolean insert(About about) {
    boolean rtu = false;
    if (about == null) {
      return rtu;
    } else {
      Connection conn = ConnectionManager.getInstance().getConnection();
      PreparedStatement ps = null;

      try {
        String sql = "insert into about(title,content,markdown,picture) VALUES (?,?,?,?)";
        ps = conn.prepareStatement(sql);
        ps.setString(1, about.getTitle());
        ps.setString(2, about.getContent());
        ps.setString(3, about.getMarkdown());
        ps.setString(4, about.getPicture_url());
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

  public boolean delete(int about_id) {
    boolean rtu = false;
    if (about_id == 0) {
      return rtu;
    } else {
      Connection conn = ConnectionManager.getInstance().getConnection();
      PreparedStatement ps = null;

      try {
        String sql = "delete from about where id=?;";
        ps = conn.prepareStatement(sql);
        ps.setInt(1, about_id);
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

  public boolean update(About about) {
    boolean rtu = false;
    if (about == null) {
      return rtu;
    } else {
      Connection conn = ConnectionManager.getInstance().getConnection();
      PreparedStatement ps = null;

      try {
        String sql = "update about set title=?,content=? ,markdown=?, picture=?";
        ps = conn.prepareStatement(sql);
        ps.setString(1, about.getTitle());
        ps.setString(2, about.getContent());
        ps.setString(3, about.getMarkdown());
        ps.setString(4, about.getPicture_url());
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

  public About getAboutByID(int about_id) {
    About rtu = null;
    if (about_id <= 0) {
      return rtu;
    } else {
      Connection conn = ConnectionManager.getInstance().getConnection();
      PreparedStatement ps = null;
      ResultSet rs = null;

      try {
        String sql = "select id,title,content,markdown,picture,status from about where id =?";
        ps = conn.prepareStatement(sql);
        ps.setInt(1, about_id);
        rs = ps.executeQuery();

        while(rs.next()) {
          rtu = new About();
          rtu.setId(rs.getInt("id"));
          rtu.setTitle(rs.getString("title"));
          rtu.setContent(rs.getString("content"));
          rtu.setMarkdown(rs.getString("markdown"));
          rtu.setPicture_url(rs.getString("picture"));
          rtu.setStatus(rs.getInt("status"));
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

  public About getAboutByTitle(String title) {
    if (null != title && !title.equals("")) {
      Connection conn = ConnectionManager.getInstance().getConnection();
      PreparedStatement ps = null;

      About about;
      try {
        String sql = "select id,title,content,markdown,picture,status from events where id = ?";
        ps = conn.prepareStatement(sql);
        ps.setString(1, title);
        ResultSet rs = ps.executeQuery();
        rs.last();
        if (rs.getRow() != 0) {
          rs.beforeFirst();
          about = new About();

          while(rs.next()) {
            about.setId(rs.getInt(1));
            about.setTitle(rs.getString(2));
            about.setContent(rs.getString(3));
            about.setMarkdown(rs.getString(4));
            about.setPicture_url(rs.getString(5));
            about.setStatus(rs.getInt(6));
          }

          About var7 = about;
          return var7;
        }

        about = null;
      } catch (SQLException var12) {
        var12.printStackTrace();
        return null;
      } catch (Exception var13) {
        var13.printStackTrace();
        return null;
      } finally {
        ConnectionManager.close((ResultSet)null, ps, conn);
      }

      return about;
    } else {
      return null;
    }
  }

  public ArrayList<About> getAboutByPage(int page, String title) {
    this.currentPage = page;
    ArrayList<About> list = new ArrayList();
    if (null == title || title.equals("")) {
      title = "";
    }

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
      String sql1 = "select count(id) as AllRecord from about where title like ?";
      conn = ConnectionManager.getInstance().getConnection();
      ps = conn.prepareStatement(sql1);
      ps.setString(1, "%" + title + "%");
      rs = ps.executeQuery();
      if (rs.next()) {
        this.allCount = rs.getInt("AllRecord");
      }

      rs.close();
      ps.close();
      this.allPageCount = (this.allCount + 10 - 1) / 10;
      if (this.allPageCount > 0 && this.currentPage > this.allPageCount) {
        this.currentPage = this.allPageCount;
      }

      String sql2 = "select * from about where title like ? limit ?, ?";
      ps = conn.prepareStatement(sql2);
      ps.setString(1, "%" + title + "%");
      ps.setInt(2, 10 * (this.currentPage - 1));
      ps.setInt(3, 10);
      rs = ps.executeQuery();

      while(rs.next()) {
        About about = new About();
        about.setId(rs.getInt(1));
        about.setTitle(rs.getString(2));
        about.setContent(rs.getString(3));
        about.setPicture_url(rs.getString(4));
        list.add(about);
      }
    } catch (SQLException var13) {
      var13.printStackTrace();
    } finally {
      ConnectionManager.close(rs, ps, conn);
      return list;
    }
  }
}
