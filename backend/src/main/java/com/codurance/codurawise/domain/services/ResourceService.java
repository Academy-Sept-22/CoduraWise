package com.codurance.codurawise.domain.services;

import com.codurance.codurawise.domain.models.Resource;
import com.codurance.codurawise.domain.ports.repositories.ResourcesRepository;

import java.util.List;

public class ResourceService {

  private final ResourcesRepository repository;

  public ResourceService(ResourcesRepository repository) {
    this.repository = repository;
  }

  public List<Resource> getAll() {
    // TODO:
    // List<Resources> resources = repository.getAll();
    // resources = sortByRating();
    // resources = sortByCreationDate();
    // return resources;
    return repository.getAllSortedByAverageRatingAndCreationDate();
  }

  public List<Resource> getByTag(String tag) {
    // TODO:
    // List<Resources> resources = repository.getByTag(tag);
    // resources = sortByRating();
    // resources = sortByCreationDate();
    // return resources;
    return repository.getByTagSortedByAverageRatingAndCreationDate(tag);
  }
}
