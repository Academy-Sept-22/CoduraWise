package com.codurance.codurawise.repos.mysql;

import com.codurance.codurawise.domain.models.Resource;
import com.codurance.codurawise.domain.models.Tag;
import com.codurance.codurawise.domain.repos.ResourcesRepository;
import com.codurance.codurawise.repos.mysql.util.PreparedStatementExecutor;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;

public class ResourcesMySQLRepository implements ResourcesRepository {

  private static final String RESOURCE_TABLE = "Resource";
  private static final String DEFAULT_USER_EMAIL = "default@codurance.com";
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

  @Override
  public Resource add(Resource resourceToAdd) {
    try {
      String sql = ("INSERT INTO `Resource` " +
        "(Title, Link, Description, Creation_Date, Media_Type, Average_Rating, Email) " +
        "VALUES (?,?,?,?,?,?,?);");

      PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      preparedStatement.setString(1, resourceToAdd.getTitle());
      preparedStatement.setString(2, resourceToAdd.getLink());
      preparedStatement.setString(3, "");
      preparedStatement.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
      preparedStatement.setString(5, resourceToAdd.getMediaType());
      preparedStatement.setDouble(6, resourceToAdd.getAverageRating());
      preparedStatement.setString(7, DEFAULT_USER_EMAIL);

      int rowsAdded = preparedStatement.executeUpdate();
      if (rowsAdded != 1) {
        throw new RuntimeException("Resource not added!");
      }

      ResultSet generatedKeysResult = preparedStatement.getGeneratedKeys();
      generatedKeysResult.next();
      int newId = generatedKeysResult.getInt(1);
      resourceToAdd.setId(newId);

      for (Tag tag: resourceToAdd.getTags()) {
        insertResourceTags(newId, tag);
      }

      return resourceToAdd;
    } catch (SQLException sqlException) {
      throw new RuntimeException("Error adding resource", sqlException);
    }
  }

  private void insertResourceTags(int newId, Tag tag) {

    try {
      String sql = ("INSERT INTO `Resource_Tag` " +
        "VALUES (?,?);");

      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, newId);
      preparedStatement.setString(2, tag.getTag());

      int rowsAdded = preparedStatement.executeUpdate();
      if (rowsAdded != 1) {
        throw new RuntimeException("Resource tag relation not added!");
      }

    } catch (SQLException sqlException) {
      throw new RuntimeException("Error adding resource tag relation", sqlException);
    }
  }

  private List<Resource> runQuery(PreparedStatement preparedStatement) throws SQLException {
    return PreparedStatementExecutor.executeResourceQuery(preparedStatement);
  }

}
