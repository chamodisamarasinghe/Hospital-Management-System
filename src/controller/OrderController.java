package controller;

import db.DbConnection;
import model.MedicalDetail;
import model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderController {
    public String getOrderId() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance()
                .getConnection().prepareStatement(
                        "SELECT orderId FROM `Order` ORDER BY orderId DESC LIMIT 1"
                ).executeQuery();
        if (rst.next()){

            int tempId = Integer.
                    parseInt(rst.getString(1).split("-")[1]);
            tempId=tempId+1;
            if (tempId<=9){
                return "O-00"+tempId;
            }else if(tempId<=99){
                return "O-0"+tempId;
            }else{
                return "O-"+tempId;
            }

        }else{
            return "O-001";
        }
    }

    public boolean placeOrder(Order order) {
        Connection con=null;
        try {
            con= DbConnection.getInstance().getConnection();
            con.setAutoCommit(false);
            PreparedStatement stm =con.
                    prepareStatement("INSERT INTO `Order` VALUES(?,?,?,?,?)");


            stm.setObject(1, order.getOrderId());
            stm.setObject(2, order.getPharmId());
            stm.setObject(3, order.getOrderDate());
            stm.setObject(4, order.getTime());
            stm.setObject(5, order.getCost());

            if (stm.executeUpdate() > 0){
                if (saveOrderDetail(order.getOrderId(), order.getProducts())){
                    con.commit();
                    return true;
                }else{
                    con.rollback();
                    return false;
                }
            }else{
                con.rollback();
                return false;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return false;
    }

    private boolean saveOrderDetail(String orderId, ArrayList<MedicalDetail> products) throws SQLException, ClassNotFoundException {
        for (MedicalDetail temp : products
        ) {
            PreparedStatement stm = DbConnection.getInstance().
                    getConnection().
                    prepareStatement("INSERT INTO `MedicalDetail` VALUES(?,?,?,?)");
            stm.setObject(1, orderId);
            stm.setObject(2, temp.getProductCode());
            stm.setObject(3, temp.getQty());
            stm.setObject(4, temp.getPrice());
            if (stm.executeUpdate() > 0) {

                if (updateQty(temp.getProductCode(), temp.getQty())){

                }else{
                    return false;
                }

            } else {
                return false;
            }
        }
        return true;
    }

    private boolean updateQty(String productCode, int qty) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection()
                .prepareStatement
                        ("UPDATE MEDICINE SET qtyOnHand=(qtyOnHand-" + qty
                                + ") WHERE productCode='" + productCode + "'");
        return stm.executeUpdate()>0;
    }
}
