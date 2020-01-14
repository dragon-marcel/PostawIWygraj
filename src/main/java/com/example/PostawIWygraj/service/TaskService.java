package com.example.PostawIWygraj.service;

import java.util.List;

import com.example.PostawIWygraj.model.Task;

public interface TaskService {
    public List<Task> getTaskByIdUser(Long id);

    public void save(Task task);

    public Task completedTask(Long id);

    public Task removeTask(Long id);

    public Task toDoTask(Long id);

    public Task changeNotificationEmial(Long id);

    public Task getTaskById(Long id);

}
