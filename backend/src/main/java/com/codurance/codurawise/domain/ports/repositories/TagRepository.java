package com.codurance.codurawise.domain.ports.repositories;

import com.codurance.codurawise.domain.models.Tag;

import java.util.List;

public interface TagRepository {

  List<Tag> getAllTags();
}
