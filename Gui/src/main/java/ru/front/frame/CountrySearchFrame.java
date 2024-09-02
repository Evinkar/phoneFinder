package ru.front.frame;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.front.service.JsonClientService;
import ru.front.service.RestClientService;
import ru.lukyanov.model.CountryDTO;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CountrySearchFrame extends JFrame {

    private final RestClientService restClientService = new RestClientService();

    HashMap<String, CountryDTO> countryHashMap = new HashMap<>(); //map с ключом названием страны и значением обьектом country

    public CountrySearchFrame() {
        setTitle("Результат поиска стран");
        setSize(300, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        findCountry().forEach(countryDTO -> {
            countryHashMap.put(countryDTO.getCountryName(), countryDTO);
        }); //получам лист country и кладем в hashmap

        JList<String> list = new JList<>(countryHashMap.keySet().stream()
                .toList().toArray(new String[0]));//добавляем в Jlist ключи из мапы, создаем массив string

        JScrollPane scrollPane = new JScrollPane(list);//оборачиваем лист в scrollPane
        scrollPane.setBounds(10, 20, 280, 300);
        add(scrollPane);

        countrySelection(list);
        setLocationRelativeTo(null);

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

        JFrame owner = this;
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //тип выбора
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting() || null==list.getSelectedValue()) {
                    return; // Игнорирует начало выбора
                }

                String selectedValue = String.valueOf(list.getSelectedValue());


                System.out.println(countryHashMap.get(selectedValue));
                openNumberFrame(countryHashMap.get(selectedValue).getCountry(), (CountrySearchFrame) owner);
                // Действие при выделении элемента
                list.clearSelection();

            }
        });
    }

    public void openNumberFrame(Long countryIndex, CountrySearchFrame previousFrame) {
        new NumberSearchFrame(countryIndex, previousFrame);
        this.setVisible(false);

    }


}
