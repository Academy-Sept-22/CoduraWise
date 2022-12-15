package com.codurance.codurawise.lambdas;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.codurance.codurawise.domain.models.Resource;
import com.codurance.codurawise.domain.models.Tag;
import com.codurance.codurawise.domain.services.ResourceService;
import com.codurance.codurawise.lambdas.base.Lambda;
import com.codurance.codurawise.lambdas.util.Response;
import com.codurance.codurawise.repos.mysql.ResourcesMySQLRepository;
import com.google.gson.*;

import java.lang.reflect.Type;

public class AddResource extends Lambda {

  private final Gson gson = new GsonBuilder()
    .registerTypeAdapter(Tag.class, new TagDeserializer())
    .setPrettyPrinting().create();
  protected final Response<Resource> response = new Response<>();

  private final ResourceService resourceService;

  public AddResource() {
    ResourcesMySQLRepository repository = new ResourcesMySQLRepository(connection);
    resourceService = new ResourceService(repository);
  }

  public AddResource(ResourceService resourceService) {
    this.resourceService = resourceService;
  }

  public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent requestEvent, Context context) {
    Resource resourceToAdd = gson.fromJson(requestEvent.getBody(), Resource.class);
    Resource resourceAdded = resourceService.add(resourceToAdd);
    return response.createResponse(201, gson.toJson(resourceAdded));
  }

  private static class TagDeserializer implements JsonDeserializer<Tag> {
    @Override
    public Tag deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
      return Tag.of(json.getAsString());
    }
  }
}
