package model;

public class Medicine {
    private String productCode;
    private String productName;
    private double unitPrice;
    private int qtyOnHand;

    public Medicine() {
    }

    public Medicine(String productCode, String productName, double unitPrice, int qtyOnHand) {
        this.setProductCode(productCode);
        this.setProductName(productName);
        this.setUnitPrice(unitPrice);
        this.setQtyOnHand(qtyOnHand);
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

    @Override
    public String toString() {
        return "Medicine{" +
                "productCode='" + productCode + '\'' +
                ", productName='" + productName + '\'' +
                ", unitPrice=" + unitPrice +
                ", qty=" + qtyOnHand +
                '}';
    }
}
