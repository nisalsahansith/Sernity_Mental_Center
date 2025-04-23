package com.project.serenity_mental_center.bo.custom;

import com.project.serenity_mental_center.bo.SuperBO;
import com.project.serenity_mental_center.dto.PaymentDto;
import com.project.serenity_mental_center.entity.Patient;
import com.project.serenity_mental_center.entity.Payment;
import com.project.serenity_mental_center.entity.TherapyProgram;
import com.project.serenity_mental_center.entity.TherapySession;

import java.util.ArrayList;

public interface PaymentBO extends SuperBO {
    public String getNextId();
    public boolean savePayment(PaymentDto paymentDto);
    public boolean updatePayment(PaymentDto paymentDto) ;
    public PaymentDto getAllById(String paymentId);
    public boolean delete(String paymentId);
    public ArrayList<PaymentDto> getAll();
    public boolean deleteSession(String paymentId);
    public double getBalance(String programID, String patientID);
    public ArrayList<PaymentDto> fondByPaymentId(String paymentId);
}
