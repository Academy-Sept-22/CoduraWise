package com.codurance.codurawise.domain.services;

import com.codurance.codurawise.domain.models.Tag;
import com.codurance.codurawise.domain.ports.repositories.TagRepository;

import java.util.List;

public class TagService {

  private final TagRepository tagRepository;

  public TagService(TagRepository tagRepository) {
    this.tagRepository = tagRepository;
  }

  public List<Tag> getAllTags() {
    // TODO: Just getAll() is fine, as tags is implied by the repository name
    return this.tagRepository.getAllTags();
  }
}
