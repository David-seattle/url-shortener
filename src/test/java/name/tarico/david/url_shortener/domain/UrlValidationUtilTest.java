package name.tarico.david.url_shortener.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class UrlValidationUtilTest {

    @Test
    public void requiresProtocol() {
        assertFalse(UrlValidationUtil.isValid("google.com"));
    }

    @Test
    public void requiresTopLevelDomain() {
        assertFalse(UrlValidationUtil.isValid("http://foo"));
    }

    @Test
    public void acceptsHttps() {
        assertTrue(UrlValidationUtil.isValid("https://www.google.com"));
    }

}