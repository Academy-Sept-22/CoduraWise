package com.codurance.codurawise.lambdas;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.codurance.codurawise.domain.services.SearchService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class SearchTest {

  @Mock
  SearchService searchService;

  @Mock
  Context context;

  @Test
  void search_calls_service() {

    // arrange
    String queryValue = "java%20spring%20configuration";
    APIGatewayProxyRequestEvent requestEvent = createEvent(queryValue);
    given(searchService.search(queryValue)).willReturn(List.of());

    // act
    Search searchLambda = new Search(searchService);
    APIGatewayProxyResponseEvent responseEvent = searchLambda.handleRequest(requestEvent, context);

    // assert
    assertThat(responseEvent.getStatusCode()).isEqualTo(200);
    assertThat(responseEvent.getBody()).isEqualTo("[]");
  }

  private static APIGatewayProxyRequestEvent createEvent(String queryValue) {
    APIGatewayProxyRequestEvent requestEvent = new APIGatewayProxyRequestEvent();
    Map<String, String> params = new HashMap<>();
    params.put("query", queryValue);
    requestEvent.setQueryStringParameters(params);
    return requestEvent;
  }

}