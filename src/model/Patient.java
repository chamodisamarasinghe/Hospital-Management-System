package model;

public class Patient {
    private String patientNo;
    private String roomNo;
    private String doctorName;
    private String patientName;
    private int age;
    private String address;
    private String birthday;
    private String category;
    private String gender;
    private String wardNo;

    public Patient() {
    }

    public Patient(String patientNo, String roomNo, String doctorName, String patientName, int age, String address, String birthday, String category, String gender,  String wardNo) {
        this.setPatientNo(patientNo);
        this.setRoomNo(roomNo);
        this.setDoctorName(doctorName);
        this.setPatientName(patientName);
        this.setAge(age);
        this.setAddress(address);
        this.setBirthday(birthday);
        this.setCategory(category);
        this.setGender(gender);
        this.setWardNo(wardNo);
    }

    public String getPatientNo() {
        return patientNo;
    }

    public void setPatientNo(String patientNo) {
        this.patientNo = patientNo;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getWardNo() {
        return wardNo;
    }

    public void setWardNo(String wardNo) {
        this.wardNo = wardNo;
    }

    @Override
    public String

    toString() {
        return "Patient{" +
                "patientNo='" + patientNo + '\'' +
                ", roomNo='" + roomNo + '\'' +
                ", doctorName='" + doctorName + '\'' +
                ", patientName='" + patientName + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", birthday='" + birthday + '\'' +
                ", category='" + category + '\'' +
                ", gender='" + gender + '\'' +
                ", wardNo='" + wardNo + '\'' +
                '}';
    }
}
