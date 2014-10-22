package io.itwapp;

public abstract class Itwapp {

    public static final String LIVE_API_BASE = "http://itwapp.io";
    public static volatile String apiKey;
    public static volatile String secretKey;

    private static volatile String apiBase = LIVE_API_BASE;

    /**
     * (FOR TESTING ONLY)
     * If you'd like your API requests to hit your own (mocked) server,
     * you can set this up here by overriding the base api URL.
     * @param overriddenApiBase the new base api url
     */
    public static void overrideApiBase(final String overriddenApiBase) {
        apiBase = overriddenApiBase;
    }

    public static String getApiBase() {
        return apiBase;
    }

}
