package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.Dao;
import web.model.Role;
import web.model.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService{

    @Autowired
    @Qualifier("userDaoImp")
    private Dao<User> userDao;

    @Autowired
    @Qualifier("roleDaoImp")
    private Dao<Role> roleDao;

//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;

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
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleDao.findOne("ROLE_USER"));
        user.setRoles(roles);
        userDao.create(user);
    }
}
