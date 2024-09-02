package ru.front.frame;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.front.service.JsonClientService;
import ru.front.service.RestClientService;
import ru.lukyanov.model.PhoneNumberDTO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class StartFrame extends JFrame {
    RestClientService restClientService = new RestClientService();
    public StartFrame(){
        setTitle("Поиск стран");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);


        JButton countrySearchButton = new JButton("Поиск по странам");
        countrySearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openCountrySearchFrame(); // Метод для открытия второго окна
            }
        });
        JButton phoneBook = new JButton("Телефонная книга");
        phoneBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    openPhoneBookFrame();
                } catch (JsonProcessingException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addComponent(countrySearchButton)
                        .addComponent(phoneBook)

        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addGap(30)
                        .addComponent(countrySearchButton)
                        .addComponent(phoneBook)
                        .addGap(30)
        );
        setLocationRelativeTo(null);
        add(panel);
        this.setVisible(true);
    }
    private void openCountrySearchFrame(){
        new CountrySearchFrame(this);
        this.setVisible(false);
    }
    private void openPhoneBookFrame() throws JsonProcessingException {
        List<PhoneNumberDTO> phoneNumberDTOList;
        phoneNumberDTOList = JsonClientService.jsonParseToArrayNumber(restClientService
                .getResponseBody("http://localhost:8080/api/loadNumberList"));
        new NumberSearchFrame(phoneNumberDTOList,this);//список обьектов
        this.setVisible(false);
    }

}
