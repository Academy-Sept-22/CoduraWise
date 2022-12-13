package com.codurance.codurawise.lambdas;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.codurance.codurawise.domain.models.Resource;
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
  APIGatewayProxyRequestEvent requestEvent = new APIGatewayProxyRequestEvent();
  Map<String, String> params = new HashMap<>();
  params.put("query", "java%20spring%20configuration");
  requestEvent.setQueryStringParameters(params);

  List<Resource> expectedData = List.of(Resource.of(1, "Title1", "Link1", 4.2, "video"));
  given(searchService.search("java%20spring%20configuration")).willReturn(expectedData);

  // act
  Search searchLambda = new Search(searchService);
  APIGatewayProxyResponseEvent responseEvent = searchLambda.handleRequest(requestEvent, context);

  // assert
  assertThat(responseEvent.getStatusCode()).isEqualTo(200);
  assertThat(responseEvent.getBody()).contains("Title1");
}

}