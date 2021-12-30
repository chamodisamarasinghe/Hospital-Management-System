package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.LabDetail;
import model.Nurse;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

public class LabDetailsController extends LabController{
    public AnchorPane labDetailsFormContext;
    public ComboBox<String> cmbTestings;
    public ComboBox<String> cmbBloodTypes;
    public TextField txtBloodTypes;
    public TextField txtTestings;
    public TextField txtDoctorName;
    public TextField txtPatientId;
    public TableView tblLabDetail;
    public TableColumn colName;
    public TableColumn colId;
    public TableColumn colBloodType;
    //public TableColumn colTesting;
    public TableColumn colTestings;
    //public TableColumn colTestings;
    //public TableColumn colTestings;


    public void initialize(){
        colId.setStyle("-fx-border-color: -fx-box-border;");
        colId.setStyle("-fx-border-color: black;-fx-table-cell-border-color:black;");
        colBloodType.setStyle("-fx-border-color: black;-fx-table-cell-border-color:black;");
        colTestings.setStyle("-fx-border-color: black;-fx-table-cell-border-color:black;");
        colName.setStyle("-fx-border-color: black;-fx-table-cell-border-color:black;");



        colId.setCellValueFactory(new PropertyValueFactory<>("patientId"));
        colBloodType.setCellValueFactory(new PropertyValueFactory<>("bloodTypes"));
        colTestings.setCellValueFactory(new PropertyValueFactory<>("testings"));
        colName.setCellValueFactory(new PropertyValueFactory<>("doctorName"));


        cmbBloodTypes.getItems().addAll("AB-negative", "B-negative", "AB-positive", "A-negative", "O-negative", "B-positive", "A-positive", "O-positive");
        cmbBloodTypes.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println(oldValue);
            System.out.println(newValue);
            txtBloodTypes.setText(newValue);
        });

        cmbTestings.getItems().addAll("Diabetes", "High blood pressure/ Cholesterol", "Osteoporosis or weak bones", "Mental health condition / depression", "Alergy testing", "DNA testing", "Drug testing", "Prothrombin test",
        "Lipid panel","Lever panel","Thyroid stimulating hormone","Hemoglobin A1C","Radioisotope(nuclear) scanning","Computed tomography(CT)","Magnetic resonance iamagine","Position emission tomography(PET)");
        cmbTestings.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println(oldValue);
            System.out.println(newValue);
            txtTestings.setText(newValue);
        });

        try {
            setDataToTable(new LabDetailsController().getAllDetails());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void setDataToTable(ArrayList<LabDetail> allDetail) {
        ObservableList<LabDetail> obList = FXCollections.observableArrayList();
        allDetail.forEach(e->{
            obList.add(
                    new LabDetail(e.getPatientId(),e.getBloodTypes(),e.getTestings(),e.getDoctorName()));
        });
        tblLabDetail.setItems(obList);
    }

    public void backToDoctorFormOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/DoctorForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) labDetailsFormContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }



    public void saveOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        LabDetail l = new LabDetail(
                txtPatientId.getText(),txtBloodTypes.getText(),txtTestings.getText(),txtDoctorName.getText()
        );

        if(new LabDetailsController().saveDetail(l))
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
        else
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
        setDataToTable(new LabDetailsController().getAllDetails());
    }



    public void updateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        LabDetail l= new LabDetail(
                txtPatientId.getText(),txtBloodTypes.getText(),
                txtTestings.getText(), txtDoctorName.getText()
        );


        if (new LabDetailsController().updateDetail(l))
            new Alert(Alert.AlertType.CONFIRMATION,"Updated..").show();
        else
            new Alert(Alert.AlertType.WARNING,"Try Again").show();
        setDataToTable(new LabDetailsController().getAllDetails());
    }

    public void deleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (new LabDetailsController().deleteDetail(txtPatientId.getText())){
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
        }else{
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
            setDataToTable(new LabDetailsController().getAllDetails());
        }
    }

    public void searchOnAction(ActionEvent actionEvent) {

    }

    private void setData(LabDetail l) {

    }
}
