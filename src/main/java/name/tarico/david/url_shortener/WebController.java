package name.tarico.david.url_shortener;

import name.tarico.david.url_shortener.domain.IdEncoderDecoder;
import name.tarico.david.url_shortener.domain.Url;
import name.tarico.david.url_shortener.domain.UrlValidationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;


@Controller
public class WebController {

    private Logger logger = LoggerFactory.getLogger(WebController.class);

    @Autowired
    private UrlRepository urlRepository;

    @Value("${domain.name}")
    private String serviceDomainName;

    @RequestMapping(value = "/")
    public String landingPage() {
        return "index";
    }

    @RequestMapping(value = "/", params = {"url"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String shortenUrl(@RequestParam(value = "url") String urlStr) {
        if (!UrlValidationUtil.isValid(urlStr)) {
            return "{\"error\": \"Invalid URL\"}";
        }

        Url url = urlRepository.save(new Url(urlStr));

        //TODO: We should validate for URL IDs that encode to an offensive word, and generate a new ID

        logger.debug("Generated ID: " + url.getId() + " for URL " + url);
        return "{\"url\": \"http://" + serviceDomainName + "/" + IdEncoderDecoder.encode(url.getId()) + "\"}";
    }

    @RequestMapping(value = "/{id}")
    public RedirectView expandUrl(@PathVariable("id") String encodedId) {
        RedirectView redirectView = new RedirectView();

        long id;
        try {
            id = IdEncoderDecoder.decode(encodedId);
        } catch (IllegalArgumentException e) {
            redirectView.setUrl("/?errorCode=1");
            return redirectView;
        }

        Url url = urlRepository.findOne(id);
        if (url == null) {
            redirectView.setUrl("/?errorCode=1");
        }
        else {
            redirectView.setUrl(url.getUrl());
        }

        return redirectView;
    }

}

