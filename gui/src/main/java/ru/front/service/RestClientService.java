package ru.front.service;

import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class RestClientService {
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private static final Logger logger = LoggerFactory.getLogger(RestClientService.class);

    public String getResponseBody(String url) throws IOException {
        logger.info("Получение responseBody из {}", url);
        Request request = new Request.Builder()
                .url(url)
                .build();

        OkHttpClient client = new OkHttpClient();

        Response response = client.newCall(request).execute();
        String responseBody = response.body().string();// получаем из response массив JSON объектов

        return responseBody;
    }

    public String postRequest(String url, String body) throws IOException {
        RequestBody requestBody = RequestBody.create(body, JSON);
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        OkHttpClient client = new OkHttpClient();
        Response response = client.newCall(request).execute();
        String responseBody = response.body().string();// получаем из response массив JSON объектов
        logger.info("Отправка тела запроса : {}", responseBody);

        return responseBody;
    }
}
