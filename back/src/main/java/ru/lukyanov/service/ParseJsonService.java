package ru.lukyanov.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import ru.lukyanov.model.Country;
import ru.lukyanov.model.Response;

import java.util.ArrayList;


public class ParseJsonService {
    ObjectMapper objectMapper = new ObjectMapper();

    public ParseJsonService() {
    }

    public ArrayList jsonParseToArray(String response) throws JsonProcessingException {

        ArrayList<Country> countryList = objectMapper
                .readValue(response, Response.class).getCountries();
        return countryList;
        //убрать getContries чтобы унифицировать метод.
    }


}
