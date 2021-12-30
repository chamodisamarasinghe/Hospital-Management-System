package model;

import java.util.Objects;

public class Appointments1 {
    private String patientNo;
    private String patientName;
    private String appointmentNo;
    private String appointmentDate;
    private double cost;

    public Appointments1() {
    }

    public Appointments1(String patientNo, String patientName, String appointmentNo, String appointmentDate, double cost) {
        this.setPatientNo(patientNo);
        this.setPatientName(patientName);
        this.setAppointmentNo(appointmentNo);
        this.setAppointmentDate(appointmentDate);
        this.setCost(cost);
    }

    public String getPatientNo() {
        return patientNo;
    }

    public void setPatientNo(String patientNo) {
        this.patientNo = patientNo;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getAppointmentNo() {
        return appointmentNo;
    }

    public void setAppointmentNo(String appointmentNo) {
        this.appointmentNo = appointmentNo;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Appointments1{" +
                "patientNo='" + patientNo + '\'' +
                ", patientName='" + patientName + '\'' +
                ", appointmentNo='" + appointmentNo + '\'' +
                ", appointmentDate='" + appointmentDate + '\'' +
                ", cost=" + cost +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointments1 that = (Appointments1) o;
        return Double.compare(that.cost, cost) == 0 &&
                Objects.equals(patientNo, that.patientNo) &&
                Objects.equals(patientName, that.patientName) &&
                Objects.equals(appointmentNo, that.appointmentNo) &&
                Objects.equals(appointmentDate, that.appointmentDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(patientNo, patientName, appointmentNo, appointmentDate, cost);
    }
}
