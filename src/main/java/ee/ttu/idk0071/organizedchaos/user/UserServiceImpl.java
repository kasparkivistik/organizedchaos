package ee.ttu.idk0071.organizedchaos.user;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findById(long id) { return userRepository.findById(id);}

    @Override
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }
}
