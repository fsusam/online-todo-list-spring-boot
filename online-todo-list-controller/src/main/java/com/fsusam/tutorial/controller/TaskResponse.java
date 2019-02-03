package com.fsusam.tutorial.controller;


public class TaskResponse {
    private Long id;

    private String task;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    @Override
    public String toString() {
        return "TaskResponse[id:"+id+", task:"+task+"]";
    }
}
