package com.shamison.TwitterUtils;

import twitter4j.Twitter;
import twitter4j.TwitterException;

public class TwitterUtils {
    private Twitter twitter;

    public TwitterUtils() {
        // OAuth認証をするクラスをインスタンス化
        OAuth oAuth = new OAuth();
        twitter = oAuth.getTwitter();
    }

    public void tweet(String tw) {
        try {
            twitter.updateStatus(tw);
        } catch (TwitterException e) {
            e.printStackTrace();
        }
    }
}
