package com.shamison.config;

import com.shamison.Main;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by shamison on 15/01/24.
 */
public class OAuthConsumerConfig {
	private Main main;

	private Properties properties;

	private String consumerKey;
	private String consumerSecret;

	public OAuthConsumerConfig() {
		properties = new Properties();
		try {
			properties.load(new FileInputStream(new File(this.getFilePath())));
		} catch (IOException e) {
			e.printStackTrace();
		}
		consumerSecret = properties.getProperty("consumerSecret");
	}


	public String getConsumerKey() {
		consumerKey = properties.getProperty("consumerKey");
		return consumerKey;

	}

	public String getConsumerSecret() {
		consumerSecret = properties.getProperty("consumerSecret");
		return consumerSecret;
	}

	public String getFilePath() {

		return Main.getInstance().getClass().getResource("/oauth_consumer.properties").toString().replaceFirst("file:", "");
	}
}
