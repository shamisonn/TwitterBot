package com.shamison.config;

import com.shamison.Main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by shamison on 15/01/24.
 */
public class OAuthConsumerConfig {
	private Main main;

	private Properties properties;

	private String accessToken;
	private String accessTokenSecret;

	public OAuthConsumerConfig() {
		properties = new Properties();
		try {
			properties.load(new FileInputStream(new File(this.getFilePath())));
		} catch (IOException e) {
			e.printStackTrace();
		}
		accessTokenSecret = properties.getProperty("accessTokenSecret");
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


	public String getAccessToken() {
		accessToken = properties.getProperty("accessToken");
		return accessToken;

	}

	public String getAccessTokenSecret() {
		accessTokenSecret = properties.getProperty("accessTokenSecret");
		return accessTokenSecret;
	}

	public String getFilePath() {

		return Main.getInstance().getClass().getResource("/oauth_config.properties").toString().replaceFirst("file:", "");
	}
}
