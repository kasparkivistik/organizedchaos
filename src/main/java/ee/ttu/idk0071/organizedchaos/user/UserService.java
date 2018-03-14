package ee.ttu.idk0071.organizedchaos.user;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class UserService {

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Resource
    private UserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findById(long id) { return userRepository.findById(id);}

    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }
}
