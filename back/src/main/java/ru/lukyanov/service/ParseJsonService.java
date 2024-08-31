package ru.lukyanov.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.lukyanov.model.Country;
import ru.lukyanov.model.PhoneNumber;
import ru.lukyanov.model.ResponseCountry;
import ru.lukyanov.model.ResponseNumber;

import java.util.ArrayList;


public class ParseJsonService {
    ObjectMapper objectMapper = new ObjectMapper();

    public ParseJsonService() {
    }

    public ArrayList jsonParseToArray(String response) throws JsonProcessingException {

        ArrayList<Country> countryList = objectMapper
                .readValue(response, ResponseCountry.class).getCountries();
        return countryList;
        //убрать getContries чтобы унифицировать метод.
    }
    public ArrayList jsonParseToArrayNumber(String response) throws JsonProcessingException{
        ArrayList<PhoneNumber> numberList = objectMapper
                .readValue(response, ResponseNumber.class).getNumbers();
        return numberList;
    }


}
