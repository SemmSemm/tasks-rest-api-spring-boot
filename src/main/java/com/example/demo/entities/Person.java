package com.example.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
public class Person implements Serializable {

    @Getter
    @Setter
    @Id
    @SequenceGenerator(
            name = "person_sequence",
            sequenceName = "person_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "person_sequence"
    )
    private Long id;

    @Getter
    @Setter
    @NotBlank(message = "Person name must not be blank.")
    private String name;

    @Getter
    @Setter
    @NotBlank(message = "Person surname must not be blank.")
    private String surname;

    @Override
    public String toString() {
        return "{ " +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                " }";
    }
}
