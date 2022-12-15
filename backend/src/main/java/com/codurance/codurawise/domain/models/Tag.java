package com.codurance.codurawise.domain.models;

import java.util.Objects;

public class Tag {

  private String tag;

  public static Tag of(String tagValue) {
    Tag tag = new Tag();
    tag.setTag(tagValue);
    return tag;
  }

  public String getTag() {
    return tag;
  }

  public void setTag(String tag) {
    this.tag = tag;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Tag tag1 = (Tag) o;

    return Objects.equals(tag, tag1.tag);
  }

  @Override
  public int hashCode() {
    return tag != null ? tag.hashCode() : 0;
  }

  @Override
  public String toString() {
    return "Tag{" +
      "tag='" + tag + '\'' +
      '}';
  }
}
