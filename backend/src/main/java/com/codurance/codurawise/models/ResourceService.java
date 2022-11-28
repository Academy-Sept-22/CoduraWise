package com.codurance.codurawise.models;

import com.codurance.codurawise.repos.dynamo.ResourcesDynamoRepository;

import java.util.Collection;

public class ResourceService {
  private ResourcesDynamoRepository repository;

  public ResourceService(ResourcesDynamoRepository repository) {
    this.repository = repository;
  }

  public Collection<Resource> getAll() {
    return repository.getAllResources();
  }
}
