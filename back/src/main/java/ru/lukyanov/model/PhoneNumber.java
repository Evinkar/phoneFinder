package ru.lukyanov.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "numbers")
public class PhoneNumber {
    @Id
    @JsonProperty("number")
    private Long number;
    @JsonProperty("country")
    private String countryIndex;
    @JsonProperty("updated_at")
    private String updatedAt;
    @JsonProperty("data_humans")
    private String dataHumans;
    @JsonProperty("full_number")
    private String fullNumber;
    @JsonProperty("country_text")
    private String countryText;
    @JsonProperty("maxdate")
    private String maxDate;
    @JsonProperty("status")
    private String status;

    public PhoneNumber(Long number, String countryName, String updatedAt, String dataHumans,
                       String fullNumber, String countryText, String maxDate, String status) {
        this.number = number;
        this.countryIndex = countryName;
        this.updatedAt = updatedAt;
        this.dataHumans = dataHumans;
        this.fullNumber = fullNumber;
        this.countryText = countryText;
        this.maxDate = maxDate;
        this.status = status;
    }

    public PhoneNumber() {
    }

    @Override
    public String toString() {
        return ("number: " + number + " country name: " + countryText
                + " Updated at: " + updatedAt + " Full number: " + fullNumber);
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getCountryIndex() {
        return countryIndex;
    }

    public void setCountryIndex(String countryIndex) {
        this.countryIndex = countryIndex;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getDataHumans() {
        return dataHumans;
    }

    public void setDataHumans(String dataHumans) {
        this.dataHumans = dataHumans;
    }

    public String getFullNumber() {
        return fullNumber;
    }

    public void setFullNumber(String fullNumber) {
        this.fullNumber = fullNumber;
    }

    public String getCountryText() {
        return countryText;
    }

    public void setCountryText(String countryText) {
        this.countryText = countryText;
    }

    public String getMaxDate() {
        return maxDate;
    }

    public void setMaxDate(String maxDate) {
        this.maxDate = maxDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public PhoneNumberDTO toDTO() {
        PhoneNumberDTO phoneNumberDTO = new PhoneNumberDTO();
        phoneNumberDTO.setNumber(this.getNumber());
        phoneNumberDTO.setFullNumber(this.getFullNumber());
        phoneNumberDTO.setCountryIndex(this.getCountryIndex());
        phoneNumberDTO.setCountryText(this.getCountryText());
        phoneNumberDTO.setStatus(this.getStatus());
        phoneNumberDTO.setDataHumans(this.getDataHumans());
        phoneNumberDTO.setMaxDate(this.getMaxDate());
        phoneNumberDTO.setUpdatedAt(this.getUpdatedAt());
        return phoneNumberDTO;
    }

    public static PhoneNumber fromDTO(PhoneNumberDTO phoneNumberDTO) {
        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setNumber(phoneNumberDTO.getNumber());
        phoneNumber.setFullNumber(phoneNumberDTO.getFullNumber());
        phoneNumber.setCountryIndex(phoneNumberDTO.getCountryIndex());
        phoneNumber.setCountryText(phoneNumberDTO.getCountryText());
        phoneNumber.setStatus(phoneNumberDTO.getStatus());
        phoneNumber.setDataHumans(phoneNumberDTO.getDataHumans());
        phoneNumber.setMaxDate(phoneNumberDTO.getMaxDate());
        phoneNumber.setUpdatedAt(phoneNumberDTO.getUpdatedAt());
        return phoneNumber;
    }
}
