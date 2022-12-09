package com.codurance.codurawise.domain.models;

public class Resource {

  private Integer id;
  private String title;
  private String link;
  private Double averageRating;

  private String mediaType;

  public static Resource of(Integer id, String title, String link, Double averageRating, String mediaType) {
    Resource resource = new Resource();
    resource.setId(id);
    resource.setTitle(title);
    resource.setLink(link);
    resource.setAverageRating(averageRating);
    resource.setMediaType(mediaType);
    return resource;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Resource resource = (Resource) o;

    if (!id.equals(resource.id)) return false;
    if (!title.equals(resource.title)) return false;
    if (!link.equals(resource.link)) return false;
    if (!averageRating.equals(resource.averageRating)) return false;
    return mediaType.equals(resource.mediaType);
  }

  @Override
  public int hashCode() {
    int result = id.hashCode();
    result = 31 * result + title.hashCode();
    result = 31 * result + link.hashCode();
    result = 31 * result + averageRating.hashCode();
    result = 31 * result + mediaType.hashCode();
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
      '}';
  }
}
