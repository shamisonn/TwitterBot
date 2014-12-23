package com.shamison;

import com.shamison.config.Config;

/**
 * Created by shamison on 14/12/15.
 */

public class Main {
	public static void main(String[] args) {
		Config config = new Config();
		config.setTokens("cccc","dddd");
		System.out.println(config.getAccessToken());
		System.out.println(config.getAccessTokenSecret());
	}

	public static Main getInstance(){
		return new Main();
	}
}
