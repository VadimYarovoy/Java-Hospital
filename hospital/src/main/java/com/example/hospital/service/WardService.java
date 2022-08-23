package com.example.hospital.service;

import com.example.hospital.dto.PeopleDto;
import com.example.hospital.entity.People;
import com.example.hospital.entity.Wards;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WardService {
    Wards findById(int id);
    void add(Wards ward);
    void deleteById(int id);
    Wards updateById(Integer id, Wards ward);
    List<Wards> findWardByPersonName(String name);
    List<Wards> findWardByPersonId(Integer id);

}
