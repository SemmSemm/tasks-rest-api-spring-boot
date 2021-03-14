package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonRootName(value = "task")
@Relation(collectionRelation = "tasks")
public class TaskDTO extends RepresentationModel<TaskDTO> {

    private Long id;
    private String title;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private String description;
    private List<PersonDTO> persons;

}
