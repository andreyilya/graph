package graph.engine.repository.jpa;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

/**
 * Base entity. Has basic CRUD methods.
 *
 * @param <ENTITY> entity, associated with current dao
 * @param <ID>     id of current entity
 * @author a.radkov
 */
public class JpaSupport<ENTITY, ID extends Serializable> {

    @PersistenceContext
    private EntityManager em;

    private SimpleJpaRepository<ENTITY, ID> repository;

    private Class<ENTITY> clazz;

    @PostConstruct
    public void init() {
        repository = new SimpleJpaRepository<ENTITY, ID>(getPersistentClass(), em);
    }

    /**
     * Spring data repository
     */
    public SimpleJpaRepository<ENTITY, ID> getRepository() {
        return repository;
    }

    /**
     * Gets the class, with which current dao instance is parametrized.
     *
     * @return type associted with current dao
     */
    @SuppressWarnings("unchecked")
    private Class<ENTITY> getPersistentClass() {
        if (clazz == null) {
            clazz = (Class<ENTITY>)
                    ((ParameterizedType) getClass()
                            .getGenericSuperclass())
                            .getActualTypeArguments()[0];
        }
        return clazz;
    }

    public EntityManager getEm() {
        return em;
    }
}
