package com.shamison;

import com.shamison.TwitterUtils.OAuth;
import com.shamison.TwitterUtils.TwitterUtils;
import com.shamison.config.Config;

/**
 * Created by shamison on 14/12/15.
 */

public class Main {
	private static Config config;
	private static TwitterUtils tu;

	public static void main(String[] args) {
		config = new Config();
		tu = new TwitterUtils();
		tu.tweet("TESTマン");
	}

	public static Main getInstance() {
		return new Main();
	}
}
