package com.codurance.codurawise.lambdas;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.codurance.codurawise.domain.models.Resource;
import com.codurance.codurawise.domain.services.SearchService;
import com.codurance.codurawise.lambdas.util.MySqlConnectionProvider;
import com.codurance.codurawise.lambdas.util.Response;
import com.codurance.codurawise.repos.SearchRepository;
import com.codurance.codurawise.repos.mysql.SearchMySQLRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class QueryResourcesByTitleAndTag implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
  private static final Logger logger = LoggerFactory.getLogger(com.codurance.codurawise.lambdas.GetResourcesByTag.class);
  private final SearchService searchService;
  private final Response<Resource> response = new Response<>();

  public QueryResourcesByTitleAndTag() {
    SearchRepository repository;
    try {
      Connection connection = MySqlConnectionProvider.createDatabaseConnection();
      repository = new SearchMySQLRepository(connection);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    searchService = new SearchService(repository);
  }

  @Override
  public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent event, Context context) {
    try {
      Map<String, String> queryParameters = event.getQueryStringParameters();
      String tag = queryParameters.get("tag");
      String title = queryParameters.get("title");
      List<Resource> resources = searchService.getResourceBySearch(title, tag);
      logger.info("Got " + resources.size() + " resources for tag " + tag + " resources for title " + title);
      return response.createResponse(resources);
    } catch (Exception e) {
      return response.createErrorResponse(e.getMessage());
    }
  }
}
