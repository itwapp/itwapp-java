package io.itwapp.rest;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.Response;

import io.itwapp.Itwapp;
import io.itwapp.exception.InvalidRequestError;
import io.itwapp.exception.ResourceNotFoundException;
import io.itwapp.exception.UnauthorizedException;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static org.junit.Assert.*;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ApiRequestTest {

    private static String interviewId = null;

    @BeforeClass
    public static void setUpBeforeClass()  {
        Itwapp.apiKey = System.getenv("itwappApiKey");
        Itwapp.secretKey = System.getenv("itwappApiSecret");
    }

    @Test
    public void a_testSignRequestWithoutQueryStringParam()   {
        Method m = null;
        try {
            m = ApiRequest.class.getDeclaredMethod("sign", String.class, String.class);
        } catch (NoSuchMethodException e) {
            fail();
        }

        m.setAccessible(true);

        String result = null;
        try {
            result = (String) m.invoke(null, "GET", "/api/v1/test/");
        } catch (IllegalAccessException e) {
            fail();
        } catch (InvocationTargetException e) {
            fail();
        }

        assertTrue(result.matches("/api/v1/test/\\?apiKey=.*&timestamp=.*&signature=.*"));
    }

    @Test
    public void b_testSignRequestWithQueryStringParam()   {
        Method m = null;
        try {
            m = ApiRequest.class.getDeclaredMethod("sign", String.class, String.class);
        } catch (NoSuchMethodException e) {
            fail();
        }

        m.setAccessible(true);

        String result = null;
        try {
            result = (String) m.invoke(null, "GET", "/api/v1/test/?foo=bar");
        } catch (IllegalAccessException e) {
            fail();
        } catch (InvocationTargetException e) {
            fail();
        }

        assertTrue(result.matches("/api/v1/test/\\?foo=bar&apiKey=.*&timestamp=.*&signature=.*"));
    }

    @Test(expected = UnauthorizedException.class)
    public void c_testParseResultWithUnauthorizedException() throws Throwable {

        Method m = null;
        try {
            m = ApiRequest.class.getDeclaredMethod("parseResult", Response.class);
        } catch (NoSuchMethodException e) {
            fail();
        }
        m.setAccessible(true);

        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        Response r = null;
        try {
            Future<Response> f = asyncHttpClient.prepareGet(Itwapp.getApiBase() + "/api/v1/applicant/12").execute();
            r = f.get();
        } catch (InterruptedException e) {
            fail();
        } catch (ExecutionException e) {
            fail();
        } catch (IOException e) {
            fail();
        }

        // Service should respond not authorized
        assertEquals(401, r.getStatusCode());

        try {
            m.invoke(null, r);
            fail();
        } catch (IllegalAccessException e) {
            fail();
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }

    }

    @Test(expected = ResourceNotFoundException.class)
    public void c_testParseResultWithNotFoundException() throws Throwable {
        Method m = null;
        try {
            m = ApiRequest.class.getDeclaredMethod("parseResult", Response.class);
        } catch (NoSuchMethodException e) {
            fail();
        }
        m.setAccessible(true);

        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        Response r = null;
        try {
            Future<Response> f = asyncHttpClient.prepareGet(Itwapp.getApiBase() + "/api/v1/not_found_page").execute();
            r = f.get();
        } catch (InterruptedException e) {
            fail();
        } catch (ExecutionException e) {
            fail();
        } catch (IOException e) {
            fail();
        }

        // Service should respond not authorized
        assertEquals(404, r.getStatusCode());

        try {
            m.invoke(null, r);
            fail();
        } catch (IllegalAccessException e) {
            fail();
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
    }

    @Test(expected = InvalidRequestError.class)
    public void c_testParseResultWithBadRequestException() throws Throwable {

        Method m = null;
        try {
            m = ApiRequest.class.getDeclaredMethod("sign", String.class, String.class);
        } catch (NoSuchMethodException e) {
            fail();
        }

        m.setAccessible(true);

        String result = null;
        try {
            result = (String) m.invoke(null, "POST", "/api/v1/applicant/");
        } catch (IllegalAccessException e) {
            fail();
        } catch (InvocationTargetException e) {
            fail();
        }

        assertTrue(result.matches("/api/v1/applicant/\\?apiKey=.*&timestamp=.*&signature=.*"));

        m = null;
        try {
            m = ApiRequest.class.getDeclaredMethod("parseResult", Response.class);
        } catch (NoSuchMethodException e) {
            fail();
        }
        m.setAccessible(true);

        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        Response r = null;
        try {
            Future<Response> f = asyncHttpClient.preparePost(Itwapp.getApiBase() + result).execute();
            r = f.get();
        } catch (InterruptedException e) {
            fail();
        } catch (ExecutionException e) {
            fail();
        } catch (IOException e) {
            fail();
        }

        // Service should respond not authorized
        assertEquals(400, r.getStatusCode());

        try {
            m.invoke(null, r);
            fail();
        } catch (IllegalAccessException e) {
            fail();
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
    }

    @Test
    public void d_testParseResultWithNormalResult()   {

        Method m = null;
        try {
            m = ApiRequest.class.getDeclaredMethod("sign", String.class, String.class);
        } catch (NoSuchMethodException e) {
            fail();
        }

        m.setAccessible(true);

        String result = null;
        try {
            result = (String) m.invoke(null, "GET", "/api/v1/interview/");
        } catch (IllegalAccessException e) {
            fail();
        } catch (InvocationTargetException e) {
            fail();
        }

        assertTrue(result.matches("/api/v1/interview/\\?apiKey=.*&timestamp=.*&signature=.*"));

        m = null;
        try {
            m = ApiRequest.class.getDeclaredMethod("parseResult", Response.class);
        } catch (NoSuchMethodException e) {
            fail();
        }
        m.setAccessible(true);

        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        Response r = null;
        try {
            Future<Response> f = asyncHttpClient.prepareGet(Itwapp.getApiBase() + result).execute();
            r = f.get();
        } catch (InterruptedException e) {
            fail();
        } catch (ExecutionException e) {
            fail();
        } catch (IOException e) {
            fail();
        }

        // Service should respond not authorized
        assertEquals(200, r.getStatusCode());

        try {
            String json = (String) m.invoke(null, r);
            JsonElement element = new JsonParser().parse(json);
            assertTrue(element.isJsonArray());

        } catch (IllegalAccessException e) {
            fail();
        } catch (InvocationTargetException e) {
            fail();
        } catch (JsonSyntaxException jse) {
            fail();
        }

    }

    @Test
    public void e_testGetRequest()    {
        String res = ApiRequest.get("/api/v1/interview/");

        JsonElement element = new JsonParser().parse(res);
        assertTrue(element.isJsonArray());
    }

    @Test
    public void f_testPostRequest()    {

        Map<String, Object> param = new HashMap<String, Object>();

        param.put("name", "interview 1");
        param.put("video", "");
        param.put("text", "");

        Map<String, Object> question = new HashMap<String, Object>();

        question.put("content", "question 1");
        question.put("readingTime", 60);
        question.put("answerTime", 60);
        question.put("number", 1);

        List<Map<String, Object>> questions = new ArrayList<Map<String, Object>>();
        questions.add(question);

        param.put("questions", questions);


        String res = ApiRequest.post("/api/v1/interview/", param);

        JsonElement element = new JsonParser().parse(res);
        assertTrue(element.isJsonObject());

        assertTrue(element.getAsJsonObject().has("_id"));

        ApiRequestTest.interviewId = element.getAsJsonObject().get("_id").getAsString();
    }

    @Test
    public void g_testPutRequest()    {
        assertNotNull(ApiRequestTest.interviewId);

        Map<String, Object> param = new HashMap<String, Object>();

        param.put("name", "interview 1");
        param.put("video", "");
        param.put("text", "");

        Map<String, Object> question = new HashMap<String, Object>();

        question.put("content", "question 1 - Updated");
        question.put("readingTime", 60);
        question.put("answerTime", 60);
        question.put("number", 1);

        List<Map<String, Object>> questions = new ArrayList<Map<String, Object>>();
        questions.add(question);

        param.put("questions", questions);

        String res = ApiRequest.put("/api/v1/interview/" + ApiRequestTest.interviewId, param);

        JsonElement element = new JsonParser().parse(res);
        assertTrue(element.isJsonObject());
    }

    @Test
    public void h_testDeleteRequest()    {
        assertNotNull(ApiRequestTest.interviewId);

        String res = ApiRequest.delete("/api/v1/interview/" + ApiRequestTest.interviewId);
        assertEquals("", res);
    }

}
