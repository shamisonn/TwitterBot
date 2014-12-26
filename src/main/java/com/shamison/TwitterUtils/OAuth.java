package com.shamison.TwitterUtils;

import com.shamison.GUI.OauthWindow;
import com.shamison.config.Config;
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
	private Config config;
	private AccessToken accessToken;
	private RequestToken requestToken;

	public OAuth() {
		config = new Config();
		// twitterクラスを作成
		twitter = TwitterFactory.getSingleton();
		// configファイルに設定が記述済みの際は設定する
		if (config.getAccessToken().length() > 0) {
			accessToken = new AccessToken(config.getAccessToken(), config.getAccessTokenSecret());
			twitter.setOAuthAccessToken(accessToken);
		}
	}

	public Twitter getTwitter() {
		return twitter;
	}

	public boolean isOAuthNull() {
		// accessTokenがnullだったら,trueを返す.
		return null == accessToken;
	}

	// OAuth認証を開始する.
	public void start() {
		try {
			// RequestTokenを取得
			requestToken = twitter.getOAuthRequestToken();
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		// pinコード取得用のURIを取得
		reqUrl = requestToken.getAuthorizationURL();

		//Pinコード取得のために専用のGUIを呼ぶ
		OauthWindow oauthWindow = new OauthWindow("OAuth画面");
		synchronized (oauthWindow) {
			try {
				oauthWindow.setLabel(reqUrl);
				oauthWindow.start();
				// OAuthの処理をwaitする.
				oauthWindow.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		createTwitter(oauthWindow.getPin());

	}

	private void createTwitter(String pin){
		try {
			accessToken = twitter.getOAuthAccessToken(requestToken, pin);
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}
}
