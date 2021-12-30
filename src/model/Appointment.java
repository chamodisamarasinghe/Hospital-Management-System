package model;

import java.util.ArrayList;

public class Appointment {
    private String appointmentNo;
    private String patientNo;
    private String appointmentDate;
    private String time;
    private double cost;
    private ArrayList<AppointmentDetails> patients;

    public Appointment() {
    }

    public Appointment(String appointmentNo, String patientNo, String appointmentDate, String time, double cost, ArrayList<AppointmentDetails> patients) {
        this.setAppointmentNo(appointmentNo);
        this.setPatientNo(patientNo);
        this.setAppointmentDate(appointmentDate);
        this.setTime(time);
        this.setCost(cost);
        this.setPatients(patients);
    }

    public String getAppointmentNo() {
        return appointmentNo;
    }

    public void setAppointmentNo(String appointmentNo) {
        this.appointmentNo = appointmentNo;
    }

    public String getPatientNo() {
        return patientNo;
    }

    public void setPatientNo(String patientNo) {
        this.patientNo = patientNo;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public ArrayList<AppointmentDetails> getPatients() {
        return patients;
    }

    public void setPatients(ArrayList<AppointmentDetails> patients) {
        this.patients = patients;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "appointmentNo='" + appointmentNo + '\'' +
                ", patientNo='" + patientNo + '\'' +
                ", appointmentDate='" + appointmentDate + '\'' +
                ", time='" + time + '\'' +
                ", cost=" + cost +
                ", patients=" + patients +
                '}';
    }
}
