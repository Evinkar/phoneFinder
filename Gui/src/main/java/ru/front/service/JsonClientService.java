package ru.front.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.lukyanov.model.CountryDTO;
import ru.lukyanov.model.PhoneNumberDTO;
import java.util.List;

public class JsonClientService {
    static ObjectMapper objectMapper = new ObjectMapper();

    public static List<CountryDTO> jsonParseToArrayCountry(String response) throws JsonProcessingException {

        return objectMapper.readValue(response, new TypeReference<>() {});


    }
    public static List<PhoneNumberDTO> jsonParseToArrayNumber(String response) throws JsonProcessingException{

        return objectMapper.readValue(response, new TypeReference<>() {});
    }
    public static String objectToJson (Object o) throws JsonProcessingException {
        return objectMapper.writeValueAsString(o);
    }

}
