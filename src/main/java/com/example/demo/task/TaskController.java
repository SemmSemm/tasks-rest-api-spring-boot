package com.example.demo.task;

import com.example.demo.components.TaskModelAssembler;
import com.example.demo.entities.Task;
import com.example.demo.dto.TaskDTO;
import com.example.demo.handlers.errors.dto.ErrorDTO;
import com.example.demo.repository.TaskRepository;
import com.example.demo.services.TaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/tasks")
@Api(value = "TaskController")
public class TaskController {

    @Autowired
    private final TaskService taskService;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskModelAssembler taskModelAssembler;

    @Autowired
    private PagedResourcesAssembler<Task> pagedResourcesAssembler;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @ApiOperation(value = "List of all created tasks.", response = PagedModel.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list.")
    })
    @GetMapping("/all")
    public ResponseEntity<PagedModel<TaskDTO>> getTasks(Pageable pageable) {

        PagedModel<TaskDTO> taskPagedModel = pagedResourcesAssembler.toModel(
                taskService.getAllTasks(pageable),
                taskModelAssembler
        );

        return new ResponseEntity<>(taskPagedModel, HttpStatus.OK);
    }

    @ApiOperation(value = "View task by specified id.", response = Task.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved task with specified id."),
            @ApiResponse(code = 404, message = "Task with specified id was not found.")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Object> getTask(@PathVariable Long id) {
        Task task = taskService.getTaskById(id);
        if(task != null) {
            return new ResponseEntity<>(task, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Task with id " + id + " was not found.", HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "Create new task", response = Task.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Task was created successfully."),
            @ApiResponse(code = 400, message = "Bad Request. Some of the fields were provided incorrectly.")
    })
    @PostMapping("/create-task")
    public ResponseEntity<Task> createNewTask(@Valid @RequestBody Task task) {
        Task newTask = taskService.createNewTask(task);
        return new ResponseEntity<>(newTask, HttpStatus.OK);
    }

    @ApiOperation(value = "Update existing task with specified id.", response = Task.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Task was updated successfully."),
            @ApiResponse(code = 400, message = "Bad Request. Some of the fields were provided incorrectly.")
    })
    @PatchMapping("/update-task/{id}")
    public ResponseEntity<Object> putTask(
            @PathVariable Long id,
            @RequestBody Task task
    ) {
        Task taskUpdated = taskService.updateTask(id, task);
        if(taskUpdated != null) {
            return new ResponseEntity<>(taskUpdated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Task with id " + id + " was not found.", HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "Delete task with specified id.", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Task was deleted successfully"),
            @ApiResponse(code = 404, message = "Task with specified id was not found.")
    })
    @DeleteMapping("/delete-task/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id) {
        boolean isDeleted = taskService.deleteTask(id);
        if(isDeleted) {
            return new ResponseEntity<String>("Task with id " + id + " was deleted successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Task with id " + id + " was not found.", HttpStatus.NOT_FOUND);
        }
    }
}
