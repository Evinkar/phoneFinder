package ru.front.component;

import io.micrometer.common.lang.Nullable;

import javax.swing.*;

public class BackButton extends JButton {

    public BackButton(JFrame currentFrame, JFrame previousFrame, @Nullable String text,  @Nullable Icon icon) {
        init(text, icon);
        setModel(new DefaultButtonModel());
        addActionListener(e -> {
            previousFrame.setVisible(true);
            currentFrame.dispose();
        });
    }
}
