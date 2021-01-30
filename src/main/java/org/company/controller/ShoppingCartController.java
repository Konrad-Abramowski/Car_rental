package org.company.controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.company.model.Car;
import java.io.IOException;
import java.util.ArrayList;


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

    public static ArrayList<Car> carsList  = new ArrayList<>();

    ObservableList<Car> carsInCartObservableList;

    @FXML
    void btnBackToClientPanelHandler (ActionEvent event) throws IOException{
        SceneController.switchScenes(event,"client_panel");
    }
    @FXML
    void showCarsInCart(ActionEvent event) {
        showCarsInCart();
    }

    public void initialize() {
        showCarsInCart();
    }

    void showCarsInCart() {

        carsInCartObservableList = FXCollections.observableArrayList(carsList);
        car_ID_col.setCellValueFactory(new PropertyValueFactory<>("id"));
        brand_col.setCellValueFactory(new PropertyValueFactory<>("brand"));
        model_col.setCellValueFactory(new PropertyValueFactory<>("model"));
        price_col.setCellValueFactory(new PropertyValueFactory<>("price"));

        tableCars.setItems(carsInCartObservableList);
    }


}
