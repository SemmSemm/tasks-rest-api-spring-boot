package com.example.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Task implements Serializable {

    @Getter
    @Setter
    @Id
    @SequenceGenerator(
            name = "task_sequence",
            sequenceName = "task_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "task_sequence"
    )
    private Long id;

    @Getter
    @Setter
    @NotBlank(message = "Title of the task must not be blank.")
    @NotNull(message = "Title of the task must not be blank.")
    private String title;

    @Getter
    @Setter
    private LocalDate dateFrom;

    @Getter
    @Setter
    private LocalDate dateTo;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "person_tasks",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id")
    )
    private List<Person> persons;

    public void addPerson(Person person) {
        persons.add(person);
    }

    public void removePerson(Person person) {
        persons.remove(person);
    }

    @Override
    public String toString() {
        return "{ " +
                "id=" + id +
                ", title='" + title + '\'' +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                ", description='" + description + '\'' +
                ", persons : " + persons + '\'' +
                " }";
    }

}
