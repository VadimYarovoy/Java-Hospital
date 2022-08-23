package com.example.hospital.service;

import com.example.hospital.dto.PeopleDto;
import com.example.hospital.entity.Diagnosis;
import com.example.hospital.entity.People;
import com.example.hospital.entity.Wards;
import com.example.hospital.exception.PeopleNotFoundException;
import com.example.hospital.exception.WardIsFullException;
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

        Long currentCount = findAll()
                .stream()
                .filter(p -> p.getWard() == ward)
                .count();

        if (currentCount == ward.getMaxCount().longValue()) {
            throw new WardIsFullException("Ward is full");
        }

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
    public People updateById(Integer id, PeopleDto person) {
        People nperson = findById(id);
        nperson.setFirstName(person.getFirstName());
        nperson.setLastName(person.getLastName());
        nperson.setPatherName(person.getPatherName());

        Diagnosis diagnos = diagnosisService.findById(person.getDiagnosisId());
        Wards ward = wardService.findById(person.getWardId());

        nperson.setDiagnosis(diagnos);
        nperson.setWard(ward);

        peopleRepository.save(nperson);

        return nperson;
    }

    @Override
    public List<People> findPeopleByDiagnosisName(String name) {
        List<People> people = (List<People>) peopleRepository.findPeopleByDiagnosisName(name);
        return people;
    }

    @Override
    public List<People> findPeopleByDiagnosisId(Integer id) {
        List<People> people = (List<People>) peopleRepository.findPeopleByDiagnosisId(id);
        return people;
    }

    @Override
    public List<People> findPeopleByWardName(String name) {
        List<People> people = (List<People>) peopleRepository.findPeopleByWardName(name);
        return people;
    }

    @Override
    public List<People> findPeopleByWardId(Integer id) {
        List<People> people = (List<People>) peopleRepository.findPeopleByWardId(id);
        return people;
    }


    @Override
    public List<People> findAll() {
        return (List<People>) peopleRepository.findAll();
    }
}
