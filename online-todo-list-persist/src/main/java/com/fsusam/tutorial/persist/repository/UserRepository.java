package com.fsusam.tutorial.persist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fsusam.tutorial.persist.domain.User;
import org.springframework.stereotype.Repository;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
