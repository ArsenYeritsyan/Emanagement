package org.procode.management.service;

import java.util.List;

import org.procode.management.model.TaskEntity;

public interface TaskService {
    public List<TaskEntity> getAllTasks();
    public void saveTask(TaskEntity task);
    public TaskEntity getTask(int id);
    public void deleteTask(int id);
}