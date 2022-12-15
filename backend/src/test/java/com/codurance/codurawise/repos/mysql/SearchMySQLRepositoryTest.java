package com.codurance.codurawise.repos.mysql;

import com.codurance.codurawise.domain.models.Resource;
import com.codurance.codurawise.domain.repos.SearchRepository;
import com.codurance.codurawise.repos.mysql.util.SqlTestBase;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SearchMySQLRepositoryTest extends SqlTestBase {

  @Test
  void searches_for_one_word_when_is_present_in_3_titles() throws Exception {

    // arrange
    sqlExecutor.executeUpdate(connection, "INSERT INTO `Media_Type` VALUES ('video');");
    sqlExecutor.executeUpdate(connection, "INSERT INTO `User` VALUES ('user1@codurance.com','User1');");
    sqlExecutor.executeUpdate(connection, "INSERT INTO `Tag` VALUES ('tag1'),('tag2');");

    sqlExecutor.insertResource(connection, 1, "Title1", "Link1", "Description1",
      "2022-11-30 00:00:00", "video",
      4.50, "user1@codurance.com");
    sqlExecutor.insertResource(connection, 2, "Title2", "Link2", "Description2",
      "2022-11-29 00:00:00", "video",
      4.20, "user1@codurance.com");
    sqlExecutor.insertResource(connection, 3, "Title3", "Link3", "Description3",
      "2022-11-30 00:00:00", "video",
      4.20, "user1@codurance.com");
    sqlExecutor.insertResource(connection, 4, "Other4", "Link4", "Description4",
      "2022-11-30 00:00:00", "video",
      4.30, "user1@codurance.com");

    // act
    SearchRepository searchRepository = new SearchMySQLRepository(connection);
    List<Resource> resourcesFound = searchRepository.search("Title");

    // assert
    assertThat(resourcesFound.size()).isEqualTo(3);
    assertThat(resourcesFound.get(0).getId()).isEqualTo(1);
    assertThat(resourcesFound.get(1).getId()).isEqualTo(3);
    assertThat(resourcesFound.get(2).getId()).isEqualTo(2);
  }

  @Test
  void searches_for_4_words_when_2_are_present_in_3_titles() throws Exception {

    // arrange
    sqlExecutor.executeUpdate(connection, "INSERT INTO `Media_Type` VALUES ('video');");
    sqlExecutor.executeUpdate(connection, "INSERT INTO `User` VALUES ('user1@codurance.com','User1');");
    sqlExecutor.executeUpdate(connection, "INSERT INTO `Tag` VALUES ('tag1'),('tag2');");

    sqlExecutor.insertResource(connection, 1, "Title 1", "Link1", "Description1",
      "2022-11-30 00:00:00", "video",
      4.50, "user1@codurance.com");
    sqlExecutor.insertResource(connection, 2, "Sample 2", "Link2", "Description2",
      "2022-11-29 00:00:00", "video",
      4.20, "user1@codurance.com");
    sqlExecutor.insertResource(connection, 3, "Title sample 3", "Link3", "Description3",
      "2022-11-30 00:00:00", "video",
      4.20, "user1@codurance.com");
    sqlExecutor.insertResource(connection, 4, "Other4", "Link4", "Description4",
      "2022-11-30 00:00:00", "video",
      4.30, "user1@codurance.com");

    // act
    SearchRepository searchRepository = new SearchMySQLRepository(connection);
    List<Resource> resourcesFound = searchRepository.search("Title", "sample", "NotExistent1", "NotExistent2");

    // assert
    assertThat(resourcesFound.size()).isEqualTo(3);
    assertThat(resourcesFound.get(0).getId()).isEqualTo(1);
    assertThat(resourcesFound.get(1).getId()).isEqualTo(3);
    assertThat(resourcesFound.get(2).getId()).isEqualTo(2);
  }

  @Test
  void searches_for_4_words_when_2_are_present_in_1_title_and_2_tags() throws Exception {

    // arrange
    sqlExecutor.executeUpdate(connection, "INSERT INTO `Media_Type` VALUES ('video');");
    sqlExecutor.executeUpdate(connection, "INSERT INTO `User` VALUES ('user1@codurance.com','User1');");
    sqlExecutor.executeUpdate(connection, "INSERT INTO `Tag` VALUES ('tag1'),('other2'),('tag4');");

    sqlExecutor.insertResource(connection, 1, "Title 1", "Link1", "Description1",
      "2022-11-30 00:00:00", "video",
      4.50, "user1@codurance.com");
    sqlExecutor.insertResourceTag(connection, 1, "tag1");
    sqlExecutor.insertResource(connection, 2, "Title sample 2", "Link2", "Description2",
      "2022-11-29 00:00:00", "video",
      4.20, "user1@codurance.com");
    sqlExecutor.insertResourceTag(connection, 2, "other2");
    sqlExecutor.insertResource(connection, 3, "Sample 3", "Link3", "Description3",
      "2022-11-30 00:00:00", "video",
      4.20, "user1@codurance.com");
    sqlExecutor.insertResource(connection, 4, "Other4", "Link4", "Description4",
      "2022-11-30 00:00:00", "video",
      4.30, "user1@codurance.com");
    sqlExecutor.insertResourceTag(connection, 4, "tag1");
    sqlExecutor.insertResourceTag(connection, 4, "tag4");

    // act
    SearchRepository searchRepository = new SearchMySQLRepository(connection);
    List<Resource> resourcesFound = searchRepository.search("Title", "tag");

    // assert
    assertThat(resourcesFound.size()).isEqualTo(3);
    assertThat(resourcesFound.get(0).getId()).isEqualTo(1);
    assertThat(resourcesFound.get(1).getId()).isEqualTo(4);
    assertThat(resourcesFound.get(2).getId()).isEqualTo(2);
  }
}