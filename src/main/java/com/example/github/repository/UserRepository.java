package com.example.github.repository;

import com.example.github.model.UserRequestCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository extends JpaRepository<UserRequestCount, String> {
}
