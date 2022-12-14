package com.codurance.codurawise.domain.services;

import com.codurance.codurawise.domain.models.Tag;
import com.codurance.codurawise.domain.ports.repositories.TagRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class TagServiceTest {

  @Mock
  TagRepository tagRepository;

  @Test
  void returns_all_tags() {

    List<Tag> tags = List.of();

    given(tagRepository.getAllTags()).willReturn(tags);

    TagService tagService = new TagService(tagRepository);
    List<Tag> resultTags = tagService.getAllTags();

    assertThat(resultTags).isEqualTo(tags);
  }

}
