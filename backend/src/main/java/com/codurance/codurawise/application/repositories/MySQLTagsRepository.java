package com.codurance.codurawise.application.repositories;

import com.codurance.codurawise.application.repositories.mysql.util.PreparedStatementExecutor;
import com.codurance.codurawise.domain.models.Tag;
import com.codurance.codurawise.domain.ports.repositories.TagRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class MySQLTagsRepository implements TagRepository {

  // TODO: try to remove redundancies in the names, this is already the tags repository
  // so the name TAG_TABLE and TAG_COLUMN are redundant, they can be just TABLE and COLUMN
  private static final String TAG_TABLE = "Tag";
  private static final String TAG_COLUMN = "Tag";
  private final Connection connection;

  public MySQLTagsRepository(Connection connection) {
    this.connection = connection;
  }

  @Override
  public List<Tag> getAllTags() {
    try {
      // TODO:
      // There is a nice java library that makes this sql syntax a little nicer
      // https://github.com/zsoltherpai/fluent-jdbc this code would look like:
      // query.select("SELECT * FROM ? ORDER BY ? ASC").params(TABLE, COLUMN).run();
      String sql = ("SELECT" +
        " * " +
        "FROM " + TAG_TABLE + " " +
        "ORDER BY " + TAG_TABLE + "." + TAG_COLUMN + " ASC;");
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      return runQuery(preparedStatement);
    } catch (SQLException sqlException) {
      // TODO:
      // Application exceptions are part of the application, I would recommend that
      // next time you add typed exceptions so it's more explicit and easier to find
      throw new RuntimeException("Error getting tags", sqlException);
    }
  }

  private List<Tag> runQuery(PreparedStatement preparedStatement) throws SQLException {
    return PreparedStatementExecutor.executeTagQuery(preparedStatement);
  }
}
