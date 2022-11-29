package com.codurance.codurawise.domain.services;

import com.codurance.codurawise.domain.models.Resource;
import com.codurance.codurawise.repos.ResourcesRepository;

import java.util.Collection;

public class ResourceService {
  private final ResourcesRepository repository;

  public ResourceService(ResourcesRepository repository) {
    this.repository = repository;
  }

  public Collection<Resource> getAll() {
    return repository.getAllResources();
  }
}
