package com.codurance.codurawise.models;

import com.codurance.codurawise.repos.ResourcesRepository;
import com.codurance.codurawise.repos.dynamo.ResourcesDynamoRepository;

import java.util.Collection;

public class ResourceService {
  private ResourcesRepository repository;

  public ResourceService(ResourcesRepository repository) {
    this.repository = repository;
  }

  public Collection<Resource> getAll() {
    return repository.getAllResources();
  }
}
