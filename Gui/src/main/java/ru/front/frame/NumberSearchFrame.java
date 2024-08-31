package ru.front.frame;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.lukyanov.model.PhoneNumber;
import ru.lukyanov.service.ParseJsonService;
import ru.lukyanov.service.ResponseClientService;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class NumberSearchFrame extends JFrame {

    ParseJsonService parseJsonService = new ParseJsonService();
    ResponseClientService responseClientService = new ResponseClientService();
    HashMap<Long, PhoneNumber> numberHashMap = new HashMap<>();

    private Long countryIndex;

    public NumberSearchFrame(Long countryIndex, CountrySearchFrame previousFrame) {
        this.countryIndex = countryIndex;
        setTitle("Number search result");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        findNumber(countryIndex).forEach(o -> {
            PhoneNumber phoneNumber = (PhoneNumber) o;

            numberHashMap.put(phoneNumber.getNumber(),phoneNumber);
        }); //получам лист number и кладем в hashmap с ключом number

        JList<Long> list = new JList<>(numberHashMap.keySet().stream().toList().toArray(new Long[0]));//добавляем в лист массив обьекта country c методом to string
        JScrollPane scrollPane = new JScrollPane(list);//оборачиваем лист в scrollPane
        //scrollPane.setBounds(10, 20, 280, 300);


        //add(scrollPane);

        JButton backToCountryButton = new JButton("Back");

        Box mainBox = Box.createVerticalBox();
        mainBox.add(backToCountryButton);
        mainBox.add(scrollPane);
        setContentPane(mainBox);
        pack();//узнать что это такое

        backToCountryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                previousFrame.setVisible(true);
                dispose();

            }
        });
        add(backToCountryButton);
        numberSelection(list);


        setVisible(true);
    }

    public ArrayList<Object> findNumber(Long countryIndex){
        ArrayList<Object> phoneNumberList = new ArrayList<>();
        try {
            phoneNumberList = parseJsonService.jsonParseToArrayNumber(responseClientService
                    .getResponseBody("https://onlinesim.ru/api/getFreePhoneList?country="+countryIndex));
            //должен передавать параметр страны!!!

            return phoneNumberList;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    public void numberSelection(JList<Long> list) {

        JFrame owner  = this;
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //тип выбора
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {
                    return; // Игнорирует начало выбора
                }
                String selectedValue = String.valueOf(list.getSelectedValue());


                System.out.println(numberHashMap.get(Long.parseLong(selectedValue)));
                openNumberCardFrame(numberHashMap.get(Long.parseLong(selectedValue)), (NumberSearchFrame) owner);
                // Действие при выделении элемента

            }
        });
    }
    public  void openNumberCardFrame(PhoneNumber phoneNumber, NumberSearchFrame previousFrame){
        new NumberCardFrame(phoneNumber, previousFrame);
        this.setVisible(false);
    }

}
