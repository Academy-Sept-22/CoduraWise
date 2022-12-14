package com.codurance.codurawise.domain.services;

import com.codurance.codurawise.domain.models.Resource;
import com.codurance.codurawise.repos.SearchRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)

public class SearchServiceTest {
  @Mock
  SearchRepository repository;
  SearchService searchService;

  @BeforeEach
  public void setup(){
    searchService = new SearchService(repository);
  }


  @Test
  public void return_by_title() {
    List<Resource> resourceList = new ArrayList<>();
    String title = "scrum";

    given(repository.queryByTitle(title)).willReturn(resourceList);

    List<Resource> result = searchService.getResourceBySearch(title, null);

    assertThat(result).isEqualTo(resourceList);
  }

  @Test
  public void return_by_tag() {
    List<Resource> resourceList = new ArrayList<>();
    String tag = "agile";

    given(repository.queryByTag(tag)).willReturn(resourceList);

    List<Resource> result = searchService.getResourceBySearch(null, tag);

    assertThat(result).isEqualTo(resourceList);
  }

  @Test
  public void return_by_title_and_tag() {
    List<Resource> resourceList = new ArrayList<>();
    String title = "scrum";
    String tag = "agile";

    given(repository.queryBothByTitleAndTag(title, tag)).willReturn(resourceList);

    List<Resource> result = searchService.getResourceBySearch(title, tag);

    assertThat(result).isEqualTo(resourceList);
  }

  @Test
  public void throws_exception_for_both_nulls() {

    Exception exception = assertThrows(RuntimeException.class, () -> searchService.getResourceBySearch(null, null));
    assertThat(exception.getMessage()).isEqualTo(
      "Must provide either title or tag to query!");
  }

  @Test
  public void search_for_several_tags() {
    //arrange
    List<Resource> expected = List.of();
    given(repository.search("java", "spring", "tdd")).willReturn(List.of());

    //act
    List<Resource> result = searchService.search("java spring tdd");

    //assert
    assertEquals(expected, result);
  }
}