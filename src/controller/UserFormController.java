package controller;

import db.DbConnection;
import model.Doctor;
import model.DoctorLogin;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserFormController {
    public boolean saveUser(User u1) throws SQLException, ClassNotFoundException, SQLException {
        Connection con = DbConnection.getInstance().getConnection();
        String query = "INSERT INTO Users VALUES(?, ?, ?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setObject(1, u1.getUserName());
        stm.setObject(2, u1.getFullName());
        stm.setObject(3, u1.getEmail());
        stm.setObject(4, u1.getPassword());

        return stm.executeUpdate() > 0;


    }


    public ArrayList<User> getAllUser() throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        PreparedStatement stm = con.prepareStatement("SELECT * FROM Users");
        ResultSet rst = stm.executeQuery();
        ArrayList<User> users = new ArrayList<>();
        while (rst.next()) {
            users.add(new User(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)
            ));
        }
        return users;
    }


    public boolean saveDoctorLogin(DoctorLogin u1) throws SQLException, ClassNotFoundException, SQLException {
        Connection con = DbConnection.getInstance().getConnection();
        String query = "INSERT INTO DoctorLogin VALUES(?, ?, ?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setObject(1, u1.getUserName());
        stm.setObject(2, u1.getFullName());
        stm.setObject(3, u1.getEmail());
        stm.setObject(4, u1.getPassword());

        return stm.executeUpdate() > 0;


    }

    public ArrayList<DoctorLogin> getAllDoctorLogin() throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        PreparedStatement stm = con.prepareStatement("SELECT * FROM DoctorLogin");
        ResultSet rst = stm.executeQuery();
        ArrayList<DoctorLogin> users = new ArrayList<>();
        while (rst.next()) {
            users.add(new DoctorLogin(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)
            ));
        }
        return users;
    }

}
