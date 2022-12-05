package com.codurance.codurawise.repos;

import com.codurance.codurawise.domain.models.Resource;

import java.util.List;

public interface SearchRepository {
  List<Resource> queryByTitleAndTag(String title, String tag);
}
