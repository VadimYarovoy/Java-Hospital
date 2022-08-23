package com.example.hospital;

import com.example.hospital.entity.Diagnosis;
import com.example.hospital.entity.People;
import com.example.hospital.entity.Wards;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.example.hospital.repository.DiagnosisRepository;
import com.example.hospital.repository.PeopleRepository;
import com.example.hospital.repository.WardsRepository;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class HospitalApplication {
    private static final Logger logger = LoggerFactory.getLogger(HospitalApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(HospitalApplication.class, args);
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    };
    @Bean
    public CommandLineRunner test(
            PeopleRepository peopleRepository,
            WardsRepository wardsRepository,
            DiagnosisRepository diagnosisRepository
    ) {
        return args -> {

        };
    }
}
