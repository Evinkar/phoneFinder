package ru.front.frame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.front.component.BackButton;
import ru.front.service.RestClientService;
import ru.lukyanov.model.PhoneNumber;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.HashMap;
import java.util.List;

public class NumberSearchFrame extends JFrame {

  private static final Logger logger = LoggerFactory.getLogger(NumberSearchFrame.class);
  private final HashMap<Long, PhoneNumber> numberHashMap = new HashMap<>();
  JFrame previousFrame;
  public NumberSearchFrame(List<PhoneNumber> numberList, JFrame previousFrame) {
    this.previousFrame=previousFrame;
    SwingUtilities.invokeLater(() -> {
      setTitle("Список номеров");
      setSize(200, 300);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      JPanel panel = new JPanel();
      GroupLayout layout = new GroupLayout(panel);
      panel.setLayout(layout);

      layout.setAutoCreateGaps(true);

      numberList.forEach(phoneNumber -> {
        numberHashMap.put(phoneNumber.getNumber(), phoneNumber);
      }); //получаем лист number и кладем в hashmap с ключом number

      JList<Long> list = new JList<>(numberHashMap.keySet()
        .stream()
        .toList()
        .toArray(new Long[0]));//добавляем в лист массив объекта Номер c методом to string
      JScrollPane scrollPane = new JScrollPane(list);//оборачиваем лист в scrollPane

      BackButton backToCountryButton = new BackButton(this, previousFrame, "назад", null);

      JLabel label = new JLabel("Выберите номер телефона");

      layout.setVerticalGroup(layout.createSequentialGroup()
        .addComponent(label)
        .addComponent(scrollPane)
        .addComponent(backToCountryButton));
      layout.setHorizontalGroup(layout.createParallelGroup()
        .addComponent(label)
        .addComponent(scrollPane)
        .addComponent(backToCountryButton)

      );
      SwingWorker<Void, String> worker = new SwingWorker<Void, String>() {
        @Override
        protected Void doInBackground() throws Exception {
          numberSelection(list);
          System.out.println("NumberSelection " + Thread.currentThread().getName());
          return null;
        }
      };
      worker.execute();

      add(panel);
      setLocationRelativeTo(null);
      setVisible(true);
    });
  }

  public void numberSelection(JList<Long> list) {
    list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //тип выбора
    list.addListSelectionListener(e -> {
      if (e.getValueIsAdjusting() || null == list.getSelectedValue()) {
        return; // Игнорирует начало выбора
      }
      // Действие при выделении элемента
      String selectedValue = String.valueOf(list.getSelectedValue());

      openNumberCardFrame(numberHashMap.get(Long.parseLong(selectedValue)));
      System.out.println(Thread.currentThread().getName());

      list.clearSelection();
    });
  }

  public void openNumberCardFrame(PhoneNumber phoneNumber) {
    new NumberCardFrame(phoneNumber, this);
    this.setVisible(false);
  }
}
