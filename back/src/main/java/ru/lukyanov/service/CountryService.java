package ru.lukyanov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lukyanov.repository.CountryRepository;


@Service
public class CountryService {
    @Autowired
    CountryRepository countryRepository;
}
