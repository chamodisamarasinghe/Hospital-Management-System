package interfaces;

import model.Doctor;
import model.Medicine;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ProductManage {
    boolean saveProduct(Medicine m) throws SQLException, ClassNotFoundException;
    boolean updateProduct(Medicine m) throws SQLException, ClassNotFoundException;
    public boolean deleteProduct(String productCode) throws SQLException, ClassNotFoundException;
    public Medicine getProduct(String productCode) throws SQLException, ClassNotFoundException;
    public ArrayList<Medicine> getAllProducts() throws SQLException, ClassNotFoundException;
}
