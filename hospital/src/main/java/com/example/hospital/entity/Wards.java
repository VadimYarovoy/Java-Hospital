package com.example.hospital.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Getter
@Setter
@Entity
@Table(name = "Wards")
public class Wards {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", length = 20, nullable = false)
    private String name;

    @Column(name = "max_count", nullable = false)
    private Integer maxCount;

    @JsonIgnore
    @OneToMany(mappedBy = "ward", cascade = CascadeType.ALL)
    private Collection<People> people;

    public Wards() {
    }

    public Wards(String name, Integer maxCount) {
        this.name = name;
        this.maxCount = maxCount;
    }

    @Override
    public String toString() {
        return "Wards{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", max_count=" + maxCount +
                '}';
    }
}
