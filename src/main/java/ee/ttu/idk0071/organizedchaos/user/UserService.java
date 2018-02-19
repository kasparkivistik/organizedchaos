package ee.ttu.idk0071.organizedchaos.user;

public interface UserService {

    void save(User user);
    User findByEmail(String email);
    User findById(long id);
}
