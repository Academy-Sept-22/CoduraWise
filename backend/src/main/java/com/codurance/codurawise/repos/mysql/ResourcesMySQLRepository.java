package com.codurance.codurawise.repos.mysql;

import com.codurance.codurawise.domain.models.Resource;
import com.codurance.codurawise.repos.ResourcesRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ResourcesMySQLRepository implements ResourcesRepository {

  private static final String RESOURCE_TABLE = "Resource";
  private final Connection connection;

  public static ResourcesMySQLRepository create(String host,
                                                int port,
                                                String database,
                                                String userName,
                                                String password) throws Exception {

    Class.forName("com.mysql.cj.jdbc.Driver");
    Connection connection = DriverManager.getConnection(
      "jdbc:mysql://" + host + ":" + port + "/" + database, userName, password);

    return new ResourcesMySQLRepository(connection);
  }

  public ResourcesMySQLRepository(Connection connection) {
    this.connection = connection;
  }

  @Override
  public List<Resource> getAllResources() {
    try {
      String sql = ("SELECT" +
        " * " +
        "FROM " + RESOURCE_TABLE + " " +
        "ORDER BY Resource.Average_Rating DESC, Resource.Creation_Date Desc;");
      return runQuery(sql);
    } catch (SQLException sqlException) {
      throw new RuntimeException("Error getting resources", sqlException);
    }
  }

  @Override
  public List<Resource> getByTag(String tag) {
    try {
      String sql = ("SELECT" +
        " Resource.* " +
        "FROM Resource " +
        "INNER JOIN Resource_Tag " +
        "USING (Resource_ID) " +
        "WHERE Resource_Tag.Tag = '"+ tag+"' " +
        "ORDER BY Resource.Average_Rating DESC, Resource.Creation_Date Desc;");
      return runQuery(sql);
    } catch (SQLException sqlException) {
      throw new RuntimeException("Error getting resources", sqlException);
    }
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
