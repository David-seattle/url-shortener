package name.tarico.david.url_shortener;

import name.tarico.david.url_shortener.domain.Url;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;

/**
 * This is a magical interface that Spring Data will use to let us read and write our URL objects
 * using Hibernate.  Spring automatically generates the implementation class.  We aren't actually adding
 * any logic to the default implementation other than adding caching.
 */
public interface UrlRepository extends CrudRepository<Url, Long> {

    @Override
    //Adds caching, using the cache manager defined in the ServiceRunner
    @Cacheable("urlCache")
    Url findOne(Long id);

}
