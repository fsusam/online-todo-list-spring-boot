package com.fsusam.tutorial.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fsusam.tutorial.persist.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.fsusam.tutorial.persist.domain.Task;
import com.fsusam.tutorial.persist.domain.User;
import com.fsusam.tutorial.persist.service.TodoOnlineTransactionalService;

@Component
public class DataInitializer implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataInitializer.class);

    @Autowired
    TodoOnlineTransactionalService todoOnlineTransactionalService;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(final String... args) throws Exception {
        LOGGER.info("initializing userRepository data...");
        final String templateUser = "user";
        final String templateTask = "Task";
        int index = 0;

        final Map<User, List<Task>> sampleData = new HashMap<>();

        for (int i = 0; i < 2; i++) {
            final User user = new User();
            user.setUsername(templateUser + (++index) + "@example.com");
            user.setPassword(passwordEncoder.encode("user1234"));

            final Task task = new Task();
            task.setUser(user);
            task.setTask("Sample " + templateTask + (index));

            final List<Task> taskList = new ArrayList<>();
            taskList.add(task);

            sampleData.put(user, taskList);
        }

        final Map<User, List<Task>> persistedData = todoOnlineTransactionalService.createSampleData(sampleData);

        persistedData.forEach((k,v) -> {
            LOGGER.info("{}",taskRepository.findByUsername(k.getUsername()));
        });

    }
}
