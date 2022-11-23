package com.codurance.codurawise.repos.dynamo;

import com.codurance.codurawise.models.Resource;
import com.codurance.codurawise.repos.ResourcesRepository;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.ScanRequest;
import software.amazon.awssdk.services.dynamodb.model.ScanResponse;

import java.util.Collection;
import java.util.stream.Collectors;

public class ResourcesDynamoRepository implements ResourcesRepository {

    private final DynamoDbClient client;
    private final String tableName;
    private final ResourceConversion resourceConversion = new ResourceConversion();

    public ResourcesDynamoRepository(String region, String tableName) {
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
        return response.items().stream().map(resourceConversion::convert).collect(Collectors.toSet());
    }

}
