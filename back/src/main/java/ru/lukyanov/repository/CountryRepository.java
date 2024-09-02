package ru.lukyanov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.lukyanov.model.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

}
