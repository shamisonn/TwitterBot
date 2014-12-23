package com.shamison.GUI;

import javax.swing.*;

/**
 * Created by shamison on 14/12/24.
 *
 * Swingで実装するGUI部分のコード
 */

public class Window {
	private JFrame jFrame;
	public Window(String title){
		jFrame = new JFrame(title);
		jFrame.setBounds(200, 200, 200, 160);
	}

	public void show(){
		jFrame.setVisible(true);
	}
}
