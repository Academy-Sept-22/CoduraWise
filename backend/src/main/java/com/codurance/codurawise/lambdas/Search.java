package com.codurance.codurawise.lambdas;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.codurance.codurawise.domain.models.Resource;
import com.codurance.codurawise.domain.services.SearchService;
import com.codurance.codurawise.lambdas.base.Lambda;
import com.codurance.codurawise.lambdas.util.Response;
import com.codurance.codurawise.repos.mysql.SearchMySQLRepository;

import java.util.List;

public class Search extends Lambda {

  protected final Response<Resource> response = new Response<>();

  private final SearchService searchService;

  public Search() {
    SearchMySQLRepository repository = new SearchMySQLRepository(connection);
    searchService = new SearchService(repository);
  }

  public Search(SearchService searchService) {
    this.searchService = searchService;
  }

  @Override
  public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent event, Context context) {
    String searchText = event.getQueryStringParameters().get("query");
    List<Resource> resources = searchService.search(searchText);
    return response.createResponse(resources);
  }
}
