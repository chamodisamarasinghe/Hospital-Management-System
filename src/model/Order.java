package model;

import java.util.ArrayList;

public class Order {
    private String orderId;
    private String pharmId;
    private String orderDate;
    private String time;
    private double cost;
    private ArrayList<MedicalDetail> products;

    public Order() {
    }

    public Order(String orderId, String pharmId, String orderDate, String time, double cost, ArrayList<MedicalDetail> products) {
        this.setOrderId(orderId);
        this.setPharmId(pharmId);
        this.setOrderDate(orderDate);
        this.setTime(time);
        this.setCost(cost);
        this.setProducts(products);
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

    public ArrayList<MedicalDetail> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<MedicalDetail> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", pharmId='" + pharmId + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", time='" + time + '\'' +
                ", cost=" + cost +
                ", products=" + products +
                '}';
    }
}
