package ru.lukyanov.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import ru.lukyanov.model.Country;
import ru.lukyanov.model.PhoneNumber;
import ru.lukyanov.model.ResponseCountry;
import ru.lukyanov.model.ResponseNumber;

import java.util.ArrayList;

@Service
public class ParseJsonService {
    ObjectMapper objectMapper = new ObjectMapper();

    public ParseJsonService() {
    }

    public ArrayList<Country> jsonParseToArrayCountry(String response) throws JsonProcessingException {

        return objectMapper.readValue(response, ResponseCountry.class).getCountries();


    }
    public ArrayList<PhoneNumber> jsonParseToArrayNumber(String response) throws JsonProcessingException{

        return objectMapper.readValue(response, ResponseNumber.class).getNumbers();
    }


}
