package com.codurance.codurawise.domain.services;

import com.codurance.codurawise.domain.models.Resource;
import com.codurance.codurawise.domain.ports.repositories.ResourcesRepository;

import java.util.List;

public class ResourceService {
  private final com.codurance.codurawise.domain.ports.repositories.ResourcesRepository repository;

  public ResourceService(ResourcesRepository repository) {
    this.repository = repository;
  }

  public List<Resource> getAll() {
    return repository.getAllSortedByAverageRatingAndCreationDate();
  }

  public List<Resource> getByTag(String tag) {
    return repository.getByTagSortedByAverageRatingAndCreationDate(tag);
  }
}
