package com.project.serenity_mental_center.controllers;

import com.project.serenity_mental_center.bo.custom.impl.PatientBOImpl;
import com.project.serenity_mental_center.bo.custom.impl.PatientProgramBOImpl;
import com.project.serenity_mental_center.bo.custom.impl.PaymentBOImpl;
import com.project.serenity_mental_center.bo.custom.impl.TherapyProgramBOImpl;
import com.project.serenity_mental_center.dto.PatientProgramDto;
import com.project.serenity_mental_center.dto.PaymentDto;
import com.project.serenity_mental_center.dto.tm.PatientProgramTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class RegisterToProgramController implements Initializable {

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnRemove;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnUpdate;

    @FXML
    private ComboBox<String> cmbPatientId;

    @FXML
    private ComboBox<String> cmbPrograms;

    @FXML
    private TableColumn<String, PatientProgramTM> colPatientId;

    @FXML
    private TableColumn<String, PatientProgramTM> colProgramId;

    @FXML
    private TableColumn<Date, PatientProgramTM> colRegDate;

    @FXML
    private DatePicker datePick;

    @FXML
    private Label lblProgramId;

    @FXML
    private Label lblTherpistId;

    @FXML
    private Pane programPane;

    @FXML
    private TableView<PatientProgramTM> tableRegistration;

    @FXML
    private TextField txtAmmount;

    @FXML
    private TextField txtBalance;

    @FXML
    private TextField txtInstallement;

    @FXML
    private TextField txtPaymentId;

    @FXML
    private TextField txtStatus;

    PatientProgramBOImpl patientProgramBO = new PatientProgramBOImpl();
    PaymentBOImpl paymentBO = new PaymentBOImpl();
    PatientBOImpl patientBO = new PatientBOImpl();
    TherapyProgramBOImpl therapyProgramBO = new TherapyProgramBOImpl();

    @FXML
    void delete(ActionEvent event) {
        PatientProgramTM selectedPatientProgram = tableRegistration.getSelectionModel().getSelectedItem();
        if (selectedPatientProgram != null){
            cmbPrograms.setValue(selectedPatientProgram.getProgramId());
            cmbPatientId.setValue(selectedPatientProgram.getPatientId());
            datePick.setValue(selectedPatientProgram.getRegisterDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        }
    }

    @FXML
    void onClick(MouseEvent event) {

    }

    @FXML
    void resetPage(ActionEvent event) {

    }

    @FXML
    void save(ActionEvent event) {
        String programId = cmbPrograms.getSelectionModel().getSelectedItem();
        String patientId = cmbPatientId.getSelectionModel().getSelectedItem();
        String paymentId = txtPaymentId.getText();
        double ammount = Double.parseDouble(txtAmmount.getText());
        LocalDate localDate = datePick.getValue();
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        String status = txtStatus.getText();
        String installment = txtInstallement.getText();
        double balance = Double.parseDouble(txtBalance.getText());
        PaymentDto paymentDto = new PaymentDto(
                programId,
                patientId,
                date,
                balance,
                status,
                installment,
                ammount,
                paymentId
        );
        PatientProgramDto patientProgramDto = new PatientProgramDto(
                patientId,
                programId,
                date
        );
        boolean isSave = patientProgramBO.savePatientProgram(patientProgramDto,paymentDto);
        if (isSave){
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION,"Patient Registration To Program is Success").show();
        }else {
            new Alert(Alert.AlertType.INFORMATION,"Patient Registration To Program is Not Success").show();
        }
    }

    @FXML
    void update(ActionEvent event) {
        String programId = cmbPrograms.getSelectionModel().getSelectedItem();
        String patientId = cmbPatientId.getSelectionModel().getSelectedItem();
        String paymentId = txtPaymentId.getText();
        double ammount = Double.parseDouble(txtAmmount.getText());
        LocalDate localDate = datePick.getValue();
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        String status = txtStatus.getText();
        String installment = txtInstallement.getText();
        double balance = Double.parseDouble(txtBalance.getText());
        PaymentDto paymentDto = new PaymentDto(
                programId,
                patientId,
                date,
                balance,
                status,
                installment,
                ammount,
                paymentId
        );
        PatientProgramDto patientProgramDto = new PatientProgramDto(
                patientId,
                programId,
                date
        );
        boolean isUpdate = patientProgramBO.updatePatientProgram(patientProgramDto,paymentDto);
        if (isUpdate){
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION,"Patient Registration To Program is Updated").show();
        }else {
            new Alert(Alert.AlertType.INFORMATION,"Patient Registration To Program is Not Update").show();
        }
    }

    @FXML
    void setBalance(KeyEvent event) {
        try {
            String programID = cmbPrograms.getSelectionModel().getSelectedItem();
            double fee = therapyProgramBO.getFee(programID);
            System.out.println(fee);
            double amount = Double.parseDouble(txtAmmount.getText());
            double balance = fee - amount;
            txtBalance.setText(String.valueOf(balance));
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colPatientId.setCellValueFactory(new PropertyValueFactory<>("patientId"));
        colProgramId.setCellValueFactory(new PropertyValueFactory<>("programId"));
        colRegDate.setCellValueFactory(new PropertyValueFactory<>("registerDate"));
        try {
            refreshPage();
            setPatientId();
            setProgramIds();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void refreshPage(){
        refreshTable();
        cmbPrograms.setValue("");
        cmbPatientId.setValue("");
        txtAmmount.setText("");
        txtBalance.setText("");
        txtInstallement.setText("");
        txtStatus.setText("");
        txtPaymentId.setText(paymentBO.getNextId());

        btnAdd.setDisable(false);
        btnRemove.setDisable(true);
        btnUpdate.setDisable(true);
    }

    public void refreshTable(){
        ArrayList<PatientProgramDto> patientProgramDtos = patientProgramBO.getAllPatientProgram();
        ObservableList<PatientProgramTM> patientProgramTMS = FXCollections.observableArrayList();
        for (PatientProgramDto patientProgramDto : patientProgramDtos){
            PatientProgramTM patientProgramTM = new PatientProgramTM(
                    patientProgramDto.getPatientId(),
                    patientProgramDto.getProgramId(),
                    patientProgramDto.getRegisterDate()
            );
            patientProgramTMS.add(patientProgramTM);
        }
        tableRegistration.setItems(patientProgramTMS);
    }

    public void setPatientId(){
        cmbPatientId.setPromptText("Select Patient ID");
        ArrayList<String> iDS = patientBO.getAllPatientId();
        cmbPatientId.getItems().addAll(iDS);
    }

    public void setProgramIds(){
        cmbPrograms.setPromptText("Select Programs ID");
        ArrayList<String> iDS = therapyProgramBO.getAllProgramId();
        cmbPrograms.getItems().addAll(iDS);
    }
}
