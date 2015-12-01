package com.shamison;

import com.shamison.twitter.Tweet;

import javax.swing.*;
import java.awt.*;

/**
 * Mainクラスのみがあるクラス.
 */
public class Main {
    public static void main(String[] args) {
        JButton jButton;
        JTextArea jTextArea;

        // Twitterへのやり取りをするクラスをインスタンス化.
        Tweet tweet = new Tweet();

        JFrame jFrame = new JFrame("Main Window");
        JPanel jPanel = new JPanel();
        jTextArea = new JTextArea();
        jButton = new JButton("SEND");

        jTextArea.setPreferredSize(new Dimension(200, 100));

        jButton.addActionListener(e -> {
            if (jTextArea.getText().length() < 141) {
                tweet.tw(jTextArea.getText());
                jTextArea.setText("");
            }
        });

        jFrame.setBounds(200, 200, 400, 160);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jPanel.add(jTextArea);
        jPanel.add(jButton);

        jFrame.add(jPanel);
        jFrame.pack();
        jFrame.setVisible(true);
    }
}
