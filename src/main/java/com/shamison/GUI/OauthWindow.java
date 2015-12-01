package com.shamison.GUI;

import org.apache.commons.lang.math.NumberUtils;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * OAuth認証のためにのみ使われる画面についてのクラス.
 */
public class OauthWindow implements Runnable {
    private JFrame jFrame;
    private JLabel jLabel;
    private JPanel jPanel;
    private JButton oauthButton;
    private JButton pinButton;
    private JTextField jTextField;

    private String pin;

    public OauthWindow(String title, String oauthUrl) {
        jFrame = new JFrame(title);
        jPanel = new JPanel();
        jLabel = new JLabel();
        setLabel(oauthUrl);        //jLabelのsetterにURLの文字列を渡す.
        oauthButton = new JButton("OPEN");
        pinButton = new JButton("PUSH");
        jTextField = new JTextField("Input_PIN_Number_Here");

        oauthButton.addActionListener(ev -> {
            Desktop desktop = Desktop.getDesktop();
            try {
                URI uri = new URI(jLabel.getText());
                // 既定のブラウザを開く.
                desktop.browse(uri);
            } catch (URISyntaxException | IOException e) {
                e.printStackTrace();
            }
            // PIN入力画面に切り替える.
            openPinInputWindow();
        });

        pinButton.addActionListener(ev -> {
            if (NumberUtils.isNumber(jTextField.getText())) {
                // pinコードを入力
                setPin(jTextField.getText());
                jFrame.setVisible(false);
                jFrame.removeAll();
                // waitを解除
                unlock();
            }
        });

        jFrame.setBounds(200, 200, 400, 160);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // synchronized されたメソッドでnotifyしてあげる
    synchronized private void unlock() {
        notifyAll();
    }

    // thread.startで呼ばれたやつ
    @Override
    synchronized public void run() {
        this.openUrlWindow();
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //OAuthURIとブラウザをOPENするボタン画面を開く.
    public void openUrlWindow() {
        jPanel.add(oauthButton);
        jFrame.add(jPanel);
        jFrame.pack();
        jFrame.setVisible(true);
    }

    // pin入力画面を開く
    private void openPinInputWindow() {
        jFrame.setVisible(false);
        jPanel.removeAll();
        jPanel.add(jTextField);
        jPanel.add(pinButton);
        jFrame.add(jPanel);
        jFrame.setVisible(true);
    }

    // PINのgetter
    public String getPin() {
        System.out.println(pin);
        return pin;
    }

    // PINのsetter
    private void setPin(String pin) {
        this.pin = pin;
    }

    //LabelのSetter
    private void setLabel(String labelName) {
        jLabel.setText(labelName);
        jPanel.add(jLabel);
    }

}
