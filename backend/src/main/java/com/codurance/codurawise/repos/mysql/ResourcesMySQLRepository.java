package com.codurance.codurawise.repos.mysql;

import com.codurance.codurawise.domain.models.Resource;
import com.codurance.codurawise.repos.ResourcesRepository;
import com.codurance.codurawise.repos.mysql.util.StatementCreator;

import java.sql.*;
import java.util.List;

public class ResourcesMySQLRepository implements ResourcesRepository {

  private static final String RESOURCE_TABLE = "Resource";
  private final Connection connection;

  public ResourcesMySQLRepository(Connection connection) {
    this.connection = connection;
  }

  @Override
  public List<Resource> getAllSortedByAverageRatingAndCreationDate() {
    try {
      String sql = ("SELECT" +
        " * " +
        "FROM " + RESOURCE_TABLE + " " +
        "ORDER BY Resource.Average_Rating DESC, Resource.Creation_Date DESC;");
      return runQuery(sql);
    } catch (SQLException sqlException) {
      throw new RuntimeException("Error getting resources", sqlException);
    }
  }

  @Override
  public List<Resource> getByTagSortedByAverageRatingAndCreationDate(String tag) {
    try {
      String sql = ("SELECT" +
        " Resource.* " +
        "FROM Resource " +
        "INNER JOIN Resource_Tag " +
        "USING (Resource_ID) " +
        "WHERE Resource_Tag.Tag = '" + tag + "' " +
        "ORDER BY Resource.Average_Rating DESC, Resource.Creation_Date DESC;");
      return runQuery(sql);
    } catch (SQLException sqlException) {
      throw new RuntimeException("Error getting resources", sqlException);
    }
  }

  private List<Resource> runQuery(String sql) throws SQLException {
    return StatementCreator.resourceStatementCreator(sql, connection);
  }

}
