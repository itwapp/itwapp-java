package io.itwapp.rest;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.*;

public class SignTest {

    @Test
    public void testEncode()    {
        try {
            String signature = Sign.encode("", "KEY");
            assertEquals("405b4e6c5e0370034caa8d9261be1819", signature);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            fail();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            fail();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
            fail();
        }


        try {
            String signature = Sign.encode("", "a");
            assertEquals("025b69987847314c3445f5cdb5c23830", signature);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            fail();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            fail();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
            fail();
        }

    }

}
