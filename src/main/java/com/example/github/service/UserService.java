package com.example.github.service;

import com.example.github.model.User;
import com.example.github.repository.UserRepository;
import com.example.github.model.UserRequestCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUser(String login) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.github.com/users/" + login;
        ResponseEntity<Map> responseEntity = restTemplate.getForEntity(url, Map.class);
        Map<String, Object> userData = responseEntity.getBody();

        User user = new User();
        user.setId((Integer) userData.get("id"));
        user.setLogin((String) userData.get("login"));
        user.setName((String) userData.get("name"));
        user.setType((String) userData.get("type"));
        user.setAvatarUrl((String) userData.get("avatar_url"));
        user.setCreatedAt((String) userData.get("created_at"));

        int followers = (int) userData.get("followers");
        int publicRepos = (int) userData.get("public_repos");

        calculate(user, followers, publicRepos);
        saveToRepository(login);

        return user;
    }

    private void saveToRepository(String login) {
        Optional<UserRequestCount> optionalRequestCount = userRepository.findById(login);
        if (optionalRequestCount.isPresent()) {
            UserRequestCount requestCount = optionalRequestCount.get();
            requestCount.setRequestCount(requestCount.getRequestCount() + 1);
            userRepository.save(requestCount);
        } else {
            UserRequestCount requestCount = new UserRequestCount();
            requestCount.setLogin(login);
            requestCount.setRequestCount(1);
            userRepository.save(requestCount);
        }
    }

    private static void calculate(User user, int followers, int publicRepos) {
        user.setCalculations(6.0 / (followers * (2.0 + publicRepos)));
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}

