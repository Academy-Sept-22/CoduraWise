package com.codurance.codurawise.lambdas;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.codurance.codurawise.domain.models.Resource;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Collection;
import java.util.HashMap;

public class ResourcesResponse {
  final Gson gson = new GsonBuilder().setPrettyPrinting().create();

  public ResourcesResponse() {
  }

  APIGatewayProxyResponseEvent createResponse(Collection<Resource> resources) {
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