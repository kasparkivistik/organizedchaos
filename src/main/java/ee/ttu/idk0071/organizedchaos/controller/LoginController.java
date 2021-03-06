package ee.ttu.idk0071.organizedchaos.controller;

import ee.ttu.idk0071.organizedchaos.security.SecurityConstants;
import ee.ttu.idk0071.organizedchaos.user.User;
import lombok.Data;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class LoginController {

    @PostMapping(value = SecurityConstants.GET_TOKEN_URL)
    public ResponseEntity<Token> getToken(@RequestBody User user) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> entity = new HttpEntity<>("{\"email\":\"" + user.getEmail() + "\",\"password\":\"" + user.getPassword() + "\"}");
        ResponseEntity answer = restTemplate.exchange("http://localhost:8080/login", HttpMethod.POST, entity, String.class);
        Token token = new Token();
        token.setToken(answer.getHeaders().get(HttpHeaders.AUTHORIZATION).get(0));
        return ResponseEntity.ok(token);
    }

    @Data
    public class Token {
        private String token;
    }
}
