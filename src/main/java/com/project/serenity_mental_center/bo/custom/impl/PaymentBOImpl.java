package com.project.serenity_mental_center.bo.custom.impl;

import com.project.serenity_mental_center.dao.custom.impl.PatientDAOImpl;
import com.project.serenity_mental_center.dao.custom.impl.PaymentDAOImpl;
import com.project.serenity_mental_center.dao.custom.impl.TherapyProgramDAOImpl;
import com.project.serenity_mental_center.dao.custom.impl.TherapySessionDAOImpl;
import com.project.serenity_mental_center.dto.PaymentDto;
import com.project.serenity_mental_center.dto.TherapySessionDTO;
import com.project.serenity_mental_center.entity.Patient;
import com.project.serenity_mental_center.entity.Payment;
import com.project.serenity_mental_center.entity.TherapyProgram;
import com.project.serenity_mental_center.entity.TherapySession;

import java.util.ArrayList;

public class PaymentBOImpl {
    PaymentDAOImpl paymentDAO = new PaymentDAOImpl();
    PatientDAOImpl patientDAO = new PatientDAOImpl();
    TherapyProgramDAOImpl therapyProgramDAO = new TherapyProgramDAOImpl();
    TherapySessionDAOImpl therapySessionDAO = new TherapySessionDAOImpl();
    public String getNextId() {
        String lastId = paymentDAO.getLastId();
        if (lastId != null) {
            String subString = lastId.substring(3);
            int lastIndex = Integer.parseInt(subString);
            int nextIndex = lastIndex + 1;
            return String.format("PAY%03d", nextIndex);
        }
        return "PAY001";
    }

    public boolean savePayment(PaymentDto paymentDto) {
        Patient patient = patientDAO.getAllById(paymentDto.getPatientId());
        TherapyProgram therapyProgram = therapyProgramDAO.getAllById(paymentDto.getProgramId());
        TherapySession therapySession = therapySessionDAO.getAllById(paymentDto.getTherapySessionId());
        Payment payment = new Payment(
                paymentDto.getId(),
                paymentDto.getAmount(),
                paymentDto.getInstallment(),
                paymentDto.getStatus(),
                paymentDto.getBalance(),
                paymentDto.getDate(),
                patient,
                therapyProgram,
                therapySession
        );
        return paymentDAO.save(payment);
    }

    public boolean updatePayment(PaymentDto paymentDto) {
        Patient patient = patientDAO.getAllById(paymentDto.getPatientId());
        TherapyProgram therapyProgram = therapyProgramDAO.getAllById(paymentDto.getProgramId());
        TherapySession therapySession = therapySessionDAO.getAllById(paymentDto.getTherapySessionId());
        Payment payment = new Payment(
                paymentDto.getId(),
                paymentDto.getAmount(),
                paymentDto.getInstallment(),
                paymentDto.getStatus(),
                paymentDto.getBalance(),
                paymentDto.getDate(),
                patient,
                therapyProgram,
                therapySession
        );
        return paymentDAO.update(payment);
    }

    public PaymentDto getAllById(String paymentId) {
        Payment payment = paymentDAO.getAllById(paymentId);
        return new PaymentDto(
                payment.getProgram().getId(),
                payment.getPatient().getId(),
                payment.getDate(),
                payment.getBalance(),
                payment.getStatus(),
                payment.getInstallment(),
                payment.getAmount(),
                payment.getId()
        );
    }

    public boolean delete(String paymentId) {
        return paymentDAO.delete(paymentId);
    }
}
