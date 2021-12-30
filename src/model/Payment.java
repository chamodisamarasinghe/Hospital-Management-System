package model;

import java.util.Objects;

public class Payment {
    private String billNo;
    private String doctorName;
    private String category;
    private double roomCharges;
    private double doctorCharge;
    private double testings;

    public Payment() {
    }

    public Payment(String billNo, String doctorName, String category, double roomCharges, double doctorCharge, double testings) {
        this.setBillNo(billNo);
        this.setDoctorName(doctorName);
        this.setCategory(category);
        this.setRoomCharges(roomCharges);
        this.setDoctorCharge(doctorCharge);
        this.setTestings(testings);
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getRoomCharges() {
        return roomCharges;
    }

    public void setRoomCharges(double roomCharges) {
        this.roomCharges = roomCharges;
    }

    public double getDoctorCharge() {
        return doctorCharge;
    }

    public void setDoctorCharge(double doctorCharge) {
        this.doctorCharge = doctorCharge;
    }

    public double getTestings() {
        return testings;
    }

    public void setTestings(double testings) {
        this.testings = testings;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "billNo='" + billNo + '\'' +
                ", doctorName='" + doctorName + '\'' +
                ", category='" + category + '\'' +
                ", roomCharges=" + roomCharges +
                ", doctorCharge=" + doctorCharge +
                ", testings=" + testings +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Double.compare(payment.roomCharges, roomCharges) == 0 &&
                Double.compare(payment.doctorCharge, doctorCharge) == 0 &&
                Double.compare(payment.testings, testings) == 0 &&
                billNo.equals(payment.billNo) &&
                doctorName.equals(payment.doctorName) &&
                category.equals(payment.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(billNo, doctorName, category, roomCharges, doctorCharge, testings);
    }
}
