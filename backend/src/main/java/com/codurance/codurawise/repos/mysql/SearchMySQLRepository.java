package com.codurance.codurawise.repos.mysql;

import com.codurance.codurawise.domain.models.Resource;
import com.codurance.codurawise.repos.SearchRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static com.codurance.codurawise.repos.mysql.util.StatementCreator.resourceStatementCreator;

public class SearchMySQLRepository implements SearchRepository {
  private final Connection connection;

  public SearchMySQLRepository(Connection connection) {
    this.connection = connection;
  }

  @Override
  public List<Resource> queryByTitleAndTag(String title, String tag) {
    if (title != null && tag == null) {
      return queryByTitle(title);
    } else if (tag != null && title == null) {
      return queryByTag(tag);
    } else {
      return queryBothByTitleAndTag(title, tag);
    }
  }

  @Override
  public List<Resource> queryBothByTitleAndTag(String title, String tag) {
    try {
      String sql = ("SELECT" +
        " Resource.* " +
        "FROM Resource " +
        "INNER JOIN Resource_Tag " +
        "USING (Resource_ID) " +
        "WHERE " + tag +
        " OR " + title +
        " ORDER BY Resource.Average_Rating DESC, Resource.Creation_Date DESC;");

      return runQuery(sql);
    } catch (SQLException sqlException) {
      throw new RuntimeException("Error searching resources", sqlException);
    }
  }

  @Override
  public List<Resource> queryByTitle(String title) {
    try {
      String sql = ("SELECT" +
        " Resource.* " +
        "FROM Resource " +
        "INNER JOIN Resource_Tag " +
        "USING (Resource_ID) " +
        "WHERE Title = '" + title + "' " +
        "ORDER BY Resource.Average_Rating DESC, Resource.Creation_Date DESC;");
      return runQuery(sql);
    } catch (SQLException sqlException) {
      throw new RuntimeException("Error getting resources", sqlException);
    }
  }

  @Override
  public List<Resource> queryByTag(String tag) {
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
    return resourceStatementCreator(sql, connection);
  }

}
