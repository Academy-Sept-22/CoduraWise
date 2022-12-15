package com.codurance.codurawise.domain.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class Resource {

  private Integer id;
  private String title;
  private String link;
  private Double averageRating;
  private String mediaType;
  private Collection<Tag> tags;

  public static Resource of(Integer id, String title, String link, Double averageRating, String mediaType) {
    Resource resource = new Resource();
    resource.setId(id);
    resource.setTitle(title);
    resource.setLink(link);
    resource.setAverageRating(averageRating);
    resource.setMediaType(mediaType);
    return resource;
  }

  public static Resource of(Integer id, String title, String link, Double averageRating, String mediaType, Collection<Tag> tags) {
    Resource resource = of(id, title, link, averageRating, mediaType);
    resource.setTags(tags);
    return resource;
  }

  public int getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setLink(String link) {
    this.link = link;
  }

  public void setAverageRating(Double averageRating) {
    this.averageRating = averageRating;
  }

  public void setMediaType(String mediaType) {
    this.mediaType = mediaType;
  }

  public void setTags(Collection<Tag> tags) {
    this.tags = new ArrayList<>(tags);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Resource resource = (Resource) o;

    if (!Objects.equals(id, resource.id)) return false;
    if (!Objects.equals(title, resource.title)) return false;
    if (!Objects.equals(link, resource.link)) return false;
    if (!Objects.equals(averageRating, resource.averageRating))
      return false;
    if (!Objects.equals(mediaType, resource.mediaType)) return false;
    return Objects.equals(tags, resource.tags);
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (title != null ? title.hashCode() : 0);
    result = 31 * result + (link != null ? link.hashCode() : 0);
    result = 31 * result + (averageRating != null ? averageRating.hashCode() : 0);
    result = 31 * result + (mediaType != null ? mediaType.hashCode() : 0);
    result = 31 * result + (tags != null ? tags.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "Resource{" +
      "id=" + id +
      ", title='" + title + '\'' +
      ", link='" + link + '\'' +
      ", averageRating=" + averageRating +
      ", mediaType='" + mediaType + '\'' +
      ", tags=" + tags +
      '}';
  }
}
