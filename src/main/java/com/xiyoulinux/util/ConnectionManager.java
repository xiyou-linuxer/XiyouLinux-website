package com.xiyoulinux.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ConnectionManager {
  private static ConnectionManager instance;
  private static ComboPooledDataSource ds;

  public ConnectionManager() {
  }

  public static final synchronized ConnectionManager getInstance() {
    if (instance == null) {
      try {
        instance = new ConnectionManager();
      } catch (Exception var1) {
        var1.printStackTrace();
      }
    }

    return instance;
  }

  public final synchronized Connection getConnection() {
    try {
      ds.getNumBusyConnections();
      return ds.getConnection();
    } catch (SQLException var2) {
      var2.printStackTrace();
      return null;
    }
  }

  public static void close(ResultSet rs, Statement stmt, Connection con) {
    try {
      if (rs != null) {
        rs.close();
      }

      if (stmt != null) {
        stmt.close();
      }

      if (con != null) {
        con.close();
      }
    } catch (SQLException var4) {
      var4.printStackTrace();
    }

  }

  protected void finalize() throws Throwable {
    DataSources.destroy(ds);
    super.finalize();
  }

  static {
    ResourceBundle rb = ResourceBundle.getBundle("c3p0");
    ds = new ComboPooledDataSource();

    try {
      ds.setDriverClass(rb.getString("driver"));
    } catch (Exception var2) {
      var2.printStackTrace();
    }

    ds.setJdbcUrl(rb.getString("url"));
    ds.setUser(rb.getString("username"));
    ds.setPassword(rb.getString("password"));
  }
}
