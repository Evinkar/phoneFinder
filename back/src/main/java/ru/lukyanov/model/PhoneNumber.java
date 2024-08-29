package ru.lukyanov.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table (name = "numbers")
@NoArgsConstructor
public class PhoneNumber {
    @Id
    private Long number;
    //private Country country;
    private String countryName;
    private LocalDateTime updatedAt;
    private String dataHumans;
    private String fullNumber;
    private String countryText;
    private LocalDateTime maxDate;
    private  String status;

    public PhoneNumber(Long number, String countryName, LocalDateTime updatedAt, String dataHumans, String fullNumber, String countryText, LocalDateTime maxDate, String status) {
        this.number = number;
        this.countryName = countryName;
        this.updatedAt = updatedAt;
        this.dataHumans = dataHumans;
        this.fullNumber = fullNumber;
        this.countryText = countryText;
        this.maxDate = maxDate;
        this.status = status;
    }
}
