package org.company.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.company.model.Car;

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
    void btnConfirmReservationHandler(ActionEvent event) throws IOException {
        LocalDate startDate = startDatePicker.getValue();
        LocalDate endDate = endDatePicker.getValue();
        if (LocalDate.now().compareTo(startDate)>0 ){
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("!");
            errorAlert.showAndWait();
        }
        if(startDate.compareTo(endDate) > 0){
            System.out.println("gicior");
        }

//        String [] startDateSplited = startDatePicker.toString().split("\\.");
//        String [] endDateSplited = endDatePicker.toString().split("\\.");
//        if (Integer.parseInt(startDateSplited[0]) < Integer.parseInt(endDateSplited[0]) &&
//                Integer.parseInt(startDateSplited[1]) <= Integer.parseInt(endDateSplited[1]) &&
//                Integer.parseInt(startDateSplited[2]) <= Integer.parseInt(endDateSplited[2])) {
//        }
        SceneController.switchScenes(event,"client_panel_shopping_cart");
    }

    private void calculateReservationCost(ArrayList<Car> carsList){

    }
}
