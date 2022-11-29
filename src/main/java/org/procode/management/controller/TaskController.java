package org.procode.management.controller;

import java.util.List;

import org.procode.management.model.TaskEntity;
import org.procode.management.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping("/tasks")
    public String showAllTasks(Model model) {
        List<TaskEntity> allTasks = taskService.getAllTasks();
        model.addAttribute("allTasks", allTasks);
        return "all-tasks-page";
    }

    @GetMapping("/tasks/{id}")
    public TaskEntity getTask(@PathVariable int id) {
        TaskEntity task = taskService.getTask(id);
        return task;
    }

    @PostMapping("/tasks")
    public TaskEntity addNewTask(@RequestBody TaskEntity task) {
        taskService.saveTask(task);
        return task;
    }
    @PutMapping("/tasks")
    public TaskEntity updateTask(@RequestBody TaskEntity task) {
        taskService.saveTask(task);
        return task;
    }

    @DeleteMapping ("/tasks/{id}")
    public String deleteTask(@PathVariable int id) {
        int employeeId = taskService.getTask(id).getEmployee().getId();
        taskService.deleteTask(id);
        return "redirect:/employee/" + employeeId + "/tasks";
    }
}
