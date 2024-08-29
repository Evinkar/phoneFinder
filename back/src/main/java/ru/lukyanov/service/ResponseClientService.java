package ru.lukyanov.service;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service("responseClient")
public class ResponseClientService {

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
}
