package com.project.serenity_mental_center.bo.custom.impl;

import com.project.serenity_mental_center.bo.BOFactory;
import com.project.serenity_mental_center.bo.custom.PatientProgramBO;
import com.project.serenity_mental_center.dao.DAOFactory;
import com.project.serenity_mental_center.dao.custom.impl.*;
import com.project.serenity_mental_center.dto.PatientProgramDto;
import com.project.serenity_mental_center.dto.PaymentDto;
import com.project.serenity_mental_center.entity.*;

import java.util.ArrayList;

public class PatientProgramBOImpl implements PatientProgramBO {
    PatientProgramDAOImpl patientProgramDAO = (PatientProgramDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PATIENT_PROGRAM);
    PaymentBOImpl paymentBO = (PaymentBOImpl) BOFactory.getInstance().getBO(BOFactory.BOType.PAYMENT);
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
