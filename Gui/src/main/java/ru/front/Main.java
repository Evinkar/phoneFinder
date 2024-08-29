package ru.front;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.front.controller.StartController;

import javax.swing.*;

@ComponentScan
public class Main {


    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("config/context.xml");

        JFrame frame = new JFrame("Text Display Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 600);

        JPanel panel = new JPanel();
        frame.add(panel);


        StartController startController = new StartController();
        startController.placeComponents(panel);

        frame.setVisible(true);
    }


}