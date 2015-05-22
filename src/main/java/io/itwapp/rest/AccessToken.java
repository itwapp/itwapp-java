package io.itwapp.rest;

public class AccessToken {

    private String apiKey;
    private String secretKey;

    public AccessToken(String apiKey, String secretKey) {
        this.apiKey = apiKey;
        this.secretKey = secretKey;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getSecretKey() {
        return secretKey;
    }
}
