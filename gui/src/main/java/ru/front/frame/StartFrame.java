package ru.front.frame;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.front.service.JsonClientService;
import ru.front.service.RestClientService;
import ru.lukyanov.model.CountryDTO;
import ru.lukyanov.model.PhoneNumberDTO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StartFrame extends JFrame {
    private static final RestClientService restClientService = new RestClientService();
    private static final Logger logger = LoggerFactory.getLogger(StartFrame.class);

    public StartFrame() {
        setTitle("Поиск стран");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);

        JButton countrySearchButton = new JButton("Поиск по странам");
        countrySearchButton.addActionListener(e -> {
            openCountrySearchFrame(); // Метод для открытия второго окна
        });

        JButton phoneBook = new JButton("Телефонная книга");
        phoneBook.addActionListener(e -> openPhoneBookFrame());

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

    public List<CountryDTO> findCountry() throws JsonProcessingException, IOException {
        List<CountryDTO> countryList = new ArrayList<>();
        String response = restClientService.getResponseBody("http://localhost:8080/api/getCountryList");
        countryList = JsonClientService.jsonParseToArrayCountry(response);

        return countryList;
    }

    private void openCountrySearchFrame() {
        try {
            new CountrySearchFrame(findCountry(), this);
            this.setVisible(false);

        } catch (JsonProcessingException e) {
            JOptionPane.showMessageDialog(this, "Произошла ошибка!",
                    "Ошибка", JOptionPane.ERROR_MESSAGE);

            logger.error("ошибка парсера country {}", e.getMessage());

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Произошла ошибка сохранения!",
                    "Ошибка", JOptionPane.ERROR_MESSAGE);

            logger.error("Ошибка отправки Get-запроса country {}", e.getMessage());
        }
    }

    private void openPhoneBookFrame() {
        List<PhoneNumberDTO> phoneNumberDTOList;
        try {
            phoneNumberDTOList = JsonClientService.jsonParseToArrayNumber(restClientService
                    .getResponseBody("http://localhost:8080/api/loadNumberList"));

            new NumberSearchFrame(phoneNumberDTOList, this);
            this.setVisible(false);

        } catch (JsonProcessingException e) {
            JOptionPane.showMessageDialog(this, "Произошла ошибка сохранения!",
                    "Ошибка", JOptionPane.ERROR_MESSAGE);

            logger.error("ошибка парсера phoneBook {}", e.getMessage());

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Произошла ошибка сохранения!",
                    "Ошибка", JOptionPane.ERROR_MESSAGE);

            logger.error("Ошибка отправки Get-запроса phoneBook {}", e.getMessage());
        }
    }
}
