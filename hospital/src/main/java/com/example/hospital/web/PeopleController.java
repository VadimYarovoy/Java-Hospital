package com.example.hospital.web;

import com.example.hospital.dto.PeopleDto;
import com.example.hospital.entity.People;
import com.example.hospital.exception.DiagnosNotFoundException;
import com.example.hospital.exception.PeopleNotFoundException;
import com.example.hospital.exception.WardNotFoundException;
import com.example.hospital.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/people")
public class PeopleController {
    @Autowired
    private PeopleService peopleService;

    @GetMapping("/find/{id}")
    public ResponseEntity<People> findById(@PathVariable("id") int id) {
        try {
            People person = peopleService.findById(id);
            return new ResponseEntity<>(person, HttpStatus.OK);
        } catch (PeopleNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable("id") int id) {
        peopleService.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(
            value = "/add",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity add(@RequestBody PeopleDto person) {
        try {
            peopleService.add(person);
            return new ResponseEntity(HttpStatus.OK);
        } catch (WardNotFoundException | DiagnosNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PutMapping(
            value = "/update/{id}",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<People> updateById(
            @PathVariable("id") Integer id,
            @RequestBody People person
    ) {
        try {
            People auto = peopleService.updateById(id, person);
            return new ResponseEntity<>(auto, HttpStatus.OK);
        } catch (PeopleNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
