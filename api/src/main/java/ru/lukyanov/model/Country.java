package ru.lukyanov.model;

public class Country {

    private Long country;
    private String countryName;

    public Country(Long country, String countryName) {
        this.country = country;
        this.countryName = countryName;
    }

    public Country() {
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
