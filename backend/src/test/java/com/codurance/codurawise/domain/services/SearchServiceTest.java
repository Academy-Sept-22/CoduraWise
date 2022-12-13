package com.codurance.codurawise.domain.services;

import com.codurance.codurawise.domain.models.Resource;
import com.codurance.codurawise.repos.SearchRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
@ExtendWith(MockitoExtension.class)

public class SearchServiceTest {
  @Mock
  SearchRepository repository;

  @Test
  public void return_by_title() {
    List<Resource> resourceList = new ArrayList<>();
    SearchService searchService = new SearchService(repository);
    String title = "scrum";

    given(repository.queryByTitle(title)).willReturn(resourceList);

    List<Resource> result = searchService.getResourceBySearch(title, null);

    assertThat(result).isEqualTo(resourceList);
  }

  @Test
  public void return_by_tag() {
    List<Resource> resourceList = new ArrayList<>();
    SearchService searchService = new SearchService(repository);
    String tag = "agile";

    given(repository.queryByTag(tag)).willReturn(resourceList);

    List<Resource> result = searchService.getResourceBySearch(null, tag);

    assertThat(result).isEqualTo(resourceList);
  }

  @Test
  public void return_by_title_and_tag() {
    List<Resource> resourceList = new ArrayList<>();
    SearchService searchService = new SearchService(repository);
    String title = "scrum";
    String tag = "agile";

    given(repository.queryBothByTitleAndTag(title, tag)).willReturn(resourceList);

    List<Resource> result = searchService.getResourceBySearch(title, tag);

    assertThat(result).isEqualTo(resourceList);
  }

  @Test
  public void throws_exception_for_both_nulls() {

    SearchService searchService = new SearchService(repository);

    Exception exception = assertThrows(RuntimeException.class, () -> {
      searchService.getResourceBySearch(null, null);
    });
    assertThat(exception.getMessage()).isEqualTo(
      "Must provide either title or tag to query!");
  }

}