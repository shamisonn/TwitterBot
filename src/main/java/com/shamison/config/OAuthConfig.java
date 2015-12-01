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

    public OAuthConfig() {
        properties = new Properties();

        File f = new File("twitter4j.properties");
        if (f.exists()) {
            try {
                properties.load(new FileInputStream(f));
                // 各種キーを取得.
                accessTokenSecret = properties.getProperty("accessTokenSecret");
                accessToken = properties.getProperty("accessToken");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            File newFile = new File("twitter4j.properties");
            try {
                PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(newFile)));
                pw.println("accessToken=");
                pw.println("accessTokenSecret=");
                pw.flush();
                pw.close();
                properties.load(new FileInputStream(newFile));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // 認証の永続化のため2つのキーを設定し,保存する.
    public void setTokens(String token, String tokenSecret) {
        properties.setProperty("accessToken", token);
        properties.setProperty("accessTokenSecret", tokenSecret);
        try {
            properties.store(new FileOutputStream("twitter4j.properties"), "");
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        return accessToken.isEmpty() || accessToken.isEmpty();
    }
}