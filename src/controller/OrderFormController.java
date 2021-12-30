package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.NewOrder;
import tm.OrderTM;
import util.DataBaseAccessCode;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

public class OrderFormController {
    public TableView<OrderTM> tblOrders;
    public TableColumn colPId;
    public TableColumn colPName;
    public TableColumn colOrderId;
    public TableColumn colOrderDate;
    public TableColumn colTotalCost;
    public AnchorPane orderFormContext;


    public void initialize(){
        colPId.setStyle("-fx-border-color: black;-fx-table-cell-border-color:black;");
        colPName.setStyle("-fx-border-color: black;-fx-table-cell-border-color:black;");
        colOrderId.setStyle("-fx-border-color: black;-fx-table-cell-border-color:black;");
        colOrderDate.setStyle("-fx-border-color: black;-fx-table-cell-border-color:black;");
        colTotalCost.setStyle("-fx-border-color: black;-fx-table-cell-border-color:black;");



        colPId.setCellValueFactory(new PropertyValueFactory<>("pharmId"));
        colPName.setCellValueFactory(new PropertyValueFactory<>("pharmName"));
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colOrderDate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        colTotalCost.setCellValueFactory(new PropertyValueFactory<>("cost"));



        try {
            loadAllData();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        tblOrders.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    try {
                        loadDetailsUi(newValue.getOrderId());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                });




    }

    private void loadDetailsUi(String orderId) throws IOException {
        FXMLLoader fxmlLoader =
                new FXMLLoader(getClass().getResource("../views/ItemDetailsForm.fxml"));
        Parent load = fxmlLoader.load();
        ItemDetailsFormController controller = fxmlLoader.getController();
        controller.loadAllData(orderId);
        Stage stage = new Stage();
        stage.setScene(new Scene(load));
        stage.show();
    }




    private void loadAllData() throws SQLException, ClassNotFoundException {
        ObservableList<OrderTM> tmList = FXCollections.observableArrayList();
        for (NewOrder tempNewOrder:new DataBaseAccessCode().getAllOrders()
        ) {
            tmList.add(
                    new OrderTM(tempNewOrder.getPharmtId(),
                            tempNewOrder.getPharmName(),
                            tempNewOrder.getOrderId(),
                            tempNewOrder.getOrderDate(),
                            tempNewOrder.getCost())
            );
        }

        tblOrders.setItems(tmList);
    }


    public void backToPharmacistOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/ManageThePharmacy.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) orderFormContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }
}

