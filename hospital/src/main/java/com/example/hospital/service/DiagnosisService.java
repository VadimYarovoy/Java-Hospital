package com.example.hospital.service;

import com.example.hospital.entity.Diagnosis;

public interface DiagnosisService {
    Diagnosis findById(int id);
    void add(Diagnosis diagnos);
    void deleteById(Integer id);
    Diagnosis updateById(Integer id, Diagnosis diagnos);

}
