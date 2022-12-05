package com.codurance.codurawise.domain.services;

import com.codurance.codurawise.domain.models.Resource;
import com.codurance.codurawise.repos.ResourcesRepository;

import java.util.List;

public class TitleService{

  private final ResourcesRepository repository;

  public TitleService(ResourcesRepository repository) {
    this.repository = repository;
  }

  public List<Resource> getResourceByTitle(String title) {
      return repository.getByTitleSortedByAverageRatingAndCreationDate(title);
  }
}
