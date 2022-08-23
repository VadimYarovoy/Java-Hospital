package com.example.hospital.service;


import com.example.hospital.repository.PeopleRepository;
import com.example.hospital.entity.Diagnosis;
import com.example.hospital.entity.People;
import com.example.hospital.entity.Wards;
import com.example.hospital.exception.PeopleNotFoundException;
import com.example.hospital.exception.WardNotFoundException;
import com.example.hospital.repository.PeopleRepository;
import com.example.hospital.repository.WardsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WardServiceImpl implements WardService{
    @Autowired
    private WardsRepository wardsRepository;
    @Autowired
    private PeopleRepository peopleRepository;

    @Override
    public Wards findById(int id) {
        Optional<Wards> ward = wardsRepository.findById(id);
        return ward.orElseThrow(() -> new WardNotFoundException("There is no ward with such id"));
    }

    @Override
    public void add(Wards ward) {
        wardsRepository.save(ward);
    }

    @Override
    public void deleteById(int id) {
        Wards ward = findById(id);
        wardsRepository.delete(ward);
    }

    @Override
    public Wards updateById(Integer id, Wards ward) {
        Wards pward = findById(id);
        pward.setName(ward.getName());
        pward.setMaxCount(ward.getMaxCount());

        wardsRepository.save(pward);

        return pward;
    }

    @Override
    public List<Wards> findWardByPersonName(String name) {
        List<Wards> ward = (List<Wards>) peopleRepository.findWardByPersonName(name);
        return ward;
    }

    @Override
    public List<Wards> findWardByPersonId(Integer id) {
        List<Wards> ward = (List<Wards>) peopleRepository.findWardByPersonId(id);
        return ward;
    }
}
