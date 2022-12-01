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
    return repository.getAllResources();
  }

  public List<Resource> getByTag(String tag) {
    return repository.getByTag(tag);
  }
}
