package ru.front;

import ru.front.frame.StartFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.setTitle("Поиск стран");
        jFrame.setSize(300, 200);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        JButton button = new JButton("Старт сессии");
        System.out.println(Thread.currentThread().getName());
        button.addActionListener(e -> {
            //SwingUtilities.invokeLater(StartFrame::new);
            SwingUtilities.invokeLater(() -> {
                final Runnable newSession = new Runnable() {
                    @Override
                    public void run() {
                        new StartFrame();
                        System.out.println(Thread.currentThread().getName());
                    }
                };
                newSession.run();
                System.out.println("This Thread is " + Thread.currentThread().getName());
            });
            // Метод для открытия второго окна
        });
        //SwingUtilities.invokeLater(StartFrame::new);
        jFrame.add(panel);
        jFrame.add(button);
        jFrame.setVisible(true);
    }
}