package com.example.hospital.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonSerialize
public class PeopleDto {
    private String firstName;
    private String lastName;
    private String patherName;
    private Integer wardId;
    private Integer diagnosisId;

    public PeopleDto(String firstName, String lastName, String patherName, Integer wardId, Integer diagnosisId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patherName = patherName;
        this.wardId = wardId;
        this.diagnosisId = diagnosisId;
    }

    @Override
    public String toString() {
        return "PeopleDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", patherName='" + patherName + '\'' +
                ", wardId=" + wardId +
                ", diagnosisId=" + diagnosisId +
                '}';
    }
}
