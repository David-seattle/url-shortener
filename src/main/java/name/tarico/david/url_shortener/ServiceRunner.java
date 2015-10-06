package name.tarico.david.url_shortener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;


/**
 * Main class that initializes Spring and the web server to start the service.
 */
@SpringBootApplication
@EnableCaching
public class ServiceRunner {

    public static void main(String[] args) {
        SpringApplication.run(ServiceRunner.class, args);
    }

    @Bean
    public CacheManager cacheManager() {
        //TODO: This is just a demonstration of caching and should be replaced with something better like EHCache or Redis
        return new ConcurrentMapCacheManager("urlCache");
    }
}
