package com.shamison.TwitterUtils;

import com.shamison.GUI.OauthWindow;
import com.shamison.config.OAuthConfig;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Created by shamison on 14/12/15.
 */

public class OAuth {
	private String reqUrl;
	private Twitter twitter;
	private OAuthConfig oAuthConfig;
	private AccessToken accessToken;

	public OAuth() {
		oAuthConfig = new OAuthConfig();
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
				.setOAuthConsumerKey(oAuthConfig.getConsumerKey())
				.setOAuthConsumerSecret(oAuthConfig.getConsumerSecret())
				.setOAuthAccessToken(oAuthConfig.getAccessToken())
				.setOAuthAccessTokenSecret(oAuthConfig.getAccessTokenSecret());
		TwitterFactory tf = new TwitterFactory(cb.build());
		twitter = tf.getInstance();

		if (oAuthConfig.isTokenEmpty()) {
			oauthStart();
		} else {
			accessToken = new AccessToken(oAuthConfig.getAccessToken(), oAuthConfig.getAccessTokenSecret());
			twitter.setOAuthAccessToken(accessToken);
		}

	}

	public boolean isOAuthNull() {
		// accessTokenがnullだったら,falseを返す.
		return null != accessToken;
	}

	// OAuthを開始する.
	public void oauthStart() {
		RequestToken requestToken = null;
		try {
			requestToken = twitter.getOAuthRequestToken();
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		reqUrl = requestToken.getAuthorizationURL();

		OauthWindow oauthWindow = new OauthWindow("認証画面", reqUrl);
		oauthWindow.start();
		try {
			oauthWindow.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			accessToken = twitter.getOAuthAccessToken(requestToken, oauthWindow.getPin());
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		twitter.setOAuthAccessToken(accessToken);
		oAuthConfig.setTokens(accessToken.getToken(), accessToken.getTokenSecret());
	}

	public Twitter getTwitter() {
		return twitter;
	}
}
