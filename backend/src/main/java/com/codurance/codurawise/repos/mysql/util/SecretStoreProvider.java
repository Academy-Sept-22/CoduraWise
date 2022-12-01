package com.codurance.codurawise.repos.mysql.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import software.amazon.awssdk.http.urlconnection.UrlConnectionHttpClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;

public class SecretStoreProvider {

  private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

  protected String getSecret(String region, String dbSecretStoreName) {

    try (SecretsManagerClient secretsClient = SecretsManagerClient.builder()
      .region(Region.of(region))
      .httpClientBuilder(UrlConnectionHttpClient.builder())
      .build()) {

      GetSecretValueRequest valueRequest = GetSecretValueRequest.builder()
        .secretId(dbSecretStoreName)
        .build();

      GetSecretValueResponse valueResponse = secretsClient.getSecretValue(valueRequest);
      return valueResponse.secretString();

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public DbSecret getDbSecret(String region, String dbSecretStoreName) {
    String secret = getSecret(region, dbSecretStoreName);
    return gson.fromJson(secret, DbSecret.class);
  }

  public static class DbSecret {
    public final String username;
    public final String password;

    public DbSecret(String username, String password) {
      this.username = username;
      this.password = password;
    }
  }

}