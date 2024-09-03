package ru.lukyanov.model;

public class PhoneNumberDTO {

    private Long number;
    private String countryIndex;
    private String updatedAt;
    private String dataHumans;
    private String fullNumber;
    private String countryText;
    private String maxDate;
    private String status;

    public PhoneNumberDTO(Long number, String countryIndex, String updatedAt, String dataHumans, String fullNumber, String countryText, String maxDate, String status) {
        this.number = number;
        this.countryIndex = countryIndex;
        this.updatedAt = updatedAt;
        this.dataHumans = dataHumans;
        this.fullNumber = fullNumber;
        this.countryText = countryText;
        this.maxDate = maxDate;
        this.status = status;
    }

    public PhoneNumberDTO() {
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
}
