
package model;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author wrx_Chur04
 */




public class Order {
    private String orderID;
    private LocalDate dateOrder;
    private Customer customer;
    private List<OrderDetail> orderDetails;

    public Order() {
    }

    public Order(String orderID, LocalDate dateOrder, Customer customer, List<OrderDetail> orderDetails) {
        this.orderID = orderID;
        this.dateOrder = dateOrder;
        this.customer = customer;
        this.orderDetails = orderDetails;
    }

    
    public String getOrderID() {
        return orderID;
    }

    public LocalDate getDateOrder() {
        return dateOrder;
    }

    

    public Customer getCustomer() {
        return customer;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public void setDateOrder(LocalDate dateOrder) {
        this.dateOrder = dateOrder;
    }

    

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public double getTotalPrice(){
        int total = 0 ; 
        for (OrderDetail orderDetail : orderDetails){
            total += orderDetail.getSubPrice() ; 
        }
        return total ; 
    }

    
    
    
    
    
    
}
