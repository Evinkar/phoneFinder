package ru.front.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.lukyanov.model.CountryDTO;
import ru.lukyanov.model.PhoneNumberDTO;

import java.util.List;

public class JsonClientService {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger logger = LoggerFactory.getLogger(JsonClientService.class);

    public static List<CountryDTO> jsonParseToArrayCountry(String response) throws JsonProcessingException {
        List<CountryDTO> countryDTOList = objectMapper.readValue(response, new TypeReference<>() {});
        logger.info("Сформирован лист из {} стран", countryDTOList.size());

        return countryDTOList;
    }

    public static List<PhoneNumberDTO> jsonParseToArrayNumber(String response) throws JsonProcessingException {
        List<PhoneNumberDTO> phoneNumberDTOList = objectMapper.readValue(response, new TypeReference<>() {});
        logger.info("Сформирован лист из {} номеров", phoneNumberDTOList.size());

        return phoneNumberDTOList;
    }

    public static String objectToJson(Object o) throws JsonProcessingException {
        logger.info("Обьект {} преобразован в JSON", o);

        return objectMapper.writeValueAsString(o);
    }
}
