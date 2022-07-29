package com.example.hospital.web;

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

@RestController
@RequestMapping("/wards")
public class WardsController {
    @Autowired
    private WardService wardService;


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

    @GetMapping("/delete/{id}")
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
}
