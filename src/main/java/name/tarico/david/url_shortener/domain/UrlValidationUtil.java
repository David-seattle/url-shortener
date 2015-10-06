package name.tarico.david.url_shortener.domain;


import org.apache.commons.validator.routines.UrlValidator;

public class UrlValidationUtil {

    /**
     * This will validate a URL has reasonable syntax, without actually checking if the URL is
     * reachable.
     *
     * Currently, this is a quick implementation.  A better solution might be to do our own validation
     * logic rather than rely on libraries so we can provide error codes indicating what specifically was wrong
     * with the URL, and to allow leniency for common user mistakes that we can correct for, such as adding
     * a missing http:// if no protocol is specified.
     *
     * @return An error message or null if the URL is valid
     */
    public static boolean isValid(String url) {
        UrlValidator urlValidator = new UrlValidator(UrlValidator.ALLOW_2_SLASHES);
        return urlValidator.isValid(url);
    }
}
