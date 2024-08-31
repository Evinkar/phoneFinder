package ru.front.frame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartFrame extends JFrame {
    public StartFrame(){
        setTitle("Главное окно");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton countrySearchButton = new JButton("Country Search");
        countrySearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openCountrySearchFrame(); // Метод для открытия второго окна
            }
        });
        add(countrySearchButton);
        setVisible(true);
    }
    private void openCountrySearchFrame(){
        new CountrySearchFrame();
        this.dispose();
    }

}
