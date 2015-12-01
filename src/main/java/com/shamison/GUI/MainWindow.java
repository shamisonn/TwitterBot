package com.shamison.GUI;

import com.shamison.TwitterUtils.TwitterUtils;

import javax.swing.*;
import java.awt.*;

/**
 * Tweetするための画面についてのクラス
 */
public class MainWindow {
    private JFrame jFrame;

    public MainWindow(String title) {
        JButton jButton;
        JTextArea jTextArea;

        // Twitterへのやり取りをするクラスをインスタンス化.
        TwitterUtils tu = new TwitterUtils();

        jFrame = new JFrame(title);
        JPanel jPanel = new JPanel();
        jTextArea = new JTextArea();
        jButton = new JButton("SEND");

        jTextArea.setPreferredSize(new Dimension(200, 100));

        jButton.addActionListener(e -> {
            if (jTextArea.getText().length() < 141)
                tu.tweet(jTextArea.getText());
        });

        jFrame.setBounds(200, 200, 400, 160);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jPanel.add(jTextArea);
        jPanel.add(jButton);

        jFrame.add(jPanel);
        jFrame.pack();
    }

    /**
     * GUIWindowを開く
     */
    public void open() {
        jFrame.setVisible(true);
    }
}
