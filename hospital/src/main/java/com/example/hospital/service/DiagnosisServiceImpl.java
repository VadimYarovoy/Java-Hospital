package com.example.hospital.service;

import com.example.hospital.entity.Diagnosis;
import com.example.hospital.entity.People;
import com.example.hospital.exception.DiagnosNotFoundException;
import com.example.hospital.exception.PeopleNotFoundException;
import com.example.hospital.repository.DiagnosisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DiagnosisServiceImpl implements DiagnosisService{
    @Autowired
    DiagnosisRepository diagnosisRepository;

    @Override
    public Diagnosis findById(int id) {
        Optional<Diagnosis> diagnos = diagnosisRepository.findById(id);
        return diagnos.orElseThrow(() -> new DiagnosNotFoundException("There is no person with such id"));
    }

    @Override
    public void add(Diagnosis diagnos) {
        diagnosisRepository.save(diagnos);
    }

    @Override
    public void deleteById(Integer id) {
        Diagnosis diagnos = findById(id);
        diagnosisRepository.delete(diagnos);
    }

    @Override
    public Diagnosis updateById(Integer id, Diagnosis diagnos) {
        Diagnosis pdiagnos = findById(id);
        pdiagnos.setName(diagnos.getName());
        return pdiagnos;
    }


}
