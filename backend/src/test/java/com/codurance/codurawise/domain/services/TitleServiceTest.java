package com.codurance.codurawise.domain.services;

import com.codurance.codurawise.domain.models.Resource;
import com.codurance.codurawise.repos.ResourcesRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
@ExtendWith(MockitoExtension.class)

public class TitleServiceTest {
    @Mock
    ResourcesRepository repository;

  @Test
  public void return_by_title() {
    List<Resource> resourceList =  new ArrayList<>();
    TitleService titleService = new TitleService(repository);

    given(repository.getByTitleSortedByAverageRatingAndCreationDate("scrum")).willReturn(resourceList);

    List<Resource> result = titleService.getResourceByTitle("scrum");

    assertThat(result).isEqualTo(resourceList);
  }

  }