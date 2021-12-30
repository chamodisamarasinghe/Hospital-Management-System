package controller;

import com.jfoenix.controls.JFXButton;
import db.DbConnection;
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
import model.MedicalDetail;
import model.Medicine;
import model.Order;
import model.Pharmacist;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import tm.CalculateTM;
import tm.OrderTM;
import tm.ReportTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PlaceOrderFormController {
    public AnchorPane placeOrderFormContext;
    public Label lblOrderId;
    public Label lblDate;
    public Label lblTime;
    public ComboBox<String> cmbPharmacistId;
    public TextField txtName;
    public TextField txtDrugName;
    public ComboBox<String> cmbProduct;
    public TextField txtProductName;
    public TextField txtUnitPrice;
    public TextField txtQty;
    public TableView <CalculateTM> tblMedicine;
    public TableColumn colProductCode;
    public TableColumn colProductName;
    public TableColumn colUnitPrice;
    public TableColumn colQty;
    public TableColumn colTotal;
    public TextField txtQtyOnHand;
    public Label txtTotal;
    public Label lblDiscount;
    public JFXButton btnMovable;
    int cartSelectedRowForRemove = -1;

    public void initialize() {
        colProductCode.setStyle("-fx-border-color: black;-fx-table-cell-border-color:black;");
        colProductName.setStyle("-fx-border-color: black;-fx-table-cell-border-color:black;");
        colUnitPrice.setStyle("-fx-border-color: black;-fx-table-cell-border-color:black;");
        colQty.setStyle("-fx-border-color: black;-fx-table-cell-border-color:black;");
        colTotal.setStyle("-fx-border-color: black;-fx-table-cell-border-color:black;");


        colProductCode.setCellValueFactory(new PropertyValueFactory<>("productCode"));
        colProductName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));


        try {
            setOrderId();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        loadDateAndTime();


        try {
            loadPharmId();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            loadProductCode();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        cmbPharmacistId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setPharmacistData(newValue);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        cmbProduct.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setProductData(newValue);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        tblMedicine.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            cartSelectedRowForRemove = (int) newValue;
        });
    }

    private void setOrderId() throws SQLException, ClassNotFoundException {
        lblOrderId.setText(new OrderController().getOrderId());
    }


    private void setProductData(String productCode) throws SQLException, ClassNotFoundException {
        Medicine m = new ProductController().getProduct(productCode);
        if (m == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set");
        } else {
            txtProductName.setText(m.getProductName());
            txtUnitPrice.setText(String.valueOf(m.getUnitPrice()));
            txtQtyOnHand.setText(String.valueOf(m.getQtyOnHand()));

        }
    }


    private void setPharmacistData(String pharmId) throws SQLException, ClassNotFoundException {
        Pharmacist h = new PharmacyController().getPharmacist(pharmId);
        if (h == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set");
        } else {
            txtName.setText(h.getPharmName());
            txtDrugName.setText(h.getDrugName());

        }
    }



    private void loadProductCode() throws SQLException, ClassNotFoundException {
        List<String> productCode = new ProductController().getProductCode();
        cmbProduct.getItems().addAll(productCode);
    }



    private void loadPharmId() throws SQLException, ClassNotFoundException {
        List<String> pharmId = new PharmacyController().getPharmId();
        cmbPharmacistId.getItems().addAll(pharmId);
    }


    private void loadDateAndTime() {
        Date date = new Date();
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






    ObservableList<CalculateTM> obList= FXCollections.observableArrayList();
    public void addToCartOnAction(ActionEvent actionEvent) {
        String productName = txtProductName.getText();
        int qtyOnHand = Integer.parseInt(txtQtyOnHand.getText());
        double unitPrice = Double.parseDouble(txtUnitPrice.getText());
        int qty = Integer.parseInt(txtQty.getText());
        double total = qty * unitPrice;

        if (qtyOnHand<qty){
            new Alert(Alert.AlertType.WARNING,"Invalid QTY").show();
            return;
        }

        CalculateTM tm = new CalculateTM(
                cmbProduct.getValue(),
                productName,
                qty,
                unitPrice,
                total
        );

        int rowNumber=isExists(tm);

        if (rowNumber==-1){
            obList.add(tm);
        }else{
            CalculateTM temp = obList.get(rowNumber);
            CalculateTM newTm = new CalculateTM(
                    temp.getProductCode(),
                    temp.getProductName(),
                    temp.getQty()+qty,
                    unitPrice,
                    total+temp.getTotal()
            );
            obList.remove(rowNumber);
            obList.add(newTm);
        }
        tblMedicine.setItems(obList);
        calculateCost();

    }

    private void calculateCost() {
        double ttl=0;
        for (CalculateTM tm:obList
        ) {
            ttl+=tm.getTotal();
        }
        txtTotal.setText(ttl+" /=");


        double discount=0;

        for (CalculateTM tm:obList
        ) {
            discount+=tm.getTotal();
            if(ttl >= 1000){
                lblDiscount.setText(String.valueOf(ttl-(ttl/10)));
            } else if(ttl <= 1000){
                ttl=ttl;
            }
        }
    }



    private int isExists(CalculateTM tm) {
        for (int i = 0; i < obList.size(); i++) {
            if (tm.getProductCode().equals(obList.get(i).getProductCode())){
                return i;
            }
        }
        return -1;
    }


    public void placeOrderOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ArrayList<MedicalDetail> products= new ArrayList<>();
        double total=0;
        for (CalculateTM tempTm:obList
        ) {
            total+=tempTm.getTotal();
            products.add(new MedicalDetail(
                    tempTm.getProductCode(),
                    tempTm.getQty(),
                    tempTm.getUnitPrice()
            ));

        }

       Order order= new Order(lblOrderId.getText(), cmbPharmacistId.getValue(),
                lblDate.getText(),
                lblTime.getText(),
                total,
               products
        );

        if (new OrderController().placeOrder(order)){
            new Alert(Alert.AlertType.CONFIRMATION, "Success").show();
            setOrderId();
        }else{
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }

    }




    public void removeOnAction(ActionEvent actionEvent) {
        if (cartSelectedRowForRemove==-1){
            new Alert(Alert.AlertType.WARNING, "Please Select a row").show();
        }else{
            obList.remove(cartSelectedRowForRemove);
            calculateCost();
            tblMedicine.refresh();
        }
    }






    public void backOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/ManageThePharmacy.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) placeOrderFormContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void openAddProductOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/ProductForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) placeOrderFormContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void openRepotOnAction(ActionEvent actionEvent) {
        try {
           JasperDesign design = JRXmlLoader.load(this.getClass().getResourceAsStream("/views/reports/report.jrxml"));
           JasperReport compileReport = JasperCompileManager.compileReport(design);
            ObservableList<CalculateTM> products = tblMedicine.getItems();
            JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, null, new JRBeanArrayDataSource(products.toArray()));
           JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException e) {
            e.printStackTrace();
        }
    }

    public void generateSqlChart(MouseEvent mouseEvent) throws JRException, SQLException, ClassNotFoundException {
        JasperDesign design = JRXmlLoader.load(this.getClass().getResourceAsStream("/views/reports/ProductDetails.jrxml"));
        JasperReport compileReport = JasperCompileManager.compileReport(design);
        JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, null, DbConnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint,false);
    }
}
