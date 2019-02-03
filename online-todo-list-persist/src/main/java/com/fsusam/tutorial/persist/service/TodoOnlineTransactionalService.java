package com.fsusam.tutorial.persist.service;

import java.util.List;
import java.util.Map;

import com.fsusam.tutorial.persist.domain.Task;
import com.fsusam.tutorial.persist.domain.User;

public interface TodoOnlineTransactionalService {

    Map<User, List<Task>> createSampleData(Map<User, List<Task>> data);

    Task createOrUpdateTask(String username, Task task);

}
