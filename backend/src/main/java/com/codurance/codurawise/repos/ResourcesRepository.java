package com.codurance.codurawise.repos;

import com.codurance.codurawise.domain.models.Resource;

import java.util.List;

public interface ResourcesRepository {

    List<Resource> getAllResources();

  List<Resource> getByTag(String tag);
}
