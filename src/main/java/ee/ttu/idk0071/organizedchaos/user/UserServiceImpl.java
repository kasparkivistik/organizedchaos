package ee.ttu.idk0071.organizedchaos.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRespository userRespository;

    @Autowired
    private RoleRespository roleRespository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>(roleRespository.findAll()));
        userRespository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRespository.findByUsername(username);
    }
}
