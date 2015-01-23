package com.shamison.GUI;

import org.apache.commons.lang.math.NumberUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


/**
 * Created by shamison on 14/12/24.
 * <p/>
 * Swingで実装するGUI部分のコード
 */
public class OauthWindow extends Thread implements ActionListener {
	private JFrame jFrame;
	private JLabel jLabel;
	private JPanel jPanel;
	private JButton oauthButton;
	private JButton pinButton;
	private JTextField jTextField;

	private String pin;

	//初期状態の画面を作成する.
	public OauthWindow(String title, String oauthUrl) {
		jFrame = new JFrame(title);
		jPanel = new JPanel();
		jLabel = new JLabel();
		setLabel(oauthUrl);
		oauthButton = new JButton("OPEN");
		pinButton = new JButton("PUSH");
		jTextField = new JTextField("Input_PIN_Number_Here");

		oauthButton.addActionListener(this);
		pinButton.addActionListener(this);

		jFrame.setBounds(200, 200, 400, 160);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	//同期処理を行い,pinコードを入力されるまで待つ.
	@Override
	synchronized public void run() {
		open();
		try {
			// pinコードが入力されるまで処理をまつ
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	//LabelのSetter
	public void setLabel(String labelName) {
		jLabel.setText(labelName);
		jPanel.add(jLabel);
	}

	//OAuthURIとOPENボタン画面を開く
	public void open() {
		jPanel.add(oauthButton);
		jFrame.add(jPanel);
		jFrame.pack();
		jFrame.setVisible(true);
	}

	// pin入力画面を開く
	private void reOpen() {
		jFrame.setVisible(false);
		jPanel.removeAll();
		jPanel.add(jTextField);
		jPanel.add(pinButton);

		jFrame.add(jPanel);
		jFrame.setVisible(true);
	}


	@Override
	synchronized public void actionPerformed(ActionEvent actionEvent) {
		// Buttonが押された際,URL(OAuth認証のために)にアクセスする.
		if (actionEvent.getSource() == oauthButton) {
			Desktop desktop = Desktop.getDesktop();
			try {
				URI uri = new URI(jLabel.getText());
				desktop.browse(uri);
			} catch (URISyntaxException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			// pin入力画面に切り替える.
			this.reOpen();
		}

		if (actionEvent.getSource() == pinButton
				&& NumberUtils.isNumber(jTextField.getText())) {
			// pinコードを取得
			setPin(jTextField.getText());
			jFrame.setVisible(false);
			jFrame.removeAll();
			//waitを解除
			notifyAll();
		}
	}

	private void setPin(String pin) {
		this.pin = pin;
	}

	public String  getPin() {
		return pin;
	}
}
