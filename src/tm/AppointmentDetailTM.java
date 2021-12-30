package tm;

import java.util.Objects;

public class AppointmentDetailTM {
    private String billNo;
    private double roomCharges;
    private double doctorCharge;
    private double testings;

    public AppointmentDetailTM() {
    }

    public AppointmentDetailTM(String billNo, double roomCharges, double doctorCharge, double testings) {
        this.setBillNo(billNo);
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
        return "AppointmentDetailTM{" +
                "billNo='" + billNo + '\'' +
                ", roomCharges=" + roomCharges +
                ", doctorCharge=" + doctorCharge +
                ", testings=" + testings +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppointmentDetailTM that = (AppointmentDetailTM) o;
        return Double.compare(that.roomCharges, roomCharges) == 0 &&
                Double.compare(that.doctorCharge, doctorCharge) == 0 &&
                Double.compare(that.testings, testings) == 0 &&
                Objects.equals(billNo, that.billNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(billNo, roomCharges, doctorCharge, testings);
    }
}
