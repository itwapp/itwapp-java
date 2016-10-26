package io.itwapp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.Response;
import io.itwapp.exception.*;
import io.itwapp.rest.AccessToken;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public abstract class Itwapp {

    public static final String LIVE_API_BASE = "https://itwapp.io";
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

    /**
     * Init api key and secret key with mail and password
     * @param mail
     * @param password
     * @throws UnauthorizedException when auth fails
     */
    public static AccessToken[] Authenticate(String mail, String password)   {
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();

        Map<String, Object> body = new HashMap<String, Object>();
        body.put("mail", mail);
        body.put("password", password);

        Gson gson = new GsonBuilder().create();
        String data = gson.toJson(body);

        try {
            Future<Response> f = asyncHttpClient.preparePost(Itwapp.getApiBase() + "/api/v1/auth/")
                    .setHeader("Content-Type", "application/json")
                    .setBody(data)
                    .execute();
            Response response = f.get();
           switch (response.getStatusCode())   {
                case 200:
                    try {
                        return gson.fromJson(response.getResponseBody(), AccessToken[].class);
                    } catch (IOException e) {
                        throw new APIException(e);
                    }
                case 401:
                    throw new UnauthorizedException();
                case 400:
                    throw new InvalidRequestError();
                case 404:
                    throw new ResourceNotFoundException();
                case 500:
                case 503:
                default:
                    throw new ServiceException();
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new APIException(e);
        }
    }

}
