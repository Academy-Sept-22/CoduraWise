package com.codurance.codurawise.lambdas;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.codurance.codurawise.domain.models.Resource;
import com.codurance.codurawise.domain.models.Tag;
import com.codurance.codurawise.domain.services.ResourceService;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class AddResourceTest {

  @Mock
  ResourceService resourceService;

  @Mock
  Context context;

  @Test
  void should_add_a_resource_to_database_and_return_it() {

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

    Resource resourceAdded = new Resource();
    resourceAdded.setId(1);
    resourceAdded.setAverageRating(3.0);
    resourceAdded.setTitle("Uncle Bob");
    resourceAdded.setLink("www.bob.com");
    resourceAdded.setMediaType("article");
    resourceToAdd.setTags(List.of(Tag.of("java"), Tag.of("agile")));

    given(resourceService.add(resourceToAdd)).willReturn(resourceAdded);

    String body = resourceJson.toString();
    APIGatewayProxyRequestEvent requestEvent = createEvent(body);

    //act
    AddResource addResource = new AddResource(resourceService);
    APIGatewayProxyResponseEvent responseEvent = addResource.handleRequest(requestEvent, context);

    //assert

    assertThat(responseEvent.getStatusCode()).isEqualTo(201);
    assertThat(responseEvent.getBody()).contains("1");
  }

  //input:frontend->apigateway->lambda->Service->repository->database->retturns ID
  //output:repository.addIDtoResource->service->lambda->frontedn

  private static APIGatewayProxyRequestEvent createEvent(String body) {
    APIGatewayProxyRequestEvent requestEvent = new APIGatewayProxyRequestEvent();
    requestEvent.setBody(body);
    return requestEvent;
  }

}
