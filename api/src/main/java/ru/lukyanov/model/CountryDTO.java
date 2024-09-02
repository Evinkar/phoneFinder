package ru.lukyanov.model;

public class CountryDTO {

    private Long country;
    private String countryName;

    public CountryDTO(Long country, String countryName) {
        this.country = country;
        this.countryName = countryName;
    }

    public CountryDTO() {
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
