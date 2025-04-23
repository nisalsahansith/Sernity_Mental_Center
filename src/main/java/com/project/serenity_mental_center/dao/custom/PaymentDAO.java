package com.project.serenity_mental_center.dao.custom;

import com.project.serenity_mental_center.dao.CrudDAO;
import com.project.serenity_mental_center.entity.Payment;


import java.util.ArrayList;

public interface PaymentDAO extends CrudDAO<Payment> {
    public boolean update(Payment payment, String therapySessionId, String patientId, String programId);
    public Payment getBalance(String programID, String patientID);
    public ArrayList<Payment> findByPaymentId(String paymentId);
}
