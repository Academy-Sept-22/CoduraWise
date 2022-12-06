package com.codurance.codurawise.repos.mysql;

import com.codurance.codurawise.domain.models.Resource;
import com.codurance.codurawise.repos.SearchRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SearchMySQLRepository implements SearchRepository {
  private final Connection connection;

  public SearchMySQLRepository(Connection connection) {
    this.connection = connection;
  }

  @Override
  public List<Resource> queryByTitleAndTag(String title, String tag) {
    String conditionsForTag = tag != null ? "Tag = '" + tag + "' " : "";
    String or = tag != null && title != null ? "OR " : "";
    String conditionsForTitle = title != null ? "Title LIKE '%" + title + "%'" : "";

    try {
      String sql = ("SELECT" +
        " Resource.* " +
        "FROM Resource " +
        "INNER JOIN Resource_Tag " +
        "USING (Resource_ID) " +
        "WHERE " + conditionsForTag +
        or + conditionsForTitle +
        "ORDER BY Resource.Average_Rating DESC, Resource.Creation_Date DESC;");

      return runQuery(sql);
    } catch (SQLException sqlException) {
      throw new RuntimeException("Error searching resources", sqlException);
    }
  }

  @Override
  public List<Resource> queryByTitle(String title) {
    return null;
  }

  @Override
  public List<Resource> queryByTag(String tag) {
    return null;
  }

  private List<Resource> runQuery(String sql) throws SQLException {
    Statement statement = connection.createStatement();
    ResultSet result = statement.executeQuery(sql);
    List<Resource> resources = new ArrayList<>();
    while (result.next()) {

      int resourceID = result.getInt("Resource_ID");
      String title = result.getString("Title");
      String link = result.getString("Link");
      Double averageRating = result.getDouble("Average_Rating");

      Resource resource = new Resource();
      resource.setId(resourceID);
      resource.setTitle(title);
      resource.setLink(link);
      resource.setAverageRating(averageRating);
      resources.add(resource);
    }
    return resources;
  }
}
