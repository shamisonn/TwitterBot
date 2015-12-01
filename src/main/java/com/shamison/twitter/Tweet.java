package com.shamison.twitter;

import twitter4j.Twitter;
import twitter4j.TwitterException;

public class Tweet {
    private Twitter twitter;

    public Tweet() {
        // OAuth認証をするクラスをインスタンス化
        OAuth oAuth = new OAuth();
        twitter = oAuth.getTwitter();
    }

    public void tw(String tw) {
        try {
            twitter.updateStatus(tw);
        } catch (TwitterException e) {
            e.printStackTrace();
        }
    }
}
