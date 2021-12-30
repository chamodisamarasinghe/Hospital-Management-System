package interfaces;

import model.Doctor;
import model.Payment;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PaymentManage {
    boolean savePayment(Payment y) throws SQLException, ClassNotFoundException;
    boolean updatePayment(Payment y) throws SQLException, ClassNotFoundException;
    public boolean deletePayment(String billNo) throws SQLException, ClassNotFoundException;
    public Payment getPayment(String billNo) throws SQLException, ClassNotFoundException;
    public ArrayList<Payment> getAllPayments() throws SQLException, ClassNotFoundException;
}
