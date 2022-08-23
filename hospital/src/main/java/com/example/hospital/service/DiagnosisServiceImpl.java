package com.example.hospital.service;

import com.example.hospital.entity.Diagnosis;
import com.example.hospital.entity.People;
import com.example.hospital.exception.DiagnosNotFoundException;
import com.example.hospital.exception.PeopleNotFoundException;
import com.example.hospital.repository.DiagnosisRepository;
import com.example.hospital.repository.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiagnosisServiceImpl implements DiagnosisService{
    @Autowired
    DiagnosisRepository diagnosisRepository;
    @Autowired
    private PeopleRepository peopleRepository;

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
        diagnosisRepository.save(pdiagnos);
        return pdiagnos;
    }

    @Override
    public List<Diagnosis> findDiagnosisByPersonId(Integer id) {
        List<Diagnosis> diagnos = (List<Diagnosis>) peopleRepository.findDiagnosisByPersonId(id);
        return diagnos;
    }

    @Override
    public List<Diagnosis> findDiagnosisByPersonName(String name) {
        List<Diagnosis> diagnos = (List<Diagnosis>) peopleRepository.findDiagnosisByPersonName(name);
        return diagnos;
    }
}
