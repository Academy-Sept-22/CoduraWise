package com.codurance.codurawise.repos.mysql;

import com.codurance.codurawise.domain.models.Resource;
import com.codurance.codurawise.repos.ResourcesRepository;
import com.codurance.codurawise.repos.mysql.util.PreparedStatementExecutor;

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
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      return runQuery(preparedStatement);
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
        "WHERE Resource_Tag.Tag = ? " +
        "ORDER BY Resource.Average_Rating DESC, Resource.Creation_Date DESC;");

      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setString(1, tag);

      return runQuery(preparedStatement);
    } catch (SQLException sqlException) {
      throw new RuntimeException("Error getting resources", sqlException);
    }
  }

  private List<Resource> runQuery(PreparedStatement preparedStatement) throws SQLException {
    return PreparedStatementExecutor.executeResourceQuery(preparedStatement);
  }

}
