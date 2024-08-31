package ru.front.frame;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.lukyanov.model.Country;
import ru.lukyanov.service.ParseJsonService;
import ru.lukyanov.service.ResponseClientService;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class CountrySearchFrame extends JFrame {
    //    @Autowired
//    private ResponseClientService responseClientService;
//    @Autowired
//    private ParseJsonService parseJsonService;
    //TODO Исправить NULL ошибку разобраться с application.context

    ParseJsonService parseJsonService = new ParseJsonService();
    ResponseClientService responseClientService = new ResponseClientService();
    HashMap<String, Country> countryHashMap = new HashMap<>(); //map с ключом названием страны и значением обьектом country

    public CountrySearchFrame() {
        setTitle("Country search result");
        setSize(300, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        findCountry().forEach(o -> {
            Country country = (Country) o;
            countryHashMap.put(country.getCountryName(),country);
        }); //получам лист country и кладем в hashmap

        JList<String> list = new JList<>(countryHashMap.keySet().stream()
                .toList().toArray(new String[0]));//добавляем в Jlist ключи из мапы, создаем массbв string

        JScrollPane scrollPane = new JScrollPane(list);//оборачиваем лист в scrollPane
        scrollPane.setBounds(10, 20, 280, 300);
        add(scrollPane);

        countrySelection(list);

        setVisible(true);
    }

    public ArrayList<Object> findCountry() {
        ArrayList<Object> countryList = new ArrayList<>();
        try {
            countryList = parseJsonService.jsonParseToArray(responseClientService
                    .getResponseBody("https://onlinesim.ru/api/getFreeCountryList"));

            return countryList;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    public void countrySelection(JList<String> list) {

        JFrame owner  = this;
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //тип выбора
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {
                    return; // Игнорирует начало выбора
                }
                String selectedValue = String.valueOf(list.getSelectedValue());


                System.out.println(countryHashMap.get(selectedValue));
                openNumberFrame(countryHashMap.get(selectedValue).getCountry(), (CountrySearchFrame) owner);
                // Действие при выделении элемента

            }
        });
    }

    public void openNumberFrame(Long countryIndex, CountrySearchFrame previousFrame) {
        new NumberSearchFrame(countryIndex, previousFrame);
        this.setVisible(false);

    }


}
