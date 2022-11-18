package com.xiyoulinux.dao;

import com.xiyoulinux.idao.Ievents;
import com.xiyoulinux.model.Events;
import com.xiyoulinux.util.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EventsDAO implements Ievents {
  private static final int PAGE_SIZE = 20;
  private int allCount;
  private int allPageCount;
  private int currentPage;

  public EventsDAO() {
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

  public boolean insert(Events events) {
    boolean rtu = false;
    if (null == events) {
      return rtu;
    } else {
      Connection conn = ConnectionManager.getInstance().getConnection();
      PreparedStatement ps = null;

      try {
        String sql = "insert into events(title,content,markdown,poster,date,time,address,label) values(?,?,?,?,?,?,?,?)";
        ps = conn.prepareStatement(sql);
        ps.setString(1, events.getTitle());
        ps.setString(2, events.getContent());
        ps.setString(3, events.getMarkdown());
        ps.setString(4, events.getPoster());
        ps.setString(5, events.getDate());
        ps.setString(6, events.getTime());
        ps.setString(7, events.getAddress());
        ps.setString(8, events.getLabel());
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

  public boolean delete(int event_id) {
    boolean rtu = false;
    if (event_id <= 0) {
      return rtu;
    } else {
      Connection conn = ConnectionManager.getInstance().getConnection();
      PreparedStatement ps = null;

      try {
        String sql = "delete from events where id = ?";
        ps = conn.prepareStatement(sql);
        ps.setInt(1, event_id);
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

  public boolean update(Events events) {
    boolean rtu = false;
    if (events == null) {
      return rtu;
    } else {
      Connection conn = ConnectionManager.getInstance().getConnection();
      PreparedStatement ps = null;

      try {
        String sql = "update events set title = ?,content = ?,markdown = ?,poster= ?,date= ?,time = ?,address = ?,label = ? where id = ?";
        ps = conn.prepareStatement(sql);
        ps.setString(1, events.getTitle());
        ps.setString(2, events.getContent());
        ps.setString(3, events.getMarkdown());
        ps.setString(4, events.getPoster());
        ps.setString(5, events.getDate());
        ps.setString(6, events.getTime());
        ps.setString(7, events.getAddress());
        ps.setString(8, events.getLabel());
        ps.setInt(9, events.getId());
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

  public Events getEventsByID(int event_id) {
    if (event_id <= 0) {
      return null;
    } else {
      Connection conn = ConnectionManager.getInstance().getConnection();
      PreparedStatement ps = null;

      Events events;
      try {
        String sql = "select id,title,content,markdown,poster,date,time,address,label,reader,status from events where id = ?";
        ps = conn.prepareStatement(sql);
        ps.setInt(1, event_id);
        ResultSet rs = ps.executeQuery();
        rs.last();
        if (rs.getRow() != 0) {
          rs.beforeFirst();
          events = new Events();

          while(rs.next()) {
            events.setId(rs.getInt(1));
            events.setTitle(rs.getString(2));
            events.setContent(rs.getString(3));
            events.setMarkdown(rs.getString(4));
            events.setPoster(rs.getString(5));
            events.setDate(rs.getString(6));
            events.setTime(rs.getString(7));
            events.setAddress(rs.getString(8));
            events.setLabel(rs.getString(9));
            events.setReader(rs.getInt(10));
            events.setStatus(rs.getInt(11));
          }

          Events var7 = events;
          return var7;
        }

        events = null;
      } catch (SQLException var12) {
        var12.printStackTrace();
        return null;
      } catch (Exception var13) {
        var13.printStackTrace();
        return null;
      } finally {
        ConnectionManager.close((ResultSet)null, ps, conn);
      }

      return events;
    }
  }

  public ArrayList<Events> getEventsByTitle(String title) {
    if (null != title && !title.equals("")) {
      Connection conn = ConnectionManager.getInstance().getConnection();
      PreparedStatement ps = null;
      ArrayList<Events> list = new ArrayList();

      try {
        String sql = "select id,title,content,markdown,poster,date,time,address,label,reader,status from events where title = ?";
        ps = conn.prepareStatement(sql);
        ps.setString(1, title);
        ResultSet rs = ps.executeQuery();

        while(rs.next()) {
          Events events = new Events();
          events.setId(rs.getInt(1));
          events.setTitle(rs.getString(2));
          events.setContent(rs.getString(3));
          events.setTitle(rs.getString(4));
          events.setPoster(rs.getString(5));
          events.setDate(rs.getString(6));
          events.setTime(rs.getString(7));
          events.setAddress(rs.getString(8));
          events.setLabel(rs.getString(9));
          events.setReader(rs.getInt(10));
          events.setStatus(rs.getInt(11));
          list.add(events);
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
    } else {
      return null;
    }
  }

  public ArrayList<Events> getEventsByPage(int page, String title) {
    this.currentPage = page;
    ArrayList<Events> list = new ArrayList();
    if (null == title || title.equals("")) {
      title = "";
    }

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
      String sql1 = "select count(id) as AllRecord from events where title like ?";
      conn = ConnectionManager.getInstance().getConnection();
      ps = conn.prepareStatement(sql1);
      ps.setString(1, "%" + title + "%");
      rs = ps.executeQuery();
      this.allCount = 0;
      if (rs.next()) {
        this.allCount = rs.getInt("AllRecord");
      }

      rs.close();
      ps.close();
      this.allPageCount = (this.allCount + 20 - 1) / 20;
      if (this.allPageCount > 0 && this.currentPage > this.allPageCount) {
        this.currentPage = this.allPageCount;
      }

      String sql2 = "select * from events where title like ? ORDER BY id DESC limit ?,?";
      ps = conn.prepareStatement(sql2);
      ps.setString(1, "%" + title + "%");
      ps.setInt(2, 20 * (this.currentPage - 1));
      ps.setInt(3, 20);
      rs = ps.executeQuery();

      while(rs.next()) {
        Events events = new Events();
        events.setId(rs.getInt(1));
        events.setTitle(rs.getString(2));
        events.setContent(rs.getString(3));
        events.setMarkdown(rs.getString(4));
        events.setPoster(rs.getString(5));
        events.setDate(rs.getString(6));
        events.setTime(rs.getString(7));
        events.setAddress(rs.getString(8));
        events.setLabel(rs.getString(9));
        events.setReader(rs.getInt(10));
        events.setStatus(rs.getInt(11));
        list.add(events);
      }
    } catch (SQLException var13) {
      var13.printStackTrace();
    } finally {
      ConnectionManager.close(rs, ps, conn);
      return list;
    }
  }

  public ArrayList<Events> getEventsByPage(int page, String title, int pagesize) {
    this.currentPage = page;
    ArrayList<Events> list = new ArrayList();
    if (null == title || title.equals("")) {
      title = "";
    }

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
      String sql1 = "select count(id) as AllRecord from events where title like ? AND status=1";
      conn = ConnectionManager.getInstance().getConnection();
      ps = conn.prepareStatement(sql1);
      ps.setString(1, "%" + title + "%");
      rs = ps.executeQuery();
      this.allCount = 0;
      if (rs.next()) {
        this.allCount = rs.getInt("AllRecord");
      }

      rs.close();
      ps.close();
      this.allPageCount = (this.allCount + pagesize - 1) / pagesize;
      if (this.allPageCount > 0 && this.currentPage > this.allPageCount) {
        this.currentPage = this.allPageCount;
      }

      String sql2 = "select * from events where title like ? AND status=1 ORDER BY id DESC limit ?,?";
      ps = conn.prepareStatement(sql2);
      ps.setString(1, "%" + title + "%");
      ps.setInt(2, pagesize * (this.currentPage - 1));
      ps.setInt(3, pagesize);
      rs = ps.executeQuery();

      while(rs.next()) {
        Events events = new Events();
        events.setId(rs.getInt(1));
        events.setTitle(rs.getString(2));
        events.setContent(rs.getString(3));
        events.setMarkdown(rs.getString(4));
        events.setPoster(rs.getString(5));
        events.setDate(rs.getString(6));
        events.setTime(rs.getString(7));
        events.setAddress(rs.getString(8));
        events.setLabel(rs.getString(9));
        events.setReader(rs.getInt(10));
        events.setStatus(rs.getInt(11));
        list.add(events);
      }
    } catch (SQLException var15) {
      var15.printStackTrace();
    } catch (Exception var16) {
      var16.printStackTrace();
    } finally {
      ConnectionManager.close(rs, ps, conn);
      return list;
    }
  }

  public ArrayList<Events> getEventsByNumber(int number) {
    ArrayList<Events> list = new ArrayList();
    Connection conn = ConnectionManager.getInstance().getConnection();
    PreparedStatement ps = null;

    try {
      String sql = "select * from events WHERE status=1 order by id desc limit ?";
      ps = conn.prepareStatement(sql);
      ps.setInt(1, number);
      ResultSet rs = ps.executeQuery();

      while(rs.next()) {
        Events events = new Events();
        events.setId(rs.getInt(1));
        events.setTitle(rs.getString(2));
        events.setContent(rs.getString(3));
        events.setMarkdown(rs.getString(4));
        events.setPoster(rs.getString(5));
        events.setDate(rs.getString(6));
        events.setTime(rs.getString(7));
        events.setAddress(rs.getString(8));
        events.setLabel(rs.getString(9));
        events.setReader(rs.getInt(10));
        events.setStatus(rs.getInt(11));
        list.add(events);
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

  public boolean alterEventsStatus(int id) {
    if (id <= 0) {
      return false;
    } else {
      Connection conn = ConnectionManager.getInstance().getConnection();
      PreparedStatement ps = null;

      boolean var8;
      try {
        String sql = "select count(id),status from events where id = ?";
        ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        int count = 0;

        int status;
        for(status = 0; rs.next(); status = rs.getInt(2)) {
          count = rs.getInt(1);
        }

        if (count != 0) {
          sql = "UPDATE events SET status = ? WHERE id = ?";
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
      } catch (SQLException var12) {
        var12.printStackTrace();
        return false;
      } finally {
        ConnectionManager.close((ResultSet)null, ps, conn);
      }

      return var8;
    }
  }

  public boolean addEventsRead(int id) {
    if (id <= 0) {
      return false;
    } else {
      Connection conn = ConnectionManager.getInstance().getConnection();
      PreparedStatement ps = null;

      try {
        String sql = "UPDATE events SET reader = reader + 1 WHERE id = ?";
        ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
        boolean var5 = true;
        return var5;
      } catch (SQLException var9) {
        var9.printStackTrace();
      } finally {
        ConnectionManager.close((ResultSet)null, ps, conn);
      }

      return false;
    }
  }

  public ArrayList<Events> getNewEvents() {
    ArrayList<Events> list = new ArrayList();
    Connection conn = ConnectionManager.getInstance().getConnection();
    PreparedStatement ps = null;

    try {
      String sql = "select id,title,content,markdown,poster,date,time,address,label,reader,status from events WHERE status = 1 ORDER BY id DESC LIMIT 0,2";
      ps = conn.prepareStatement(sql);
      ResultSet rs = ps.executeQuery();

      while(rs.next()) {
        Events events = new Events();
        events.setId(rs.getInt(1));
        events.setTitle(rs.getString(2));
        events.setContent(rs.getString(3));
        events.setMarkdown(rs.getString(4));
        events.setPoster(rs.getString(5));
        events.setDate(rs.getString(6));
        events.setTime(rs.getString(7));
        events.setAddress(rs.getString(8));
        events.setLabel(rs.getString(9));
        events.setReader(rs.getInt(10));
        events.setStatus(rs.getInt(11));
        list.add(events);
      }
    } catch (SQLException var11) {
      var11.printStackTrace();
    } catch (Exception var12) {
      var12.printStackTrace();
    } finally {
      ConnectionManager.close((ResultSet)null, ps, conn);
    }

    return list;
  }
}

