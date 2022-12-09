package com.codurance.codurawise.lambdas.util;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.List;

public class Response<T> {
  private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

  public APIGatewayProxyResponseEvent createResponse(List<T> elements) {
    return createResponse(200, gson.toJson(elements));
  }

  public APIGatewayProxyResponseEvent createErrorResponse(String message) {
    return createResponse(500, message);
  }

  private APIGatewayProxyResponseEvent createResponse(int statusCode, String bodyContent) {
    APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
    response.setIsBase64Encoded(false);
    response.setStatusCode(statusCode);
    HashMap<String, String> headers = new HashMap<>();
    headers.put("Content-Type", "text/json");
    headers.put("Access-Control-Allow-Origin", "*");
    headers.put("Access-Control-Allow-Credentials", "true");
    response.setHeaders(headers);
    response.setBody(bodyContent);
    return response;
  }
}