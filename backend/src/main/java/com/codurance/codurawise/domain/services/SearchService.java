package com.codurance.codurawise.domain.services;

import com.codurance.codurawise.domain.models.Resource;
import com.codurance.codurawise.repos.ResourcesRepository;

import java.util.List;

public class SearchService{

  private final ResourcesRepository repository;

  public SearchService(ResourcesRepository repository) {
    this.repository = repository;
  }

  public List<Resource> getResourceBySearch(String search) {
      return repository.getBySearchSortedByAverageRatingAndCreationDate(search);
  }
}
