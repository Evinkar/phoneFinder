package ru.front.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.lukyanov.model.*;

import java.util.List;

public class JsonClientService {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger logger = LoggerFactory.getLogger(JsonClientService.class);

    public static List<Country> jsonParseToArrayCountry(String response) throws JsonProcessingException {
        List<Country> countryList = objectMapper.readValue(response, ResponseCountry.class)
            .getCountries();
        logger.info("Сформирован лист из {} стран", countryList.size());

        return countryList;
    }

    public static List<PhoneNumber> jsonParseToArrayNumber(String response) throws JsonProcessingException {
        List<PhoneNumber> phoneNumberList = objectMapper.readValue(response, ResponseNumber.class)
          .getNumbers();
        logger.info("Сформирован лист из {} номеров", phoneNumberList.size());

        return phoneNumberList;
    }
    public static List<PhoneNumber> jsonParseToArrayNumberFromPB(String response) throws JsonProcessingException {
        List<PhoneNumber> phoneNumberList = objectMapper.readValue(response, new TypeReference<List<PhoneNumber>>() {});
        logger.info("Сформирован лист из {} номеров, загружено из телефонной книги", phoneNumberList.size());

        return phoneNumberList;
    }

    public static String objectToJson(Object o) throws JsonProcessingException {
        logger.info("Объект {} преобразован в JSON", o);

        return objectMapper.writeValueAsString(o);
    }
}
