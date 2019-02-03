package com.fsusam.tutorial.app;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.fsusam.tutorial.security.AuthController;
import com.fsusam.tutorial.security.AuthenticationRequest;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StartApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestTodoListAuthorizationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    private String createURLWithPort(final String uri) {
        return "http://localhost:" + port + "/todo-list/" + uri;
    }

    @Test
    public void callSigninWithUsernameAndPassword_thenReturnJWTTokenAndUsername() throws JSONException {
        headers.add("Content-Type", "application/json");

        final AuthenticationRequest authenticationRequest = new AuthenticationRequest();
        authenticationRequest.setUsername("user1@example.com");
        authenticationRequest.setPassword("user1234");

        final HttpEntity<AuthenticationRequest> entity = new HttpEntity<>(authenticationRequest, headers);

        final ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("auth/signin"),
                HttpMethod.POST, entity, String.class);

        JSONAssert.assertEquals("{\n" +
                "    \"username\": \"user1@example.com\"\n" +
                "}", response.getBody(), false);
    }

}
