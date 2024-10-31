package ru.front.model;

import ru.lukyanov.model.Country;

import java.util.ArrayList;

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
