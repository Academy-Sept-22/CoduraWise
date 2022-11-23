package com.codurance.codurawise.lambdas;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Map;

public class GetResources implements RequestHandler<Map<String,String>, String> {

    private static final String REGION_PROPERTY = "AWS_REGION";
    private static final String TABLE_NAME_PROPERTY = "TABLE_NAME";
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final String tableName;
    private final String region;

    public GetResources() {
        tableName = System.getenv(TABLE_NAME_PROPERTY);
        region = System.getenv(REGION_PROPERTY);
    }

    @Override
    public String handleRequest(Map<String,String> input, Context context) {

        return gson.toJson("200 OK");
    }

}
