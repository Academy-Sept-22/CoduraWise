package com.codurance.codurawise.lambdas;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.codurance.codurawise.api.SearchAPI;
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
    APIGatewayProxyRequestEvent requestEvent = new APIGatewayProxyRequestEvent();
    Map<String, String> params = new HashMap<>();
    params.put("query", "java%20spring%20configuration");
    requestEvent.setQueryStringParameters(params);

    String resourcesJson = "[{\n" +
      "  \"Id\": \"1\",\n" +
      "  \"Title\": \"Title1\",\n" +
      "  \"Link\": \"Link1\",\n" +
      "  \"AverageRating\": 4.5,\n" +
      "  \"MediaType\": \"video\"\n" +
      "}]";
    given(searchAPI.search("java%20spring%20configuration")).willReturn(resourcesJson);

    // act
    Search searchLambda = new Search(searchAPI);
    APIGatewayProxyResponseEvent responseEvent = searchLambda.handleRequest(requestEvent, context);

    // assert
    assertThat(responseEvent.getStatusCode()).isEqualTo(200);
    assertThat(responseEvent.getBody()).isEqualTo(resourcesJson);
  }

}