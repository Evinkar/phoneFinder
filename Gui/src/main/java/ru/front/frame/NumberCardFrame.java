package ru.front.frame;

import ru.lukyanov.model.PhoneNumber;

import javax.swing.*;

public class NumberCardFrame extends JFrame {
    public NumberCardFrame(PhoneNumber number, NumberSearchFrame previousFrame) {
        setTitle("Number Card");
        setSize(600, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
