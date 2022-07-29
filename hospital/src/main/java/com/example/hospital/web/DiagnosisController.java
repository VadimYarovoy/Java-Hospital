package com.example.hospital.web;

import com.example.hospital.entity.Diagnosis;
import com.example.hospital.exception.DiagnosNotFoundException;
import com.example.hospital.service.DiagnosisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/diagnosis")
public class DiagnosisController {
    @Autowired
    private DiagnosisService diagnosisService;

    @PostMapping(
            value = "/add",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<Diagnosis> add(@RequestBody Diagnosis diagnos) {
        diagnosisService.add(diagnos);
        return new ResponseEntity<>(diagnos, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Diagnosis> findById(@PathVariable("id") int id) {
        try {
            Diagnosis diagnos = diagnosisService.findById(id);
            return new ResponseEntity<>(diagnos, HttpStatus.OK);
        } catch (DiagnosNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable("id") Integer id) {
        try {
            diagnosisService.deleteById(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (DiagnosNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PutMapping(
            value = "/update/{id}",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<Diagnosis> updateById(
            @PathVariable("id") Integer id,
            @RequestBody Diagnosis pdiagnos
    ) {
        try {
            Diagnosis diagnos = diagnosisService.updateById(id, pdiagnos);
            return new ResponseEntity<>(diagnos, HttpStatus.OK);
        } catch (DiagnosNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
