package ee.ttu.idk0071.organizedchaos.security;

import org.springframework.http.HttpHeaders;

public class SecurityConstants {
    public static final String SECRET = "SecretKeyToGenJWTs";
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = HttpHeaders.AUTHORIZATION;
    public static final String SIGN_UP_URL = "/api/users";
    public static final String USERS = "/api/users/**";
    public static final String GET_TOKEN_URL = "/api/getToken";
}
