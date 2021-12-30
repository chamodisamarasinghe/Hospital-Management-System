package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTimePicker;
import interfaces.DoctorManage;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Doctor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import tm.CalculateTM;
import tm.DoctorTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class ManageTheDoctorsFormController extends DoctorController implements DoctorManage {
    public AnchorPane manageDoctorFormContext;
    public TextField txtDoctorId;
    public TextField txtDoctorName;
    public TextField txtContactNo;
    public TextField txtWorkingTime;
    public TextField txtEmail;
    public TextField txtRoomNo;
    public TextField txtCategory;
    public TextField txtWorkingDate;
    public ComboBox<String> cmbCategory;
    public TableView tblDoctor;
    public TableColumn colDocId;
    public TableColumn colName;
    public TableColumn colContactNo;
    public TableColumn colDate;
    public TableColumn colTime;
    public TableColumn colEmail;
    public TableColumn colRoomNo;
    public TableColumn colCategory;

    public JFXButton btnSave;
    public JFXButton btnContent;
    public DatePicker txtDate;
    public JFXTimePicker txtTime;
    private int cartSelectedRowForRemove = -1;

    static ArrayList<Doctor> doctors = new ArrayList();

    public void initialize() {
        btnSave.setDisable(true);

        colDocId.setStyle("-fx-border-color: black;-fx-table-cell-border-color:black;");
        colName.setStyle("-fx-border-color: black;-fx-table-cell-border-color:black;");
        colContactNo.setStyle("-fx-border-color: black;-fx-table-cell-border-color:black;");
        colDate.setStyle("-fx-border-color: black;-fx-table-cell-border-color:black;");
        colTime.setStyle("-fx-border-color: black;-fx-table-cell-border-color:black;");
        colEmail.setStyle("-fx-border-color: black;-fx-table-cell-border-color:black;");
        colRoomNo.setStyle("-fx-border-color: black;-fx-table-cell-border-color:black;");
        colCategory.setStyle("-fx-border-color: black;-fx-table-cell-border-color:black;");



        colDocId.setCellValueFactory(new PropertyValueFactory<>("doctorId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("doctorName"));
        colContactNo.setCellValueFactory(new PropertyValueFactory<>("doctorContactNo"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("workingDate"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("workingTime"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("doctorEmail"));
        colRoomNo.setCellValueFactory(new PropertyValueFactory<>("roomNo"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));



        cmbCategory.getItems().addAll("ENT", "VOG", "Neuro", "Gynaecologist", "Oncologist", "Surgeon", "Cardiologist", "Dentist", "Cancer");
        cmbCategory.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println(oldValue);
            System.out.println(newValue);
            txtCategory.setText(newValue);
        });


        try {
            setDoctorsToTable(new DoctorController().getAllDoctors());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }


    private void setDoctorsToTable(ArrayList<Doctor> allDoctors) {
        ObservableList<Doctor> obList = FXCollections.observableArrayList();
        allDoctors.forEach(e -> {
            obList.add(
                    new Doctor(e.getDoctorId(), e.getDoctorName(), e.getDoctorContactNo(), e.getWorkingDate(), e.getWorkingTime(), e.getDoctorEmail(), e.getRoomNo(), e.getCategory()));
        });
        tblDoctor.setItems(obList);
    }


    ObservableList<DoctorTM> obList = FXCollections.observableArrayList();

    public void addDoctorOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        Doctor d = new Doctor(
                txtDoctorId.getText(), txtDoctorName.getText(), txtContactNo.getText(), txtWorkingDate.getText(), txtWorkingTime.getText(), txtEmail.getText(), txtRoomNo.getText(), txtCategory.getText()
        );

        if (new DoctorController().saveDoctor(d))
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
        else
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
        setDoctorsToTable(new DoctorController().getAllDoctors());


    }


    public void updateDoctorOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Doctor d = new Doctor(
                txtDoctorId.getText(), txtDoctorName.getText(),
                txtContactNo.getText(), txtWorkingDate.getText(), txtWorkingTime.getText(), txtEmail.getText(), txtRoomNo.getText(), txtCategory.getText()
        );


        if (new DoctorController().updateDoctor(d))
            new Alert(Alert.AlertType.CONFIRMATION, "Updated..").show();
        else
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        setDoctorsToTable(new DoctorController().getAllDoctors());
    }


    public void deleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        if (new DoctorController().deleteDoctor(txtDoctorId.getText())) {
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
            setDoctorsToTable(new DoctorController().getAllDoctors());

        }




    }

    public void backToDoctorFormOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/AdminForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) manageDoctorFormContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void docId_keyRealeased(KeyEvent keyEvent) {
        String regEx = "^(D00-)[0-9]{3,4}$";
        String typeText = txtDoctorId.getText();
        Pattern compile = Pattern.compile(regEx);
        boolean matches = compile.matcher(typeText).matches();

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (matches) {
                txtDoctorId.setStyle("-fx-border-color: dodgerblue");

            } else {
                txtDoctorId.setStyle("-fx-border-color: red");

            }

            if (keyEvent.getCode() == KeyCode.ENTER) {
                if (matches) {
                    txtDoctorName.requestFocus();


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
                    txtContactNo.requestFocus();
                }

            }
        }

    }

    public void contact_keyRealeased(KeyEvent keyEvent) {
        String regEx = "^[0-9]{3}[-]?[0-9]{7}$";
        String typeText = txtContactNo.getText();
        Pattern compile = Pattern.compile(regEx);
        boolean matches = compile.matcher(typeText).matches();

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (matches) {
                txtContactNo.setStyle("-fx-border-color: dodgerblue");

            } else {
                txtContactNo.setStyle("-fx-border-color: red");

            }


            if (keyEvent.getCode() == KeyCode.ENTER) {
                if (matches) {
                    txtWorkingDate.requestFocus();
                }

            }
        }
    }

    public void date_keyRealeased(KeyEvent keyEvent) {
        String regEx = "^[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$";
        String typeText = txtWorkingDate.getText();
        Pattern compile = Pattern.compile(regEx);
        boolean matches = compile.matcher(typeText).matches();

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (matches) {
                txtWorkingDate.setStyle("-fx-border-color: dodgerblue");

            } else {
                txtWorkingDate.setStyle("-fx-border-color: red");

            }


            if (keyEvent.getCode() == KeyCode.ENTER) {
                if (matches) {
                    txtWorkingTime.requestFocus();
                }

            }
        }

    }

    public void time_keyRealeased(KeyEvent keyEvent) {
        String regEx = "^(0?[1-9]|1[0-2]):([0-5]\\d)\\s?((?:[Aa]|[Pp])\\.?[Mm]\\.?)";
        String typeText = txtWorkingTime.getText();
        Pattern compile = Pattern.compile(regEx);
        boolean matches = compile.matcher(typeText).matches();

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (matches) {
                txtWorkingTime.setStyle("-fx-border-color: dodgerblue");

            } else {
                txtWorkingTime.setStyle("-fx-border-color: red");

            }


            if (keyEvent.getCode() == KeyCode.ENTER) {
                if (matches) {
                    txtEmail.requestFocus();
                }

            }
        }

    }

    public void email_keyRealeased(KeyEvent keyEvent) {
        String regEx = "^[a-z]{2,}[0-9]*[@](gmail.|yahoo.)(com|lk)$";
        String typeText = txtEmail.getText();
        Pattern compile = Pattern.compile(regEx);
        boolean matches = compile.matcher(typeText).matches();

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (matches) {
                txtEmail.setStyle("-fx-border-color: dodgerblue");

            } else {
                txtEmail.setStyle("-fx-border-color: red");

            }


            if (keyEvent.getCode() == KeyCode.ENTER) {
                if (matches) {
                    txtRoomNo.requestFocus();
                }

            }
        }
    }

    public void rNo_keyRealeased(KeyEvent keyEvent) {
        String regEx = "^(R00-)[0-9]{3,4}$";
        String typeText = txtRoomNo.getText();
        Pattern compile = Pattern.compile(regEx);
        boolean matches = compile.matcher(typeText).matches();

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (matches) {
                txtRoomNo.setStyle("-fx-border-color: dodgerblue");

            } else {
                txtRoomNo.setStyle("-fx-border-color: red");
                btnSave.setDisable(true);
            }

            if (keyEvent.getCode() == KeyCode.ENTER) {
                if(matches){
                    btnSave.setDisable(false);
                }else {
                    btnSave.setDisable(true);
                }
            }
        }
    }

    public void beanArrayEvent(MouseEvent mouseEvent) {
        try {
            JasperDesign design = JRXmlLoader.load(this.getClass().getResourceAsStream("/views/reports/BeanArrayReport.jrxml"));
            JasperReport compileReport = JasperCompileManager.compileReport(design);
            ObservableList<DoctorTM> products = tblDoctor.getItems();
            JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, null, new JRBeanArrayDataSource(products.toArray()));
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException e) {
            e.printStackTrace();
        }
    }
}


