package ru.front.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import ru.lukyanov.model.Country;
import ru.lukyanov.service.ResponseClientService;
import ru.lukyanov.service.ParseJsonService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
@Controller
@ComponentScan
public class StartController {
    @Autowired
    private ResponseClientService responseClientService;
    @Autowired
    private ParseJsonService parseJsonService;
    //Исправить NULL ошибку


    public void placeComponents(JPanel panel) {
        panel.setLayout(null);


        ArrayList<Object> countryList = new ArrayList<>();
        try {
            countryList = parseJsonService.jsonParseToArray(responseClientService
                    .getResponseBody("https://onlinesim.ru/api/getFreeCountryList"));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        //преобразует в массив текста и используя toString выводится в scrollPane
        JList<Country> list = new JList<>(countryList.toArray(new Country[0]));

        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setBounds(10, 20, 280, 300);
        panel.add(scrollPane);
        panel.setVisible(true);


        JButton button = new JButton("Change text");
        button.setBounds(60, 330, 150, 25);
        panel.add(button);

        // Обработчик события для кнопки
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //label.setText("Text changed");
            }
        });
    }
}
