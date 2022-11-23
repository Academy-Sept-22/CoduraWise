package com.codurance.codurawise.repos.dynamo;

import com.codurance.codurawise.model.Resource;
import com.codurance.codurawise.repos.ResourcesRepository;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.ScanRequest;
import software.amazon.awssdk.services.dynamodb.model.ScanResponse;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public class DynamoResourcesTable implements ResourcesRepository {

    private final DynamoDbClient client;
    private final String tableName;

    public DynamoResourcesTable(String region, String tableName) {
        client = DynamoDbClient.builder().region(Region.of(region)).build();
        this.tableName = tableName;
    }

    @Override
    public Collection<Resource> getAllResources() {
        ScanRequest scanRequest = ScanRequest.builder()
                .tableName(tableName)
                .build();

        ScanResponse response = client.scan(scanRequest);

        // transform list of Map<String,Attribute> into set of Resource
        return response.items().stream().map(this::convert).collect(Collectors.toSet());
    }

    protected Resource convert(Map<String, AttributeValue> values) {
        Resource resource = new Resource();
        resource.setId(Integer.parseInt(values.get("Id").n()));
        resource.setTitle(values.get("Title").s());
        resource.setLink(values.get("Link").s());
        return resource;
    }
}
