package com.codurance.codurawise.lambdas;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.codurance.codurawise.repos.SearchRepository;

public class Search implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

  public Search(SearchRepository repository) {
    throw new UnsupportedOperationException();
  }

  @Override
  public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent event, Context context) {
    throw new UnsupportedOperationException();
  }
}
