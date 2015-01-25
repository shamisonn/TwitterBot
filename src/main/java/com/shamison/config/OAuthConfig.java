package com.shamison.config;

import java.io.*;
import java.util.Properties;

/**
 * OAuth認証のための4つのキーを保存,ロードするためのクラス.
 */

public class OAuthConfig {
	private Properties properties;

	private String accessToken;
	private String accessTokenSecret;
	private String consumerKey;
	private String consumerSecret;
	private File f;

	public OAuthConfig() {
		properties = new Properties();
		try {
			// oauth_config.propertiesをロード
			f = new File("oauth_config.properties");
			properties.load(new FileInputStream(f));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 各種キーを取得.
		accessTokenSecret = properties.getProperty("accessTokenSecret");
		accessToken = properties.getProperty("accessToken");
		consumerKey = properties.getProperty("consumerKey");
		consumerSecret = properties.getProperty("consumerSecret");

	}

	// 認証の永続化のため2つのキーを設定し,保存する.
	public void setTokens(String token, String tokenSecret) {
		properties.setProperty("accessToken", token);
		properties.setProperty("accessTokenSecret", tokenSecret);
		try {
			properties.store(new FileOutputStream("oauth_config.properties"), "Comments");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// ConsumerKeyのgetter
	public String getConsumerKey() {
		consumerKey = properties.getProperty("consumerKey");
		return consumerKey;
	}

	// ConsumerSecretのgetter
	public String getConsumerSecret() {
		consumerSecret = properties.getProperty("consumerSecret");
		return consumerSecret;
	}

	// AccessTokenのgetter
	public String getAccessToken() {
		accessToken = properties.getProperty("accessToken");
		if (accessToken.isEmpty())
			return null;
		return accessToken;
	}

	//AccessTokenSecretのgetter
	public String getAccessTokenSecret() {
		accessTokenSecret = properties.getProperty("accessTokenSecret");
		if (accessTokenSecret.isEmpty())
			return null;
		return accessTokenSecret;
	}

	// 過去に認証していなければfalseを返す.
	public boolean isTokenEmpty() {
		if (accessToken.isEmpty() || accessToken.isEmpty())
			return true;
		return false;
	}
}