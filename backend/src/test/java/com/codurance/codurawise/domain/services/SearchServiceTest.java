package com.codurance.codurawise.domain.services;

import com.codurance.codurawise.domain.models.Resource;
import com.codurance.codurawise.repos.SearchRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
@ExtendWith(MockitoExtension.class)

public class SearchServiceTest {
    @Mock
    SearchRepository repository;

  @Test
  public void return_by_title() {
    List<Resource> resourceList =  new ArrayList<>();
    SearchService searchService = new SearchService(repository);
    String title = "scrum";

    given(repository.queryByTitleAndTag(title, null)).willReturn(resourceList);

    List<Resource> result = searchService.getResourceBySearch(title, null);

    assertThat(result).isEqualTo(resourceList);
  }

  @Test
  public void return_by_tag() {
    List<Resource> resourceList =  new ArrayList<>();
    SearchService searchService = new SearchService(repository);
    String tag = "agile";

    given(repository.queryByTitleAndTag(null, tag)).willReturn(resourceList);

    List<Resource> result = searchService.getResourceBySearch(null, tag);

    assertThat(result).isEqualTo(resourceList);
  }

  @Test
  public void return_by_title_and_tag() {
    List<Resource> resourceList =  new ArrayList<>();
    SearchService searchService = new SearchService(repository);
    String title = "scrum";
    String tag = "agile";

    given(repository.queryByTitleAndTag(title, tag)).willReturn(resourceList);

    List<Resource> result = searchService.getResourceBySearch(title, tag);

    assertThat(result).isEqualTo(resourceList);
  }

  }