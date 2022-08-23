package com.example.hospital.web;

import com.example.hospital.entity.Diagnosis;
import com.example.hospital.entity.People;
import com.example.hospital.entity.Wards;
import com.example.hospital.exception.PeopleNotFoundException;
import com.example.hospital.exception.WardNotFoundException;
import com.example.hospital.service.PeopleService;
import com.example.hospital.service.WardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/wards")
public class WardsController {
    @Autowired
    private WardService wardService;
    @Autowired
    private PeopleService peopleService;


    @PostMapping(
            value = "/add",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<Wards> add(@RequestBody Wards ward) {
        wardService.add(ward);
        return new ResponseEntity<>(ward, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Wards> findById(@PathVariable("id") int id) {
        try {
            Wards ward = wardService.findById(id);
            return new ResponseEntity<>(ward, HttpStatus.OK);
        } catch (WardNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable("id") int id) {
        wardService.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping(
            value = "/update/{id}",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<Wards> updateById(
            @PathVariable("id") Integer id,
            @RequestBody Wards ward
    ) {
        try {
            Wards pward = wardService.updateById(id, ward);
            return new ResponseEntity<>(ward, HttpStatus.OK);
        } catch (WardNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }


    @GetMapping("/find-by-person-id/{id}")
    public ResponseEntity<List<Wards>> findByPersonId(@PathVariable("id") Integer id) {
        try {
            List<Wards> ward = wardService.findWardByPersonId(id);
            return new ResponseEntity<>(ward, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/find-by-person-name/{name}")
    public ResponseEntity<List<Wards>> findByDiagnosisName(@PathVariable("name") String name) {
        try {
            List<Wards> ward = wardService.findWardByPersonName(name);
            return new ResponseEntity<>(ward, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
