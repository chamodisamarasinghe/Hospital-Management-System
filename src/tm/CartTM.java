package tm;

public class CartTM {
    private String billNo;
    private String doctorName;
    private String category;
    private double roomCharges;
    private double doctorCharge;
    private double testings;
    private double total;

    public CartTM() {
    }

    public CartTM(String billNo, String doctorName, String category, double roomCharges, double doctorCharge, double testings, double total) {
        this.setBillNo(billNo);
        this.setDoctorName(doctorName);
        this.setCategory(category);
        this.setRoomCharges(roomCharges);
        this.setDoctorCharge(doctorCharge);
        this.setTestings(testings);
        this.setTotal(total);
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "CartTM{" +
                "billNo='" + billNo + '\'' +
                ", doctorName='" + doctorName + '\'' +
                ", category='" + category + '\'' +
                ", roomCharges=" + roomCharges +
                ", doctorCharge=" + doctorCharge +
                ", testings=" + testings +
                ", total=" + total +
                '}';
    }
}
