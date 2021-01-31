package org.company.controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
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
    private TableColumn<Car, Void> remove_col;

    @FXML
    private Button btnBackToClientPanel;

    @FXML
    private Button btnMakeReservation;

    public static ArrayList<Car> carsList  = new ArrayList<>();

    ObservableList<Car> carsInCartObservableList;

    @FXML
    void btnMakeReservationHandler(ActionEvent event) throws IOException{
        if (carsList.size()>0){
            SceneController.switchScenes(event,"client_panel_make_reservation");
        }
        else{
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Your shopping cart must not be empty!");
            errorAlert.showAndWait();
        }
    }

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
        addButtonsToTable();
    }
    private void addButtonsToTable() {

        Callback<TableColumn<Car, Void>, TableCell<Car, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Car, Void> call(final TableColumn<Car, Void> param) {
                return new TableCell<>() {

                    private final Button btn = new Button("Remove");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Car car = getTableView().getItems().get(getIndex());
                            carsList.remove(car);
                            showCarsInCart();
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
            }
        };

        remove_col.setCellFactory(cellFactory);

    }

}
