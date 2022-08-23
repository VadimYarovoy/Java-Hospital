package com.example.hospital.repository;

import com.example.hospital.entity.Wards;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface WardsRepository extends CrudRepository<Wards, Integer> {

}
