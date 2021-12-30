package controller;

import interfaces.PaymentManage;
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
import model.Nurse;
import model.Payment;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class AddAppointmentFormController extends PaymentController{
    public TextField txtAppointNum;
    public TextField txtCategory;
    public TextField txtRoomCharges;
    public TextField TxtDoctorCharge;
    public TextField txtTestings;
    public AnchorPane appointmentContext;
    public TextField txtAppointmentId;
    public TextField txtDoctorName;
    public TextField txtBillNo;
    public TextField txtDoctorCharge;
    public TableView tblPayment;
    public TableColumn colBillNo;
    public TableColumn colName;
    public TableColumn colCategory;
    public TableColumn colRoomCharge;
    public TableColumn colDoctorCharge;
    public TableColumn colTesting;
    public ComboBox<String> cmbDoctorName;
    public ComboBox<String> cmbCategory;

    public void initialize() {
        colBillNo.setStyle("-fx-border-color: black;-fx-table-cell-border-color:black;");
        colName.setStyle("-fx-border-color: black;-fx-table-cell-border-color:black;");
        colCategory.setStyle("-fx-border-color: black;-fx-table-cell-border-color:black;");
        colRoomCharge.setStyle("-fx-border-color: black;-fx-table-cell-border-color:black;");
        colDoctorCharge.setStyle("-fx-border-color: black;-fx-table-cell-border-color:black;");
        colTesting.setStyle("-fx-border-color: black;-fx-table-cell-border-color:black;");

        colBillNo.setCellValueFactory(new PropertyValueFactory<>("billNo"));
        colName.setCellValueFactory(new PropertyValueFactory<>("doctorName"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colRoomCharge.setCellValueFactory(new PropertyValueFactory<>("roomCharges"));
        colDoctorCharge.setCellValueFactory(new PropertyValueFactory<>("doctorCharge"));
        colTesting.setCellValueFactory(new PropertyValueFactory<>("testings"));


        cmbCategory.getItems().addAll("ENT", "VOG", "Neuro", "Gynaecologist", "Oncologist", "Surgeon", "Cardiologist", "Dentist", "Cancer");
        cmbCategory.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println(oldValue);
            System.out.println(newValue);
            txtCategory.setText(newValue);
        });


        try {
            setPaymentsToTable(new PaymentController().getAllPayments());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        try {
            loadDoctorName();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            loadDoctorId();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        cmbDoctorName.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setDoctorName(newValue);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {

                e.printStackTrace();
            }
        });
    }

    private void setDoctorName(String doctorId) throws SQLException, ClassNotFoundException {
        Doctor d = new DoctorController().getDoctor(doctorId);
        if (d == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set");
        } else {
            txtDoctorName.setText(d.getDoctorName());

        }
    }

    private void loadDoctorId() throws SQLException, ClassNotFoundException {
        List<String> DoctorId = new DoctorController().getDoctorId();
        cmbDoctorName.getItems().addAll(DoctorId);
    }

    private void loadDoctorName() throws SQLException, ClassNotFoundException {
        List<String> DoctorName = new ManageAllTheAppointmentsFormController().getDoctorName();
        cmbDoctorName.getItems().addAll(DoctorName);


    }



    private void setPaymentsToTable(ArrayList<Payment> allPayments) {
        ObservableList<Payment> obList = FXCollections.observableArrayList();
        allPayments.forEach(e -> {
            obList.add(
                    new Payment(e.getBillNo(), e.getDoctorName(), e.getCategory(), e.getRoomCharges(), e.getDoctorCharge(), e.getTestings()));
        });
        tblPayment.setItems(obList);
    }


    public void addOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Payment y = new Payment(
                txtBillNo.getText(), txtDoctorName.getText(), txtCategory.getText(), Double.parseDouble(txtRoomCharges.getText()), Double.parseDouble(txtDoctorCharge.getText()), Double.parseDouble(txtTestings.getText())
        );

        if (new PaymentController().savePayment(y))
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
        else
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
        setPaymentsToTable(new PaymentController().getAllPayments());

    }

    public void updateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Payment y= new Payment(
                txtBillNo.getText(), txtDoctorName.getText(), txtCategory.getText(), Double.parseDouble(txtRoomCharges.getText()), Double.parseDouble(txtDoctorCharge.getText()), Double.parseDouble(txtTestings.getText())

        );


        if (new PaymentController().updatePayment(y))
            new Alert(Alert.AlertType.CONFIRMATION,"Updated..").show();
        else
            new Alert(Alert.AlertType.WARNING,"Try Again").show();
        setPaymentsToTable(new PaymentController().getAllPayments());
    }

    public void deleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (new PaymentController().deletePayment(txtBillNo.getText())){
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
        }else{
            new Alert(Alert.AlertType.WARNING, "Try Again").show();

        }
    }

    public void backManagePaymentsOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/ManageThePaymentsForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) appointmentContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void billNo_keyRealeased(KeyEvent keyEvent) {
        String regEx = "^(B00-)[0-9]{3,4}$";
        String typeText = txtBillNo.getText();
        Pattern compile = Pattern.compile(regEx);
        boolean matches = compile.matcher(typeText).matches();

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (matches) {
                txtBillNo.setStyle("-fx-border-color: dodgerblue");

            } else {
                txtBillNo.setStyle("-fx-border-color: red");

            }

            if (keyEvent.getCode() == KeyCode.ENTER) {
                if (matches) {
                    txtDoctorName.requestFocus();


                }
            }
        }
    }


    public void roomCharge_keyRealeased(KeyEvent keyEvent) {
        String regEx = "^[0-9]{3,}.[0-9]{2}$";
        String typeText = txtRoomCharges.getText();
        Pattern compile = Pattern.compile(regEx);
        boolean matches = compile.matcher(typeText).matches();

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (matches) {
                txtRoomCharges.setStyle("-fx-border-color: dodgerblue");

            } else {
                txtRoomCharges.setStyle("-fx-border-color: red");

            }

            if (keyEvent.getCode() == KeyCode.ENTER) {
                if (matches) {
                    txtDoctorCharge.requestFocus();


                }
            }
        }
    }

    public void doctorCharge_keyRealeased(KeyEvent keyEvent) {
        String regEx = "^[0-9]{3,}.[0-9]{2}$";
        String typeText = txtDoctorCharge.getText();
        Pattern compile = Pattern.compile(regEx);
        boolean matches = compile.matcher(typeText).matches();

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (matches) {
                txtDoctorCharge.setStyle("-fx-border-color: dodgerblue");

            } else {
                txtDoctorCharge.setStyle("-fx-border-color: red");

            }

            if (keyEvent.getCode() == KeyCode.ENTER) {
                if (matches) {
                    txtTestings.requestFocus();


                }
            }
        }

    }

    public void testing_keyRealeased(KeyEvent keyEvent) {
        String regEx = "^[0-9]{3,}.[0-9]{2}$";
        String typeText = txtTestings.getText();
        Pattern compile = Pattern.compile(regEx);
        boolean matches = compile.matcher(typeText).matches();

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (matches) {
                txtTestings.setStyle("-fx-border-color: dodgerblue");

            } else {
                txtTestings.setStyle("-fx-border-color: red");

            }

            if (keyEvent.getCode() == KeyCode.ENTER) {
                if (matches) {
                    txtTestings.requestFocus();


                }
            }
        }

    }
}
