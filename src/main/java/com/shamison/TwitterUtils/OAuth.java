package com.shamison.TwitterUtils;

import com.shamison.config.Config;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

import javax.swing.*;

/**
 * Created by shamison on 14/12/15.
 */

public class OAuth {
	private String reqUrl;
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

	public boolean isOAuthNull() {
		// accessTokenがnullだったら,trueを返す.
		return null == accessToken;
	}

	// OAuthを開始する.
	public void start() {
		RequestToken requestToken = null;
		try {
			requestToken = twitter.getOAuthRequestToken();
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		reqUrl = requestToken.getAuthorizationURL();

	}
}
