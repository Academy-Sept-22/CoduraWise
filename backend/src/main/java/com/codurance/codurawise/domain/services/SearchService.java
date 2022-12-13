package com.codurance.codurawise.domain.services;

import com.codurance.codurawise.domain.models.Resource;
import com.codurance.codurawise.repos.SearchRepository;

import java.util.List;

public class SearchService {

  private final SearchRepository repository;

  public SearchService(SearchRepository repository) {
    this.repository = repository;
  }

  public List<Resource> getResourceBySearch(String title, String tag) {
    if (title == null && tag == null) {
      throw new RuntimeException("Must provide either title or tag to query!");
    } else if (title != null && tag == null) {
      return repository.queryByTitle(title);
    } else if (title == null) {
      return repository.queryByTag(tag);
    } else {
      return repository.queryBothByTitleAndTag(title, tag);
    }
  }

    public List<Resource> search(String text) {
        throw new UnsupportedOperationException();

    }
}
