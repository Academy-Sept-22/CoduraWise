package com.codurance.codurawise.domain.services;

import com.codurance.codurawise.domain.models.Resource;
import com.codurance.codurawise.repos.ResourcesRepository;

import java.util.List;

public class ResourceService {
  private final ResourcesRepository repository;

  public ResourceService(ResourcesRepository repository) {
    this.repository = repository;
  }

  public List<Resource> getAll() {
    return repository.getAllSortedByAverageRatingAndCreationDate();
  }

  public List<Resource> getByTag(String tag) {
    return repository.getByTagSortedByAverageRatingAndCreationDate(tag);
  }

    public Resource add(Resource resourceToAdd) {
        throw new UnsupportedOperationException();

    }
}
