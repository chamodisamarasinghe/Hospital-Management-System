package model;

public class MedicalDetail {
    private String productCode;
    private int qty;
    private double price;

    public MedicalDetail(String productCode) {
        this.setProductCode(productCode);
    }

    public MedicalDetail(String productCode, int qty, double price) {
        this.setProductCode(productCode);
        this.setQty(qty);
        this.setPrice(price);
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "MedicalDetail{" +
                "productCode='" + productCode + '\'' +
                ", qty=" + qty +
                ", price=" + price +
                '}';
    }
}
