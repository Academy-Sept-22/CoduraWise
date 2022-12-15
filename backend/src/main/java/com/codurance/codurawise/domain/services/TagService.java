package com.codurance.codurawise.domain.services;

import com.codurance.codurawise.domain.models.Tag;
import com.codurance.codurawise.domain.repos.TagRepository;

import java.util.List;

public class TagService {
  private final TagRepository tagRepository;

  public TagService(TagRepository tagRepository) {
    this.tagRepository = tagRepository;
  }

  public List<Tag> getAllTags() {
    return this.tagRepository.getAllTags();
  }
}
