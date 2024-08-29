package ru.lukyanov.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
@Getter
@Setter
@Data
//@JsonIgnoreProperties(ignoreUnknown = true)
public class Response {

    private String response;

    private ArrayList<Country> countries;

    public ArrayList<Country> getCountries() {
        return countries;
    }

    public String getResponse() {
        return response;
    }
}
