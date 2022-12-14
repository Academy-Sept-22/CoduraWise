package com.codurance.codurawise.domain.ports.repositories;

import com.codurance.codurawise.domain.models.Resource;

import java.util.List;

public interface ResourcesRepository {

  List<Resource> getAllSortedByAverageRatingAndCreationDate();

  List<Resource> getByTagSortedByAverageRatingAndCreationDate(String tag);
}
