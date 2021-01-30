package org.company.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.company.App;
import org.company.model.Account;
import org.company.model.Address;
import org.company.model.Client;

import java.io.IOException;

public class RegisterController {
    @FXML
    private TextField firstNameTF;
    @FXML
    private TextField lastNameTF;
    @FXML
    private TextField loginTF;
    @FXML
    private TextField passwordTF;
    @FXML
    private TextField emailTF;
    @FXML
    private TextField streetTF;
    @FXML
    private TextField houseTF;
    @FXML
    private TextField countryTF;
    @FXML
    private TextField postalCodeTF;
    @FXML
    private TextField cityTF;


    @FXML
    private Button addClientBtn;
    @FXML
    private Button returnBtn;

    public void handleButtonAction(ActionEvent event) throws IOException {
        if (event.getSource() == addClientBtn) {
            String firstName = firstNameTF.getText().trim();
            String lastName = lastNameTF.getText().trim();
            String login = loginTF.getText().trim();
            String password = passwordTF.getText().trim();
            String email = emailTF.getText().trim();
            String street = streetTF.getText().trim();
            String house = houseTF.getText().trim();
            String country = countryTF.getText().trim();
            String postalCode = postalCodeTF.getText().trim();
            String city = cityTF.getText().trim();
            if (firstName.isEmpty()) {
                firstNameTF.setPromptText("First name must not be empty!");
            }
            if (lastName.isEmpty()) {
                lastNameTF.setPromptText("Last name must not be empty!");
            }
            if (login.isEmpty()) {
                loginTF.setPromptText("Login must not be empty!");
            }
            if (password.isEmpty()) {
                passwordTF.setPromptText("Password must not be empty!");
            }
            if (email.isEmpty()) {
                emailTF.setPromptText("Email must not be empty!");
            }
            if (street.isEmpty()) {
                streetTF.setPromptText("Street name must not be empty!");
            }
            if (house.isEmpty()) {
                houseTF.setPromptText("House number must not be empty!");
            }
            if (country.isEmpty()) {
                countryTF.setPromptText("Country must not be empty!");
            }
            if (postalCode.isEmpty()) {
                postalCodeTF.setPromptText("Postal code must not be empty!");
            }
            if (city.isEmpty()) {
                cityTF.setPromptText("City must not be empty!");
            }
            if (!firstName.isEmpty() && !lastName.isEmpty()
                    && !login.isEmpty() && !password.isEmpty()
                    && !email.isEmpty() && !street.isEmpty()
                    && !house.isEmpty() && !country.isEmpty()
                    && !postalCode.isEmpty() && !city.isEmpty()) {
                Address address = new Address(country, city, street,house,postalCode);
                Account account = new Account(login,password,email);
                Client client = new Client(firstName,lastName,account,address);
                App.clientDao.create(client);
                SceneController.switchScenes(event, "login", "view/css/login.css");
                } else {
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setHeaderText("Login error");
                    errorAlert.setContentText("Bad credentials. Please login again.");
                    errorAlert.showAndWait();
                }
            } else if (event.getSource() == returnBtn){
            SceneController.switchScenes(event, "login", "view/css/login.css");
        }
    }
}
