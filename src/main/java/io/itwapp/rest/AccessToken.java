package io.itwapp.rest;

public class AccessToken {

    private String company;
    private String apiKey;
    private String secretKey;

    public AccessToken(String company, String apiKey, String secretKey) {
        this.company = company;
        this.apiKey = apiKey;
        this.secretKey = secretKey;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public String getCompany() {
        return company;
    }
}
