package controller;

import db.DbConnection;
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
import model.Doctor;
import model.Patient;
import tm.PatientTM;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class
ManageAllTheAppointmentsFormController extends PatientController{
    public AnchorPane appointmentFormContext;
    public TextField txtPatientNo;
    public TextField txtRoomNo;
    public TextField txtPatientName;
    public TextField txtAge;
    public TextField txtAddress;
    public TextField txtBirthday;
    public TextField txtGender;
    public TextField txtDoctorName;
    public TextField txtCategory;
    public TextField txtWardNo;
    public ComboBox<String> cmbCategory;
    public ComboBox<String> cmbWardNo;
    public ComboBox<String> cmbGender;
    public ComboBox<String> cmbRoomNo;
    public TableView tblPatient;
    public TableColumn colPatientNo;
    public TableColumn colRoomNo;
    public TableColumn colDoctorName;
    public TableColumn colPatientName;
    public TableColumn colAge;
    public TableColumn colAddress;
    public TableColumn colBirthday;
    public TableColumn colGender;
    public TableColumn colCategory;
    public TableColumn colWardNo;
    private int cartSelectedRowForRemove=-1;


    public void initialize(){
        colPatientNo.setStyle("-fx-border-color: black;-fx-table-cell-border-color:black;");
        colRoomNo.setStyle("-fx-border-color: black;-fx-table-cell-border-color:black;");
        colDoctorName.setStyle("-fx-border-color: black;-fx-table-cell-border-color:black;");
        colPatientName.setStyle("-fx-border-color: black;-fx-table-cell-border-color:black;");
        colAge.setStyle("-fx-border-color: black;-fx-table-cell-border-color:black;");
        colAddress.setStyle("-fx-border-color: black;-fx-table-cell-border-color:black;");
        colBirthday.setStyle("-fx-border-color: black;-fx-table-cell-border-color:black;");
        colCategory.setStyle("-fx-border-color: black;-fx-table-cell-border-color:black;");
        colGender.setStyle("-fx-border-color: black;-fx-table-cell-border-color:black;");
        colWardNo.setStyle("-fx-border-color: black;-fx-table-cell-border-color:black;");



        colPatientNo.setCellValueFactory(new PropertyValueFactory<>("patientNo"));
        colRoomNo.setCellValueFactory(new PropertyValueFactory<>("roomNo"));
        colDoctorName.setCellValueFactory(new PropertyValueFactory<>("doctorName"));
        colPatientName.setCellValueFactory(new PropertyValueFactory<>("patientName"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colBirthday.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colWardNo.setCellValueFactory(new PropertyValueFactory<>("wardNo"));

        try {
            setPatientsToTable(new PatientController().getAllPatients());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        cmbGender.getItems().addAll("Male","Female");
        cmbGender.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println(oldValue);
            System.out.println(newValue);
            txtGender.setText(newValue);
        });

        cmbCategory.getItems().addAll("ENT","VOG","Neuro","Gynaecologist","Oncologist","Surgeon","Cardiologist","Dentist","Cancer");
        cmbCategory.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println(oldValue);
            System.out.println(newValue);
            txtCategory.setText(newValue);
        });

        cmbWardNo.getItems().addAll("W-1","W-2","W-3","W-4","W-5","W-6","W-7","W-8","W-9","W-10","W-11","W-12","W-13");
        cmbWardNo.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println(oldValue);
            System.out.println(newValue);
            txtWardNo.setText(newValue);
        });

        cmbRoomNo.getItems().addAll("R-1","R-2","R-3","R-4","R-5","R-6","R-7","R-8","R-9","R-10","R-11","R-12","R-13","R-14","R-15");
        cmbRoomNo.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println(oldValue);
            System.out.println(newValue);
            txtRoomNo.setText(newValue);
        });

    }

    private void setPatientsToTable(ArrayList<Patient> allPatients) {
        ObservableList<Patient> obList = FXCollections.observableArrayList();
        allPatients.forEach(e->{
            obList.add(
                    new Patient(e.getPatientNo(),e.getRoomNo(),e.getDoctorName(),e.getPatientName(),e.getAge(),e.getAddress(),e.getBirthday(),e.getCategory(),e.getGender(),e.getWardNo()));
        });
        tblPatient.setItems(obList);
    }


    public void backReceptionistOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/ReceptionistForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) appointmentFormContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void saveOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Patient p = new Patient(
                txtPatientNo.getText(),txtRoomNo.getText(),txtDoctorName.getText(), txtPatientName.getText(),Integer.valueOf(txtAge.getText()),txtAddress.getText(),txtBirthday.getText(),txtCategory.getText(),txtGender.getText(),txtWardNo.getText()
        );

        if(new PatientController().savePatient(p))
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
        else
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
        setPatientsToTable(new PatientController().getAllPatients());
    }

    public void updateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Patient p= new Patient(
                txtPatientNo.getText(),txtRoomNo.getText(),txtDoctorName.getText(), txtPatientName.getText(),Integer.valueOf(txtAge.getText()),txtAddress.getText(),txtBirthday.getText(),txtCategory.getText(),txtGender.getText(),txtWardNo.getText()
        );
        if (new PatientController().updatePatient(p))
            new Alert(Alert.AlertType.CONFIRMATION,"Updated..").show();
        else
            new Alert(Alert.AlertType.WARNING,"Try Again").show();
    }

    public void cancelOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (new PatientController().deletePatient(txtPatientNo.getText())){
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
        }else{
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
            setPatientsToTable(new PatientController().getAllPatients());
        }
    }

    ObservableList<PatientTM> obList= FXCollections.observableArrayList();
    public void addToTableOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String patientNo = txtPatientNo.getText();

        Patient p= new PatientController().getPatient(patientNo);
        if (p==null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        } else {
            setData(p);
        }
    }

    private void setData(Patient p) {
        txtPatientNo.setText(p.getPatientNo());
        txtRoomNo.setText(p.getRoomNo());
        txtDoctorName.setText(p.getDoctorName());
        txtPatientName.setText(p.getPatientName());
        txtAge.setText(String.valueOf(p.getAge()));
        txtAddress.setText(p.getAddress());
        txtBirthday.setText(p.getBirthday());
        txtCategory.setText(p.getCategory());
        txtGender.setText(p.getGender());
        txtWardNo.setText(p.getWardNo());
    }


    public void deleteOnAction(ActionEvent actionEvent) {
        if (cartSelectedRowForRemove == -1) {
            new Alert(Alert.AlertType.WARNING, "Please Select a row").show();
        } else {
            obList.remove(cartSelectedRowForRemove);
            tblPatient.refresh();
        }
    }

    public List<String> getDoctorName() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().
                getConnection().prepareStatement("SELECT * FROM Doctor").executeQuery();
        List<String> codes = new ArrayList<>();
        while (rst.next()) {
            codes.add(
                    rst.getString(1)
            );
        }
        return codes;
    }

    public void patientNo_keyRealeased(KeyEvent keyEvent) {
        String regEx = "^(P00-)[0-9]{3,4}$";
        String typeText = txtPatientNo.getText();
        Pattern compile = Pattern.compile(regEx);
        boolean matches = compile.matcher(typeText).matches();

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (matches) {
                txtPatientNo.setStyle("-fx-border-color: dodgerblue");

            } else {
                txtPatientNo.setStyle("-fx-border-color: red");

            }

            if (keyEvent.getCode() == KeyCode.ENTER) {
                if (matches) {
                    txtRoomNo.requestFocus();

                }
            }
        }
    }

    public void patientName_keyRealeased(KeyEvent keyEvent) {
        String regEx = "^[A-z ]{3,20}$";
        String typeText = txtPatientName.getText();
        Pattern compile = Pattern.compile(regEx);
        boolean matches = compile.matcher(typeText).matches();

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (matches) {
                txtPatientName.setStyle("-fx-border-color: dodgerblue");

            } else {
                txtPatientName.setStyle("-fx-border-color: red");

            }

            if (keyEvent.getCode() == KeyCode.ENTER) {
                if (matches) {
                    txtAge.requestFocus();
                }
            }
        }
    }



    public void age_keyRealeased(KeyEvent keyEvent) {
        String regEx = "^[0-9]{1,2}$";
        String typeText = txtAge.getText();
        Pattern compile = Pattern.compile(regEx);
        boolean matches = compile.matcher(typeText).matches();

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (matches) {
                txtAge.setStyle("-fx-border-color: dodgerblue");

            } else {
                txtAge.setStyle("-fx-border-color: red");

            }


            if (keyEvent.getCode() == KeyCode.ENTER) {
                if (matches) {
                    txtAddress.requestFocus();
                }

            }
        }

    }



    public void address_keyRealeased(KeyEvent keyEvent) {
        String regEx = "^[A-z0-9, ]{6,30}$";
        String typeText = txtAddress.getText();
        Pattern compile = Pattern.compile(regEx);
        boolean matches = compile.matcher(typeText).matches();

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (matches) {
                txtAddress.setStyle("-fx-border-color: dodgerblue");

            } else {
                txtAddress.setStyle("-fx-border-color: red");

            }


            if (keyEvent.getCode() == KeyCode.ENTER) {
                if (matches) {
                    txtBirthday.requestFocus();
                }

            }
        }
    }



    public void birthday_keyRealeased(KeyEvent keyEvent) {
        String regEx = "^[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$";
        String typeText = txtBirthday.getText();
        Pattern compile = Pattern.compile(regEx);
        boolean matches = compile.matcher(typeText).matches();

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (matches) {
                txtBirthday.setStyle("-fx-border-color: dodgerblue");

            } else {
                txtBirthday.setStyle("-fx-border-color: red");

            }


            if (keyEvent.getCode() == KeyCode.ENTER) {
                if (matches) {
                    txtCategory.requestFocus();
                }

            }
        }
    }



    public void docName_keyRealeased(KeyEvent keyEvent) {
        String regEx = "^[A-z ]{3,20}$";
        String typeText = txtDoctorName.getText();
        Pattern compile = Pattern.compile(regEx);
        boolean matches = compile.matcher(typeText).matches();

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (matches) {
                txtDoctorName.setStyle("-fx-border-color: dodgerblue");

            } else {
                txtDoctorName.setStyle("-fx-border-color: red");

            }


            if (keyEvent.getCode() == KeyCode.ENTER) {
                if (matches) {
                    txtPatientName.requestFocus();
                }

            }
        }

    }
}
