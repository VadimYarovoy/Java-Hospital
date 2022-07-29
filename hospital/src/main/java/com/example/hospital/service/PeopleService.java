package com.example.hospital.service;

import com.example.hospital.dto.PeopleDto;
import com.example.hospital.entity.People;

import java.util.List;

public interface PeopleService {
    List<People> findPeopleByDiagnosisName(String name);
    People findById(int id);
    void deleteById(int id);
    void add(PeopleDto peopleDto);

    People updateById(Integer id, People person);
}
