package com.example.hospital.service;

import com.example.hospital.dto.PeopleDto;
import com.example.hospital.entity.Diagnosis;
import com.example.hospital.entity.People;
import com.example.hospital.entity.Wards;
import com.example.hospital.exception.PeopleNotFoundException;
import com.example.hospital.repository.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PeopleServiceImpl implements PeopleService{
    @Autowired
    private PeopleRepository peopleRepository;

    @Autowired
    private WardService wardService;

    @Autowired
    private DiagnosisService diagnosisService;

    @Override
    public List<People> findPeopleByDiagnosisName(String name) {
        List<People> people = (List<People>) peopleRepository.findPeopleByDiagnosisName(name);
        return people;
    }

    @Override
    public People findById(int id) {
        Optional<People> person = peopleRepository.findById(id);
        return person.orElseThrow(() -> new PeopleNotFoundException("There is no person with such id"));
    }

    @Override
    public void deleteById(int id){
        peopleRepository.deleteById(id);
    }

    @Override
    public void add(PeopleDto peopleDto) {
        Integer wardId = peopleDto.getWardId();
        Integer diagnosisId = peopleDto.getDiagnosisId();
        Wards ward = wardService.findById(wardId);
        Diagnosis diagnos = diagnosisService.findById(diagnosisId);

        People person = new People(
                peopleDto.getFirstName(),
                peopleDto.getLastName(),
                peopleDto.getPatherName(),
                ward,
                diagnos
        );

        peopleRepository.save(person);
    }

    @Override
    public People updateById(Integer id, People person) {
        People nperson = findById(id);
        nperson.setFirstName(person.getFirstName());
        nperson.setLastName(person.getLastName());
        nperson.setPatherName(person.getPatherName());
        nperson.setDiagnosis(person.getDiagnosis());
        nperson.setWard(person.getWard());

        return nperson;
    }
}
