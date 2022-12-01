package com.codurance.codurawise.lambdas;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.codurance.codurawise.domain.models.Resource;
import com.codurance.codurawise.domain.services.ResourceService;
import com.codurance.codurawise.lambdas.util.MySqlConnectionProvider;
import com.codurance.codurawise.lambdas.util.Response;
import com.codurance.codurawise.repos.ResourcesRepository;
import com.codurance.codurawise.repos.mysql.ResourcesMySQLRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.List;

public class GetResources implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

  private static final Logger logger = LoggerFactory.getLogger(GetResources.class);
  private final Response<Resource> response = new Response<>();
  private final ResourceService resourceService;

  public GetResources() {

    ResourcesRepository repository;
    try {
      Connection connection = MySqlConnectionProvider.createDatabaseConnection();
      repository = new ResourcesMySQLRepository(connection);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    resourceService = new ResourceService(repository);
  }

  @Override
  public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent event, Context context) {
    List<Resource> resources = resourceService.getAll();
    logger.info("Got " + resources.size() + " resources.");
    return response.createResponse(resources);
  }
}
