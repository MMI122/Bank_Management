package com.example.managementapp;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class JsonUploader {

    public void uploadJson(String jsonArrayString) {
        try {
            // Create HttpClient
            HttpClient client = HttpClient.newHttpClient();

            // Build HTTP request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.jsonbin.io/v3/b")) // Replace with your API endpoint
                    .header("Content-Type", "application/json")
                    .header("X-Master-Key", "$2a$10$W.zLLhFongqhQo9XAUBhA.14u3xfAWQNnqH3bAlpf5.WCtPnTDE0y") // Replace with your actual API key
                    .POST(HttpRequest.BodyPublishers.ofString(jsonArrayString))
                    .build();

            // Send the request and get the response
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Print response (debugging purposes)
            System.out.println("Response code: " + response.statusCode());
            System.out.println("Response body: " + response.body());
        } catch (Exception e) {
            e.printStackTrace(); // Print any errors
        }
    }
}
