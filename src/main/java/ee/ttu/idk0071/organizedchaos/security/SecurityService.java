package ee.ttu.idk0071.organizedchaos.security;

public interface SecurityService {

    String findLoggedInUsername();
    void autoLogin(String username, String password);
}
