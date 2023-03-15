package com.example.github.model;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@NoArgsConstructor
public class User {
    private Integer id;
    private String login;
    private String name;
    private String type;
    private String avatarUrl;
    private String createdAt;
    private double calculations;
}
