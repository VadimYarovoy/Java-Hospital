package com.example.hospital.repository;

import com.example.hospital.entity.People;
import com.example.hospital.entity.Diagnosis;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface DiagnosisRepository extends CrudRepository<Diagnosis, Integer> {
}
