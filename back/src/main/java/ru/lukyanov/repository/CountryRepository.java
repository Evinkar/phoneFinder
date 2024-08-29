package ru.lukyanov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lukyanov.model.Country;


public interface CountryRepository extends JpaRepository<Country, Long> {

}
