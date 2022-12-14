package com.codurance.codurawise.application.repositories.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnection {
  public static Connection createConnection(String host,
                                            String port,
                                            String database,
                                            String username,
                                            String password) throws ClassNotFoundException, SQLException {
    Class.forName("com.mysql.cj.jdbc.Driver");
    return DriverManager.getConnection(
      "jdbc:mysql://" + host + ":" + port + "/" + database, username, password);
  }
}