package com.shamison.TwitterUtils;

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
	private AccessToken accessToken;

	public OAuth(){
		config = new Config();
		twitter = TwitterFactory.getSingleton();
		accessToken = new AccessToken(config.getAccessToken(),config.getAccessTokenSecret());
		twitter.setOAuthAccessToken(accessToken);
	}

	public Twitter getTwitter() {
		return twitter;
	}

	public boolean isOAuth() {
		// accessTokenがnullだったら,falseを返す.
		return null != accessToken;
	}

	// OAuthを開始する.
	public void start() {

	}
}
