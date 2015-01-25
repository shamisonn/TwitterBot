package com.shamison.GUI;

import com.shamison.TwitterUtils.TwitterUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Tweetするための画面についてのクラス
 */
public class MainWindow implements ActionListener {
	private JFrame jFrame;
	private JPanel jPanel;
	private JButton jButton;
	private JTextArea jTextArea;

	private TwitterUtils tu;

	/**
	 *
	 * @param title
	 * GUIWindowのタイトルを引数にとる.
	 */
	public MainWindow(String title) {
		// Twitterへのやり取りをするクラスをインスタンス化.
		tu = new TwitterUtils();

		jFrame = new JFrame(title);
		jPanel = new JPanel();
		jTextArea = new JTextArea();
		jTextArea.setPreferredSize(new Dimension(200, 100));
		jButton = new JButton("SEND");

		jButton.addActionListener(this);

		jFrame.setBounds(200, 200, 400, 160);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// text areaの文字列が140文字以下だったらツイート
		if (e.getSource() == jButton
				&& jTextArea.getText().length() < 141) {
			// ツイートする.
			tu.tweet(jTextArea.getText());
		}
	}

	/**
	 * GUIWindowを開く
	 */
	public void open() {
		jPanel.add(jTextArea);
		jPanel.add(jButton);
		jFrame.add(jPanel);
		jFrame.pack();
		jFrame.setVisible(true);
	}
}
