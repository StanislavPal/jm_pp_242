package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.Dao;
import web.model.Role;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    @Qualifier("roleDaoImp")
    private Dao<Role> roleDao;

    @Transactional(readOnly = true)
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Transactional
    public Role getById(long id) {
        return roleDao.findOne(id);
    }

    @Transactional
    public Role findOne(String role) {
        return roleDao.findOne(role);
    }

    @Transactional
    public void delete(long id) {
        roleDao.deleteById(id);
    }

    @Transactional
    public void update(Role role) {
        roleDao.update(role);
    }

    @Transactional
    public void create(Role role) {
        roleDao.create(role);
    }
}
