package com.shamison.TwitterUtils;

import com.shamison.GUI.OauthWindow;
import com.shamison.Main;
import com.shamison.config.OAuthConfig;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Created by shamison on 14/12/15.
 */

public class OAuth{
	private String reqUrl;
	private Twitter twitter;
	private OAuthConfig OAuthConfig;
	private AccessToken accessToken;

	public OAuth(){
		OAuthConfig = new OAuthConfig();
		ConfigurationBuilder cb = new ConfigurationBuilder();
		String consumer =
				Main.getInstance().getClass().getResource("/oauth_config.properties").toString().replaceFirst("file:", "");

		cb.setDebugEnabled(true)
				.setOAuthConsumerKey("")
				.setOAuthConsumerSecret("")
				.setOAuthAccessToken(null)
				.setOAuthAccessTokenSecret(null);
		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter twitter = tf.getInstance();
		twitter = TwitterFactory.getSingleton();
		if (OAuthConfig.getAccessToken().length() < 1){
			oauthStart();
		}else {
			accessToken = new AccessToken(OAuthConfig.getAccessToken(), OAuthConfig.getAccessTokenSecret());
			twitter.setOAuthAccessToken(accessToken);
		}

	}

	public Twitter getTwitter() {
		return twitter;
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
	}
}
