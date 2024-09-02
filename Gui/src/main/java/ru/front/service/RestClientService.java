package ru.front.service;

import okhttp3.*;

import java.io.IOException;

public class RestClientService {
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    public String getResponseBody(String url) {
        Request request = new Request.Builder()
                .url(url)
                .build();

        OkHttpClient client = new OkHttpClient();

        try {
            Response response = client.newCall(request).execute();
            String responseBody = response.body().string();// получаем из response массив JSON объектов
            return responseBody;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public String postRequest(String url,String body) {
        RequestBody requestBody = RequestBody.create(body, JSON);
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        OkHttpClient client = new OkHttpClient();

        try {
            Response response = client.newCall(request).execute();
            String responseBody = response.body().string();// получаем из response массив JSON объектов
            return responseBody;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
