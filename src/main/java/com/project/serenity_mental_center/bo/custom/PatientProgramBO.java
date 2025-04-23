package com.project.serenity_mental_center.bo.custom;

import com.project.serenity_mental_center.bo.SuperBO;
import com.project.serenity_mental_center.dto.PatientProgramDto;
import com.project.serenity_mental_center.dto.PaymentDto;
import com.project.serenity_mental_center.entity.PatientProgram;
import com.project.serenity_mental_center.entity.PatientProgramId;

import java.util.ArrayList;

public interface PatientProgramBO extends SuperBO {
    public ArrayList<PatientProgramDto> getAllPatientProgram();
    public boolean savePatientProgram(PatientProgramDto patientProgramDto, PaymentDto paymentDto);
    public boolean savePayment(PaymentDto paymentDto);
    public boolean updatePatientProgram(PatientProgramDto patientProgramDto, PaymentDto paymentDto);
    public boolean updatePayment(PaymentDto paymentDto);
    public boolean deletePatientProgram(String programId, String patientId, String paymentId);
    public ArrayList<String> getProgramsIdByPatient(String patientId);
}
