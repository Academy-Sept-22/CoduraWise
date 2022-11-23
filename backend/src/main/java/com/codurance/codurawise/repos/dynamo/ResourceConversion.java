package com.codurance.codurawise.repos.dynamo;

import com.codurance.codurawise.models.Resource;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.Map;

public class ResourceConversion {

    protected Resource convert(Map<String, AttributeValue> values) {
        Resource resource = new Resource();
        resource.setId(Integer.parseInt(values.get("Id").n()));
        resource.setTitle(values.get("Title").s());
        resource.setLink(values.get("Link").s());
        return resource;
    }
}