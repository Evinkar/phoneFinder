package ru.front.frame;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.front.component.BackButton;
import ru.front.service.JsonClientService;
import ru.front.service.RestClientService;
import ru.lukyanov.model.CountryDTO;
import ru.lukyanov.model.PhoneNumberDTO;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CountrySearchFrame extends JFrame {

    private final RestClientService restClientService = new RestClientService();

    HashMap<String, CountryDTO> countryHashMap = new HashMap<>(); //map с ключом названием страны и значением обьектом country

    public CountrySearchFrame(JFrame previousFrame ) {
        setTitle("Результат поиска стран");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);//положение окна на экране

        setLayout(new BoxLayout(this.getContentPane(),BoxLayout.Y_AXIS));

        findCountry().forEach(countryDTO -> {
            countryHashMap.put(countryDTO.getCountryName(), countryDTO);
        }); //получам лист country и кладем в hashmap

        JList<String> list = new JList<>(countryHashMap.keySet().stream()
                .toList().toArray(new String[0]));//добавляем в Jlist ключи из мапы, создаем массив string

        JScrollPane scrollPane = new JScrollPane(list);//оборачиваем лист в scrollPane

        JButton backToStartButton = new BackButton(this,previousFrame,"назад",null);

        add(scrollPane);
        add(backToStartButton);
        countrySelection(list);


        setVisible(true);
    }

    public List<CountryDTO> findCountry() {
        List<CountryDTO> countryList = new ArrayList<>();
        String response = restClientService.getResponseBody("http://localhost:8080/api/getCountryList");
        try {
            countryList = JsonClientService.jsonParseToArrayCountry(response);
            return countryList;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    public void countrySelection(JList<String> list) {
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //тип выбора
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting() || null==list.getSelectedValue()) {
                    return; // Игнорирует начало выбора
                }

                String selectedValue = String.valueOf(list.getSelectedValue());

                openNumberFrame(countryHashMap.get(selectedValue).getCountry());
                // Действие при выделении элемента
                list.clearSelection();

            }
        });
    }
    public List<PhoneNumberDTO> findNumber(Long countryIndex) {
        List<PhoneNumberDTO> phoneNumberList = new ArrayList<>();
        String response = restClientService.getResponseBody("http://localhost:8080/api/getNumberList?countryIndex="+countryIndex);
        try {
            phoneNumberList = JsonClientService.jsonParseToArrayNumber(response);
            return phoneNumberList;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void openNumberFrame(Long countryIndex) {
        new NumberSearchFrame(findNumber(countryIndex), this);
        this.setVisible(false);

    }


}
