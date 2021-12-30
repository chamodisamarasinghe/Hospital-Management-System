package controller;

import com.jfoenix.controls.JFXButton;
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
import tm.NurseTM;
//import util.ValidationUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Pattern;

public class ManageTheNurseFormController extends NurseController {
    public AnchorPane nurseDetailsFormContext;
    public TextField txtNurseId;
    public TextField txtName;
    public TextField txtRoomNo;
    public TextField txtWardNo;
    public TableView tblNurse;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colRoomNo;
    public TableColumn colWardNo;
    public ComboBox<String> cmbNurseId;
    public TextField txtName1;
    public TextField txtRoomNo1;
    public TextField txtWardNo1;
    public JFXButton btnAdd;
    private int cartSelectedRowForRemove = -1;
    private String nurseId;


    public void initialize() {




        colId.setStyle("-fx-border-color: black;-fx-table-cell-border-color:black;");
        colName.setStyle("-fx-border-color: black;-fx-table-cell-border-color:black;");
        colRoomNo.setStyle("-fx-border-color: black;-fx-table-cell-border-color:black;");
        colWardNo.setStyle("-fx-border-color: black;-fx-table-cell-border-color:black;");


        colId.setCellValueFactory(new PropertyValueFactory<>("nurseId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("nurseName"));
        colRoomNo.setCellValueFactory(new PropertyValueFactory<>("roomNo"));
        colWardNo.setCellValueFactory(new PropertyValueFactory<>("wardNo"));

        try {
            setNurseToTable(new NurseController().getAllNurses());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            loadNurseId(nurseId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        cmbNurseId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setNurseData(newValue);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });


    }


    private void setNurseData(String nurseId) throws SQLException, ClassNotFoundException {
        Nurse n = new NurseController().getNurse(nurseId);
        if (n == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set");
        } else {
            txtName1.setText(n.getNurseName());
            txtRoomNo1.setText(n.getRoomNo());
            txtWardNo1.setText(n.getWardNo());

        }
    }

    private void loadNurseId(String nurseId) throws SQLException, ClassNotFoundException {
        List<String> patientNum = new NurseController().getNurseId(nurseId);
        cmbNurseId.getItems().addAll(patientNum);
    }

    private void setNurseToTable(ArrayList<Nurse> allNurse) {
        ObservableList<Nurse> obList = FXCollections.observableArrayList();
        allNurse.forEach(e -> {
            obList.add(
                    new Nurse(e.getNurseId(), e.getNurseName(), e.getRoomNo(), e.getWardNo()));
        });
        tblNurse.setItems(obList);
    }

    ObservableList<NurseTM> obList = FXCollections.observableArrayList();

    public void addOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Nurse n = new Nurse(
                txtNurseId.getText(), txtName.getText(), txtRoomNo.getText(), txtWardNo.getText()
        );

        if (new NurseController().saveNurse(n))
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
        else
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
        setNurseToTable(new NurseController().getAllNurses());

    }


    public void updateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Nurse n = new Nurse(
                txtNurseId.getText(), txtName.getText(),
                txtRoomNo.getText(), txtWardNo.getText()
        );


        if (new NurseController().updateNurse(n))
            new Alert(Alert.AlertType.CONFIRMATION, "Updated..").show();
        else
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        setNurseToTable(new NurseController().getAllNurses());
    }

    public void deleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (new NurseController().deleteNurse(txtNurseId.getText())) {
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
            setNurseToTable(new NurseController().getAllNurses());
        }
    }

    public void backToReceptionOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/ReceptionistForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) nurseDetailsFormContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }

   /* public void moveToNurseName(ActionEvent actionEvent) {
        txtName.requestFocus();
    }

    public void moveToRoomNo(ActionEvent actionEvent) {
        txtRoomNo.requestFocus();
    }

    public void moveToWardNo(ActionEvent actionEvent) {
        txtWardNo.requestFocus();
    }*/


    public void searchOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String nurseId = txtNurseId.getText();

        Nurse n = (Nurse) new NurseController().getNurseId(nurseId);
        if (n == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        } else {
            setData(n);
        }
    }

    private void setData(Nurse n) {
        txtNurseId.setText(n.getNurseId());
        txtName.setText(n.getNurseName());
        txtRoomNo.setText(n.getRoomNo());
        txtWardNo.setText(n.getWardNo());

    }


    public void nurseId_keyRealeased(KeyEvent keyEvent) {
        String regEx = "^(N00-)[0-9]{3,4}$";
        String typeText = txtNurseId.getText();
        Pattern compile = Pattern.compile(regEx);
        boolean matches = compile.matcher(typeText).matches();

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (matches) {
                txtNurseId.getParent().setStyle("-fx-border-color: dodgerblue");

            } else {
                txtNurseId.getParent().setStyle("-fx-border-color: red");

            }

            if (keyEvent.getCode() == KeyCode.ENTER) {
                if (matches) {
                    txtName.requestFocus();


                }
            }
        }
    }


    public void nurseName_keyRealeased(KeyEvent keyEvent) {
        String regEx = "^[A-z ]{3,20}$";
        String typeText = txtName.getText();
        Pattern compile = Pattern.compile(regEx);
        boolean matches = compile.matcher(typeText).matches();

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (matches) {
                txtName.getParent().setStyle("-fx-border-color: dodgerblue");

            } else {
                txtName.getParent().setStyle("-fx-border-color: red");

            }


            if (keyEvent.getCode() == KeyCode.ENTER) {
                if (matches) {
                    txtRoomNo.requestFocus();
                }

            }
        }
    }


    public void roomNo_keyRealeased(KeyEvent keyEvent) {
        String regEx = "^(R00-)[0-9]{3,4}$";
        String typeText = txtRoomNo.getText();
        Pattern compile = Pattern.compile(regEx);
        boolean matches = compile.matcher(typeText).matches();

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (matches) {
                txtRoomNo.getParent().setStyle("-fx-border-color: dodgerblue");

            } else {
                txtRoomNo.getParent().setStyle("-fx-border-color: red");

            }

            if (keyEvent.getCode() == KeyCode.ENTER) {
                if (matches) {
                    txtWardNo.requestFocus();
                }

            }
        }
    }


    public void wardNo_keyRealeased(KeyEvent keyEvent) {
        String regEx = "^(W00-)[0-9]{3,4}$";
        String typeText = txtWardNo.getText();
        Pattern compile = Pattern.compile(regEx);
        boolean matches = compile.matcher(typeText).matches();

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (matches) {
                txtWardNo.getParent().setStyle("-fx-border-color: dodgerblue");

            } else {
                txtWardNo.getParent().setStyle("-fx-border-color: red");

            }

            if (keyEvent.getCode() == KeyCode.ENTER) {
                if (matches) {
                    if (matches) {
                        txtWardNo.requestFocus();
                    }
                }
            }
        }
    }
}


