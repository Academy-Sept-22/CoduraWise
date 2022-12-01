package com.codurance.codurawise.repos.mysql;

import com.codurance.codurawise.domain.models.Tag;
import com.codurance.codurawise.repos.TagRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TagsMySQLRepository implements TagRepository {

  private static final String TAG_TABLE = "Tag";
  private static final String TAG_COLUMN= "Tag";
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
      return runQuery(sql);
    } catch (SQLException sqlException) {
      throw new RuntimeException("Error getting tags", sqlException);
    }
  }

  private List<Tag> runQuery(String sql) throws SQLException {
    Statement statement = connection.createStatement();
    ResultSet result = statement.executeQuery(sql);
    List<Tag> tags = new ArrayList<>();
    while (result.next()) {
      String tagValue = result.getString("Tag");
      Tag tag = new Tag();
      tag.setTag(tagValue);
      tags.add(tag);
    }
    return tags;
  }
}
