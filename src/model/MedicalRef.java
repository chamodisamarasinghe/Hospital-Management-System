package model;

public class MedicalRef {
    private String refId;
    private String refName;
    private String orderDate;
    private String orderId;
    private String productCode;

    public MedicalRef() {
    }

    public MedicalRef(String refId, String refName, String orderDate, String orderId, String productCode) {
        this.setRefId(refId);
        this.setRefName(refName);
        this.setOrderDate(orderDate);
        this.setOrderId(orderId);
        this.setProductCode(productCode);
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    public String getRefName() {
        return refName;
    }

    public void setRefName(String refName) {
        this.refName = refName;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    @Override
    public String toString() {
        return "MedicalRef{" +
                "refId='" + refId + '\'' +
                ", refName='" + refName + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", orderId='" + orderId + '\'' +
                ", productCode='" + productCode + '\'' +
                '}';
    }
}
