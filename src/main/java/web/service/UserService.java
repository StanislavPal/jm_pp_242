package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.Dao;
import web.model.Role;
import web.model.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    @Qualifier("userDaoImp")
    private Dao<User> userDao;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Transactional
    public User getById(long id) {
        return userDao.findOne(id);
    }

    @Transactional
    public void delete(long id) {
        userDao.deleteById(id);
    }

    @Transactional
    public void update(User user) {
        userDao.update(user);
    }

    @Transactional
    public void create(User user) {
        user.setPassword( passwordEncoder.encode( user.getPassword() ) );
        Set<Role> dbRoles = new HashSet<>();
        if (user.getRoles() != null) {
            for (Role role : user.getRoles() ) {
                dbRoles.add(roleService.findOne( role.getRole() ));
            }
        }
        user.setRoles(dbRoles);
        userDao.create(user);
    }

    @Transactional
    public User findOne(String username) {
        return userDao.findOne(username);
    }
}
