package com.shamison;

import com.shamison.TwitterUtils.OAuth;
import com.shamison.config.Config;

/**
 * Created by shamison on 14/12/15.
 */

public class Main {
	private static OAuth oAuth;
	private static Config config;

	public static void main(String[] args) {
		oAuth = new OAuth();
		config = new Config();
	}

	public static Main getInstance(){
		return new Main();
	}
}
