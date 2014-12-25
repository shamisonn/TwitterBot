package com.shamison;

import com.shamison.TwitterUtils.OAuth;
import com.shamison.TwitterUtils.TwitterUtils;

/**
 * Created by shamison on 14/12/15.
 */

public class Main {
	private static OAuth oAuth;
	private static TwitterUtils tu;
	public static void main(String[] args) {
		tu = new TwitterUtils();

	}

	public static Main getInstance(){
		return new Main();
	}
}
