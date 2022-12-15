package com.codurance.codurawise.domain.services;

import com.codurance.codurawise.domain.models.Resource;
import com.codurance.codurawise.domain.models.Tag;
import com.codurance.codurawise.repos.ResourcesRepository;
import com.codurance.codurawise.repos.TagRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ResourceServiceTest {
  @Mock
  ResourcesRepository repository;

  @Mock
  TagRepository tagRepository;

  @Test
  public void returns_all_resources() {
    List<Resource> resourceCollection = new ArrayList<>();
    ResourceService resourceService = new ResourceService(repository, tagRepository);

    given(repository.getAllSortedByAverageRatingAndCreationDate()).willReturn(resourceCollection);

    List<Resource> result = resourceService.getAll();

    assertThat(result).isEqualTo(resourceCollection);
  }

  @Test
  public void return_by_tag() {
    List<Resource> resourceCollection =  new ArrayList<>();
    ResourceService resourceService = new ResourceService(repository, tagRepository);

    given(repository.getByTagSortedByAverageRatingAndCreationDate("java")).willReturn(resourceCollection);

    List<Resource> result = resourceService.getByTag("java");

    assertThat(result).isEqualTo(resourceCollection);
  }

  @Test
  void adds_resource_with_new_tag() {

    // arrange

    // get all tags from TagRepository
    List<Tag> existingTags = List.of(Tag.of("tdd"));
    given(tagRepository.getAllTags()).willReturn(existingTags);

    Resource resourceToAdd = new Resource();
    resourceToAdd.setAverageRating(3.0);
    resourceToAdd.setTitle("Uncle Bob");
    resourceToAdd.setLink("www.bob.com");
    resourceToAdd.setMediaType("article");
    resourceToAdd.setTags(List.of(Tag.of("agile"),
      Tag.of("java"),
      Tag.of("tdd")));

    // if resourceToAdd has new tag then add new tag to TagRepository
    // add resourceToAdd to ResourceRepository
    // get id generated and put in resourceAdded
    Resource resourceAdded = new Resource();
    resourceAdded.setId(1);
    resourceAdded.setAverageRating(3.0);
    resourceAdded.setTitle("Uncle Bob");
    resourceAdded.setLink("www.bob.com");
    resourceAdded.setMediaType("article");
    resourceToAdd.setTags(List.of(Tag.of("agile"),
      Tag.of("java"),
      Tag.of("tdd")));

    given(repository.add(resourceToAdd)).willReturn(resourceAdded);

    // act
    ResourceService resourceService = new ResourceService(repository, tagRepository);
    Resource resource = resourceService.add(resourceToAdd);

    // assert
    assertThat(resource).isEqualTo(resourceAdded);

    ArgumentCaptor<Tag> tagCaptor = ArgumentCaptor.forClass(Tag.class);
    verify(tagRepository, times(2)).add(tagCaptor.capture());
    assertThat(tagCaptor.getAllValues()).contains(Tag.of("agile"));
    assertThat(tagCaptor.getAllValues()).contains(Tag.of("java"));
  }
}