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
 * OAuth認証のためにのみ使われる画面についてのクラス.
 */
public class OauthWindow extends Thread implements ActionListener {
	private JFrame jFrame;
	private JLabel jLabel;
	private JPanel jPanel;
	private JButton oauthButton;
	private JButton pinButton;
	private JTextField jTextField;

	private String pin;

	/**
	 *
	 * @param title
	 * @param oauthUrl
	 * コンストラクタ.
	 * 引数にはwindowのタイトルとOAuth認証の際にアクセスするURLがある.
	 */
	public OauthWindow(String title, String oauthUrl) {
		jFrame = new JFrame(title);
		jPanel = new JPanel();
		jLabel = new JLabel();
		setLabel(oauthUrl);		//jLabelのsetterにURLの文字列を渡す.
		oauthButton = new JButton("OPEN");
		pinButton = new JButton("PUSH");
		jTextField = new JTextField("Input_PIN_Number_Here");

		oauthButton.addActionListener(this);
		pinButton.addActionListener(this);

		jFrame.setBounds(200, 200, 400, 160);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	//同期処理を行い,OAuth認証の際のPINコードを入力されるまで待つ.
	@Override
	synchronized public void run() {
		open();		//windowを開く
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

	//OAuthURIとブラウザをOPENするボタン画面を開く.
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
				// 既定のブラウザを開く.
				desktop.browse(uri);
			} catch (URISyntaxException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			// PIN入力画面に切り替える.
			this.reOpen();
		}

		// PINを入力する.その際,入力されている文字列が数字であるか判定する.
		if (actionEvent.getSource() == pinButton
				&& NumberUtils.isNumber(jTextField.getText())) {
			// pinコードを入力
			setPin(jTextField.getText());
			jFrame.setVisible(false);
			jFrame.removeAll();
			// waitを解除
			notifyAll();
		}
	}

	// PINのsetter
	private void setPin(String pin) {
		this.pin = pin;
	}

	// PINのgetter
	public String getPin() {
		return pin;
	}
}
