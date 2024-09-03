package ru.lukyanov.service;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service("responseClient")
public class ResponseClientService {
    private static final Logger logger = LoggerFactory.getLogger(ResponseClientService.class);
    public String getResponseBody(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        OkHttpClient client = new OkHttpClient();

        Response response = client.newCall(request).execute();
        if(response.body() != null){
        return response.body().string();
        } else {
            logger.error("Нет тела запроса");
            return "";
        }

    }
}
