package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Doctor;
import model.DoctorLogin;
import model.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class LogInFormController {
    public AnchorPane logInFormContext;
    public AnchorPane dashBoardContext;
    public TextField txtUseName;
    public TextField txtPassWord;

    public DoctorLogin doctor;
    public User user;
    public void openFormsOnAction(ActionEvent actionEvent) throws IOException, ClassNotFoundException, SQLException {
        Stage stage = (Stage) dashBoardContext.getScene().getWindow();
        stage.close();
       /* if(txtUseName.getText().equalsIgnoreCase("Admin") && txtPassWord.getText().equals("123")) {
            Stage window =(Stage)logInFormContext.getScene().getWindow();
            window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../views/AdminForm.fxml"))));
            stage.centerOnScreen();
        }*//*else if (txtUseName.getText().equalsIgnoreCase("Reception")&& txtPassWord.getText().equals("456")){
            Stage window =(Stage)logInFormContext.getScene().getWindow();
            window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../views/ReceptionistForm.fxml"))));
            stage.centerOnScreen();
        }else if(txtUseName.getText().equalsIgnoreCase("Doctor")&& txtPassWord.getText().equals("678")){
            Stage window =(Stage)logInFormContext.getScene().getWindow();
            window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../views/DoctorForm.fxml"))));
            stage.centerOnScreen();
        }else {
            new Alert(Alert.AlertType.WARNING,"Incorrect User Name, Password. Try again..", ButtonType.CLOSE).show();
        }*/

        if (txtUseName.getText().equalsIgnoreCase("admin") && txtPassWord.getText().equals("1234")) {
            Stage window = (Stage) logInFormContext.getScene().getWindow();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("../views/AdminForm.fxml")));
            window.centerOnScreen();
            window.setScene(scene);
            //scene.setFill(TRANSPARENT);
            window.setScene(scene);
            window.show();
        } else if (isValid(txtUseName.getText())) {
            if (passwordCheck(txtPassWord.getText())) {
                Stage window = (Stage) logInFormContext.getScene().getWindow();
                window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../views/ReceptionistForm.fxml"))));
                window.centerOnScreen();
            } else {
                new Alert(Alert.AlertType.WARNING, "Incorrect UserName,Password. Try Again...");
            }
        } else if (txtUseName.getText().equalsIgnoreCase("Doctor") && txtPassWord.getText().equals("456")) {
            Stage window = (Stage) logInFormContext.getScene().getWindow();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("../views/DoctorForm.fxml")));
            window.centerOnScreen();
            window.setScene(scene);
            //scene.setFill(TRANSPARENT);
            window.setScene(scene);
            window.show();


        }
    else{
            new Alert(Alert.AlertType.WARNING, "Incorrect UserName,Password. Try Again...");
        }

        }
    Stage stage;

    private boolean passwordCheck(String text){
        return user.getPassword().equals(text);
    }

    private boolean doctorCheck(String text){
        return doctor.getPassword().equals(text);
    }


    private boolean isValid(String text) throws SQLException, ClassNotFoundException {
        ArrayList<User> allUser = new UserFormController().getAllUser();
        for (User u : allUser) {
            if (u.getUserName().equalsIgnoreCase(text)) {
                user = u;
                return true;
            }
        }
        return false;
    }

    private boolean isValidDoctor(String text) throws SQLException, ClassNotFoundException {
        ArrayList<DoctorLogin> allDoctorLogin = new UserFormController().getAllDoctorLogin();
        System.out.println("abs");
        for (DoctorLogin u : allDoctorLogin) {
            System.out.println(u.getUserName());
            if (u.getUserName().equalsIgnoreCase(text)) {
                doctor = u;
                return true;
            }
        }
        return false;
    }



    public void closeOnAction(ActionEvent actionEvent) {
        stage=(Stage) logInFormContext.getScene().getWindow();
        stage.close();
    }

    public void closeStage(AnchorPane dashBoardFormContext) {
        this.dashBoardContext=dashBoardFormContext;
    }

    public void moveToPassWord(ActionEvent actionEvent) {
        txtPassWord.requestFocus();
    }

    public void userOnAction(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) logInFormContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../views/User.fxml"))));
        window.centerOnScreen();
    }
}

