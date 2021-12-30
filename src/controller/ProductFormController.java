package controller;

import com.jfoenix.controls.JFXButton;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Medicine;
import model.Nurse;
import model.Pharmacist;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import tm.CalculateTM;
import tm.OrderDetailTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class ProductFormController extends ProductController{
    public AnchorPane productFormContext;
    public TextField txtProductCode;
    public TextField productName;
    public TextField txtUnitPrice;
    public TextField txtQty;
    public TableView tblProducts;
    public TableColumn colCode;
    public TableColumn colName;
    public TableColumn colQty;
    public TableColumn colUnitPrice;
    public TextField txtProductName;
    public ComboBox<String > cmbProductName;
    public ComboBox<String> cmbProductName1;
    public JFXButton btnDetail;

    public void initialize(){
        colCode.setStyle("-fx-border-color: black;-fx-table-cell-border-color:black;");
        colName.setStyle("-fx-border-color: black;-fx-table-cell-border-color:black;");
        colUnitPrice.setStyle("-fx-border-color: black;-fx-table-cell-border-color:black;");
        colQty.setStyle("-fx-border-color: black;-fx-table-cell-border-color:black;");




        colCode.setCellValueFactory(new PropertyValueFactory<>("productCode"));
        colName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));

        try {
            setProductToTable(new ProductController().getAllProducts());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        cmbProductName.getItems().addAll("Multi-Vitamins", "Speciality supplements", "Nutrition Drinks", "Health food and drinks", "Protein Supplements", "Skin Care", "Hair Care", "Face Care","Eye Care",
                "Body Care","Mother & Baby","Medical Devices","Diabetes","Fitness & Supplements","Ayurveda","Health & Wellness","Pet Care");
        cmbProductName.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println(oldValue);
            System.out.println(newValue);
            txtProductName.setText(newValue);
        });

        cmbProductName1.getItems().addAll("P001", "P002", "P003", "P004", "P005", "P006", "P007", "P008","P009",
                "P010","P011","P012","P013","P014","P015","P016","P017");
        cmbProductName1.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println(oldValue);
            System.out.println(newValue);
            txtProductCode.setText(newValue);
        });


    }

    private void setProductToTable(ArrayList<Medicine> allProducts) {
        ObservableList<Medicine> obList = FXCollections.observableArrayList();
        allProducts.forEach(e->{
            obList.add(
                    new Medicine(e.getProductCode(),e.getProductName(),e.getUnitPrice(),e.getQtyOnHand()));
        });
        tblProducts.setItems(obList);
    }


    public void addOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Medicine m = new Medicine(
                txtProductCode.getText(),txtProductName.getText(),Double.parseDouble(txtUnitPrice.getText()), Integer.valueOf(txtQty.getText())
        );

        if(new ProductController().saveProduct(m))
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
        else
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
        setProductToTable(new ProductController().getAllProducts());
    }



    public void updateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Medicine m = new Medicine(
                txtProductCode.getText(),txtProductName.getText(),Double.parseDouble(txtUnitPrice.getText()), Integer.valueOf(txtQty.getText())
        );


        if(new ProductController().updateProduct(m))
            new Alert(Alert.AlertType.CONFIRMATION,"Updated..").show();
        else
            new Alert(Alert.AlertType.WARNING,"Try Again").show();
        setProductToTable(new ProductController().getAllProducts());

    }

    public void deleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (new ProductController().deleteProduct(txtProductCode.getText())){
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
        }else{
            new Alert(Alert.AlertType.WARNING, "Try Again").show();

        }
    }

    public void backToPlaceOrder(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/PlaceOrderForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) productFormContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void unitPrice_keyRealeased(KeyEvent keyEvent) {
        String regEx = "^[0-9]{3,}.[0-9]{2}$";
        String typeText = txtUnitPrice.getText();
        Pattern compile = Pattern.compile(regEx);
        boolean matches = compile.matcher(typeText).matches();

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (matches) {
                txtUnitPrice.setStyle("-fx-border-color: dodgerblue");

            } else {
                txtUnitPrice.setStyle("-fx-border-color: red");

            }

            if (keyEvent.getCode() == KeyCode.ENTER) {
                if (matches) {
                    txtQty.requestFocus();


                }
            }
        }
    }

    public void qty_keyRealeased(KeyEvent keyEvent) {
        String regEx = "^[0-9]{1,}$";
        String typeText = txtQty.getText();
        Pattern compile = Pattern.compile(regEx);
        boolean matches = compile.matcher(typeText).matches();

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (matches) {
                txtQty.setStyle("-fx-border-color: dodgerblue");

            } else {
                txtQty.setStyle("-fx-border-color: red");

            }

            if (keyEvent.getCode() == KeyCode.ENTER) {
                if (matches) {
                    txtQty.requestFocus();


                }
            }
        }
    }

    public void openChart(ActionEvent actionEvent) {
        
    }

    public void generateSqlChart(MouseEvent mouseEvent) throws JRException, SQLException, ClassNotFoundException {
        JasperDesign design = JRXmlLoader.load(this.getClass().getResourceAsStream("/views/reports/ProductDetails.jrxml"));
        JasperReport compileReport = JasperCompileManager.compileReport(design);
        JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, null, DbConnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint,false);
    }
}

