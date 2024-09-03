package ru.lukyanov.model;

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
