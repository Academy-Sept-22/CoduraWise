package com.codurance.codurawise.lambdas;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.codurance.codurawise.domain.models.Resource;
import com.codurance.codurawise.domain.services.ResourceService;
import com.codurance.codurawise.repos.ResourcesRepository;
import com.codurance.codurawise.repos.dynamo.ResourcesDynamoRepository;
import com.codurance.codurawise.repos.mysql.ResourcesMySQLRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.HashMap;

public class GetResources implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

  private static final String REGION_PROPERTY = "AWS_REGION";
  private static final String TABLE_NAME_PROPERTY = "TABLE_NAME";

  private static final Logger logger = LoggerFactory.getLogger(GetResources.class);
  private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
  private final ResourceService resourceService;

  public GetResources() {
    String tableName = System.getenv(TABLE_NAME_PROPERTY);
    String region = System.getenv(REGION_PROPERTY);
    ResourcesRepository repository = null;
    try {
      repository = ResourcesMySQLRepository.create(
        "codurawise-rds-test.csmvfqdkjhyz.eu-west-3.rds.amazonaws.com",
        3306, "CoduraWise", "admin", "CoduraWise");
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    resourceService = new ResourceService(repository);
  }

  @Override
  public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent event, Context context) {
    Collection<Resource> resources = resourceService.getAll();
    logger.info("Got " + resources.size() + " resources.");
    return createResponse(resources);
  }

  private APIGatewayProxyResponseEvent createResponse(Collection<Resource> resources) {
    APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
    response.setIsBase64Encoded(false);
    response.setStatusCode(200);
    HashMap<String, String> headers = new HashMap<>();
    headers.put("Content-Type", "text/json");
    headers.put("Access-Control-Allow-Origin", "*");
    headers.put("Access-Control-Allow-Credentials", "true");
    response.setHeaders(headers);
    response.setBody(gson.toJson(resources));
    return response;
  }

}
