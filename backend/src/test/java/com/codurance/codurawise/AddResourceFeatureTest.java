package com.codurance.codurawise;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.codurance.codurawise.domain.models.Resource;
import com.codurance.codurawise.domain.models.Tag;
import com.codurance.codurawise.domain.services.ResourceService;
import com.codurance.codurawise.domain.services.TagService;
import com.codurance.codurawise.lambdas.AddResource;
import com.codurance.codurawise.repos.mysql.ResourcesMySQLRepository;
import com.codurance.codurawise.repos.mysql.TagsMySQLRepository;
import com.codurance.codurawise.repos.mysql.util.SqlTestBase;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class AddResourceFeatureTest extends SqlTestBase {

  @Mock
  Context context;

  private ResourceService resourceService;
  private TagService tagService;

  @BeforeEach
  public void repoSetup() {
    TagsMySQLRepository tagRepository = new TagsMySQLRepository(connection);
    ResourcesMySQLRepository resourceRepository = new ResourcesMySQLRepository(connection);
    resourceService = new ResourceService(resourceRepository, tagRepository);
    tagService = new TagService(tagRepository);
  }

  @Test
  void searches_by_several_words() throws SQLException {

    sqlExecutor.executeUpdate(connection, "INSERT INTO `Media_Type` VALUES ('article');");
    sqlExecutor.executeUpdate(connection, "INSERT INTO `User` VALUES ('default@codurance.com','Default');");

    //arrange
    JsonObject resourceJson = new JsonObject();
    resourceJson.addProperty("title", "Uncle Bob");
    resourceJson.addProperty("link", "www.bob.com");
    resourceJson.addProperty("averageRating", 3.0);
    resourceJson.addProperty("mediaType", "article");
    JsonArray tagsArray = new JsonArray();
    tagsArray.add("java");
    tagsArray.add("agile");
    resourceJson.add("tags", tagsArray);

    Resource resourceToAdd = new Resource();
    resourceToAdd.setAverageRating(3.0);
    resourceToAdd.setTitle("Uncle Bob");
    resourceToAdd.setLink("www.bob.com");
    resourceToAdd.setMediaType("article");
    resourceToAdd.setTags(List.of(Tag.of("java"), Tag.of("agile")));

    String body = resourceJson.toString();
    APIGatewayProxyRequestEvent requestEvent = createEvent(body);

    //act
    AddResource addResource = new AddResource(resourceService);
    APIGatewayProxyResponseEvent responseEvent = addResource.handleRequest(requestEvent, context);

    // assert
    assertThat(responseEvent.getStatusCode()).isEqualTo(201);
    assertThat(responseEvent.getBody()).contains("Uncle Bob");

    assertThat(resourceService.getAll().get(0).getId()).isGreaterThan(0);
    assertThat(resourceService.getAll().get(0).getTitle()).isEqualTo(resourceToAdd.getTitle());
    assertThat(tagService.getAllTags()).contains(Tag.of("java"), Tag.of("agile"));

    System.out.println("Resource -----------------------");
    sqlExecutor.queryAndPrint(connection, "SELECT * FROM Resource;");
    System.out.println("Tag -----------------------");
    sqlExecutor.queryAndPrint(connection, "SELECT * FROM Tag;");
    System.out.println("Resource_Tag -----------------------");
    sqlExecutor.queryAndPrint(connection, "SELECT * FROM Resource_Tag;");

  }

  private static APIGatewayProxyRequestEvent createEvent(String body) {
    APIGatewayProxyRequestEvent requestEvent = new APIGatewayProxyRequestEvent();
    requestEvent.setBody(body);
    return requestEvent;
  }
}
