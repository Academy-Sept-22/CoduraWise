package com.codurance.codurawise.repos.mysql.util;

import java.io.PrintWriter;
import java.sql.*;

public class SqlExecutor {

  public void executeUpdate(Connection connection, String sql) throws SQLException {
    try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
      preparedStatement.executeUpdate();
    }
  }

  public void queryAndPrint(Connection connection, String sql) throws SQLException {
    try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
      ResultSet resultSet = preparedStatement.executeQuery();
      printResultSet(resultSet, new PrintWriter(System.out), -1);
    }
  }

  public void insertResource(Connection connection, int id, String title, String link, String description,
                      String date, String type, double averageRating, String email) throws SQLException {
    String sql = "INSERT INTO `Resource` VALUES (?,?,?,?,?,?,?,?)";
    try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
      preparedStatement.setInt(1, id);
      preparedStatement.setString(2, title);
      preparedStatement.setString(3, link);
      preparedStatement.setString(4, description);
      preparedStatement.setString(5, date);
      preparedStatement.setString(6, type);
      preparedStatement.setDouble(7, averageRating);
      preparedStatement.setString(8, email);
      preparedStatement.executeUpdate();
    }
  }

  public void insertResourceTag(Connection connection, int id, String tag) throws SQLException {
    String sql = "INSERT INTO `Resource_Tag` VALUES (?,?)";
    try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
      preparedStatement.setInt(1, id);
      preparedStatement.setString(2, tag);
      preparedStatement.executeUpdate();
    }
  }

  public static void printResultSet(ResultSet rs, PrintWriter out, int top) {
    int i, fieldNum, count;
    ResultSetMetaData rsMeta;

    try {
      count = 0;
      rsMeta = rs.getMetaData();
      fieldNum = rsMeta.getColumnCount();
      while (rs.next() && (count < top || top <= 0)) {
        for (i = 0; i < fieldNum; i++)
          out.write(rs.getString(i + 1) + "\t");
        out.write("\n");
        out.flush();
        count++;
      }
      out.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}