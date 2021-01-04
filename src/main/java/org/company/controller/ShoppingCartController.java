package org.company.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.company.model.Car;

import java.io.IOException;

public class ShoppingCartController {
    @FXML
    private TableView<Car> tableCars;

    @FXML
    private TableColumn<Car, Integer> car_ID_col;

    @FXML
    private TableColumn<Car, String> brand_col;

    @FXML
    private TableColumn<Car, String> model_col;

    @FXML
    private TableColumn<Car, Integer> price_col;

    @FXML
    private TextField totalCostTextField;

    @FXML
    private Button btnBackToClientPanel;

    @FXML
    private Button btnMakeReservation;


    @FXML
    void btnBackToClientPanelHandler (ActionEvent event) throws IOException{
        SceneController.switchScenes(event,"client_panel");
    }


}
