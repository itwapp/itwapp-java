package io.itwapp.rest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ning.http.client.*;
import io.itwapp.Itwapp;
import io.itwapp.exception.*;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public abstract class ApiRequest {

    /**
     *
     * @param action the action to call
     * @return the string result of request
     * @throws UnauthorizedException
     * @throws InvalidRequestError
     * @throws ResourceNotFoundException
     * @throws ServiceException
     * @throws APIException
     */
    public static String get(String action) throws UnauthorizedException, InvalidRequestError, ResourceNotFoundException, ServiceException, APIException    {

        String signedRequest = sign("GET", action);
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();

        try {
            Future<Response> f = asyncHttpClient.prepareGet(Itwapp.getApiBase() + signedRequest).execute();
            Response r = f.get();
            return parseResult(r);
        } catch (InterruptedException e) {
            throw new APIException(e);
        } catch (ExecutionException e) {
            throw new APIException(e);
        } catch (IOException e) {
            throw new APIException(e);
        }
    }

    /**
     *
     * @param action the action to call
     * @param body the HTTP body to pass with the request
     * @return the string result of request
     * @throws UnauthorizedException
     * @throws InvalidRequestError
     * @throws ResourceNotFoundException
     * @throws ServiceException
     * @throws APIException
     */
    public static String post(String action, Map<String, Object> body) throws UnauthorizedException, InvalidRequestError, ResourceNotFoundException, ServiceException, APIException {

        String signedRequest = sign("POST", action);
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();

        Gson gson = new GsonBuilder().create();

        String data = gson.toJson(body);

        try {
            Future<Response> f = asyncHttpClient.preparePost(Itwapp.getApiBase() + signedRequest)
                    .setHeader("Content-Type", "application/json")
                    .setBody(data)
                    .execute();
            Response r = f.get();
            return parseResult(r);
        } catch (InterruptedException e) {
            throw new APIException(e);
        } catch (ExecutionException e) {
            throw new APIException(e);
        } catch (IOException e) {
            throw new APIException(e);
        }
    }

    /**
     *
     * @param action the action to call
     * @param body the HTTP body to pass with the request
     * @return the string result of request
     * @throws UnauthorizedException
     * @throws InvalidRequestError
     * @throws ResourceNotFoundException
     * @throws ServiceException
     * @throws APIException
     */
    public static String put(String action, Map<String, Object> body) throws UnauthorizedException, InvalidRequestError, ResourceNotFoundException, ServiceException, APIException {

        String signedRequest = sign("PUT", action);
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();

        Gson gson = new GsonBuilder().create();

        String data = gson.toJson(body);

        try {
            Future<Response> f = asyncHttpClient.preparePut(Itwapp.getApiBase() + signedRequest)
                    .setHeader("Content-Type", "application/json")
                    .setBody(data)
                    .execute();
            Response r = f.get();
            return parseResult(r);
        } catch (InterruptedException e) {
            throw new APIException(e);
        } catch (ExecutionException e) {
            throw new APIException(e);
        } catch (IOException e) {
            throw new APIException(e);
        }

    }

    /**
     *
     * @param action the action to call
     * @return the string result of request
     * @throws UnauthorizedException
     * @throws InvalidRequestError
     * @throws ResourceNotFoundException
     * @throws ServiceException
     * @throws APIException
     */
    public static String delete(String action) throws UnauthorizedException, InvalidRequestError, ResourceNotFoundException, ServiceException, APIException {
        String signedRequest = sign("DELETE", action);
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();

        try {
            Future<Response> f = asyncHttpClient.prepareDelete(Itwapp.getApiBase() + signedRequest).execute();
            Response r = f.get();
            return parseResult(r);
        } catch (InterruptedException e) {
            throw new APIException(e);
        } catch (ExecutionException e) {
            throw new APIException(e);
        } catch (IOException e) {
            throw new APIException(e);
        }
    }

    /**
     *
     * @param response the ning’s HTTP response
     * @return the body of the request
     * @throws UnauthorizedException
     * @throws InvalidRequestError
     * @throws ResourceNotFoundException
     * @throws ServiceException
     */
    private static String parseResult(Response response) throws UnauthorizedException, InvalidRequestError, ResourceNotFoundException, ServiceException, APIException{
        switch (response.getStatusCode())   {
            case 200:
                try {
                    return response.getResponseBody();
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
    }

    /**
     *
     * @param mode the HTTP request mode. GET | POST | PUT | DELETE
     * @param action the action to call
     * @return the signed request
     * @throws APIException if there is an internal exception.
     */
    private static String sign(String mode, String action) throws APIException {
        StringBuilder url = new StringBuilder();
        url.append(action);
        if(action.contains("?"))   {
            url.append("&apiKey=");
        }else   {
            url.append("?apiKey=");
        }
        url.append(Itwapp.apiKey);

        url.append("&timestamp=");
        url.append(System.currentTimeMillis());

        try {
            String signature = Sign.encode(mode + ":" + url.toString(), Itwapp.secretKey);
            url.append("&signature=");
            url.append(signature);

            return url.toString();
        } catch (UnsupportedEncodingException e) {
            throw new APIException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new APIException(e);
        } catch (InvalidKeyException e) {
            throw new APIException(e);
        }
    }

}
