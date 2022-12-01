package com.codurance.codurawise.lambdas.util;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.List;

public class Response<T> {
  final Gson gson = new GsonBuilder().setPrettyPrinting().create();

  public Response() {
  }

  public APIGatewayProxyResponseEvent createResponse(List<T> elements) {
    APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
    response.setIsBase64Encoded(false);
    response.setStatusCode(200);
    HashMap<String, String> headers = new HashMap<>();
    headers.put("Content-Type", "text/json");
    headers.put("Access-Control-Allow-Origin", "*");
    headers.put("Access-Control-Allow-Credentials", "true");
    response.setHeaders(headers);
    response.setBody(gson.toJson(elements));
    return response;
  }
}