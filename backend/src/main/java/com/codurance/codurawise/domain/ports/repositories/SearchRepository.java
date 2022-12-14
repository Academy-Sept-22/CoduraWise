package com.codurance.codurawise.domain.ports.repositories;

import com.codurance.codurawise.domain.models.Resource;

import java.util.List;

// TODO: This repository I find odd, is this the same resources' repository?
// If is the same, then I guess you have one service that is unnecessarily split.
// If not, then forget that I ever said anything. regardless I think that if you're
// using the word get in all others, then it might be better to keep consistency in
// the names you're using as query is a SQL specific word.
public interface SearchRepository {

  List<Resource> queryByTitleAndTag(String title, String tag);

  List<Resource> queryByTitle(String title);

  List<Resource> queryByTag(String tag);

  List<Resource> queryBothByTitleAndTag(String title, String tag);
}
