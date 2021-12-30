package tm;

import javafx.scene.control.Button;

public class DoctorTM {
     private String doctorId;
     private String doctorName;
     private String doctorContactNo;
     private String workingDate;
     private String workingTime;
     private String doctorEmail;
     private String roomNo;
     private String category;
     private String btn;

    public DoctorTM(String btn) {
        this.setBtn(btn);
    }

    public DoctorTM(String doctorId, String doctorName, String doctorContactNo, String workingDate, String workingTime, String doctorEmail, String roomNo, String category, Button btn) {
    }

    public DoctorTM(String doctorId, String doctorName, String doctorContactNo, String workingDate, String workingTime, String doctorEmail, String roomNo, String category) {
        this.setDoctorId(doctorId);
        this.setDoctorName(doctorName);
        this.setDoctorContactNo(doctorContactNo);
        this.setWorkingDate(workingDate);
        this.setWorkingTime(workingTime);
        this.setDoctorEmail(doctorEmail);
        this.setRoomNo(roomNo);
        this.setCategory(category);
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorContactNo() {
        return doctorContactNo;
    }

    public void setDoctorContactNo(String doctorContactNo) {
        this.doctorContactNo = doctorContactNo;
    }

    public String getWorkingDate() {
        return workingDate;
    }

    public void setWorkingDate(String workingDate) {
        this.workingDate = workingDate;
    }

    public String getWorkingTime() {
        return workingTime;
    }

    public void setWorkingTime(String workingTime) {
        this.workingTime = workingTime;
    }

    public String getDoctorEmail() {
        return doctorEmail;
    }

    public void setDoctorEmail(String doctorEmail) {
        this.doctorEmail = doctorEmail;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "DoctorTM{" +
                "doctorId='" + doctorId + '\'' +
                ", doctorName='" + doctorName + '\'' +
                ", doctorContactNo='" + doctorContactNo + '\'' +
                ", workingDate='" + workingDate + '\'' +
                ", workingTime='" + workingTime + '\'' +
                ", doctorEmail='" + doctorEmail + '\'' +
                ", roomNo='" + roomNo + '\'' +
                ", category='" + category + '\'' +
                '}';
    }

    public String getBtn() {
        return btn;
    }

    public void setBtn(String btn) {
        this.btn = btn;
    }
}
