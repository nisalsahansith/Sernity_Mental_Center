package com.project.serenity_mental_center.entity;

import jakarta.persistence.*;
import lombok.*;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
@Entity
@Table
public class Payment {
    @Id
    String id;

    @Column(nullable = false)
    double amount;

    String installment;
    String status;
    double balance;

    @Column(nullable = false)
    Date date;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "therapy_program_id")
    private TherapyProgram program;

    @ManyToOne
    @JoinColumn(name = "therapy_session_id")
    @Nullable
    private TherapySession therapySession;
}
