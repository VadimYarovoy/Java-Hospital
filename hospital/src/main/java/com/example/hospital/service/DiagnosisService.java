package com.example.hospital.service;

import com.example.hospital.entity.Diagnosis;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DiagnosisService {
    Diagnosis findById(int id);
    void add(Diagnosis diagnos);
    void deleteById(Integer id);
    Diagnosis updateById(Integer id, Diagnosis diagnos);
    List<Diagnosis> findDiagnosisByPersonId(Integer id);
    List<Diagnosis> findDiagnosisByPersonName(String name);
}
