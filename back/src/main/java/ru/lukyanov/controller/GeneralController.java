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

        String responseBody = (responseClientService.getResponseBody("https://onlinesim.ru/api/getFreeCountryList"));
        ArrayList<Country> countries;

        try {
            countries = parseJsonService.jsonParseToArrayCountry(responseBody);
        } catch (JsonProcessingException e) {
            logger.error("Ошибка при парсинге",e);
            throw new RuntimeException(e);
        }
        return countries.stream().map(country -> country.toDTO()).toList();

    }

    @GetMapping("/getNumberList")
    public List<PhoneNumberDTO> getNumberResponse(@RequestParam Long countryIndex) {
        String responseBody = (responseClientService.
                getResponseBody("https://onlinesim.ru/api/getFreePhoneList?country=" + countryIndex));
        ArrayList<PhoneNumber> numbers;

        try {
            numbers = parseJsonService.jsonParseToArrayNumber(responseBody);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return numbers.stream().map(PhoneNumber::toDTO).toList();
    }

    @PostMapping("/saveNumber")
    public PhoneNumberDTO saveNumber(@RequestBody PhoneNumberDTO phoneNumberDTO) {
        PhoneNumberDTO savedNumber = numberRepository.save(PhoneNumber.fromDTO(phoneNumberDTO)).toDTO();
        logger.info("Обьект сохранен {}",savedNumber.getFullNumber());
        return savedNumber;

    }

    @GetMapping("/loadNumberList")
    public List<PhoneNumberDTO> loadAllSavedNumbers() {
        return numberRepository.findAll().stream().map(PhoneNumber::toDTO).toList();
    }


}
