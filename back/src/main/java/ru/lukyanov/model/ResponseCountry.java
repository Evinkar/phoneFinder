package ru.lukyanov.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
@Getter
@Setter
@Data
//@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseCountry {

    private String response;

    private ArrayList<Country> countries;

    public ArrayList<Country> getCountries() {
        return countries;
    }

    public String getResponse() {
        return response;
    }
}
