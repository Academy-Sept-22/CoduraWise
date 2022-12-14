package com.codurance.codurawise.repos.mysql.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlSchema {

  public void createTables(Connection connection) throws SQLException {

    String suffix = "ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci";
    try (Statement statement = connection.createStatement()) {
      statement.executeUpdate("CREATE TABLE `Media_Type` (" +
        "  `Media_Type` varchar(45) NOT NULL," +
        "  PRIMARY KEY (`Media_Type`)" +
        ") " + suffix + ";");

      statement.executeUpdate("CREATE TABLE `User` (" +
        "  `Email` varchar(45) NOT NULL," +
        "  `Name` varchar(45) NOT NULL," +
        "  PRIMARY KEY (`Email`)" +
        ") " + suffix + ";");

      statement.executeUpdate("CREATE TABLE `Resource` (" +
        "  `Resource_ID` int NOT NULL AUTO_INCREMENT," +
        "  `Title` varchar(100) NOT NULL," +
        "  `Link` varchar(100) NOT NULL," +
        "  `Description` varchar(300) NOT NULL," +
        "  `Creation_Date` datetime NOT NULL," +
        "  `Media_Type` varchar(45) NOT NULL," +
        "  `Average_Rating` decimal(3,2) NOT NULL," +
        "  `Email` varchar(45) NOT NULL," +
        "  PRIMARY KEY (`Resource_ID`)," +
        "  KEY `Resource_Media_Type_idx` (`Media_Type`)," +
        "  KEY `Resource_Email_idx` (`Email`)," +
        "  CONSTRAINT `Resource_Email` FOREIGN KEY (`Email`) REFERENCES `User` (`Email`)," +
        "  CONSTRAINT `Resource_Media_Type` FOREIGN KEY (`Media_Type`) REFERENCES `Media_Type` (`Media_Type`)" +
        ") " + suffix + ";");

      statement.executeUpdate("CREATE TABLE `Tag` (" +
        "  `Tag` varchar(45) NOT NULL," +
        "  PRIMARY KEY (`Tag`)" +
        ") " + suffix + ";");

      statement.executeUpdate("CREATE TABLE `Review` (" +
        "  `Resource_ID` int NOT NULL," +
        "  `Email` varchar(45) NOT NULL," +
        "  `Rating` int NOT NULL," +
        "  `Review_Text` varchar(300) DEFAULT NULL," +
        "  `Date_Of_Review` datetime NOT NULL," +
        "  PRIMARY KEY (`Resource_ID`,`Email`)," +
        "  KEY `Email_idx` (`Email`)," +
        "  CONSTRAINT `Email` FOREIGN KEY (`Email`) REFERENCES `User` (`Email`)," +
        "  CONSTRAINT `Review_Resource` FOREIGN KEY (`Resource_ID`) REFERENCES `Resource` (`Resource_ID`) ON DELETE CASCADE ON UPDATE CASCADE" +
        ") " + suffix + ";");

      statement.executeUpdate("CREATE TABLE `Resource_Tag` (" +
        "  `Resource_ID` int NOT NULL," +
        "  `Tag` varchar(45) NOT NULL," +
        "  PRIMARY KEY (`Resource_ID`,`Tag`)," +
        "  KEY `Tag_Resource_Tag_idx` (`Tag`)," +
        "  CONSTRAINT `Resource_Resource_Tag` FOREIGN KEY (`Resource_ID`) REFERENCES `Resource` (`Resource_ID`) ON DELETE CASCADE ON UPDATE CASCADE," +
        "  CONSTRAINT `Tag_Resource_Tag` FOREIGN KEY (`Tag`) REFERENCES `Tag` (`Tag`) ON DELETE CASCADE ON UPDATE CASCADE" +
        ") " + suffix + ";");
    }
  }

  public void dropTables(Connection connection) throws SQLException {
    try (Statement statement = connection.createStatement()) {
      statement.executeUpdate("DROP TABLE IF EXISTS `Resource_Tag`;");
      statement.executeUpdate("DROP TABLE IF EXISTS `Review`;");
      statement.executeUpdate("DROP TABLE IF EXISTS `Resource`;");
      statement.executeUpdate("DROP TABLE IF EXISTS `Tag`;");
      statement.executeUpdate("DROP TABLE IF EXISTS `User`;");
      statement.executeUpdate("DROP TABLE IF EXISTS `Media_Type`;");
    }
  }
}