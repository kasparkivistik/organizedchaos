package ee.ttu.idk0071.organizedchaos.user;

public interface SecurityService {

    String findLoggedInUsername();
    void autoLogin(String username, String password);
}
