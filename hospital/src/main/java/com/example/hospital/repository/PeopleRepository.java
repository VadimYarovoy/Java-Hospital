package com.example.hospital.repository;

import com.example.hospital.entity.People;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PeopleRepository extends CrudRepository<People, Integer> {

    @Query("SELECT p FROM People p " +
            "JOIN Diagnosis d " +
            "ON p.diagnosis = d " +
            "WHERE d.name = :name")
    Iterable<People> findPeopleByDiagnosisName(@Param("name") String name);
}
