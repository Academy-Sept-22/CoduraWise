package com.codurance.codurawise.lambdas;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

public class GetResources implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private static final String REGION_PROPERTY = "AWS_REGION";
    private static final String TABLE_NAME_PROPERTY = "TABLE_NAME";

    private static final Logger logger = LoggerFactory.getLogger(GetResources.class);
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final String tableName;
    private final String region;

    public GetResources() {
        tableName = System.getenv(TABLE_NAME_PROPERTY);
        region = System.getenv(REGION_PROPERTY);
    }

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent event, Context context) {

        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
        response.setIsBase64Encoded(false);
        response.setStatusCode(200);
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "text/json");
        response.setHeaders(headers);
        response.setBody(gson.toJson(tableName + " - " + region));
        // log execution details
        logEnvironment(event, context, gson);
        return response;
//        return gson.toJson("200 OK");
    }

    public static void logEnvironment(Object event, Context context, Gson gson)
    {
        // log execution details
        logger.info("ENVIRONMENT VARIABLES: " + gson.toJson(System.getenv()));
        logger.info("CONTEXT: " + gson.toJson(context));
        // log event details
        logger.info("EVENT: " + gson.toJson(event));
        logger.info("EVENT TYPE: " + event.getClass().toString());
    }

}
