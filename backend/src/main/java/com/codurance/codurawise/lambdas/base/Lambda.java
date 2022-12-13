package com.codurance.codurawise.lambdas.base;

import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.codurance.codurawise.lambdas.util.MySqlConnectionProvider;

import java.sql.Connection;

public abstract class Lambda implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

  protected static Connection connection;

  static {
    connection = initializeConnection();
  }

  private static Connection initializeConnection() {
    try {
      if (isRunningOnAWS()) {
        return MySqlConnectionProvider.createDatabaseConnection();
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    return null;
  }

  private static boolean isRunningOnAWS() {
    return System.getenv("AWS_REGION") != null;
  }
}
