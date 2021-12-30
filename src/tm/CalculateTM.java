package tm;

public class CalculateTM {
    private String productCode;
    private String productName;
    private int qty;
    private double unitPrice;
    private double total;

    public CalculateTM() {
    }

    public CalculateTM(String productCode, String productName, int qty, double unitPrice, double total) {
        this.setProductCode(productCode);
        this.setProductName(productName);
        this.setQty(qty);
        this.setUnitPrice(unitPrice);
        this.setTotal(total);
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

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "CalculateTM{" +
                "productCode='" + productCode + '\'' +
                ", productName='" + productName + '\'' +
                ", qty=" + qty +
                ", unitPrice=" + unitPrice +
                ", total=" + total +
                '}';
    }
}
