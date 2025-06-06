package main;

/**
 *
 * @author wrx_Chur04
 */
import java.util.ArrayList;
import model.Product ; 
import util.Validation ; 
import manager.ManageProduct ; 
import manager.ManageOrder ; 
import model.Order ; 

public class Main {

    public static void main(String[] args) {
        ArrayList<Product> PL = new ArrayList<Product>();
        ArrayList<Order> OL = new ArrayList<Order>();
        while (true) {
            ManageProduct.showMenu();
            int choice = Validation.checkInputInt(1, 11);
            switch (choice) {
                case 1:
                    ManageProduct.createNewProduct(PL);
                    break;

                case 2:
                    ManageProduct.updateProductPrice(PL);
                    break;

                case 3:
                    ManageProduct.displayProduct(PL);
                    break;

                case 4:
                    ManageProduct.sortProduct(PL);
                    break;
                    
                case 5 : 
                    ManageProduct.deleteProduct(PL);
                    break ; 

                case 6:
                    ManageOrder.createNewOrder(OL, PL);
                    
                    break ; 
                    
                case 7:
                    ManageOrder.exportInvoiceByID(OL);
                    break ; 
                    
                    
                case 8 : 
                    ManageOrder.printAllOrder(OL);
                    break ; 
                    
                case 9 : 
                    ManageOrder.deteleOrderById(OL);
                    break ; 
                    
                case 10: 
                    ManageOrder.updateOrderByOrderID(OL , PL);
                    break ; 
                    
                case 11: 
                    return ; 
                    

            }
        }
    }

}
