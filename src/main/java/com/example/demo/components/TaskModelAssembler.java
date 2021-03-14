package com.example.demo.components;

import com.example.demo.entities.Person;
import com.example.demo.entities.Task;
import com.example.demo.dto.PersonDTO;
import com.example.demo.dto.TaskDTO;
import com.example.demo.task.TaskController;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TaskModelAssembler extends RepresentationModelAssemblerSupport<Task, TaskDTO> {

    public TaskModelAssembler() {
        super(TaskController.class, TaskDTO.class);
    }

    @Override
    public TaskDTO toModel(Task entity) {
        TaskDTO taskDTO = instantiateModel(entity);
        taskDTO.setId(entity.getId());
        taskDTO.setTitle(entity.getTitle());
        taskDTO.setDateFrom(entity.getDateFrom());
        taskDTO.setDateTo(entity.getDateTo());
        taskDTO.setDescription(entity.getDescription());
        taskDTO.setPersons(toPersonModel(entity.getPersons()));

        return taskDTO;
    }

    private List<PersonDTO> toPersonModel(List<Person> persons) {
        if (persons.isEmpty())
            return Collections.emptyList();

        return persons.stream()
                .map(person -> PersonDTO.builder()
                        .id(person.getId())
                        .name(person.getName())
                        .surname(person.getSurname())
                        .build())
                .collect(Collectors.toList());
    }
}
