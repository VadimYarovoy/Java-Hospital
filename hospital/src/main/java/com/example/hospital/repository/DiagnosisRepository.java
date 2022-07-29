package com.example.hospital.repository;

import com.example.hospital.entity.Diagnosis;
import org.springframework.data.repository.CrudRepository;

public interface DiagnosisRepository extends CrudRepository<Diagnosis, Integer> {
}
