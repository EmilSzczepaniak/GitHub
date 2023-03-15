package com.example.github;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.example.github.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.example.github.model.User;
import com.example.github.model.UserRequestCount;
import com.example.github.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        userService = new UserService();
        userService.setUserRepository(userRepository);
    }

    @Test
    public void testGetUser() {
        // Mock the RestTemplate and its response
        RestTemplate restTemplateMock = mock(RestTemplate.class);
        Map<String, Object> userData = new HashMap<>();
        userData.put("id", 12345);
        userData.put("login", "testuser");
        userData.put("name", "Test User");
        userData.put("type", "User");
        userData.put("avatar_url", "http://example.com/avatar.jpg");
        userData.put("created_at", "2022-03-14T15:30:00Z");
        userData.put("followers", 50);
        userData.put("public_repos", 10);
        ResponseEntity<Map> responseEntityMock = new ResponseEntity<>(userData, HttpStatus.OK);
        when(restTemplateMock.getForEntity("https://api.github.com/users/testuser", Map.class)).thenReturn(responseEntityMock);

        // Mock the UserRepository and its response
        UserRequestCount userRequestCount = new UserRequestCount();
        userRequestCount.setLogin("testuser");
        userRequestCount.setRequestCount(0);
        when(userRepository.findById("testuser")).thenReturn(Optional.of(userRequestCount));

        // Call the getUser method and assert the result
        User result = userService.getUser("testuser");
        assertEquals("User", result.getType());
        assertEquals( (Integer) 19480, result.getId());

        // Verify that the UserRepository was called to save the request count
        userRequestCount.setRequestCount(1);
        when(userRepository.save(userRequestCount)).thenReturn(userRequestCount);
    }
}
