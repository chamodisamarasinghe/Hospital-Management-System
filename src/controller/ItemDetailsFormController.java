package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.OrderDetail;
import tm.OrderDetailTM;
import util.DataBaseAccessCode;

import java.sql.SQLException;

public class ItemDetailsFormController {
    public Label lblOrderId;
    public TableView<OrderDetailTM> tblMedicalDetails;
    public TableColumn colProductCode;
    public TableColumn colQty;
    public TableColumn colUnitPrice;
    public Label lblTotalCost;

    public void initialize(){
        colProductCode.setStyle("-fx-border-color: black;-fx-table-cell-border-color:black;");
        colQty.setStyle("-fx-border-color: black;-fx-table-cell-border-color:black;");
        colUnitPrice.setStyle("-fx-border-color: black;-fx-table-cell-border-color:black;");



        colProductCode.setCellValueFactory(new PropertyValueFactory<>("productCode"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    public void loadAllData(String id){
        lblOrderId.setText(id);
        double total=0;
        try {
            ObservableList<OrderDetailTM> tmList = FXCollections.observableArrayList();
            for (OrderDetail tempD : new DataBaseAccessCode().getAllOrderDetails(id)
            ) {
                total+=tempD.getPrice();
                tmList.add(new OrderDetailTM(
                        tempD.getProductCode(), tempD.getQty(), tempD.getPrice()));
            }
            tblMedicalDetails.setItems(tmList);
            lblTotalCost.setText(total+"/=");

            new DataBaseAccessCode().getAllOrderDetails(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
