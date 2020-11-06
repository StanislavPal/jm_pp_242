package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import web.dao.Dao;
import web.model.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

/*    ver1 */
    @Autowired
    @Qualifier("userDaoImp")
    private final Dao<User> userDao;

    public UserDetailsServiceImpl(Dao<User> userDao) {
        this.userDao = userDao;
    }

//     «Пользователь» – это просто Object. В большинстве случаев он может быть
//      приведен к классу UserDetails.
//     Для создания UserDetails используется интерфейс UserDetailsService, с единственным методом:
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDao.findOne(username);
    }
/* end ver1 */


/////*    ver2 */
//    private final UserService userService;
//
//    @Autowired
//    public UserDetailsServiceImpl(UserService userService) {
//        this.userService = userService;
//    }
//
//    // «Пользователь» – это просто Object. В большинстве случаев он может быть
//    //  приведен к классу UserDetails.
//    // Для создания UserDetails используется интерфейс UserDetailsService, с единственным методом:
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return userService.findOne(username);
//    }
/////* end ver2 */
}
