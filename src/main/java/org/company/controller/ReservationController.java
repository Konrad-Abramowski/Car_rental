package org.company.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.company.App;
import org.company.model.Car;
import org.company.model.Client;
import org.company.model.Loan;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class ReservationController {

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    private TextField totalCostTextField;

    @FXML
    private Button btnConfirmReservation;

    @FXML
    private Button btnCalculateCost;

    long daysOfLoan;
    LocalDate startLoanDate;
    LocalDate endLoanDate;
    @FXML
    void btnCalculateCostHandler(ActionEvent event) {
        startLoanDate = startDatePicker.getValue();
        endLoanDate = endDatePicker.getValue();
        if (LocalDate.now().compareTo(startLoanDate)>0 ){
            displayAllert("Incorrect dates!");
        }
        else if(startLoanDate.compareTo(endLoanDate) >= 0){
            displayAllert("Incorrect dates!");
        }
        else {
            daysOfLoan = ChronoUnit.DAYS.between(startLoanDate, endLoanDate);
            float totalCost = calculateReservationCost(ShoppingCartController.carsList);
            totalCostTextField.setText(Float.toString(totalCost));
        }
    }

    @FXML
    void btnConfirmReservationHandler(ActionEvent event) throws IOException {
        if (!totalCostTextField.getText().isEmpty()) {
            LocalDateTime localStartDateTime = startLoanDate.atStartOfDay();
            LocalDateTime localEndDateTime = startLoanDate.atStartOfDay();

            for (Car car : ShoppingCartController.carsList) {
                float price = car.getPrice() * daysOfLoan;
                Client client = App.clientDao.get(App.activeUserId).get();
                Loan loan = new Loan(localStartDateTime, localEndDateTime, price, client, car);
                App.loanDao.create(loan);
            }
            ShoppingCartController.carsList.clear();

            Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);
            errorAlert.setHeaderText("You have confirmed the reservation successfully!");
            errorAlert.showAndWait();
            SceneController.switchScenes(event, "client_panel_shopping_cart");
        }
        else {
            displayAllert("You have to calculate cost first");
        }
    }

    private void  displayAllert(String allertText){
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setHeaderText(allertText);
        errorAlert.showAndWait();
    }

    private float calculateReservationCost(ArrayList<Car> carsList){
        float reservationCost = 0;
        for (Car car:carsList
             ) {
            reservationCost += car.getPrice() * daysOfLoan;
        }
        return reservationCost;
    }
}
