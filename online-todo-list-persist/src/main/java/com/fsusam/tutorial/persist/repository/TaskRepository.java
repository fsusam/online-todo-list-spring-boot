package com.fsusam.tutorial.persist.repository;

import com.fsusam.tutorial.persist.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fsusam.tutorial.persist.domain.User;

import java.util.List;

@Repository("taskRepository")
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("SELECT t FROM Task t WHERE t.user = :user")
    List<Task> findByUser(@Param("user") User user);

    @Query("SELECT t FROM Task t WHERE t.user.username = :username")
    List<Task> findByUsername(@Param("username") String username);
}
