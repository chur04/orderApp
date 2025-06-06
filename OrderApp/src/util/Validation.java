
package util;

/**
 *
 * @author wrx_Chur04
 */
import java.util.ArrayList;
import java.util.Scanner;
import model.Product ; 
import model.Order ; 




public class Validation {
    private static final Scanner sc = new Scanner(System.in);

    public static int checkInputInt(int min, int max) {
        while (true) {
            try {
                int result = Integer.parseInt(sc.nextLine().trim());

                if (result < min | result > max) {
                    throw new NumberFormatException();
                }
                return result;
            } catch (NumberFormatException e) {
                System.out.println("Must enter in range [" + (min) + "-" + (max) + "].");
                System.out.print("Please enter again: ");
            }
        }
    }

    public static String checkInputString() {
        while (true) {
            String result = sc.nextLine().trim();

            if (!result.isEmpty()) {
                return result;
            }
            System.out.println("Must enter somthing. Can be empty! ");
            System.out.print("Please enter again: ");
        }
    }
    public static boolean checkInputYN() {
        while (true) {
            String result = checkInputString() ; 

            if (result.equalsIgnoreCase("Y")) {
                return true ;
            }
            
            if(result.equalsIgnoreCase("N")){
                return false ; 
            } 
            System.out.println("Must enter Y/y or N/n !");
            System.out.print("Please enter again: ");
        }
    }
    
    public static double checkInputPrice(){
        while(true){
            try {
                double result = Double.parseDouble(sc.nextLine().trim()) ; 
                
                if (result <= 0 ){
                    throw new NumberFormatException () ; 
                }
                return result; 
            } catch(NumberFormatException e){
                System.out.println("Must enter a positive number");
                System.out.print("Please enter again: ");
            }
        }
    }

    public static boolean checkProductExist(ArrayList<Product> PL, String id, String name, Double price) {
        for (Product product : PL) {
            if (id.equalsIgnoreCase(product.getProductID())
                    && name.equalsIgnoreCase(product.getProductName())
                    && price.equals(product.getProductPrice())) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkIDProductExist(ArrayList<Product> PL , String id){
        for (Product product : PL){
            if (id.equalsIgnoreCase(product.getProductID())){
                return false ; 
            }
        }
        return true ; 
    }
    
    public static boolean checkIDOrderExist(ArrayList<Order> OL , String id){
        for (Order order : OL ){
            if (id.equalsIgnoreCase(order.getOrderID())){
                return false ; 
            }
        }
        return true ; 
    }
    
}
