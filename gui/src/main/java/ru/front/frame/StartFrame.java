package ru.front.frame;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.front.service.JsonClientService;
import ru.front.service.RestClientService;
import ru.lukyanov.model.*;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StartFrame extends JFrame {

  private static final Logger logger = LoggerFactory.getLogger(StartFrame.class);

  public StartFrame() {
    SwingUtilities.invokeLater(() -> {
      setTitle("Поиск стран");
      setSize(300, 200);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      JPanel panel = new JPanel();
      GroupLayout layout = new GroupLayout(panel);
      panel.setLayout(layout);
      layout.setAutoCreateGaps(true);

      JButton countrySearchButton = new JButton("Поиск по странам");
      countrySearchButton.addActionListener(e -> {
        SwingWorker<Void, String> worker = new SwingWorker<Void, String>() {
          @Override
          protected Void doInBackground() throws Exception {
            // Метод для открытия окна с результатом поиска стран
            openCountrySearchFrame();
            System.out.println("openCountrySearchFrame " + Thread.currentThread().getName());
            return null;
          }
        };
        worker.execute();
      });


      JButton phoneBook = new JButton("Телефонная книга");
      phoneBook.addActionListener(e -> {
        SwingWorker<Void, String> worker = new SwingWorker<Void, String>() {
          @Override
          protected Void doInBackground() throws Exception {
            // Метод для открытия окна телефонной книги
            openPhoneBookFrame();
            System.out.println(Thread.currentThread().getName());
            return null;
          }
        };
        worker.execute();
      });

      layout.setHorizontalGroup(layout.createSequentialGroup().addComponent(countrySearchButton)
        .addComponent(phoneBook));
      layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
        .addGap(30)
        .addComponent(countrySearchButton)
        .addComponent(phoneBook)
        .addGap(30));

      setLocationRelativeTo(null);
      add(panel);
      this.setVisible(true);
    });
  }


  private void openCountrySearchFrame() {
    try {
      new CountrySearchFrame(findCountry(), this);
      this.setVisible(false);

    } catch (JsonProcessingException e) {
      JOptionPane.showMessageDialog(this, "Произошла ошибка!", "Ошибка", JOptionPane.ERROR_MESSAGE);

      logger.error("ошибка парсера country {}", e.getMessage());

    } catch (IOException e) {
      JOptionPane.showMessageDialog(this, "Произошла ошибка получения ответа!", "Ошибка", JOptionPane.ERROR_MESSAGE);

      logger.error("Ошибка отправки Get-запроса country {}", e.getMessage());
    }
  }

  private void openPhoneBookFrame() {

    try {

      new NumberSearchFrame(findNumber(), this);
      this.setVisible(false);

    } catch (JsonProcessingException e) {
      JOptionPane.showMessageDialog(this, "Произошла ошибка!", "Ошибка", JOptionPane.ERROR_MESSAGE);

      logger.error("ошибка парсера phoneBook {}", e.getMessage());

    } catch (IOException e) {
      JOptionPane.showMessageDialog(this, "Произошла ошибка получения ответа!", "Ошибка", JOptionPane.ERROR_MESSAGE);

      logger.error("Ошибка отправки Get-запроса phoneBook {}", e.getMessage());
    }
  }

  private List<PhoneNumber> findNumber() throws IOException {
    String response = RestClientService.getResponseBody("http://localhost:8080/api/loadNumberList");
    return JsonClientService.jsonParseToArrayNumberFromPB(response);

  }

  public List<Country> findCountry() throws JsonProcessingException, IOException {
    List<Country> countryList = new ArrayList<>();
    String response = RestClientService.getResponseBody("https://onlinesim.ru/api/getFreeCountryList");
    return countryList = JsonClientService.jsonParseToArrayCountry(response);

  }

}
