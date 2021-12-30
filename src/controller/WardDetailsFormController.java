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
import model.Ward;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class WardDetailsFormController extends WardController{
    public AnchorPane wardDetailsFormContext;
    public TextField txtAdmitDate;
    public TextField txtAvailabilityRooms;
    public TextField txtId;
    public TableView tblWard;
    public TableColumn colId;
    public TableColumn colBedNo;
    public TableColumn colAdmitDate;
    public TableColumn colAvailabilityRooms;
    public TextField txtBedNo;
    public ComboBox cmbWard;
    public TextField txtAdmitDate1;
    public TextField txtPatientId;
    public TextField txtNurseId;
    public ComboBox<String> cmbNurseId;
    public TextField txtWardNo;
    public TextField txtNurseName;
    public ComboBox<String> cmbBedNo;
    public ComboBox<String> cmbPatientId;
    public ComboBox<String> cmbAvailable;
    private String nurseId;

    public void initialize() {
        colId.setStyle("-fx-border-color: black;-fx-table-cell-border-color:black;");
        colBedNo.setStyle("-fx-border-color: black;-fx-table-cell-border-color:black;");
        colAdmitDate.setStyle("-fx-border-color: black;-fx-table-cell-border-color:black;");
        colAvailabilityRooms.setStyle("-fx-border-color: black;-fx-table-cell-border-color:black;");



        colId.setCellValueFactory(new PropertyValueFactory<>("patientId"));
        colBedNo.setCellValueFactory(new PropertyValueFactory<>("bedNo"));
        colAdmitDate.setCellValueFactory(new PropertyValueFactory<>("admitDate"));
        colAvailabilityRooms.setCellValueFactory(new PropertyValueFactory<>("availabilityOfRooms"));

        try {
            loadNurseId();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        cmbNurseId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setNurseDate(newValue);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        });

        cmbPatientId.getItems().addAll("P00-1", "P00-2", "P00-3");
        cmbPatientId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println(oldValue);
            System.out.println(newValue);
            txtPatientId.setText(newValue);
        });

        cmbBedNo.getItems().addAll("B-1", "B-2", "B-3", "B-4", "B-5", "B-6", "B-7", "B-8", "B-9","B-10","B-11","B-12","B-13","B-14","B-15");
        cmbBedNo.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println(oldValue);
            System.out.println(newValue);
            txtBedNo.setText(newValue);
        });

        cmbAvailable.getItems().addAll("YES", "NO");
        cmbAvailable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println(oldValue);
            System.out.println(newValue);
            txtAvailabilityRooms.setText(newValue);
        });





        try {
            setWardToTable(new WardController().getAllWards());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    private void setWardToTable(ArrayList<Ward> allWards) {
        ObservableList<Ward> obList = FXCollections.observableArrayList();
        allWards.forEach(e->{
            obList.add(
                    new Ward(e.getPatientId(),e.getBedNo(),e.getAdmitDate(),e.getAvailabilityOfRooms()));
        });
        tblWard.setItems(obList);
    }

    private void setNurseDate(String nurseId) throws SQLException, ClassNotFoundException {
        Nurse n = new NurseController().getNurse(nurseId);
        if (n == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set");
        } else {
            txtWardNo.setText(n.getWardNo());
            txtNurseName.setText(n.getNurseName());
        }
    }

    private void loadNurseId() throws SQLException, ClassNotFoundException {
        List<String> NurseId = new NurseController().getNurseId(nurseId);
        cmbNurseId.getItems().addAll(NurseId);
    }


    public void backToDoctorFormOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/AdminForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) wardDetailsFormContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }



    public void addOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Ward w = new Ward(
                txtPatientId.getText(),
                txtBedNo.getText(),
                txtAdmitDate.getText(),
                txtAvailabilityRooms.getText()
        );

        if(new WardController().saveWard(w))
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
        else
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
        setWardToTable(new WardController().getAllWards());
    }

    public void updateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Ward w= new Ward(
                txtPatientId.getText(),txtBedNo.getText(),
                txtAdmitDate.getText(), txtAvailabilityRooms.getText()
        );


        if (new WardController().updateWard(w))
            new Alert(Alert.AlertType.CONFIRMATION,"Updated..").show();
        else
            new Alert(Alert.AlertType.WARNING,"Try Again").show();
        setWardToTable(new WardController().getAllWards());

    }

    public void deleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (new WardController().deleteWard(txtPatientId.getText())){
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
        }else{
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
            setWardToTable(new WardController().getAllWards());
        }

    }

    public void admitDate_keyRealeased(KeyEvent keyEvent) {
        String regEx = "^[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$";
        String typeText = txtAdmitDate.getText();
        Pattern compile = Pattern.compile(regEx);
        boolean matches = compile.matcher(typeText).matches();

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (matches) {
                txtAdmitDate.setStyle("-fx-border-color: dodgerblue");

            } else {
                txtAdmitDate.setStyle("-fx-border-color: red");

            }


            if (keyEvent.getCode() == KeyCode.ENTER) {
                if (matches) {
                    txtAvailabilityRooms.requestFocus();
                }

            }
        }
    }
}
