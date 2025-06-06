package manager;

/**
 *
 * @author wrx_Chur04
 */
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
import model.Product;
import util.Validation ; 




public class ManageProduct {

    private Scanner sc = new Scanner(System.in);

    public static void showMenu() {
        System.out.println();
        System.out.println("========Menu=======");
        System.out.println("    1. ADD Product To Menu");
        System.out.println("    2. Update Price Of Product");
        System.out.println("    3. Display List Product");
        System.out.println("    4. Sort product by price");
        System.out.println("    5. Delete product by ID");
        System.out.println("    6. Order Product and Export Invoice");
        System.out.println("    7. Print List Order");
        System.out.println("    8. Delete Order By ID");
        System.out.println("    9. Update Order By ID");
        System.out.println("    10. Exit");
        System.out.println("===================");
        System.out.print("Enter your choice: ");

    }

    public static void createNewProduct(ArrayList<Product> PL) {
        while (true) {
            System.out.println();
            String id;
            do {
                id = "PD" + (int) (Math.random() * 1000);
            } while (!Validation.checkIDProductExist(PL, id));
            System.out.println("Product ID: " + id);
            System.out.print("Ente product name: ");
            String name = Validation.checkInputString();

            System.out.print("Enter product price: ");
            double price = Validation.checkInputPrice();

            if (!Validation.checkProductExist(PL, id, name, price)) {
                System.out.println("The product were exist! ");
                continue;
            }

            PL.add(new Product(id, name, price));
            System.out.println("========ADD PRODUCT SUCCESS=======");
            System.out.println();
            System.out.print("Do you wan na continue add product(Y/N) : ");
            if (!Validation.checkInputYN()) {
                break;

            }
        }
    }

    public static void updateProductPrice(ArrayList<Product> PL) {

        if (PL.isEmpty()) {

            System.out.println("Product list is empty . Please add new product!");

        } else {
            System.out.print("Enter product ID to update price of this product: ");
            String idFind = Validation.checkInputString();
            for (Product product : PL) {
                if (product.getProductID().equalsIgnoreCase(idFind)) {
                    System.out.println();
                    System.out.print("Enter new price: ");
                    double newPrice = Validation.checkInputPrice();
                    product.setProductPrice(newPrice);
                    System.out.println();
                    System.out.println("=====Update Success====");
                    return;
                }
            }

            System.out.println("Found product fail cause! Product ID not exist!");
        }

    }

    public static void sortProduct(ArrayList<Product> PL) {

        if (PL.isEmpty()) {
            System.out.println("Product list is empty . Please add new product!");
        } else {
            Collections.sort(PL);
            System.out.println("====Sort Successfully====");
        }

    }

    public static void displayProduct(ArrayList<Product> PL) {
        if (PL.isEmpty()) {
            System.out.println("Product list is empty . Please add new product!");
        } else {
            System.out.println("       ======List Products=====");
            System.out.println(String.format("%-15s %-15s %-15s\n", "ID", "Name", "Price"));
            for (Product product : PL) {
                System.out.println(product.toString());
            }

        }
    }
    
    public static void deleteProduct(ArrayList<Product> PL){
        if (PL.isEmpty()) {
            System.out.println("Product list is empty . Please add new product!");
        } else {
            System.out.print("Enter product ID to delete: ");
            String fID = Validation.checkInputString() ; 
            for (Product product : PL){
                if (fID.equalsIgnoreCase(product.getProductID())){
                    PL.remove(product) ; 
                    System.out.println();
                    System.out.println("====Delete Successfully====");
                    break ; 
                }
            }
            System.out.println("Can not delete cause ID is not valid! ");
            
        }
    }
}
