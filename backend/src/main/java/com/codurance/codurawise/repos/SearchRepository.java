package com.codurance.codurawise.repos;

import com.codurance.codurawise.domain.models.Resource;

import java.util.List;
import java.util.Map;

public interface SearchRepository {
  List<Resource> queryByTitleAndTag(Map<String, String> query);
}
