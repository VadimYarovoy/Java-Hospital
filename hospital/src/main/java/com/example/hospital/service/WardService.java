package com.example.hospital.service;

import com.example.hospital.dto.PeopleDto;
import com.example.hospital.entity.People;
import com.example.hospital.entity.Wards;

public interface WardService {
    Wards findById(int id);
    void add(Wards ward);

    void deleteById(int id);

    Wards updateById(Integer id, Wards ward);
}
