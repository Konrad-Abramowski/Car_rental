package org.company.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import org.company.App;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtPassword;
    @FXML
    private ToggleGroup userType;
    @FXML
    private Button btnSignin;
    @FXML
    private Button btnSignup;

    @FXML
    public void handleButtonAction(ActionEvent event) throws IOException {
        if (event.getSource() == btnSignin) {
            if (txtUsername.getText().trim().isEmpty()) {
                txtUsername.setPromptText("Username must not be empty!");
            }
            if (txtPassword.getText().trim().isEmpty()) {
                txtPassword.setPromptText("Password must not be empty!");
            }
            if (!txtUsername.getText().trim().isEmpty() || !txtPassword.getText().trim().isEmpty()) {
                RadioButton selectedRadioButton = (RadioButton) userType.getSelectedToggle();
                String selectedUserType = selectedRadioButton.getText();
                if (selectedUserType.equals("Client")) {
                    int userId = App.clientDao.login(
                            txtUsername.getText().trim(),
                            txtPassword.getText().trim());
                    if (userId != 0) {
                        App.activeUserId = userId;
                        SceneController.switchScenes(event, "client_panel");
                    } else {
                        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                        errorAlert.setHeaderText("Login error");
                        errorAlert.setContentText("Bad credentials. Please login again.");
                        errorAlert.showAndWait();
                    }
                } else {
                    int userId = App.employeeDao.login(
                            txtUsername.getText().trim(),
                            txtPassword.getText().trim());
                    if (userId != 0) {
                        App.activeUserId = userId;
                        SceneController.switchScenes(event, "employee_panel");
                    } else {
                        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                        errorAlert.setHeaderText("Login error");
                        errorAlert.setContentText("Bad credentials. Please login again.");
                        errorAlert.showAndWait();
                    }
                }
            }
        } else if (event.getSource() == btnSignup){
            SceneController.switchScenes(event, "register");
        }
    }
}
