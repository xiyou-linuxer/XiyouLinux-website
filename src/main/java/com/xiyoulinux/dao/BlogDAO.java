//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.xiyoulinux.dao;

import com.xiyoulinux.idao.Iblog;
import com.xiyoulinux.model.Blog;
import com.xiyoulinux.util.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BlogDAO implements Iblog {
  private static final int PAGE_SIZE = 20;
  private int allCount;
  private int allPageCount;
  private int currentPage;

  public BlogDAO() {
  }

  public static int getPageSize() {
    return 20;
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

  public boolean insert(Blog blog) {
    boolean rtu = false;
    if (blog == null) {
      return rtu;
    } else {
      Connection conn = ConnectionManager.getInstance().getConnection();
      PreparedStatement ps = null;

      try {
        String sql = "insert into blog(title,author,date,time,summary,url) values(?,?,?,?,?,?)";
        ps = conn.prepareStatement(sql);
        ps.setString(1, blog.getTitle());
        ps.setString(2, blog.getAuthor());
        ps.setString(3, blog.getDate());
        ps.setString(4, blog.getTime());
        ps.setString(5, blog.getSummary());
        ps.setString(6, blog.getUrl());
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

  public boolean delete(int blog_id) {
    boolean rtu = false;
    if (blog_id == 0) {
      return rtu;
    } else {
      Connection conn = ConnectionManager.getInstance().getConnection();
      PreparedStatement ps = null;

      try {
        String sql = "delete from blog where id = ?";
        ps = conn.prepareStatement(sql);
        ps.setInt(1, blog_id);
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

  public boolean update(Blog blog) {
    boolean rtu = false;
    if (blog == null) {
      return rtu;
    } else {
      Connection conn = ConnectionManager.getInstance().getConnection();
      PreparedStatement ps = null;

      try {
        String sql = "update blog set title=?,author=?,date=?,time=?,summary=?,url=? where id = ?";
        ps = conn.prepareStatement(sql);
        ps.setString(1, blog.getTitle());
        ps.setString(2, blog.getAuthor());
        ps.setString(3, blog.getDate());
        ps.setString(4, blog.getTime());
        ps.setString(5, blog.getSummary());
        ps.setString(6, blog.getUrl());
        ps.setInt(7, blog.getId());
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

  public Blog getBlogByID(int blog_id) {
    Blog rtu = null;
    if (blog_id <= 0) {
      return rtu;
    } else {
      Connection conn = ConnectionManager.getInstance().getConnection();
      PreparedStatement ps = null;
      ResultSet rs = null;

      try {
        String sql = "select id,title,author,date,time,summary,url,status from blog where id = ?";
        ps = conn.prepareStatement(sql);
        ps.setInt(1, blog_id);
        rs = ps.executeQuery();

        while(rs.next()) {
          rtu = new Blog();
          rtu.setId(rs.getInt("id"));
          rtu.setTitle(rs.getString("title"));
          rtu.setAuthor(rs.getString("author"));
          rtu.setDate(rs.getString("date"));
          rtu.setTime(rs.getString("time"));
          rtu.setSummary(rs.getString("summary"));
          rtu.setUrl(rs.getString("url"));
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

  public ArrayList<Blog> getBlogByTitle(String title) {
    if (null != title && !title.equals("")) {
      Connection conn = ConnectionManager.getInstance().getConnection();
      PreparedStatement ps = null;
      ArrayList<Blog> list = new ArrayList();

      try {
        String sql = "select id,title,author,date,time,summary,url,status from blog where title = ?";
        ps = conn.prepareStatement(sql);
        ps.setString(1, title);
        ResultSet rs = ps.executeQuery();

        while(rs.next()) {
          Blog blog = new Blog();
          blog.setId(rs.getInt(1));
          blog.setTitle(rs.getString(2));
          blog.setAuthor(rs.getString(3));
          blog.setDate(rs.getString(4));
          blog.setTime(rs.getString(5));
          blog.setSummary(rs.getString(6));
          blog.setUrl(rs.getString(7));
          blog.setStatus(rs.getInt(8));
          list.add(blog);
        }

        ArrayList var15 = list;
        return var15;
      } catch (SQLException var12) {
        var12.printStackTrace();
      } catch (Exception var13) {
        var13.printStackTrace();
      } finally {
        ConnectionManager.close((ResultSet)null, ps, conn);
      }

      return list;
    } else {
      return null;
    }
  }

  public ArrayList<Blog> getBlogByPage(int page, String title) {
    this.currentPage = page;
    ArrayList<Blog> list = new ArrayList();
    if (null == title || title.equals("")) {
      title = "";
    }

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
      String sql1 = "select count(id) as AllRecord from blog where title like ?";
      conn = ConnectionManager.getInstance().getConnection();
      ps = conn.prepareStatement(sql1);
      ps.setString(1, "%" + title + "%");
      rs = ps.executeQuery();
      if (rs.next()) {
        this.allCount = rs.getInt("AllRecord");
      }

      rs.close();
      ps.close();
      this.allPageCount = (this.allCount + 20 - 1) / 20;
      if (this.allPageCount > 0 && this.currentPage > this.allPageCount) {
        this.currentPage = this.allPageCount;
      }

      String sql2 = "select * from blog where title like ? ORDER BY id DESC limit ?,?";
      ps = conn.prepareStatement(sql2);
      ps.setString(1, "%" + title + "%");
      ps.setInt(2, 20 * (this.currentPage - 1));
      ps.setInt(3, 20);
      rs = ps.executeQuery();

      while(rs.next()) {
        Blog blog = new Blog();
        blog.setId(rs.getInt(1));
        blog.setTitle(rs.getString(2));
        blog.setAuthor(rs.getString(3));
        blog.setDate(rs.getString(4));
        blog.setTime(rs.getString(5));
        blog.setSummary(rs.getString(6));
        blog.setUrl(rs.getString(7));
        blog.setStatus(rs.getInt(8));
        list.add(blog);
      }
    } catch (SQLException var13) {
      var13.printStackTrace();
    } finally {
      ConnectionManager.close(rs, ps, conn);
      return list;
    }
  }

  public ArrayList<Blog> getBlogByPage(int page, String title, int pagesize) {
    this.currentPage = page;
    ArrayList<Blog> list = new ArrayList();
    if (null == title || title.equals("")) {
      title = "";
    }

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
      String sql1 = "select count(id) as AllRecord from blog where title like ? AND status=1";
      conn = ConnectionManager.getInstance().getConnection();
      ps = conn.prepareStatement(sql1);
      ps.setString(1, "%" + title + "%");
      rs = ps.executeQuery();
      if (rs.next()) {
        this.allCount = rs.getInt("AllRecord");
      }

      rs.close();
      ps.close();
      this.allPageCount = (this.allCount + pagesize - 1) / pagesize;
      if (this.allPageCount > 0 && this.currentPage > this.allPageCount) {
        this.currentPage = this.allPageCount;
      }

      String sql2 = "select * from blog where title like ? AND status=1 ORDER BY id DESC limit ?,?";
      ps = conn.prepareStatement(sql2);
      ps.setString(1, "%" + title + "%");
      ps.setInt(2, pagesize * (this.currentPage - 1));
      ps.setInt(3, pagesize);
      rs = ps.executeQuery();

      while(rs.next()) {
        Blog blog = new Blog();
        blog.setId(rs.getInt(1));
        blog.setTitle(rs.getString(2));
        blog.setAuthor(rs.getString(3));
        blog.setDate(rs.getString(4));
        blog.setTime(rs.getString(5));
        blog.setSummary(rs.getString(6));
        blog.setUrl(rs.getString(7));
        blog.setStatus(rs.getInt(8));
        list.add(blog);
      }
    } catch (SQLException var14) {
      var14.printStackTrace();
    } finally {
      ConnectionManager.close(rs, ps, conn);
      return list;
    }
  }

  public ArrayList<Blog> getBlogByNumber(int number) {
    ArrayList<Blog> list = new ArrayList();
    Connection conn = ConnectionManager.getInstance().getConnection();
    PreparedStatement ps = null;

    try {
      String sql = "select id,title,author,date,time,summary,url,status from blog order by id desc limit ?";
      ps = conn.prepareStatement(sql);
      ps.setInt(1, number);
      ResultSet rs = ps.executeQuery();

      while(rs.next()) {
        Blog blog = new Blog();
        blog.setId(rs.getInt(1));
        blog.setTitle(rs.getString(2));
        blog.setAuthor(rs.getString(3));
        blog.setDate(rs.getString(4));
        blog.setTime(rs.getString(5));
        blog.setSummary(rs.getString(6));
        blog.setUrl(rs.getString(7));
        blog.setStatus(rs.getInt(8));
        list.add(blog);
      }

      ArrayList var15 = list;
      return var15;
    } catch (SQLException var12) {
      var12.printStackTrace();
    } catch (Exception var13) {
      var13.printStackTrace();
    } finally {
      ConnectionManager.close((ResultSet)null, ps, conn);
    }

    return null;
  }

  public boolean alterBlogStatus(int id) {
    if (id <= 0) {
      return false;
    } else {
      Connection conn = ConnectionManager.getInstance().getConnection();
      PreparedStatement ps = null;

      try {
        String sql = "select count(id),status from blog where id = ?";
        ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        int count = 0;

        int status;
        for(status = 0; rs.next(); status = rs.getInt(2)) {
          count = rs.getInt(1);
        }

        boolean var8;
        if (count != 0) {
          sql = "UPDATE blog SET status = ? WHERE id = ?";
          ps = conn.prepareStatement(sql);
          if (status == 0) {
            ps.setInt(1, 1);
          } else {
            ps.setInt(1, 0);
          }

          ps.setInt(2, id);
          ps.executeUpdate();
          var8 = true;
          return var8;
        }

        var8 = false;
        return var8;
      } catch (SQLException var12) {
        var12.printStackTrace();
      } finally {
        ConnectionManager.close((ResultSet)null, ps, conn);
      }

      return false;
    }
  }
}
