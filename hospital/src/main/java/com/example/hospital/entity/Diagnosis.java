package com.example.hospital.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Getter
@Setter
@Entity
@Table(name = "Diagnosis")
public class Diagnosis {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", length = 20, nullable = false)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "diagnosis", cascade = CascadeType.ALL)
    private Collection<People> people;

    public Diagnosis() {
    }

    public Diagnosis(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Diagnosis{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
