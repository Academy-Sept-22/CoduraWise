package com.codurance.codurawise.domain.services;

import com.codurance.codurawise.domain.models.Resource;
import com.codurance.codurawise.domain.models.Tag;
import com.codurance.codurawise.domain.repos.ResourcesRepository;
import com.codurance.codurawise.domain.repos.TagRepository;

import java.util.Collection;
import java.util.List;

public class ResourceService {
  private final ResourcesRepository repository;
  private final TagRepository tagRepository;

  public ResourceService(ResourcesRepository repository, TagRepository tagRepository) {
    this.repository = repository;
    this.tagRepository = tagRepository;
  }

  public List<Resource> getAll() {
    return repository.getAllSortedByAverageRatingAndCreationDate();
  }

  public List<Resource> getByTag(String tag) {
    return repository.getByTagSortedByAverageRatingAndCreationDate(tag);
  }

    public Resource add(Resource resourceToAdd) {

      // TODO: this logic in repository to use transactions?

      Collection<Tag> existingTags = tagRepository.getAllTags();

      Collection<Tag> resourceTags = resourceToAdd.getTags();
      for (Tag tag: resourceTags) {
        if (!existingTags.contains(tag)) {
          tagRepository.add(tag);
        }
      }

      return repository.add(resourceToAdd);
    }
}
