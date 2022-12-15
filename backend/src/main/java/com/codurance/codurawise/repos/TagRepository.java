package com.codurance.codurawise.repos;

import com.codurance.codurawise.domain.models.Tag;

import java.util.List;

public interface TagRepository {

  List<Tag> getAllTags();

    Tag add(Tag tag);
}
