package graph.engine.repository.api;

import java.io.Serializable;
import org.springframework.data.repository.Repository;

/**
 * User: a.radkov
 * Date: 20.02.14
 */
public interface BaseRepository<T, ID extends Serializable> extends Repository<T, ID> {
}
