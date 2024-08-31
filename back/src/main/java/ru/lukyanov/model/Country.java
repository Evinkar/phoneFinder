package ru.lukyanov.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity

@Table(name = "countries")

public class Country {
    @Id
    @JsonProperty("country")
    private Long country;
    @JsonProperty("country_text")
    private String countryName;


    public Country(Long country, String countryName) {
        this.country = country;
        this.countryName = countryName;
    }

    public Country() {
    }
    @Override
    public String toString(){
        return ("number: " + country + " country name: " + countryName);
    }

    public Long getCountry() {
        return country;
    }

    public void setCountry(Long country) {
        this.country = country;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
