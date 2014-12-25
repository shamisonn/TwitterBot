package com.shamison;

import com.shamison.TwitterUtils.OAuth;

/**
 * Created by shamison on 14/12/15.
 */

public class Main {
	private static OAuth oAuth;
	public static void main(String[] args) {
		oAuth = new OAuth();
		if(oAuth.isOAuthNull()){
			oAuth.start();
		}

	}

	public static Main getInstance(){
		return new Main();
	}
}
