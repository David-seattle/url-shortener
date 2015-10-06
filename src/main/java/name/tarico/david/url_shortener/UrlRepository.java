package name.tarico.david.url_shortener;

import name.tarico.david.url_shortener.domain.Url;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;

public interface UrlRepository extends CrudRepository<Url, Long> {

    @Override
    @Cacheable("urlCache")
    Url findOne(Long id);

}
