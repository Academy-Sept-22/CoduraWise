package com.codurance.codurawise.domain.models;

public class Resource {

    private Integer id;
    private String title;
    private String link;

    private Double averageRating;

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
}
