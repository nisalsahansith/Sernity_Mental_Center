package com.project.serenity_mental_center.bo.custom.impl;

import com.project.serenity_mental_center.dao.custom.impl.*;
import com.project.serenity_mental_center.dto.PatientProgramDto;
import com.project.serenity_mental_center.dto.PaymentDto;
import com.project.serenity_mental_center.entity.*;

import java.util.ArrayList;

public class PatientProgramBOImpl {
    PatientProgramDAOImpl patientProgramDAO = new PatientProgramDAOImpl();
    PaymentBOImpl paymentBO = new PaymentBOImpl();
    public ArrayList<PatientProgramDto> getAllPatientProgram() {
        ArrayList<PatientProgram> patientPrograms = patientProgramDAO.getAll();
        ArrayList<PatientProgramDto> patientProgramDtos = new ArrayList<>();
        for (PatientProgram program: patientPrograms){
            PatientProgramDto patientProgramDto = new PatientProgramDto(
                    program.getPatientId().getId(),
                    program.getProgramId().getId(),
                    program.getRegistration_date(),
                    program.getPayment().getId()
            );
            patientProgramDtos.add(patientProgramDto);
        }
        return patientProgramDtos;
    }

    public boolean savePatientProgram(PatientProgramDto patientProgramDto, PaymentDto paymentDto) {
        boolean isSavePayment = savePayment(paymentDto);
        if (isSavePayment){
//            Patient patient = patientDAO.getAllById(paymentDto.getPatientId());
//            TherapyProgram therapyProgram = therapyProgramDAO.getAllById(paymentDto.getProgramId());
            PatientProgramId patientProgramId = new PatientProgramId(paymentDto.getPatientId(),paymentDto.getProgramId());
//            PatientProgram program = new  PatientProgram(
//                    patientProgramId,
//                    patient,
//                    therapyProgram,
//                    patientProgramDto.getRegisterDate()
//            );
            boolean isSave = patientProgramDAO.save(patientProgramId,paymentDto.getPatientId(),paymentDto.getProgramId(),patientProgramDto.getRegisterDate(),patientProgramDto.getPaymentId());
            System.out.println(isSave);
            return isSave;
        }else {
            return false;
        }
    }

    public boolean savePayment(PaymentDto paymentDto) {
        return paymentBO.savePayment(paymentDto);
    }

    public boolean updatePatientProgram(PatientProgramDto patientProgramDto, PaymentDto paymentDto) {
        boolean isUpdatePayment = updatePayment(paymentDto);
        if (isUpdatePayment){
            PatientProgramId patientProgramId = new PatientProgramId(paymentDto.getPatientId(),paymentDto.getProgramId());
            boolean isUpdate = patientProgramDAO.update(patientProgramId,paymentDto.getPatientId(),paymentDto.getProgramId(),patientProgramDto.getRegisterDate(),patientProgramDto.getPaymentId());
            System.out.println(isUpdate);
            return isUpdate;
        }else {
            return false;
        }
    }

    public boolean updatePayment(PaymentDto paymentDto) {
        return paymentBO.updatePayment(paymentDto);
    }

    public boolean deletePatientProgram(String programId, String patientId, String paymentId) {
        PatientProgramId patientProgramId = new PatientProgramId(patientId, programId);
        return patientProgramDAO.delete(patientProgramId);
    }

    public ArrayList<String> getProgramsIdByPatient(String patientId) {
        ArrayList<PatientProgram> patientPrograms = patientProgramDAO.getAllByID(patientId);
        System.out.println(patientPrograms.isEmpty());
        ArrayList<String> therapyID = new ArrayList<>();
        for (PatientProgram program : patientPrograms){
            therapyID.add(program.getProgramId().getId());
        }
        return therapyID;
    }
}
