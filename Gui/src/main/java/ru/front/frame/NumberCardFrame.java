package ru.front.frame;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.front.frame.message.PhoneNumberMessages;
import ru.front.service.JsonClientService;
import ru.front.service.RestClientService;
import ru.lukyanov.model.PhoneNumberDTO;
import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class NumberCardFrame extends JFrame {
    private RestClientService restClientService = new RestClientService();
    private PhoneNumberDTO phoneNumber;

    private final List<String> displayedFields = Arrays.asList(
            "number",
            "countryIndex",
            "dataHumans",
            "fullNumber",
            "countryText",
            "status");

    public NumberCardFrame(PhoneNumberDTO phoneNumber, JFrame previousFrame) {

        this.phoneNumber = phoneNumber;
        setTitle("Number Card");
        setSize(600, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        List<String> attributeNames = Arrays.stream(phoneNumber.getClass().getDeclaredFields())
                .map(Field::getName).toList();//Собирает в лист названия полей

        Container contentPane = getContentPane();
        contentPane.setLayout(new GridLayout(0, 2, 10, 10));

        for (String attributeName : attributeNames) {
            try {
                addLabelAndTextToContentPane(contentPane,
                        attributeName,
                        getFieldValue(phoneNumber, attributeName));

            } catch (NoSuchFieldException | IllegalAccessException e) {
                System.out.println("Некорректное создание поля");
            }
        }

        JButton savePhoneButton = new JButton("Save");
        savePhoneButton.addActionListener(e -> {
            try {
                restClientService.postRequest("http://localhost:8080/api/saveNumber",
                        JsonClientService.objectToJson(phoneNumber));
            } catch (JsonProcessingException ex) {
                throw new RuntimeException(ex);
            }
        });
        add(savePhoneButton);


        JButton backToCountryButton = new JButton("Back");
        backToCountryButton.addActionListener(e -> {
            previousFrame.setVisible(true);
            dispose();

        });
        add(backToCountryButton);

        contentPane.add(backToCountryButton);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }


    public void addLabelAndTextToContentPane(Container container,//возможно поменять на panel
                                             String attributeName,
                                             String attributeValue) {

        if (attributeValue != null && displayedFields.contains(attributeName)) {
            attributeName = PhoneNumberMessages.get(attributeName);

            JLabel label = new JLabel(attributeName);
            JTextField textField = new JTextField(attributeValue);
            textField.setEditable(false);

            container.add(label);
            container.add(textField);
        }
    }

    public static String getFieldValue(Object obj, String fieldName)
            throws NoSuchFieldException, IllegalAccessException {
        Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);//игнорирует приватность полей
        return field.get(obj).toString();
    }


}