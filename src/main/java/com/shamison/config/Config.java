package com.shamison.config;


import java.io.*;
import java.net.URL;
import java.util.Properties;

import com.shamison.Main;

/**
 * Created by shamison on 14/12/15.
 */
public class Config {
	private Main main;

	private Properties properties;

	private String accessToken;
	private String accessTokenSecret;

	public Config() {
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
			properties.store(new FileOutputStream(new File(this.getFilePath())), "config.properties");
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
		return Main.getInstance().getClass().getResource("/config.properties").toString().replaceFirst("file:", "");
	}

}