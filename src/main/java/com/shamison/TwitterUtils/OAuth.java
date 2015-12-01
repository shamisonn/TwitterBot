package com.shamison.TwitterUtils;

import com.shamison.GUI.OauthWindow;
import com.shamison.config.OAuthConfig;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;

public class OAuth {
    private Twitter twitter;
    private OAuthConfig oAuthConfig;

    public OAuth() {
        // 認証キーをoauth_config.propertiesから取ってくるクラスをインスタンス化
        oAuthConfig = new OAuthConfig();
        // キーを設定.
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("DyAjpRmoeKpmPrnYssu8DxXl2")
                .setOAuthConsumerSecret("uE9giYbJF3UII4595YBdsXt0nFO776KApaVMKJMGVEzI9WerWa")
                .setOAuthAccessToken(oAuthConfig.getAccessToken())
                .setOAuthAccessTokenSecret(oAuthConfig.getAccessTokenSecret());
        // 認証済みのtwitterオブジェクトのインスタンス化
        TwitterFactory tf = new TwitterFactory(cb.build());
        twitter = tf.getInstance();
    }

    public void getKeys() {
        try {
            // キー取得用のURLを取得する.
            RequestToken requestToken = twitter.getOAuthRequestToken();

            // 認証用のGUIのオブジェクトをインスタンス化
            OauthWindow oauthWindow = new OauthWindow("認証画面", requestToken.getAuthorizationURL());

            // GUIを開く
            Thread t = new Thread(oauthWindow);
            t.start();
            // PINを取得したらここに戻ってくる.
            t.join();

            // accessTokenを設定.
            AccessToken accessToken = twitter.getOAuthAccessToken(requestToken, oauthWindow.getPin());
            // accessTokenをセットし,認証を終える.
            twitter.setOAuthAccessToken(accessToken);
            // 認証の永続化のためにoauth_config.propertiesにキーを保存.
            oAuthConfig.setTokens(accessToken.getToken(), accessToken.getTokenSecret());


        } catch (TwitterException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Twitter getTwitter() {
        if (oAuthConfig.isTokenEmpty())
            this.getKeys();
        return twitter;
    }
}
