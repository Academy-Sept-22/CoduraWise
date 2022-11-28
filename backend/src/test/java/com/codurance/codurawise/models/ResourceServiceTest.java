package com.codurance.codurawise.models;

import com.codurance.codurawise.repos.dynamo.ResourcesDynamoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ResourceServiceTest {
  @Mock ResourcesDynamoRepository repository;

  @Test
  public void returns_all_resources() {
    Collection<Resource> repositoryCollection = new ArrayList<>();
    ResourceService resourceService = new ResourceService(repository);

    given(repository.getAllResources()).willReturn(repositoryCollection);

    Collection result = resourceService.getAll();

    assertThat(result).isEqualTo(repositoryCollection);
  }


}