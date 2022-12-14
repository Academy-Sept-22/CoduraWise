package com.codurance.codurawise.domain.services;

import com.codurance.codurawise.domain.models.Resource;
import com.codurance.codurawise.repos.SearchRepository;

import java.util.List;

public class SearchService {

  private final SearchRepository repository;

  public SearchService(SearchRepository repository) {
    this.repository = repository;
  }

  public List<Resource> search(String text) {
    String[] arrOfText = text.split(" ");
    return repository.search(arrOfText);
  }
}
