package com.example.hospital.repository;

import com.example.hospital.entity.People;
import com.example.hospital.entity.Wards;
import com.example.hospital.entity.Diagnosis;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PeopleRepository extends CrudRepository<People, Integer> {

    @Query("SELECT p FROM People p " +
            "JOIN Diagnosis d " +
            "ON p.diagnosis = d " +
            "WHERE d.name = :name")
    Iterable<People> findPeopleByDiagnosisName(@Param("name") String name);

    @Query("SELECT p FROM People p " +
            "JOIN Wards w " +
            "ON p.ward = w " +
            "WHERE w.name = :name")
    Iterable<People> findPeopleByWardName(@Param("name") String name);

    @Query("SELECT p FROM People p " +
            "JOIN Wards w " +
            "ON p.ward = w " +
            "WHERE w.id = :id")
    Iterable<People> findPeopleByWardId(@Param("id") Integer id);

    @Query("SELECT p FROM People p " +
            "JOIN Diagnosis d " +
            "ON p.diagnosis = d " +
            "WHERE d.id = :id")
    Iterable<People> findPeopleByDiagnosisId(@Param("id") Integer id);

    @Query("SELECT d FROM People p " +
            "JOIN Diagnosis d " +
            "ON p.diagnosis = d " +
            "WHERE p.id = :id")
    Iterable<Diagnosis> findDiagnosisByPersonId(@Param("id") Integer id);

    @Query("SELECT d FROM People p " +
            "JOIN Diagnosis d " +
            "ON p.diagnosis = d " +
            "WHERE p.firstName = :name")
    Iterable<Diagnosis> findDiagnosisByPersonName(@Param("name") String name);

    @Query("SELECT w FROM People p " +
            "JOIN Wards w " +
            "ON p.ward = w " +
            "WHERE p.id = :id ")
    Iterable<Wards> findWardByPersonId(@Param("id") Integer id);

    @Query("SELECT w FROM People p " +
            "JOIN Wards w " +
            "ON p.ward = w " +
            "WHERE p.firstName = :name ")
    Iterable<Wards> findWardByPersonName(@Param("name") String name);
}
