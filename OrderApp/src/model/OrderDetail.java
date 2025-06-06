
package model;

/**
 *
 * @author wrx_Chur04
 */




public class OrderDetail {
    private  Product product;
    private int quantity;

    public OrderDetail() {
    }

    public OrderDetail(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public double getSubPrice(){
        return product.getProductPrice() * quantity ; 
    }

    @Override
    public String toString() {
        return String.format("%-10s %-15s %-10.2f %-10d %-10.2f\n", product.getProductID() , product.getProductName() , product.getProductPrice() , quantity , getSubPrice()) ; 
    }
    
    
}
