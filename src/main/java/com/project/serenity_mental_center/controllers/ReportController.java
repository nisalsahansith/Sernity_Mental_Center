package com.project.serenity_mental_center.controllers;

import com.project.serenity_mental_center.bo.BOFactory;
import com.project.serenity_mental_center.bo.custom.impl.PatientBOImpl;
import com.project.serenity_mental_center.bo.custom.impl.TherapistBOImpl;
import com.project.serenity_mental_center.dto.PatientDto;
import com.project.serenity_mental_center.dto.TherapySessionDTO;
import com.project.serenity_mental_center.entity.Patient;
import com.project.serenity_mental_center.entity.TherapySession;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ReportController implements Initializable {

    @FXML
    private Pane bodyPane;

    @FXML
    private ComboBox<String> cmbPatientId;

    @FXML
    private TextArea txtAreReport;

    PatientBOImpl patientBO = (PatientBOImpl) BOFactory.getInstance().getBO(BOFactory.BOType.PATIENT);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<String> patientId = patientBO.getAllPatientId();
        cmbPatientId.getItems().setAll(patientId);

        cmbPatientId.setOnAction(event -> {
            setPerformance();
        });
    }

    public void setPerformance(){
        String patientId = cmbPatientId.getSelectionModel().getSelectedItem();
        ArrayList<TherapySessionDTO> therapySessionDTOS = patientBO.getAllById(patientId);

        StringBuilder sessionDetails = new StringBuilder();

        for (TherapySessionDTO dto : therapySessionDTOS) {
            sessionDetails.append("Session ID: ").append(dto.getId()).append("\n");
            sessionDetails.append("Date: ").append(dto.getDate()).append("\n");
            sessionDetails.append("Time: ").append(dto.getStartTime()).append(" - ").append(dto.getEndTime()).append("\n");
            sessionDetails.append("Therapist: ").append(dto.getTherapistId()).append("\n");
            sessionDetails.append("==================================\n");
        }

        // Assuming you have a TextArea named txtSessionDetails
        txtAreReport.setText(sessionDetails.toString());

    }
}
