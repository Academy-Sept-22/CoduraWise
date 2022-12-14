package com.codurance.codurawise.application.repositories;

import com.codurance.codurawise.application.repositories.mysql.util.PreparedStatementExecutor;
import com.codurance.codurawise.domain.models.Resource;
import com.codurance.codurawise.domain.ports.repositories.ResourcesRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class MySQLResourcesRepository implements ResourcesRepository {

  // TODO: try to remove redundancies in the names, this is already the tags repository
  // so the name TAG_TABLE and TAG_COLUMN are redundant, they can be just TABLE and COLUMN
  private static final String RESOURCE_TABLE = "Resource";
  private final Connection connection;

  public MySQLResourcesRepository(Connection connection) {
    this.connection = connection;
  }

  @Override
  public List<Resource> getAllSortedByAverageRatingAndCreationDate() {
    try {
      // TODO:
      // There is a nice java library that makes this sql syntax a little nicer
      // https://github.com/zsoltherpai/fluent-jdbc it may help you remove some lines
      // and clean a little bit this code
      String sql = ("SELECT" +
        " * " +
        "FROM " + RESOURCE_TABLE + " " +
        "ORDER BY Resource.Average_Rating DESC, Resource.Creation_Date DESC;");
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      return runQuery(preparedStatement);
    } catch (SQLException sqlException) {
      // TODO:
      // Application exceptions are part of the application, I would recommend that
      // next time you add typed exceptions so it's more explicit and easier to find
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
