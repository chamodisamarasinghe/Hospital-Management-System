package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Nurse;
import model.Pharmacist;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class ManageThePharmacyController extends PharmacyController{
    public AnchorPane pharmacyDetailsFormContext;
    public ComboBox<String> cmbPharmacistId;
    public TextField txtPharmacistId;
    public TextField txtPharmacistName;
    public TextField txtDrug;
    public TextField txtProductName;
    public ComboBox<String> cmbRefId;
    public TextField txtRefId;
    public ComboBox<String> cmbDrugs;
    public ComboBox<String> cmbProductName;
    public TableView tblPharmacy;
    public TableColumn colPharmacistId;
    public TableColumn colName;
    public TableColumn colDrug;
    public TableColumn colProduct;
    public TableColumn colRefId;

    public void initialize(){
        colPharmacistId.setStyle("-fx-border-color: black;-fx-table-cell-border-color:black;");
        colName.setStyle("-fx-border-color: black;-fx-table-cell-border-color:black;");
        colDrug.setStyle("-fx-border-color: black;-fx-table-cell-border-color:black;");
        colRefId.setStyle("-fx-border-color: black;-fx-table-cell-border-color:black;");




        colPharmacistId.setCellValueFactory(new PropertyValueFactory<>("pharmId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("pharmName"));
        colDrug.setCellValueFactory(new PropertyValueFactory<>("drugName"));
        colRefId.setCellValueFactory(new PropertyValueFactory<>("refId"));


        cmbDrugs.getItems().addAll("Amoxicillin", "Hydrochlorothiazide", "Amlodipine (generic for Norvasc)", "Lipitor (atorvastatin)", "Metformin", "Azithromycin", "Levothyroxine", "Lisinopril","Cannabis",
        "CNS Stimulants","Narcotic Analgesics","Hallucinogens","apixaban","adalimumab");
        cmbDrugs.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println(oldValue);
            System.out.println(newValue);
            txtDrug.setText(newValue);
        });

        cmbPharmacistId.getItems().addAll("PH-1", "PH-2", "PH-3","PH-4","PH-5","Ph-6","PH-7");
        cmbPharmacistId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println(oldValue);
            System.out.println(newValue);
            txtPharmacistId.setText(newValue);
        });



        cmbRefId.getItems().addAll("Ref-1","Ref-2");
        cmbRefId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println(oldValue);
            System.out.println(newValue);
            txtRefId.setText(newValue);
        });


        try {
            setPharmacistToTable(new PharmacyController().getAllPharmacists());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setPharmacistToTable(ArrayList<Pharmacist> allPharmacist) {
        ObservableList<Pharmacist> obList = FXCollections.observableArrayList();
        allPharmacist.forEach(e->{
            obList.add(
                    new Pharmacist(e.getPharmId(),e.getPharmName(),e.getDrugName(),e.getRefId()));
        });
        tblPharmacy.setItems(obList);
    }


    public void backToAdminOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/AdminForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) pharmacyDetailsFormContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void addOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
       Pharmacist h = new Pharmacist(
                txtPharmacistId.getText(),txtPharmacistName.getText(),txtDrug.getText(),txtRefId.getText()
        );

        if(new PharmacyController().savePharmacist(h))
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
        else
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
        setPharmacistToTable(new PharmacyController().getAllPharmacists());
    }



    public void updateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Pharmacist h = new Pharmacist(
                txtPharmacistId.getText(), txtPharmacistName.getText(), txtDrug.getText(), txtRefId.getText()
        );


        if (new PharmacyController().updatePharmacist(h))
            new Alert(Alert.AlertType.CONFIRMATION,"Updated..").show();
        else
            new Alert(Alert.AlertType.WARNING,"Try Again").show();
        setPharmacistToTable(new PharmacyController().getAllPharmacists());
    }



    public void deleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (new PharmacyController().deletePharmacist(txtPharmacistId.getText())){
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
        }else{
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
            setPharmacistToTable(new PharmacyController().getAllPharmacists());
        }
    }

    public void openProductFormOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/PlaceOrderForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) pharmacyDetailsFormContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void openOrdersFormOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/OrderForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) pharmacyDetailsFormContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void name_keyRealeased(KeyEvent keyEvent) {
        String regEx = "^[A-z ]{3,20}$";
        String typeText = txtPharmacistName.getText();
        Pattern compile = Pattern.compile(regEx);
        boolean matches = compile.matcher(typeText).matches();

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (matches) {
                txtPharmacistName.setStyle("-fx-border-color: dodgerblue");

            } else {
                txtPharmacistName.setStyle("-fx-border-color: red");

            }


            if (keyEvent.getCode() == KeyCode.ENTER) {
                if (matches) {
                    txtDrug.requestFocus();
                }

            }
        }

    }
}
