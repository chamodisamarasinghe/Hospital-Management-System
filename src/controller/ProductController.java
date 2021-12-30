package controller;

import db.DbConnection;
import interfaces.ProductManage;
import model.Doctor;
import model.Medicine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductController implements ProductManage {
    public List<String> getProductCode() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().
                getConnection().prepareStatement("SELECT * FROM Medicine").executeQuery();
        List<String> codes = new ArrayList<>();
        while (rst.next()) {
            codes.add(
                    rst.getString(1)
            );
        }
        return codes;
    }


    @Override
    public boolean saveProduct(Medicine m) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        String query = "INSERT INTO Medicine VALUES(?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setObject(1, m.getProductCode());
        stm.setObject(2, m.getProductName());
        stm.setObject(3, m.getUnitPrice());
        stm.setObject(4, m.getQtyOnHand());


        return stm.executeUpdate() > 0;
    }

    @Override
    public boolean updateProduct(Medicine m) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE Medicine SET productName=?, unitPrice=?, qtyOnHand=? WHERE productCode=?");
        stm.setObject(1, m.getProductName());
        stm.setObject(2, m.getUnitPrice());
        stm.setObject(3, m.getQtyOnHand());
        stm.setObject(4, m.getProductCode());

        return stm.executeUpdate() > 0;
    }

    @Override
    public boolean deleteProduct(String productCode) throws SQLException, ClassNotFoundException {
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Medicine WHERE productCode='" + productCode + "'").executeUpdate() > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Medicine getProduct(String productCode) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance()
                .getConnection().prepareStatement("SELECT * FROM Medicine WHERE productCode=?");
        stm.setObject(1, productCode);

        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            return new Medicine(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getInt(4)



            );

        } else {
            return null;
        }

    }


    @Override
    public ArrayList<Medicine> getAllProducts() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Medicine");
        ResultSet rst = stm.executeQuery();
        ArrayList<Medicine> products = new ArrayList<>();
        while (rst.next()) {
            products.add(new Medicine(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getInt(4)


            ));
        }
        return products;
    }
}
