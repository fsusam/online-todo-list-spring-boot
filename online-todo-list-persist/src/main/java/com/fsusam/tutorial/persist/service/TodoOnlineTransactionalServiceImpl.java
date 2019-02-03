package com.fsusam.tutorial.persist.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fsusam.tutorial.persist.domain.Task;
import com.fsusam.tutorial.persist.domain.User;
import com.fsusam.tutorial.persist.repository.TaskRepository;
import com.fsusam.tutorial.persist.repository.UserRepository;

@Service
@Transactional
public class TodoOnlineTransactionalServiceImpl implements TodoOnlineTransactionalService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TodoOnlineTransactionalServiceImpl.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    TaskRepository taskRepository;

    @Override
    public Map<User, List<Task>> createSampleData(final Map<User, List<Task>> data) {
        LOGGER.info("createSampleData...");
        data.forEach((k, v) -> {
            userRepository.save(k);
            v.forEach(item -> taskRepository.save(item));
            LOGGER.info("{}", v);
        });

        return data;
    }

    @Override
    public Task createOrUpdateTask(final String username, final Task task) {
        final User user = userRepository.findByUsername(username);
        task.setUser(user);
        return taskRepository.save(task);

    }
}
