package ru.front.frame;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.front.component.BackButton;
import ru.front.service.JsonClientService;
import ru.front.service.RestClientService;
import ru.lukyanov.model.CountryDTO;
import ru.lukyanov.model.PhoneNumberDTO;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class CountrySearchFrame extends JFrame {

    private final RestClientService restClientService = new RestClientService();
    private static final Logger logger = LoggerFactory.getLogger(CountrySearchFrame.class);
    private final HashMap<String, CountryDTO> countryHashMap = new HashMap<>(); //map с ключом названием страны и значением обьектом country

    public CountrySearchFrame(List<CountryDTO> countryDTOList, JFrame previousFrame) throws JsonProcessingException {
        setTitle("Результат поиска стран");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);//положение окна на экране

        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        countryDTOList.forEach(countryDTO -> {
            countryHashMap.put(countryDTO.getCountryName(), countryDTO);
        }); //получам лист country и кладем в hashmap

        JList<String> list = new JList<>(countryHashMap.keySet().stream()
                .toList().toArray(new String[0]));//добавляем в Jlist ключи из мапы, создаем массив string

        JScrollPane scrollPane = new JScrollPane(list);//оборачиваем лист в scrollPane

        JButton backToStartButton = new BackButton(this, previousFrame, "назад", null);

        add(scrollPane);
        add(backToStartButton);
        countrySelection(list);

        setVisible(true);
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

            list.clearSelection();
        });
    }

    public List<PhoneNumberDTO> findNumber(Long countryIndex) throws JsonProcessingException, IOException {
        String response = restClientService.getResponseBody(
                "http://localhost:8080/api/getNumberList?countryIndex=" + countryIndex);
        return JsonClientService.jsonParseToArrayNumber(response);
    }

    public void openNumberFrame(Long countryIndex) {
        try {
            new NumberSearchFrame(findNumber(countryIndex), this);
            this.setVisible(false);

        } catch (JsonProcessingException e) {
            JOptionPane.showMessageDialog(this, "Произошла ошибка!",
                    "Ошибка", JOptionPane.ERROR_MESSAGE);

            logger.error("ошибка парсера {}", e.getMessage());

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Произошла ошибка сохранения!",
                    "Ошибка", JOptionPane.ERROR_MESSAGE);

            logger.error("Ошибка отправки Get-запроса {}", e.getMessage());
        }
    }
}
