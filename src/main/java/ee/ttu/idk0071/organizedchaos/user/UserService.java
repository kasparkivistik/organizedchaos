package ee.ttu.idk0071.organizedchaos.user;

public interface UserService {

    void save(User user);
    User findByUsername(String username);
}
