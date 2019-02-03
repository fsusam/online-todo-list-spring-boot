package com.fsusam.tutorial.controller;

public class TaskRequest {
    private Long id;

    private String task;

    private String username;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(final String task) {
        this.task = task;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "TaskRequest[ id:" + id + ", task:" + task + ", username:"+username+"]";
    }
}
