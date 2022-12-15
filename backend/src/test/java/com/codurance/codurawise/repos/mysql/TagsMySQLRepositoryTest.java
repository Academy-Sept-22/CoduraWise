package com.codurance.codurawise.repos.mysql;

import com.codurance.codurawise.domain.models.Tag;
import com.codurance.codurawise.repos.mysql.util.SqlTestBase;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TagsMySQLRepositoryTest extends SqlTestBase {

  @Test
  void adds_tags() {

    TagsMySQLRepository repository = new TagsMySQLRepository(connection);
    repository.add(Tag.of("java"));
    repository.add(Tag.of("agile"));

    assertThat(repository.getAllTags())
      .contains(Tag.of("java"), Tag.of("agile"));
  }

}