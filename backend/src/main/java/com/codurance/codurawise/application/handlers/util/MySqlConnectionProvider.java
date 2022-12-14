package com.codurance.codurawise.application.handlers.util;

import com.codurance.codurawise.application.repositories.mysql.MySqlConnection;
import com.codurance.codurawise.application.repositories.mysql.util.SecretStoreProvider;

import java.sql.Connection;

/**
 * Gets the environment variables from the Lambda context used to
 * - get secret
 * - create MySQL connection
 */
public class MySqlConnectionProvider {

  private static final String REGION_PROPERTY = "AWS_REGION";
  private static final String DATABASE_URL_PROPERTY = "DATABASE_URL";
  private static final String DATABASE_PORT_PROPERTY = "DATABASE_PORT";
  private static final String DATABASE_NAME_PROPERTY = "DATABASE_NAME";
  private static final String DATABASE_SECRET_STORE_NAME_PROPERTY = "DATABASE_SECRET_STORE_NAME";

  public static Connection createDatabaseConnection() throws Exception {
    String region = System.getenv(REGION_PROPERTY);
    String databaseUrl = System.getenv(DATABASE_URL_PROPERTY);
    String databasePort = System.getenv(DATABASE_PORT_PROPERTY);
    String databaseName = System.getenv(DATABASE_NAME_PROPERTY);
    String databaseSecretStoreName = System.getenv(DATABASE_SECRET_STORE_NAME_PROPERTY);

    SecretStoreProvider.DbSecret dbSecret = new SecretStoreProvider().getDbSecret(region, databaseSecretStoreName);

    return MySqlConnection.createConnection(databaseUrl,
      databasePort, databaseName, dbSecret.username, dbSecret.password);
  }
}