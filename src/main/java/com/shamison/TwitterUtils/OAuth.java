package com.shamison.TwitterUtils;

import com.shamison.config.OAuthConfig;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

/**
 * Created by shamison on 14/12/15.
 */

public class OAuth {
	private String reqUrl;
	private Twitter twitter;
	private String pin;
	private OAuthConfig OAuthConfig;
	private AccessToken accessToken;

	public OAuth(){
		OAuthConfig = new OAuthConfig();
		twitter = TwitterFactory.getSingleton();
		accessToken = new AccessToken(OAuthConfig.getAccessToken(), OAuthConfig.getAccessTokenSecret());
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
		RequestToken requestToken = null;
		try {
			requestToken = twitter.getOAuthRequestToken();
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		reqUrl = requestToken.getAuthorizationURL();

	}
}
