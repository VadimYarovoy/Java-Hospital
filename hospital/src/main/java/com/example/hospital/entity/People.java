package com.example.hospital.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "People")
public class People {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "first_name", length = 20, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 20, nullable = false)
    private String lastName;

    @Column(name = "pather_name", length = 20, nullable = false)
    private String patherName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ward_id")
    private Wards ward;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "diagnosis_id")
    private Diagnosis diagnosis;

    public People() {
    }

    public People(String firstName, String lastName, String patherName, Wards ward, Diagnosis diagnosis) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patherName = patherName;
        this.ward = ward;
        this.diagnosis = diagnosis;
    }

    @Override
    public String toString() {
        return "People{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", patherName='" + patherName + '\'' +
                ", ward=" + ward +
                ", diagnos=" + diagnosis +
                '}';
    }
}
