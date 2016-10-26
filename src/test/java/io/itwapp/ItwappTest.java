package io.itwapp;

import io.itwapp.rest.AccessToken;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ItwappTest {

    private static String mail;
    private static String password;

    private final static String itwappApiKey = System.getenv("itwappApiKey");
    private final static String itwappApiSecret = System.getenv("itwappApiSecret");
    private final static String companyId = System.getenv("itwappCompanyId");

    @BeforeClass
    public static void setUpBeforeClass()  {
        mail = System.getenv("itwappMail");
        password = System.getenv("itwappPassword");

        assertNotNull(mail);
        assertNotNull(password);
    }

    @Test
    public void a_auth()    {
        AccessToken[] accessToken = Itwapp.Authenticate(mail, password);

        AccessToken maybeAccessToken = findAccessTokenInArrayWithCompanyId(accessToken, companyId);
        assertNotNull(maybeAccessToken);

        assertEquals(itwappApiKey, maybeAccessToken.getApiKey());
        assertEquals(itwappApiSecret, maybeAccessToken.getSecretKey());
    }

    private AccessToken findAccessTokenInArrayWithCompanyId(AccessToken[] accessTokens, String companyId) {
        for(AccessToken accessToken : accessTokens) {
            if(accessToken.getCompany().compareTo(companyId) == 0) {
                return accessToken;
            }
        }
        return null;
    }

}
