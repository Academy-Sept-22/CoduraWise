package com.codurance.codurawise.repos.mysql;

import com.codurance.codurawise.domain.models.Resource;
import com.codurance.codurawise.repos.ResourcesRepository;

import java.sql.*;
import java.util.Collection;
import java.util.HashSet;

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
  public Collection<Resource> getAllResources() {
    try {
      Statement statement = connection.createStatement();
      String sql = ("SELECT * FROM " + RESOURCE_TABLE + ";");
      ResultSet result = statement.executeQuery(sql);
      HashSet<Resource> resources = new HashSet<>();
      while (result.next()) {
        int resourceID = result.getInt("Resource_ID");
        String title = result.getString("Title");
        String link = result.getString("Link");
        Resource resource = new Resource();
        resource.setId(resourceID);
        resource.setTitle(title);
        resource.setLink(link);
        resources.add(resource);
      }
      return resources;
    } catch (SQLException sqlException) {
      throw new RuntimeException("Error getting resources", sqlException);
    }
  }
}
