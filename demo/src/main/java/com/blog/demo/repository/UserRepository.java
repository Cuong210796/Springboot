package com.blog.demo.repository;

import com.blog.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u from User u where u.email = ?1")
    User findByEmail(String email);
}
