package web.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public abstract class AbstractJpaDao<T> implements Dao<T> {

    private Class< T > clazz;

    @PersistenceContext
    EntityManager entityManager;

    public final void setClazz( Class< T > clazzToSet ){
        this.clazz = clazzToSet;
    }

    @Override
    public T findOne( long id ){
        return entityManager.find( clazz, id );
    }

    @Override
    public List< T > findAll(){
        return entityManager.createQuery("from " + clazz.getName(), clazz).getResultList();
    }

    @Override
    public void create( T entity ){
        entityManager.persist( entity );
    }

    @Override
    public void update( T entity ){
        entityManager.merge( entity );
    }

    @Override
    public void delete( T entity ){
        entityManager.remove( entity );
    }

    @Override
    public void deleteById( long entityId ){
        T entity = findOne( entityId );
        delete( entity );
    }
}