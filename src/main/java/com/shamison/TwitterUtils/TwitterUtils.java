package com.shamison.TwitterUtils;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

/**
 * Tweetをするためのクラス.
 * twitterオブジェクトを扱うのでクラス名称を
 * 「TwitterUtils」としている.
 */

public class TwitterUtils {
	private OAuth oAuth;
	private Twitter twitter;

	public TwitterUtils() {
		// OAuth認証をするクラスをインスタンス化
		this.oAuth = new OAuth();
		// OAuth認証されたtwitterオブジェクトをgetterで持ってくる.
		twitter = oAuth.getTwitter();
	}

	public void tweet(String tw) {
		try {
			// Tweet処理
			Status status = twitter.updateStatus(tw);
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}

}
