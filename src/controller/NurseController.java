package controller;

import db.DbConnection;
import interfaces.NurseManage;
import model.Nurse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NurseController implements NurseManage {
    public List<String> getNurseId(String nurseId) throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().
                getConnection().prepareStatement("SELECT * FROM Nurse").executeQuery();
        List<String> codes = new ArrayList<>();
        while (rst.next()) {
            codes.add(
                    rst.getString(1)
            );
        }
        return codes;
    }
    @Override
    public boolean saveNurse(Nurse n) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        String query = "INSERT INTO Nurse VALUES(?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setObject(1, n.getNurseId());
        stm.setObject(2, n.getNurseName());
        stm.setObject(3, n.getRoomNo());
        stm.setObject(4, n.getWardNo());

        return stm.executeUpdate() > 0;
    }

    @Override
    public boolean updateNurse(Nurse n) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE Nurse SET nurseName=?, roomNo=?, wardNo=? WHERE nurseId=?");
        stm.setObject(1, n.getNurseName());
        stm.setObject(2, n.getRoomNo());
        stm.setObject(3, n.getWardNo());
        stm.setObject(4, n.getNurseId());

        return stm.executeUpdate() > 0;
    }

    @Override
    public boolean deleteNurse(String nurseId) throws SQLException, ClassNotFoundException {
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Nurse WHERE nurseId='" + nurseId + "'").executeUpdate() > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Nurse getNurse(String nurseId) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance()
                .getConnection().prepareStatement("SELECT * FROM Nurse WHERE nurseId=?");
        stm.setObject(1, nurseId);

        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            return new Nurse(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)

            );

        } else {
            return null;
        }

    }


    @Override
    public ArrayList<Nurse> getAllNurses() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Nurse");
        ResultSet rst = stm.executeQuery();
        ArrayList<Nurse> nurses = new ArrayList<>();
        while (rst.next()) {
            nurses.add(new Nurse(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)


            ));
        }
        return nurses;
    }
}
