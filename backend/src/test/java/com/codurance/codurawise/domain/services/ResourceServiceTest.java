package com.codurance.codurawise.domain.services;

import com.codurance.codurawise.domain.models.Resource;
import com.codurance.codurawise.domain.ports.repositories.ResourcesRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ResourceServiceTest {
  @Mock
  ResourcesRepository repository;

  @Test
  public void returns_all_resources() {
    List<Resource> resourceCollection = new ArrayList<>();
    ResourceService resourceService = new ResourceService(repository);

    given(repository.getAllSortedByAverageRatingAndCreationDate()).willReturn(resourceCollection);

    List<Resource> result = resourceService.getAll();

    assertThat(result).isEqualTo(resourceCollection);
  }

  @Test
  public void return_by_tag() {
    List<Resource> resourceCollection =  new ArrayList<>();
    ResourceService resourceService = new ResourceService(repository);

    given(repository.getByTagSortedByAverageRatingAndCreationDate("java")).willReturn(resourceCollection);

    List<Resource> result = resourceService.getByTag("java");

    assertThat(result).isEqualTo(resourceCollection);
  }
}