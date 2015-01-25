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
 * OAuth認証するためのクラス.
 */

public class OAuth {
	private String reqUrl;
	private Twitter twitter;
	private OAuthConfig oAuthConfig;
	private AccessToken accessToken;

	public OAuth() {
		// 認証キーをoauth_config.propertiesから取ってくるクラスをインスタンス化
		oAuthConfig = new OAuthConfig();
		// キーを設定.
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
				.setOAuthConsumerKey(oAuthConfig.getConsumerKey())
				.setOAuthConsumerSecret(oAuthConfig.getConsumerSecret())
				.setOAuthAccessToken(oAuthConfig.getAccessToken())
				.setOAuthAccessTokenSecret(oAuthConfig.getAccessTokenSecret());
		// 認証済みのtwitterオブジェクトのインスタンス化
		TwitterFactory tf = new TwitterFactory(cb.build());
		twitter = tf.getInstance();

		// もしaccessTokenとaccessTokenSecretが未設定ならキーを取得する.
		if (oAuthConfig.isTokenEmpty()) {
			oauthStart();
		}

	}

	// OAuth認証を開始し,キーを取得する.
	public void oauthStart() {
		RequestToken requestToken = null;
		try {
			// キー取得用のURLを取得する.
			requestToken = twitter.getOAuthRequestToken();
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		reqUrl = requestToken.getAuthorizationURL();

		// 認証用のGUIのオブジェクトをインスタンス化
		OauthWindow oauthWindow = new OauthWindow("認証画面", reqUrl);
		// GUIを開く
		oauthWindow.start();
		try {
			// PINを取得したらここに戻ってくる.
			oauthWindow.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		try {
			// accessTokenを設定.
			accessToken = twitter.getOAuthAccessToken(requestToken, oauthWindow.getPin());
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		// accessTokenをセットし,認証を終える.
		twitter.setOAuthAccessToken(accessToken);
		// 認証の永続化のためにoauth_config.propertiesにキーを保存.
		oAuthConfig.setTokens(accessToken.getToken(), accessToken.getTokenSecret());
	}

	public Twitter getTwitter() {
		return twitter;
	}
}
