package ee.ttu.idk0071.organizedchaos.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;


@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRespository userRespository;

    @Resource
    private RoleRespository roleRespository;

    @Resource
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
