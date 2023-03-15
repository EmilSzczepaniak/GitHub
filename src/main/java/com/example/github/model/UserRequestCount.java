package com.example.github.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
@Table(name = "user_request_count")
public class UserRequestCount {
    @Id
    private String login;
    @Column
    private int requestCount;
}
