package com.codurance.codurawise.lambdas;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.codurance.codurawise.api.SearchAPI;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class SearchTest {

  @Mock
  SearchAPI searchAPI;

  @Mock
  Context context;

  @Test
  void search_calls_service() {

    // arrange
    String queryValue = "java%20spring%20configuration";
    APIGatewayProxyRequestEvent requestEvent = createEvent(queryValue);
    String resourcesJson = createResourcesJson();
    given(searchAPI.search(queryValue)).willReturn(resourcesJson);

    // act
    Search searchLambda = new Search(searchAPI);
    APIGatewayProxyResponseEvent responseEvent = searchLambda.handleRequest(requestEvent, context);

    // assert
    assertThat(responseEvent.getStatusCode()).isEqualTo(200);
    assertThat(responseEvent.getBody()).isEqualTo(resourcesJson);
  }

  private static APIGatewayProxyRequestEvent createEvent(String queryValue) {
    APIGatewayProxyRequestEvent requestEvent = new APIGatewayProxyRequestEvent();
    Map<String, String> params = new HashMap<>();
    params.put("query", queryValue);
    requestEvent.setQueryStringParameters(params);
    return requestEvent;
  }

  private static String createResourcesJson() {
    JsonObject object = new JsonObject();
    object.addProperty("Id", 1);
    object.addProperty("Title", "Title1");
    object.addProperty("Link", "Link1");
    object.addProperty("AverageRating", 4.5);
    object.addProperty("MediaType", "video");
    JsonArray array = new JsonArray();
    array.add(object);
    return array.toString();
  }

}