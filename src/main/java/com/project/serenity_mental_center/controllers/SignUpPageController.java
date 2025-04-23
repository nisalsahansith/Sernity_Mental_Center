package com.project.serenity_mental_center.controllers;

import com.project.serenity_mental_center.bo.BOFactory;
import com.project.serenity_mental_center.bo.custom.impl.TherapistBOImpl;
import com.project.serenity_mental_center.bo.custom.impl.UserBOImpl;
import com.project.serenity_mental_center.dto.UserDto;
import com.project.serenity_mental_center.entity.User;
import com.project.serenity_mental_center.util.EncryptPassword;
import com.project.serenity_mental_center.util.Validation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class SignUpPageController implements Initializable {

    @FXML
    private Button btnSignUp;

    @FXML
    private Group grpHidePassword;

    @FXML
    private Group grptxtshowPassword;

    @FXML
    private Label lblError;

    @FXML
    private Label lblErrorEmail;

    @FXML
    private Label lblErrorName;

    @FXML
    private Label lblPassword;

    @FXML
    private Label lblSignIn;

    @FXML
    private Label lblUserName;

    @FXML
    private Pane signUpPane;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtPasswordField;

    @FXML
    private ComboBox<String> cmbRole;

    @FXML
    private TextField txtUserName;

    UserBOImpl userBO = (UserBOImpl) BOFactory.getInstance().getBO(BOFactory.BOType.USER);

    @FXML
    void navigateLogin(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LoginPage.fxml"));
            Parent homePageRoot = loader.load();
            Scene homeScene = new Scene(homePageRoot);

            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryStage.setScene(homeScene);
            primaryStage.setTitle("Login Page");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void navigateToHome(ActionEvent event) {
        String name = txtName.getText();
        String email = txtEmail.getText();
        boolean isCorrectEmail = Validation.isValid(email,"gmail");
        if (!isCorrectEmail){
            txtEmail.setStyle("-fx-border-color: red");
        }else {
            txtEmail.setStyle("-fx-border-color: black");
        }
        String password ;
        if (txtPassword.isVisible()){
            password = txtPassword.getText();
        }else {
            password = txtPasswordField.getText();
        }
        String role = cmbRole.getSelectionModel().getSelectedItem();
        String userName = txtUserName.getText();
        String userId = userBO.getNextUserId();
        if (isCorrectEmail && !name.isEmpty() && !password.isEmpty() && !role.isEmpty() && !userName.isEmpty() && !userId.isEmpty()) {
            boolean isHasEmail = userBO.getUserEmail(email);
            if (!isHasEmail) {
                String hashPassword = EncryptPassword.hashPassword(password);
                UserDto userDto = new UserDto(
                        userId,
                        name,
                        userName,
                        hashPassword,
                        role,
                        email
                );
                boolean isSave = userBO.saveUser(userDto);
                if (isSave) {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LoginPage.fxml"));
                        Parent homePageRoot = loader.load();
                        Scene homeScene = new Scene(homePageRoot);

                        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        primaryStage.setScene(homeScene);
                        primaryStage.setTitle("Login Page");
                        primaryStage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    lblError.setText("Sign Up is failed");
                }
            } else {
                lblError.setText("This email is already use");
            }
        }else {
            new Alert(Alert.AlertType.ERROR,"Invalid or Null input").show();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<String> roles = Arrays.asList("Admin", "Receptionist");
        ObservableList<String> roleList = FXCollections.observableArrayList(roles);
        cmbRole.setItems(roleList);
    }


    @FXML
    void hidePassword(MouseEvent event) {
        grptxtshowPassword.setVisible(false);
        grpHidePassword.setVisible(true);
    }

    @FXML
    void showPassword(MouseEvent event) {
        String pw = txtPassword.getText();
        txtPasswordField.setText(pw);
        grptxtshowPassword.setVisible(true);
        grpHidePassword.setVisible(false);
    }
}
