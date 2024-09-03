package ru.front.component;

import jakarta.annotation.Nullable;

import javax.swing.*;

public class BackButton extends JButton {

    public BackButton(JFrame currentFrame, JFrame previousFrame, @Nullable String text, @Nullable Icon icon) {
        init(text, icon);
        setModel(new DefaultButtonModel());
        addActionListener(e -> {
            previousFrame.setVisible(true);
            currentFrame.dispose();
        });
    }
}
