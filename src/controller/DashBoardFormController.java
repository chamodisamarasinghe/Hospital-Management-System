package controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

public class DashBoardFormController {
    public AnchorPane dashBoardFormContext;
    public JFXButton btnServices;
    public Label lblDate;
    public Label lblTime;



    public void initialize(){
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


    public void openAboutOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/AboutUs.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) dashBoardFormContext.getScene().getWindow();
        window.setScene(new Scene(load));

    }


    public void openLogInFormOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/LogInForm.fxml"));
        Parent parent = loader.load();
        LogInFormController controller = loader.getController();
        Scene scene= new Scene(parent);
        Stage stage = new Stage();
        controller.closeStage(dashBoardFormContext);
        stage.setScene(scene);
       // stage.setTitle("Dash Board");
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
    }



    public void openServicesOnAction(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../views/ServicesForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) dashBoardFormContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void openTimeTableFormOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/TimeTableForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) dashBoardFormContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }
}
