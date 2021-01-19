package web.dao;

import org.springframework.stereotype.Repository;
import web.model.Role;

@Repository
public class RoleDaoImp extends AbstractJpaDao<Role> implements Dao<Role> {
    public RoleDaoImp() {
        setClazz(Role.class );
    }

    @Override
    public Role findOne(String role) {
        return entityManager.createQuery(
                "select r " +
                        "from Role r " +
                        "where r.name = :role", Role.class )
                .setParameter("role", role)
                .getSingleResult();
    }

    @Override
    public void deleteById( long entityId ){
        int id = entityManager.createQuery(
                "delete Role r where r.id = :id")
                .setParameter("id", entityId)
                .executeUpdate();
    }
}