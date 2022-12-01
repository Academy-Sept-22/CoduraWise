package com.codurance.codurawise.repos.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlConnection {
  public static Connection createConnection(String host, int port, String database, String userName, String password) throws ClassNotFoundException, SQLException {
    Class.forName("com.mysql.cj.jdbc.Driver");
    return DriverManager.getConnection(
      "jdbc:mysql://" + host + ":" + port + "/" + database, userName, password);
  }
}