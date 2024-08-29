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
    private String countryText;


    public Country(Long country, String countryText) {
        this.country = country;
        this.countryText = countryText;
    }

    public Country() {
    }
    @Override
    public String toString(){
        return ("number: " + country + " country name: " + countryText);
    }

    public Long getCountry() {
        return country;
    }

    public void setCountry(Long country) {
        this.country = country;
    }

    public String getCountryText() {
        return countryText;
    }

    public void setCountryText(String countryText) {
        this.countryText = countryText;
    }
}
