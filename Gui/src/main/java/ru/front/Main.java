package ru.front;


import ru.front.frame.StartFrame;

import javax.swing.*;


public class Main {


    public static void main(String[] args) {
        SwingUtilities.invokeLater(StartFrame::new);
        //ApplicationContext context = new ClassPathXmlApplicationContext("config/context.xml");

    }


}