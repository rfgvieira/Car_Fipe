package com.rfgvieira.Car_Fipe.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

public class Service {
    public Optional<String> getDataFromApi(String adress){
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(adress)).build();
        HttpResponse<String> response = null;

        try{
            response = client.send(request,HttpResponse.BodyHandlers.ofString());
        }  catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        if(response.statusCode() != 200){
            return Optional.empty();
        }

        return Optional.of(response.body());
    }

}
