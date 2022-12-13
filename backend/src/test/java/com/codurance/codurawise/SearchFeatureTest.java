package com.codurance.codurawise;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.codurance.codurawise.domain.models.Resource;
import com.codurance.codurawise.domain.services.SearchService;
import com.codurance.codurawise.lambdas.Search;
import com.codurance.codurawise.repos.SearchRepository;
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
public class SearchFeatureTest {

  @Mock
  Context context;

  @Mock
  SearchRepository repository;


  @Test
  void searches_by_several_words() {

    // arrange
    APIGatewayProxyRequestEvent requestEvent = new APIGatewayProxyRequestEvent();
    Map<String, String> params = new HashMap<>();
    params.put("query", "java%20spring%20configuration");
    requestEvent.setQueryStringParameters(params);

    List<Resource> expectedData = List.of(Resource.of(1, "Title1", "Link1", 4.2, "video"));
    given(repository.search("java","spring","configuration")).willReturn(expectedData);

    // act
    Search searchLambda = new Search(new SearchService(repository));
    APIGatewayProxyResponseEvent responseEvent = searchLambda.handleRequest(requestEvent, context);

    // assert
    assertThat(responseEvent.getStatusCode()).isEqualTo(200);
    assertThat(responseEvent.getBody()).contains("Title1");

  }

}
