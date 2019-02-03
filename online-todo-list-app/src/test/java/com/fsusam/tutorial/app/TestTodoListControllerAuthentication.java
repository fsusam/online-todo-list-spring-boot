package com.fsusam.tutorial.app;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StartApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestTodoListControllerAuthentication {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    private String createURLWithPort(final String uri) {
        return "http://localhost:" + port +"/todo-list/"+ uri;
    }

    @Test
    public void callListTaskWithoutAuthentication_thenReturnForbidden()
            throws Exception {
        final HttpEntity<String> entity = new HttpEntity<>(null, headers);

        final ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("api/list-task"),
                HttpMethod.POST, entity, String.class);

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());

    }

    @Test
    public void callAddOrUpdateTaskWithoutAuthentication_thenReturnForbidden()
            throws Exception {
        final HttpEntity<String> entity = new HttpEntity<>(null, headers);

        final ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("api/add-update-task"),
                HttpMethod.POST, entity, String.class);

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());

    }

    @Test
    public void callRemoveTaskWithoutAuthentication_thenReturnForbidden()
            throws Exception {
        final HttpEntity<String> entity = new HttpEntity<>(null, headers);

        final ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("api/remove-task"),
                HttpMethod.POST, entity, String.class);

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());

    }
}
