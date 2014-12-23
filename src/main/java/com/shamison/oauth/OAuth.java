package com.shamison.oauth;

import com.shamison.config.Config;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

/**
 * Created by shamison on 14/12/15.
 */

public class OAuth {
	private String OAuthUrl;
	private Twitter twitter;
	private String pin;
	private Config config;

	OAuth(){
		config = new Config();
		twitter = TwitterFactory.getSingleton();
		AccessToken accessToken
				= new AccessToken(config.getAccessToken(),config.getAccessTokenSecret());
		twitter.setOAuthAccessToken(accessToken);
	}
}
