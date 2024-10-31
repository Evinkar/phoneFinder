package ru.front.frame;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.*;
import ru.front.component.BackButton;
import ru.front.service.*;
import ru.lukyanov.model.*;

import javax.swing.*;
import java.io.IOException;
import java.util.*;

public class CountrySearchFrame extends JFrame {


  private static final Logger logger = LoggerFactory.getLogger(CountrySearchFrame.class);
  private final HashMap<String, Country> countryHashMap = new HashMap<>(); //map с ключом названием страны и значением обьектом country

  public CountrySearchFrame(List<Country> countryList, JFrame previousFrame) throws JsonProcessingException {
    SwingUtilities.invokeLater(() -> {
      setTitle("Результат поиска стран");
      setSize(300, 400);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setLocationRelativeTo(null);//положение окна на экране

      setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

      countryList.forEach(country -> {
        countryHashMap.put(country.getCountryName(), country);
      }); //получам лист country и кладем в hashmap

      JList<String> list = new JList<>(countryHashMap.keySet()
        .stream()
        .toList()
        .toArray(new String[0]));//добавляем в Jlist ключи из мапы, создаем массив string

      JScrollPane scrollPane = new JScrollPane(list);//оборачиваем лист в scrollPane

      JButton backToStartButton = new BackButton(this, previousFrame, "назад", null);

      add(scrollPane);
      add(backToStartButton);
      SwingWorker<Void,String> worker = new SwingWorker<Void, String>() {
        @Override
        protected Void doInBackground() throws Exception {
          countrySelection(list);
          System.out.println("CountrySelection " + Thread.currentThread().getName());
          return null;
        }
      };
      worker.execute();
      setVisible(true);
    });
  }

  public void countrySelection(JList<String> list) {
    list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //тип выбора
    list.addListSelectionListener(e -> {
      if (e.getValueIsAdjusting() || null == list.getSelectedValue()) {
        return; // Игнорирует начало выбора
      }
      // Действие при выделении элемента
      String selectedValue = String.valueOf(list.getSelectedValue());

      openNumberFrame(countryHashMap.get(selectedValue).getCountry());
      System.out.println(Thread.currentThread().getName());
      list.clearSelection();
    });
  }

  public List<PhoneNumber> findNumber(Long countryIndex) throws JsonProcessingException, IOException {
    String response = RestClientService.getResponseBody("https://onlinesim.ru/api/getFreePhoneList?country="
      + countryIndex);
    return JsonClientService.jsonParseToArrayNumber(response);
  }

  public void openNumberFrame(Long countryIndex) {

    try {
      new NumberSearchFrame(findNumber(countryIndex), this);
      this.setVisible(false);

    } catch (JsonProcessingException e) {
      JOptionPane.showMessageDialog(this, "Произошла ошибка!", "Ошибка", JOptionPane.ERROR_MESSAGE);

      logger.error("ошибка парсера {}", e.getMessage());

    } catch (IOException e) {
      JOptionPane.showMessageDialog(this, "Произошла ошибка сохранения!", "Ошибка", JOptionPane.ERROR_MESSAGE);

      logger.error("Ошибка отправки Get-запроса {}", e.getMessage());
    }
  }
}
