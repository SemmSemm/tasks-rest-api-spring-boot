package com.example.demo.services;

import com.example.demo.components.TaskModelAssembler;
import com.example.demo.entities.Person;
import com.example.demo.entities.Task;
import com.example.demo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task createNewTask(Task task) {
        return taskRepository.save(task);
    }

    public Page<Task> getAllTasks(Pageable pageable) {
        Page<Task> tasks = taskRepository.findAll(pageable);
        if(tasks.getTotalElements() == 0) {
            throw new EntityNotFoundException("There is no tasks exist.");
        }
        return tasks;
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException("Task with id " + id + " was not found");
        });
    }

    public Task updateTask(Long id, Task task) {
        return taskRepository.findById(id).map(taskUpdated -> {
            if(task.getTitle() != null) {
                taskUpdated.setTitle(task.getTitle());
            }
            if(task.getDescription() != null) {
                taskUpdated.setDescription(task.getDescription());
            }
            if(task.getDateFrom() != null) {
                taskUpdated.setDateFrom(task.getDateFrom());
            }
            if(task.getDateTo() != null) {
                taskUpdated.setDateTo(task.getDateTo());
            }
            if(task.getPersons() != null) {
                for (Person person : task.getPersons()) {
                    taskUpdated.addPerson(person);
                }
            }
            taskRepository.save(taskUpdated);
            return taskUpdated;
        }).orElseThrow(() -> {
            throw new EntityNotFoundException("Task with id " + id + " was not found");
        });

    }

    public boolean deleteTask(Long id) {
        return taskRepository.findById(id).map(task -> {
            taskRepository.delete(task);
            return true;
        }).orElseThrow(() -> {
            throw new EntityNotFoundException("Task with id " + id + " was not found");
        });
    }
}
