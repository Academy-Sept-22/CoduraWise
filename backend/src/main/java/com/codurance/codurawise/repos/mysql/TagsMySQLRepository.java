package com.codurance.codurawise.repos.mysql;

import com.codurance.codurawise.domain.models.Tag;
import com.codurance.codurawise.domain.repos.TagRepository;

import java.sql.*;
import java.util.List;

import static com.codurance.codurawise.repos.mysql.util.PreparedStatementExecutor.executeTagQuery;

public class TagsMySQLRepository implements TagRepository {

  private static final String TAG_TABLE = "Tag";
  private static final String TAG_COLUMN = "Tag";
  private final Connection connection;

  public TagsMySQLRepository(Connection connection) {
    this.connection = connection;
  }

  @Override
  public List<Tag> getAllTags() {
    try {
      String sql = ("SELECT" +
        " * " +
        "FROM " + TAG_TABLE + " " +
        "ORDER BY " + TAG_TABLE + "." + TAG_COLUMN + " ASC;");
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      return runQuery(preparedStatement);
    } catch (SQLException sqlException) {
      throw new RuntimeException("Error getting tags", sqlException);
    }
  }

  @Override
  public Tag add(Tag tag) {
    try {
      String sql = ("INSERT INTO `Tag` VALUES (?);");

      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setString(1, tag.getTag());
      int rowsAdded = preparedStatement.executeUpdate();
      if (rowsAdded != 1) {
        throw new RuntimeException("Tag not added!");
      }

      return tag;
    } catch (SQLException sqlException) {
      throw new RuntimeException("Error adding tag", sqlException);
    }
  }

  private List<Tag> runQuery(PreparedStatement preparedStatement) throws SQLException {
    return executeTagQuery(preparedStatement);
  }

}
