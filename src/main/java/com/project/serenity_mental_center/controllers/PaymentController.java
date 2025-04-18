package com.project.serenity_mental_center.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class PaymentController {

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableView<?> tablePatient;

    @FXML
    private TableColumn<?, ?> tblCharge;

    @FXML
    private TableColumn<?, ?> tblInstallment;

    @FXML
    private TableColumn<?, ?> tblPatientId;

    @FXML
    private TableColumn<?, ?> tblPaymentId;

    @FXML
    private TableColumn<?, ?> tblSessionId;

    @FXML
    private TableColumn<?, ?> tblStatus;

    @FXML
    private TextField txtCharge;

    @FXML
    private TextField txtInstallment;

    @FXML
    private TextField txtPAtientId;

    @FXML
    private TextField txtPaymentId;

    @FXML
    private TextField txtSessionId;

    @FXML
    private TextField txtStatus;

    @FXML
    private Pane userPane;

    @FXML
    void deletePayment(ActionEvent event) {

    }

    @FXML
    void donePayment(ActionEvent event) {

    }

    @FXML
    void resetPage(ActionEvent event) {

    }

    @FXML
    void tableClick(MouseEvent event) {

    }

    @FXML
    void updatePayment(ActionEvent event) {

    }

}


