package com.shamison.config;


import java.io.*;
import java.util.Properties;

import com.shamison.Main;

/**
 * Created by shamison on 14/12/15.
 */
public class OAuthConfig {
	private Main main;

	private Properties properties;

	private String accessToken;
	private String accessTokenSecret;
	private String consumerKey;
	private String consumerSecret;

	public OAuthConfig() {
		properties = new Properties();
		try {
			properties.load(new FileInputStream(new File(this.getFilePath())));
		} catch (IOException e) {
			e.printStackTrace();
		}
		accessTokenSecret = properties.getProperty("accessTokenSecret");
		accessToken = properties.getProperty("accessToken");
		consumerKey = properties.getProperty("consumerKey");
		consumerSecret = properties.getProperty("consumerSecret");

	}

	public void setTokens(String token, String tokenSecret) {
		properties.setProperty("accessToken", token);
		properties.setProperty("accessTokenSecret", tokenSecret);
		try {
			properties.store(new FileOutputStream(new File(this.getFilePath())), "oauth_config.properties");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getConsumerKey() {
		consumerKey = properties.getProperty("consumerKey");
		return consumerKey;
	}

	public String getConsumerSecret() {
		consumerSecret = properties.getProperty("consumerSecret");
		return consumerSecret;
	}

	public String getAccessToken() {
		accessToken = properties.getProperty("accessToken");
		if (accessToken.isEmpty())
			return null;
		return accessToken;
	}

	public String getAccessTokenSecret() {
		accessTokenSecret = properties.getProperty("accessTokenSecret");
		if (accessTokenSecret.isEmpty())
			return null;
		return accessTokenSecret;
	}

	public String getFilePath() {
		return Main.getInstance().getClass().getResource("/oauth_config.properties").toString().replaceFirst("file:", "");
	}

	public boolean isTokenEmpty() {
		if (accessToken.isEmpty() || accessToken.isEmpty())
			return true;
		return false;
	}
}