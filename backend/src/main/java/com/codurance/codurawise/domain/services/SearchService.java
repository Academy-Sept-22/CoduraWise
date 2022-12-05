package com.codurance.codurawise.domain.services;

import com.codurance.codurawise.domain.models.Resource;
import com.codurance.codurawise.repos.SearchRepository;

import java.util.List;
import java.util.Map;

public class SearchService{

  private final SearchRepository repository;

  public SearchService(SearchRepository repository) {
    this.repository = repository;
  }

  public List<Resource> getResourceBySearch(Map<String, String> query) {
      return repository.queryByTitleAndTag(query);
  }
}
