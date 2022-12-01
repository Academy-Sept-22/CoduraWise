package com.codurance.codurawise.lambdas;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.codurance.codurawise.domain.models.Resource;
import com.codurance.codurawise.domain.services.ResourceService;
import com.codurance.codurawise.repos.ResourcesRepository;
import com.codurance.codurawise.repos.mysql.MysqlConnection;
import com.codurance.codurawise.repos.mysql.ResourcesMySQLRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class GetResourcesByTag implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

  private static final String REGION_PROPERTY = "AWS_REGION";
  private static final String TABLE_NAME_PROPERTY = "TABLE_NAME";

  private static final Logger logger = LoggerFactory.getLogger(com.codurance.codurawise.lambdas.GetResourcesByTag.class);
  private final ResourceService resourceService;
  private final Response<Resource> response = new Response<>();

  public GetResourcesByTag() {
    String tableName = System.getenv(TABLE_NAME_PROPERTY);
    String region = System.getenv(REGION_PROPERTY);
    ResourcesRepository repository;
    try {
      Connection connection = MysqlConnection.createConnection("codurawisedb-dev.codurance.io",
        3306, "CoduraWise", "admin", "CoduraWise");
      repository = new ResourcesMySQLRepository(connection);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    resourceService = new ResourceService(repository);
  }

  @Override
  public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent event, Context context) {
    Map<String, String> pathParameters = event.getPathParameters();
    String tag = pathParameters.get("tag");
    List<Resource> resources = resourceService.getByTag(tag);
    logger.info("Got " + resources.size() + " resources for tag " + tag);
    return response.createResponse(resources);
  }
}
