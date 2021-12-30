package controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import tm.CalculateTM;
import tm.CartTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ManageThePaymentsFormController {
    public AnchorPane paymentFormContext;
    public TextField txtRoomCharges;
    public TextField txtDoctorCharge;
    public TextField txtTestings;
    public TextField txtDoctorName;
    public TextField txtCategory;
    public ComboBox<String> cmbBillNo;
    public TextField txtPatientName;
    public ComboBox<String> cmbPatientNo;
    public TextField txtAge;
    public TextField txtGender;
    public TableView tblAppointment;
    public TableColumn colBillNo;
    public TableColumn colDoctorNAme;
    public TableColumn colCategory;
    public TableColumn colRoomCharges;
    public TableColumn colDoctorCharges;
    public TableColumn colTestings;
    public TableColumn colTotal;
    public Label txtDate;
    public Label txtTime;
    public Label lblDate;
    public Label lblTime;
    public Label lblTotal;
    public Label lblAppointmentId;
    public Label lblAppointmentId1;
    public AnchorPane btnBill;
    int cartSelectedRowForRemove = -1;


    public void initialize() {
        colBillNo.setStyle("-fx-border-color: black;-fx-table-cell-border-color:black;");
        colDoctorNAme.setStyle("-fx-border-color: black;-fx-table-cell-border-color:black;");
        colCategory.setStyle("-fx-border-color: black;-fx-table-cell-border-color:black;");
        colRoomCharges.setStyle("-fx-border-color: black;-fx-table-cell-border-color:black;");
        colDoctorCharges.setStyle("-fx-border-color: black;-fx-table-cell-border-color:black;");
        colTestings.setStyle("-fx-border-color: black;-fx-table-cell-border-color:black;");
        colTotal.setStyle("-fx-border-color: black;-fx-table-cell-border-color:black;");



        colBillNo.setCellValueFactory(new PropertyValueFactory<>("billNo"));
        colDoctorNAme.setCellValueFactory(new PropertyValueFactory<>("doctorName"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colRoomCharges.setCellValueFactory(new PropertyValueFactory<>("roomCharges"));
        colDoctorCharges.setCellValueFactory(new PropertyValueFactory<>("doctorCharge"));
        colTestings.setCellValueFactory(new PropertyValueFactory<>("testings"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));


        try {
            setAppointmentNo();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        try {
            loadBillNo();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        cmbBillNo.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setPaymentData(newValue);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        try {
            loadPatientNo();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        cmbPatientNo.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setPatientDate(newValue);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        tblAppointment.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            cartSelectedRowForRemove = (int) newValue;
        });

        loadDateAndTime();
    }

    private void loadDateAndTime() {
        Date date=new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(f.format(date));

        Timeline time = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            lblTime.setText(
                    currentTime.getHour() + " : " + currentTime.getMinute() +
                            " : " + currentTime.getSecond()
            );
        }),
                new KeyFrame(Duration.seconds(1))
        );
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
    }

    private void setPatientDate(String patientNo) throws SQLException, ClassNotFoundException {
        Patient p = new PatientController().getPatient(patientNo);
        if (p == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set");
        } else {
            txtPatientName.setText(p.getPatientName());
            txtAge.setText(String.valueOf(p.getAge()));
            txtGender.setText(p.getGender());
        }
    }

    private void loadPatientNo() throws SQLException, ClassNotFoundException {
        List<String> PatientNo = new PatientController().getPatientNo();
        cmbPatientNo.getItems().addAll(PatientNo);
    }



    private void setPaymentData(String billNo) throws SQLException, ClassNotFoundException {
       Payment y = new PaymentController().getPayment(billNo);
        if (y == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set");
        } else {
            txtDoctorName.setText(y.getDoctorName());
            txtCategory.setText(y.getCategory());
            txtRoomCharges.setText(String.valueOf(y.getRoomCharges()));
            txtDoctorCharge.setText(String.valueOf(y.getDoctorCharge()));
            txtTestings.setText(String.valueOf(y.getTestings()));
        }
    }

    private void loadBillNo() throws SQLException, ClassNotFoundException {
        List<String> BillNo = new PaymentController().getBillNo();
        cmbBillNo.getItems().addAll(BillNo);
    }

    public void backToReceptionistOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/ReceptionistForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) paymentFormContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void openAddPayment(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/AddAppointmentForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) paymentFormContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    ObservableList<CartTM> obList= FXCollections.observableArrayList();
    public void addToCartOnAction(ActionEvent actionEvent) {
        String doctorName = txtDoctorName.getText();
        String category = txtCategory.getText();
        double roomCharges = Double.parseDouble(txtRoomCharges.getText());
        double doctorCharge = Double.parseDouble(txtDoctorCharge.getText());
        double testings = Double.parseDouble(txtTestings.getText());
        double total = roomCharges+doctorCharge+testings;

        CartTM tm = new CartTM(
                cmbPatientNo.getValue(),
                doctorName,
                category,
                roomCharges,
                doctorCharge,
                testings,
                total
        );

        int rowNumber=isExists(tm);

        if (rowNumber==-1){
            obList.add(tm);
        }else{

            CartTM temp = obList.get(rowNumber);
            CartTM newTm = new CartTM(
                    temp.getBillNo(),
                    temp.getDoctorName(),
                    temp.getCategory(),
                    temp.getRoomCharges(),
                    temp.getDoctorCharge(),
                    temp.getTestings(),
                    total+temp.getTotal()
            );

            obList.remove(rowNumber);
            obList.add(newTm);
        }
        tblAppointment.setItems(obList);
        calculatePayment();
    }

    private void calculatePayment() {
        double ttl=0;
        for (CartTM tm:obList
        ) {
            ttl+=tm.getTotal();
        }
        lblTotal.setText(ttl+" /=");
    }

    private int isExists(CartTM tm) {
        for (int i = 0; i < obList.size(); i++) {
            if (tm.getBillNo().equals(obList.get(i).getBillNo())){
                return i;
            }
        }
        return -1;
    }


    public void removeOnAction(ActionEvent actionEvent) {
        if (cartSelectedRowForRemove==-1){
            new Alert(Alert.AlertType.WARNING, "Please Select a row").show();
        }else{
            obList.remove(cartSelectedRowForRemove);
            tblAppointment.refresh();
        }
    }

    public void placeAppointmentOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ArrayList<AppointmentDetails> patients= new ArrayList<>();
        double total=0;
        for (CartTM tempTm:obList
        ) {
            total+=tempTm.getTotal();
            patients.add(new AppointmentDetails(tempTm.getBillNo(),
                    tempTm.getRoomCharges(),tempTm.getDoctorCharge(),tempTm.getTestings()));
        }

        Appointment appointment= new Appointment(lblAppointmentId.getText(),
                cmbPatientNo.getValue(),
                lblDate.getText(),
                lblTime.getText(),
                total,
                patients

        );

        if (new AppointmentController().placeAppointment(appointment)){
            new Alert(Alert.AlertType.CONFIRMATION, "Success").show();
            setAppointmentNo();
        }else{
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }

    }

    private void setAppointmentNo() throws SQLException, ClassNotFoundException {
            lblAppointmentId.setText(new AppointmentController().getAppointmentNo());


    }

    public void openAppointmentFormOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/AppointmentForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) paymentFormContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void printBillEvent(MouseEvent mouseEvent) {
        try {
            JasperDesign design = JRXmlLoader.load(this.getClass().getResourceAsStream("/views/reports/Appointment.jrxml"));
            JasperReport compileReport = JasperCompileManager.compileReport(design);
            ObservableList<CartTM> products = tblAppointment.getItems();
            JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, null, new JRBeanArrayDataSource(products.toArray()));
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException e) {
            e.printStackTrace();
        }
    }
}

