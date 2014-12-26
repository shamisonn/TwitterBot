package com.shamison.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by shamison on 14/12/24.
 *
 * Swingで実装するGUI部分のコード
 */
public class OauthWindow implements ActionListener {
	private JFrame jFrame;
	private JComponent jComponent;
	private JLabel jLabel;
	private JPanel jPanel;
	private JButton oauthButton;
	private JButton pinButton;
	private JTextField jTextField;

	private String pin;

	public OauthWindow(String title){
		jFrame = new JFrame(title);
		jPanel = new JPanel();
		jLabel = new JLabel();
		oauthButton = new JButton("OPEN");
		pinButton = new JButton("PUSH");
		jTextField = new JTextField("Input PIN Number Here.");

		oauthButton.addActionListener(this);

		jFrame.setBounds(200, 200, 400, 160);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public void setLabel(String labelName){
		jLabel.setText(labelName);
		jPanel.add(jLabel);
	}

	public void open(){
		jPanel.add(oauthButton);
		jFrame.add(jPanel);
		jFrame.pack();
		jFrame.setVisible(true);
	}

	private void reOpen(){
		jFrame.setVisible(false);
		jPanel.removeAll();
		jPanel.add(jTextField);
		jPanel.add(pinButton);

		jFrame.add(jPanel);
		jFrame.setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		// Buttonが押された際,URL(OAuth認証のために)にアクセスする.
		if (actionEvent.getSource() == oauthButton){
			Desktop desktop = Desktop.getDesktop();
			try {
				URI uri = new URI(jLabel.getText());
				desktop.browse(uri);
			} catch (URISyntaxException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			this.reOpen();
		}

		if (actionEvent.getSource() == pinButton){
			setPin(jTextField.getText());
			jFrame.setVisible(false);
		}
	}

	private void setPin(String pin) {
		this.pin = pin;
	}

	public String getPin() {
		return pin;
	}
}
