package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonRootName(value = "person")
@Relation(collectionRelation = "persons")
public class PersonDTO extends RepresentationModel<PersonDTO> {

    private Long id;
    private String name;
    private String surname;

}
