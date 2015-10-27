package io.itwapp.rest;

import javax.crypto.spec.SecretKeySpec;
import javax.crypto.Mac;
import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public abstract class Sign {

    public static String encode(String toSign, String key) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {

        byte[] data_bytes = toSign.getBytes("UTF8");
        byte[] key_bytes = key.getBytes("UTF8");
        SecretKeySpec key_spec  = new SecretKeySpec(key_bytes, "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(key_spec);
        byte[] raw_hash = mac.doFinal(data_bytes);

        return md5(new String(Base64.encodeBase64(raw_hash)));
    }

    private static String md5(String s) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest m = MessageDigest.getInstance("MD5");
        byte[] b = s.getBytes("UTF-8");
        m.update(b, 0, b.length);
        String md = new java.math.BigInteger(1, m.digest()).toString(16);
        while (md.length() < 32)
            md = "0" + md;
        return md;
    }

}
