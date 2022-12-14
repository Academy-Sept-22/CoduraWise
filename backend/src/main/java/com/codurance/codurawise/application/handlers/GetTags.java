package com.codurance.codurawise.application.handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.codurance.codurawise.application.handlers.util.MySqlConnectionProvider;
import com.codurance.codurawise.application.handlers.util.Response;
import com.codurance.codurawise.application.repositories.MySQLTagsRepository;
import com.codurance.codurawise.domain.models.Tag;
import com.codurance.codurawise.domain.ports.repositories.TagRepository;
import com.codurance.codurawise.domain.services.TagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.List;

public class GetTags implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

  private static final Logger logger = LoggerFactory.getLogger(GetTags.class);
  private final Response<Tag> response = new Response<>();

  private final TagService tagService;

  public GetTags() {
    TagRepository tagRepository;
    try {
      Connection connection = MySqlConnectionProvider.createDatabaseConnection();
      tagRepository = new MySQLTagsRepository(connection);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    this.tagService = new TagService(tagRepository);
  }

  @Override
  public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {
    List<Tag> tags = tagService.getAllTags();
    logger.info("Got " + tags.size() + " tags");
    return response.createResponse(tags);
  }
}
