package ru.lukyanov.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.lukyanov.model.Country;
import ru.lukyanov.model.CountryDTO;
import ru.lukyanov.model.PhoneNumber;
import ru.lukyanov.model.PhoneNumberDTO;
import ru.lukyanov.repository.NumberRepository;
import ru.lukyanov.service.ParseJsonService;
import ru.lukyanov.service.ResponseClientService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class GeneralController {
    private static final Logger logger = LoggerFactory.getLogger(GeneralController.class);
    @Autowired
    ResponseClientService responseClientService;
    @Autowired
    ParseJsonService parseJsonService;
    @Autowired
    NumberRepository numberRepository;

    @GetMapping("/getCountryList")
    public List<CountryDTO> getCountryResponse() {

        try {
            String responseBody = (responseClientService
                    .getResponseBody("https://onlinesim.ru/api/getFreeCountryList"));
            ArrayList<Country> countries;
            countries = parseJsonService.jsonParseToArrayCountry(responseBody);
            return countries.stream().map(country -> country.toDTO()).toList();
        } catch (JsonProcessingException e) {
            logger.error("Ошибка при парсинге Country{}", e.getMessage());
            throw new RuntimeException(e);
        } catch (IOException e) {
            logger.error("Ошибка GET-запроса getCountryResponse {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/getNumberList")
    public List<PhoneNumberDTO> getNumberResponse(@RequestParam Long countryIndex) throws RuntimeException {

        try {
            String responseBody = (responseClientService.
                    getResponseBody("https://onlinesim.ru/api/getFreePhoneList?country=" + countryIndex));
            ArrayList<PhoneNumber> numbers;
            numbers = parseJsonService.jsonParseToArrayNumber(responseBody);

            return numbers.stream().map(PhoneNumber::toDTO).toList();
        } catch (JsonProcessingException e) {
            logger.error("Ошибка при парсинге PhoneNumber {}", e.getMessage());
            throw new RuntimeException(e);
        } catch (IOException e) {
            logger.error("Ошибка GET-запроса getPhoneNumberList {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/saveNumber")
    public PhoneNumberDTO saveNumber(@RequestBody PhoneNumberDTO phoneNumberDTO) {
        PhoneNumberDTO savedNumber = numberRepository.save(PhoneNumber.fromDTO(phoneNumberDTO)).toDTO();
        logger.info("Обьект {} сохранен в БД", savedNumber.getFullNumber());
        return savedNumber;
    }

    @GetMapping("/loadNumberList")
    public List<PhoneNumberDTO> loadAllSavedNumbers() {
        List<PhoneNumberDTO> phoneNumberDTOList = numberRepository.findAll().stream().map(PhoneNumber::toDTO).toList();
        logger.info("Получен из БД лист из {} номеров", phoneNumberDTOList.size());
        return phoneNumberDTOList;
    }
}
