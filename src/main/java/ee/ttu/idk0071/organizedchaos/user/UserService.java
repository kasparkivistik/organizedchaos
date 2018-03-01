package ee.ttu.idk0071.organizedchaos.user;

import java.util.List;

public interface UserService {

    void save(User user);
    User findByEmail(String email);
    User findById(long id);
    List<User> getAllUsers();
}
