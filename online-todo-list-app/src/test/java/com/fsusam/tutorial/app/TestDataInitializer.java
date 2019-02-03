package com.fsusam.tutorial.app;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fsusam.tutorial.persist.repository.TaskRepository;
import com.fsusam.tutorial.persist.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.fsusam.tutorial.persist.domain.Task;
import com.fsusam.tutorial.persist.domain.User;
import com.fsusam.tutorial.persist.service.TodoOnlineTransactionalService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StartApplication.class)
public class TestDataInitializer {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestDataInitializer.class);

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    public void whenStartDataInitializer_ExpectedTwoTasks() {
        assertEquals(2,taskRepository.findAll().size());
    }

    @Test
    public void whenStartDataInitializer_ExpectedTwoUsers() {
        assertEquals(2,userRepository.findAll().size());
    }
}
