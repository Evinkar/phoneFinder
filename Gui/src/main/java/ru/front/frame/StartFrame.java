package ru.front.frame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartFrame extends JFrame {
    public StartFrame(){
        setTitle("Поиск стран");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);


        JButton countrySearchButton = new JButton("Country Search");
        countrySearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openCountrySearchFrame(); // Метод для открытия второго окна
            }
        });
        JButton phoneBook = new JButton("Телефонная книга");
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
        setVisible(true);
    }
    private void openCountrySearchFrame(){
        new CountrySearchFrame();
        this.dispose();
    }

}
