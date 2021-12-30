package model;

import java.util.Objects;

public class NewOrder {
    private String pharmtId;
    private String pharmName;
    private String orderId;
    private String orderDate;
    private double cost;

    public NewOrder() {
    }

    public NewOrder(String pharmtId, String pharmName, String orderId, String orderDate, double cost) {
        this.setPharmtId(pharmtId);
        this.setPharmName(pharmName);
        this.setOrderId(orderId);
        this.setOrderDate(orderDate);
        this.setCost(cost);
    }

    public String getPharmtId() {
        return pharmtId;
    }

    public void setPharmtId(String pharmtId) {
        this.pharmtId = pharmtId;
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
        return "NewOrder{" +
                "pharmtId='" + pharmtId + '\'' +
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
        NewOrder newOrder = (NewOrder) o;
        return Double.compare(newOrder.cost, cost) == 0 &&
                Objects.equals(pharmtId, newOrder.pharmtId) &&
                Objects.equals(pharmName, newOrder.pharmName) &&
                Objects.equals(orderId, newOrder.orderId) &&
                Objects.equals(orderDate, newOrder.orderDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pharmtId, pharmName, orderId, orderDate, cost);
    }
}
