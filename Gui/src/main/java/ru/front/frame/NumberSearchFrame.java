package ru.front.frame;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.front.service.JsonClientService;
import ru.front.service.RestClientService;
import ru.lukyanov.model.PhoneNumberDTO;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NumberSearchFrame extends JFrame {

    private RestClientService restClientService = new RestClientService();
    private final HashMap<Long, PhoneNumberDTO> numberHashMap = new HashMap<>();

    private Long countryIndex;

    public NumberSearchFrame(Long countryIndex, CountrySearchFrame previousFrame) {
        this.countryIndex = countryIndex;
        setTitle("Результат поиска номеров");
        setSize(200, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);

        layout.setAutoCreateGaps(true);


        findNumber(countryIndex).forEach(phoneNumberDTO -> {
            numberHashMap.put(phoneNumberDTO.getNumber(), phoneNumberDTO);
        }); //получам лист number и кладем в hashmap с ключом number

        JList<Long> list = new JList<>(numberHashMap.keySet().stream().toList().toArray(new Long[0]));//добавляем в лист массив обьекта country c методом to string
        JScrollPane scrollPane = new JScrollPane(list);//оборачиваем лист в scrollPane
        JButton backToCountryButton = new JButton("Back");

        JLabel label = new JLabel("Выберите номер телефона");
        JLabel labelCountryName = new JLabel();

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(label)
                        .addComponent(scrollPane)
                        .addComponent(backToCountryButton)
        );
        layout.setHorizontalGroup(
                layout.createParallelGroup()
                        .addComponent(label)
                        .addComponent(scrollPane)
                        .addComponent(backToCountryButton)

        );


        backToCountryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                previousFrame.setVisible(true);
                dispose();

            }
        });


        numberSelection(list);
        add(panel);
        setLocationRelativeTo(null);
        setVisible(true);

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

    public void numberSelection(JList<Long> list) {

        JFrame owner = this;
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //тип выбора
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting() ) {
                    return; // Игнорирует начало выбора
                }
                String selectedValue = String.valueOf(list.getSelectedValue());


                System.out.println(numberHashMap.get(Long.parseLong(selectedValue)));
                openNumberCardFrame(numberHashMap.get(Long.parseLong(selectedValue)), (NumberSearchFrame) owner);
                // Действие при выделении элемента
                list.clearSelection();

            }
        });
    }

    public void openNumberCardFrame(PhoneNumberDTO phoneNumberDTO, NumberSearchFrame previousFrame) {
        new NumberCardFrame(phoneNumberDTO, previousFrame);
        this.setVisible(false);
    }

}
