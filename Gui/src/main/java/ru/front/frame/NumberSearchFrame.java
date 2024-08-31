package ru.front.frame;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.lukyanov.model.PhoneNumber;
import ru.lukyanov.service.ParseJsonService;
import ru.lukyanov.service.ResponseClientService;

import javax.swing.*;
import java.awt.*;
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

        JList<PhoneNumber> list = new JList<>(findNumber(countryIndex).toArray(new PhoneNumber[0]));//добавляем в лист массив обьекта country c методом to string
        JScrollPane scrollPane = new JScrollPane(list);//оборачиваем лист в scrollPane
        //scrollPane.setBounds(10, 20, 280, 300);


        //add(scrollPane);

        JButton backToCountryButton = new JButton("Back");

        Box mainBox = Box.createVerticalBox();
        mainBox.add(backToCountryButton);
        mainBox.add(scrollPane);
        add(mainBox);
        pack();

        backToCountryButton.setBounds(350, 350,10, 50);
        backToCountryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                previousFrame.setVisible(true);
                dispose();

            }
        });
        add(backToCountryButton);


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

}
