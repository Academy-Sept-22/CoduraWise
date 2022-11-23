package com.codurance.codurawise.repos;

import com.codurance.codurawise.models.Resource;

import java.util.Collection;

public interface ResourcesRepository {

    Collection<Resource> getAllResources();
}
