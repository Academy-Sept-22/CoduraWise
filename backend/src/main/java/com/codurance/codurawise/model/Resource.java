package com.codurance.codurawise.model;

import com.amazonaws.services.lambda.runtime.events.models.dynamodb.AttributeValue;

public class Resource {

    private Integer id;
    private String title;
    private String link;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
