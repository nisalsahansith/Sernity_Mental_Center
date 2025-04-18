package com.project.serenity_mental_center.controllers;

import com.project.serenity_mental_center.bo.custom.impl.*;
import com.project.serenity_mental_center.dto.TherapySessionDTO;
import com.project.serenity_mental_center.dto.tm.TherapySessionTM;
import com.project.serenity_mental_center.entity.TherapistProgram;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TherapySessionController implements Initializable {

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnCancle;

    @FXML
    private Button btnReschedule;

    @FXML
    private Button btnReset;

    @FXML
    private ComboBox<String> cmbPatientId;

    @FXML
    private ComboBox<String> cmbProgramId;

    @FXML
    private ComboBox<String> cmbTherapistId;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colDay;

    @FXML
    private TableColumn<?, ?> colEndTime;

    @FXML
    private TableColumn<?, ?> colPatientId;

    @FXML
    private TableColumn<?, ?> colProgramId;

    @FXML
    private TableColumn<?, ?> colSessionId;

    @FXML
    private TableColumn<?, ?> colStartTime;

    @FXML
    private TableColumn<?, ?> colTherapistId;

    @FXML
    private TableColumn<?, ?> colTime;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Label lblEndTime;

    @FXML
    private Label lblStartTime;

    @FXML
    private TableView<?> tableAvailability;

    @FXML
    private TableView<TherapySessionTM> tableSession;

    @FXML
    private TextField txtEndTime;

    @FXML
    private TextField txtSessionId;

    @FXML
    private TextField txtStartTime;

    @FXML
    private Pane userPane;

    TherapySessionBOImpl therapySessionBO = new TherapySessionBOImpl();
    TherapyProgramBOImpl therapyProgramBO = new TherapyProgramBOImpl();
    PatientBOImpl patientBO = new PatientBOImpl();
    TherapistBOImpl therapistBO = new TherapistBOImpl();
    TherapistProgramBOImpl therapistProgramBO = new TherapistProgramBOImpl();

    @FXML
    void addSession(ActionEvent event) {

    }

    @FXML
    void cancleSession(ActionEvent event) {

    }

    @FXML
    void rescheduleSession(ActionEvent event) {

    }

    @FXML
    void resetPage(ActionEvent event) {

    }

    @FXML
    void tableClick(MouseEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colSessionId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colProgramId.setCellValueFactory(new PropertyValueFactory<>("therapyProgramID"));
        colTherapistId.setCellValueFactory(new PropertyValueFactory<>("therapistId"));
        colPatientId.setCellValueFactory(new PropertyValueFactory<>("patientId"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colStartTime.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        colEndTime.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        try {
            refreshPage();
            setProgramIds();
            setPatientIds();
            setTherapistIds();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void refreshPage(){
        refreshTable();
        cmbPatientId.setValue("");
        cmbProgramId.setValue("");
        cmbTherapistId.setValue("");
        txtSessionId.setText(therapySessionBO.getNextSessionId());
        txtEndTime.setText("");
        txtStartTime.setText("");
        datePicker.cancelEdit();
        cmbTherapistId.setDisable(true);
    }

    public void refreshTable(){
        ArrayList<TherapySessionDTO> therapySessionDTOS = therapySessionBO.getAll();
        ObservableList<TherapySessionTM> therapySessionTMS = FXCollections.observableArrayList();
        for (TherapySessionDTO therapySessionDTO : therapySessionDTOS){
            TherapySessionTM tm = new TherapySessionTM(
                    therapySessionDTO.getId(),
                    therapySessionDTO.getDate(),
                    therapySessionDTO.getStartTime(),
                    therapySessionDTO.getEndTime(),
                    therapySessionDTO.getTherapistId(),
                    therapySessionDTO.getPatientId(),
                    therapySessionDTO.getTherapyProgramID()
            );
            therapySessionTMS.add(tm);
        }
        tableSession.setItems(therapySessionTMS);
    }

    public void setProgramIds(){
        cmbProgramId.setPromptText("Select Program ID");
        ArrayList<String> iDS = therapyProgramBO.getAllProgramId();
        cmbProgramId.getItems().addAll(iDS);
    }

    public void setPatientIds(){
        cmbPatientId.setPromptText("Select Patient ID");
        ArrayList<String> iDS = patientBO.getAllPatientId();
        cmbPatientId.getItems().addAll(iDS);
    }

    public void setTherapistIds(){
        String programId = cmbProgramId.getSelectionModel().getSelectedItem();
        cmbTherapistId.setPromptText("Select Therapist ID");
        if (programId.isEmpty()){
            cmbTherapistId.setDisable(false);
            ArrayList<String> therapistId = therapistBO.getAllTherapistId();
            cmbTherapistId.getItems().addAll(therapistId);
        }else{
            ArrayList<String> IDS = therapistProgramBO.getTherapistId(programId);
            cmbTherapistId.getItems().addAll(IDS);
            cmbTherapistId.setDisable(true);
        }
    }
}
