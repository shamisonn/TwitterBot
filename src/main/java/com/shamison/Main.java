package com.shamison;

import com.shamison.GUI.MainWindow;

/**
 * Mainクラスのみがあるクラス.
 */
public class Main {
    public static void main(String[] args) {
        // Tweetするための画面を作る.
        MainWindow mw = new MainWindow("Tweet画面");
        // 画面を開く
        mw.open();
    }
}
