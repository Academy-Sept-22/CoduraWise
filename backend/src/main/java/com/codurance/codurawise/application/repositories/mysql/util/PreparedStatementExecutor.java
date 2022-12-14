package com.codurance.codurawise.application.repositories.mysql.util;

import com.codurance.codurawise.domain.models.Resource;
import com.codurance.codurawise.domain.models.Tag;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PreparedStatementExecutor {
  public static List<Resource> executeResourceQuery(PreparedStatement preparedStatement) throws SQLException {
    ResultSet result = preparedStatement.executeQuery();
    List<Resource> resources = new ArrayList<>();
    while (result.next()) {

      int resourceID = result.getInt("Resource_ID");
      String title = result.getString("Title");
      String link = result.getString("Link");
      Double averageRating = result.getDouble("Average_Rating");
      String mediaType = result.getString("Media_Type");

      Resource resource = new Resource();
      resource.setId(resourceID);
      resource.setTitle(title);
      resource.setLink(link);
      resource.setAverageRating(averageRating);
      resource.setMediaType(mediaType);
      resources.add(resource);
    }
    return resources;
  }

  public static List<Tag> executeTagQuery(PreparedStatement preparedStatement) throws SQLException {
    ResultSet result = preparedStatement.executeQuery();
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