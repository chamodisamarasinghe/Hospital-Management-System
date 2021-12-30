package tm;

import java.util.Objects;

public class ReportTM {
    private String orderId;
    private String pharmId;
    private String orderDate;
    private String time;
    private double cost;

    public ReportTM() {
    }

    public ReportTM(String orderId, String pharmId, String orderDate, String time, double cost) {
        this.setOrderId(orderId);
        this.setPharmId(pharmId);
        this.setOrderDate(orderDate);
        this.setTime(time);
        this.setCost(cost);
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPharmId() {
        return pharmId;
    }

    public void setPharmId(String pharmId) {
        this.pharmId = pharmId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
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

    @Override
    public String toString() {
        return "ReportTM{" +
                "orderId='" + orderId + '\'' +
                ", pharmId='" + pharmId + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", time='" + time + '\'' +
                ", cost=" + cost +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportTM reportTM = (ReportTM) o;
        return Double.compare(reportTM.cost, cost) == 0 &&
                Objects.equals(orderId, reportTM.orderId) &&
                Objects.equals(pharmId, reportTM.pharmId) &&
                Objects.equals(orderDate, reportTM.orderDate) &&
                Objects.equals(time, reportTM.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, pharmId, orderDate, time, cost);
    }
}
