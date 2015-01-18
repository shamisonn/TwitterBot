package com.shamison.TwitterUtils;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

/**
 * Created by shamison on 14/12/23.
 */
public class TwitterUtils {
	private OAuth oAuth;
	private Twitter twitter;

	public TwitterUtils() {
		oAuth = new OAuth();
		if (oAuth.isOAuthNull()) {
			oAuth.start();
		}
		twitter = oAuth.getTwitter();
	}

	public void tweet(String tw){
		try {
			Status status = twitter.updateStatus(tw);
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}

}
