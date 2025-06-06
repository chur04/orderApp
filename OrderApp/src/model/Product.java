
package model;

/**
 *
 * @author wrx_Chur04
 */




public class Product implements Comparable<Product> {

    private String productID;
    private String productName;

    private double productPrice;

    public Product() {
    }

    public Product(String productID, String productName, double productPrice) {
        this.productID = productID;
        this.productName = productName;
        this.productPrice = productPrice;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductID() {
        return productID;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    @Override
    public String toString() {
        return String.format("%-15s %-15s %-15f\n", productID, productName, productPrice);
    }

    @Override
    public int compareTo(Product o) {

        return Double.compare(productPrice, o.getProductPrice());

    }
}
