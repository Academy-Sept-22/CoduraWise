package com.codurance.codurawise.domain.services;

import com.codurance.codurawise.domain.models.Resource;
import com.codurance.codurawise.repos.SearchRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
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