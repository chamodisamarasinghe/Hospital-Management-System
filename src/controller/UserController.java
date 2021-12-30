package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import model.DoctorLogin;
import model.User;

import java.sql.SQLException;
import java.util.regex.Pattern;

public class UserController extends UserFormController{
    public AnchorPane userContext;
    public TextField txtUserName;
    public TextField txtPassWord;
    public TextField txtFullName;
    public TextField txtEmail;
    public ComboBox cmbRoleType;
    private String selectedRoleType;

    public void initialize(){
        cmbRoleType.getItems().addAll("Doctor", "Reception");
        cmbRoleType.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectedRoleType = String.valueOf(newValue);
        });
    }

    public void userNameOnAction(ActionEvent actionEvent) {

    }

    public void passWordOnAction(ActionEvent actionEvent) {
    }

    public void fullNameOnAction(ActionEvent actionEvent) {
    }

    public void emailOnAction(ActionEvent actionEvent) {
    }

    public void addOnAction(ActionEvent actionEvent) {
        try {
            if (selectedRoleType == "Reception") {
                User u1 = new User(
                        txtUserName.getText(),
                        txtFullName.getText(),
                        txtEmail.getText(),
                        txtPassWord.getText()
                );

                if (new UserFormController().saveUser(u1)) {
                    new Alert(Alert.AlertType.CONFIRMATION, "ReceptionSaved..").show();
                } else {
                    new Alert(Alert.AlertType.WARNING, "Try Again..").show();
                }
            }else if (selectedRoleType == "Doctor") {
                DoctorLogin u1 = new DoctorLogin(
                        txtUserName.getText(),
                        txtFullName.getText(),
                        txtEmail.getText(),
                        txtPassWord.getText()
                );

                if (new UserFormController().saveDoctorLogin(u1)) {
                    new Alert(Alert.AlertType.CONFIRMATION, "DoctorSaved..").show();
                } else {
                    new Alert(Alert.AlertType.WARNING, "Try Again..").show();
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void userNameKeyEvent(KeyEvent keyEvent) {
        String regEx = "^[A-z ]{3,20}$";
        String typeText = txtUserName.getText();
        Pattern compile = Pattern.compile(regEx);
        boolean matches = compile.matcher(typeText).matches();

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (matches) {
                txtUserName.setStyle("-fx-border-color: dodgerblue");

            } else {
                txtUserName.setStyle("-fx-border-color: red");

            }


            if (keyEvent.getCode() == KeyCode.ENTER) {
                if (matches) {
                    txtFullName.requestFocus();
                }

            }
        }

    }

    public void passWordKeyEvent(KeyEvent keyEvent) {
        String regEx = "^[0-9]{3,4}$";
        String typeText = txtPassWord.getText();
        Pattern compile = Pattern.compile(regEx);
        boolean matches = compile.matcher(typeText).matches();

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (matches) {
                txtPassWord.setStyle("-fx-border-color: dodgerblue");

            } else {
                txtPassWord.setStyle("-fx-border-color: red");

            }


            if (keyEvent.getCode() == KeyCode.ENTER) {
                if (matches) {
                    txtPassWord.requestFocus();
                }

            }
        }
    }

    public void fullNameKeyEvent(KeyEvent keyEvent) {
        String regEx = "^[A-z ]{3,20}$";
        String typeText = txtFullName.getText();
        Pattern compile = Pattern.compile(regEx);
        boolean matches = compile.matcher(typeText).matches();

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (matches) {
                txtFullName.setStyle("-fx-border-color: dodgerblue");

            } else {
                txtFullName.setStyle("-fx-border-color: red");

            }


            if (keyEvent.getCode() == KeyCode.ENTER) {
                if (matches) {
                    txtEmail.requestFocus();
                }

            }
        }
    }

    public void emailKeyEvent(KeyEvent keyEvent) {
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
                    txtPassWord.requestFocus();
                }

            }
        }

    }
}
