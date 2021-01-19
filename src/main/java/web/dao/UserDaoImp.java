package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;

@Repository
public class UserDaoImp extends AbstractJpaDao<User> implements Dao<User> {
    public UserDaoImp() {
        setClazz(User.class );
    }

    @Override
    public User findOne(String username) {
        return entityManager.createQuery(
                "select u " +
                        "from User u " +
                        "where u.username = :username", User.class )
                .setParameter("username", username)
                .getSingleResult();
    }

    @Override
    public void deleteById( long entityId ){
        int id = entityManager.createQuery(
                "delete User u where u.id = :id")
                .setParameter("id", entityId)
                .executeUpdate();
    }
}