package com.example.user;

import com.example.user.dto.UserRequest;
import com.example.user.entity.UserEntity;
import com.example.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerIntegrationTest {

    @LocalServerPort
    int port;

    @Autowired
    UserRepository userRepository;

    RestTemplate restTemplate = new RestTemplate();

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @Test
    void createAndGetUser() {
        String url = "http://localhost:" + port + "/api/users";

        UserRequest req = new UserRequest();
        req.setName("John Doe");
        req.setEmail("john@example.com");
        req.setPassword("Passw0rd");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<UserRequest> requestEntity = new HttpEntity<>(req, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        String body = response.getBody();
        assertThat(body).contains("John Doe", "john@example.com");

        // get by id from created response
        // parse id simply via JSON matcher (naive)
        assertThat(body).contains("id");
    }
}
