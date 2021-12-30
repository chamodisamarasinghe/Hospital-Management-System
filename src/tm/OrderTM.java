package tm;

import java.util.Objects;

public class OrderTM {
    private String pharmId;
    private String pharmName;
    private String orderId;
    private String orderDate;
    private double cost;

    public OrderTM() {
    }

    public OrderTM(String pharmId, String pharmName, String orderId, String orderDate, double cost) {
        this.setPharmId(pharmId);
        this.setPharmName(pharmName);
        this.setOrderId(orderId);
        this.setOrderDate(orderDate);
        this.setCost(cost);
    }

    public String getPharmId() {
        return pharmId;
    }

    public void setPharmId(String pharmId) {
        this.pharmId = pharmId;
    }

    public String getPharmName() {
        return pharmName;
    }

    public void setPharmName(String pharmName) {
        this.pharmName = pharmName;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "OrderTM{" +
                "pharmId='" + pharmId + '\'' +
                ", pharmName='" + pharmName + '\'' +
                ", orderId='" + orderId + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", cost=" + cost +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderTM orderTM = (OrderTM) o;
        return Double.compare(orderTM.cost, cost) == 0 &&
                Objects.equals(pharmId, orderTM.pharmId) &&
                Objects.equals(pharmName, orderTM.pharmName) &&
                Objects.equals(orderId, orderTM.orderId) &&
                Objects.equals(orderDate, orderTM.orderDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pharmId, pharmName, orderId, orderDate, cost);
    }
}
