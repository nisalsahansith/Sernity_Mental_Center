package com.project.serenity_mental_center.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class PatientProgramId {
    private String patientId;
    private String programId;
}
