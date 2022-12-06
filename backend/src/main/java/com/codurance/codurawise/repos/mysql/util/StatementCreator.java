package com.codurance.codurawise.repos.mysql.util;

import com.codurance.codurawise.domain.models.Resource;
import com.codurance.codurawise.domain.models.Tag;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StatementCreator {
  public static List<Resource> resourceStatementCreator(String sql, Connection connection) throws SQLException {
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

  public static List<Tag> tagsStatementCreator(String sql, Connection connection) throws SQLException {
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