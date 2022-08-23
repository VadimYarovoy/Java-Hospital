package com.example.hospital.service;

import com.example.hospital.dto.PeopleDto;
import com.example.hospital.entity.People;

import java.util.List;

public interface PeopleService {
    People findById(int id);
    void deleteById(int id);
    void add(PeopleDto peopleDto);
    People updateById(Integer id, PeopleDto person);
    List<People> findPeopleByDiagnosisName(String name);
    List<People> findPeopleByDiagnosisId(Integer id);
    List<People> findPeopleByWardName(String name);
    List<People> findPeopleByWardId(Integer id);

    List<People> findAll();

}
