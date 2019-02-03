package com.fsusam.tutorial.controller;

import static org.springframework.http.ResponseEntity.ok;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fsusam.tutorial.persist.domain.Task;
import com.fsusam.tutorial.persist.repository.TaskRepository;
import com.fsusam.tutorial.persist.service.TodoOnlineTransactionalService;

@RestController
@RequestMapping("/api")
public class TodoListController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TodoListController.class);

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    TodoOnlineTransactionalService todoOnlineTransactionalService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/list-task")
    public ResponseEntity<List<TaskResponse>> listTask(@RequestBody final UserRequest userRequest) {
        return new ResponseEntity<>(convertToTaskResponse(taskRepository.findByUsername(userRequest.getUsername())), HttpStatus.OK);
    }

    @PostMapping("/add-update-task")
    public ResponseEntity addOrUpdateTask(@RequestBody final TaskRequest taskRequest) {
        LOGGER.info("{}", taskRequest);
        final Task task = todoOnlineTransactionalService.createOrUpdateTask(taskRequest.getUsername(), convertToTaskEntity(taskRequest));
        final Map<Object, Object> model = new HashMap<>();
        model.put("message", "SUCCESS");
        model.put("id", task.getId());
        return ok(model);
    }

    @DeleteMapping("/remove-task")
    public ResponseEntity removeTask(@RequestBody final TaskRequest taskRequest) {
        LOGGER.info("{}", taskRequest);
        taskRepository.delete(convertToTaskEntity(taskRequest));
        final Map<Object, Object> model = new HashMap<>();
        model.put("message", "SUCCESS");
        return ok(model);
    }

    private Task convertToTaskEntity(final TaskRequest taskRequest) {
        final Task task = modelMapper.map(taskRequest, Task.class);
        LOGGER.info("{}", task);
        return task;
    }

    private List<TaskResponse> convertToTaskResponse(final List<Task> task) {
        final List<TaskResponse> taskResponseList = new ArrayList<>();
        task.forEach(item -> {
            final TaskResponse taskResponse = modelMapper.map(item, TaskResponse.class);
            taskResponseList.add(taskResponse);
        });
        return taskResponseList;
    }
}
