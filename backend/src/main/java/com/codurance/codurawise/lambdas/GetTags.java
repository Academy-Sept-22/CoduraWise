package com.codurance.codurawise.lambdas;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.codurance.codurawise.domain.models.Tag;
import com.codurance.codurawise.domain.services.TagService;
import com.codurance.codurawise.repos.TagRepository;
import com.codurance.codurawise.repos.mysql.MysqlConnection;
import com.codurance.codurawise.repos.mysql.TagsMySQLRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.List;

public class GetTags implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

  private static final Logger logger = LoggerFactory.getLogger(com.codurance.codurawise.lambdas.GetTags.class);
  private final Response<Tag> response = new Response<>();

  private final TagService tagService;

  public GetTags() {
    Connection connection;
    try {
      connection = MysqlConnection.createConnection("codurawisedb-dev.codurance.io",
        3306, "CoduraWise", "admin", "CoduraWise");
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    TagRepository tagRepository = new TagsMySQLRepository(connection);
    this.tagService = new TagService(tagRepository);
  }

  @Override
  public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {
    List<Tag> tags = tagService.getAllTags();
    logger.info("Got " + tags.size() + " tags");
    return response.createResponse(tags);
  }
}
