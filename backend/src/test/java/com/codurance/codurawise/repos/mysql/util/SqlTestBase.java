package com.codurance.codurawise.repos.mysql.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlTestBase {

  protected Connection connection;
  private final SqlSchema sqlSchema = new SqlSchema();
  protected SqlExecutor sqlExecutor = new SqlExecutor();

  @BeforeEach
  void setup() throws Exception {
    connection = createConnection();
    sqlSchema.createTables(connection);
  }

  @AfterEach
  void tearDown() throws Exception {
    sqlSchema.dropTables(connection);
    connection.close();
  }

  protected Connection createConnection() throws ClassNotFoundException, SQLException {
    Class.forName("com.mysql.cj.jdbc.Driver");
    // Text comparison in MySQL is case insensitive by default, while in H2 it is case sensitive (as in most other databases).
    // H2 does support case insensitive text comparison, but it needs to be set separately, using SET IGNORECASE TRUE.
    // This affects comparison using =, LIKE, REGEXP.
    return DriverManager.getConnection(
      "jdbc:h2:mem:test;MODE=MySQL;" +
        "DATABASE_TO_LOWER=TRUE;" +
        "CASE_INSENSITIVE_IDENTIFIERS=TRUE;" +
        "IGNORECASE=TRUE;");
  }

//  protected Connection createConnection() throws ClassNotFoundException, SQLException {
//    Class.forName("com.mysql.cj.jdbc.Driver");
//    // TODO: could be troublesome in pipeline (because its already running in a container)
//    MySQLContainer<?> container = new MySQLContainer<>(DockerImageName.parse("mysql:8.0.31"));
//    container.start();
//    return DriverManager.getConnection(container.getJdbcUrl(),
//      container.getUsername(), container.getPassword());
//  }
}
