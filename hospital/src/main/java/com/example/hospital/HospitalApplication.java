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

import java.util.List;

@SpringBootApplication
public class HospitalApplication {
    private static final Logger logger = LoggerFactory.getLogger(HospitalApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(HospitalApplication.class, args);
    }

    @Bean
    public CommandLineRunner test(
            PeopleRepository peopleRepository,
            WardsRepository wardsRepository,
            DiagnosisRepository diagnosisRepository
    ) {
        return args -> {
            /*Diagnosis diagnos = new Diagnosis("Волчанка");
            diagnosisRepository.save(diagnos);
            logger.info(diagnos.toString());*/

            /*Wards ward = new Wards("номер 6", 5);
            wardsRepository.save(ward);

            Diagnosis diagnos = diagnosisRepository.findById(2).get();

            People person = new People("Иван", "Иванов", "Иванович",ward,diagnos);
            peopleRepository.save(person);
            logger.info(ward.toString());
            logger.info(diagnos.toString());
            logger.info(person.toString());*/

            /*List<People> people = (List<People>) peopleRepository.findPeopleByDiagnosisName("Волчанка");

            for (People man: people) {
                logger.info(man.toString());
            }*/
        };
    }
}
