package com.codurance.codurawise.lambdas;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.codurance.codurawise.api.SearchAPI;
import com.codurance.codurawise.domain.services.SearchService;
import com.codurance.codurawise.lambdas.base.Lambda;
import com.codurance.codurawise.lambdas.util.MySqlConnectionProvider;
import com.codurance.codurawise.lambdas.util.Response;
import com.codurance.codurawise.repos.mysql.SearchMySQLRepository;

import java.sql.Connection;

public class Search extends Lambda {

  private final SearchAPI searchAPI;

  public Search() {
    SearchMySQLRepository repository = new SearchMySQLRepository(connection);
    searchAPI = new SearchAPI(new SearchService(repository));
  }

  public Search(SearchAPI searchAPI) {
    this.searchAPI = searchAPI;
  }

  @Override
  public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent event, Context context) {
    String searchText = event.getQueryStringParameters().get("query");
    try {
      String json = searchAPI.search(searchText);
      return response.createResponse(200, json);
    } catch (Exception e) {
      return response.createResponse(500, e.getMessage());
    }
  }
}
