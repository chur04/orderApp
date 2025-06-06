package manager;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import model.Customer;
import model.OrderDetail;
import model.Product;
import model.Order;
import util.Validation;

/**
 *
 * @author wrx_Chur04
 */
public class ManageOrder {

    public static void createNewOrder(ArrayList<Order> OL, ArrayList<Product> PL) {
        System.out.println();

        String id;
        do {

            id = "OD" + (int) (Math.random() * 1000);
            System.out.println("Order ID: " + id);

        } while (!Validation.checkIDOrderExist(OL, id));

        System.out.println("Order Date: " + (LocalDate.now()));

        System.out.print("Enter customer name: ");
        String name = Validation.checkInputString();

        System.out.print("Enter customer address: ");
        String address = Validation.checkInputString();

        Customer customer = new Customer(name, address);
        ArrayList<OrderDetail> orderDetails = new ArrayList<OrderDetail>();

        while (true) {
            System.out.println();
            System.out.println("====Add product to order by select a product from below list====");
            Collections.sort(PL);
            System.out.println(String.format("%-15s %-15s %-15s\n", "ProductID", "Product Name", "Product Price"));
            for (Product product : PL) {
                System.out.println(product.toString());
            }

            System.out.print("Enter the product ID want to order: ");
            String idPO = Validation.checkInputString();
            Product productSelect = null;
            for (Product product : PL) {
                if (idPO.equalsIgnoreCase(product.getProductID())) {
                    productSelect = product;
                    break;
                }
            }

            if (productSelect == null) {
                System.out.println("Please enter exactly product ID to order! Enter again: ");
                continue;
            }
            System.out.print("Enter quantity: ");
            int quantity = Validation.checkInputInt(1, 1000);
            orderDetails.add(new OrderDetail(productSelect, quantity));

            System.out.print("Do you wan na add more product to order(Y/N): ");
            if (!Validation.checkInputYN()) {
                break;
            }

        }

        Order oder = new Order(id, LocalDate.now(), customer, orderDetails);
        OL.add(oder);
        System.out.println();
        System.out.println("            ========= INVOICE =========");
        for (Order order : OL) {
            System.out.println("Order ID: " + order.getOrderID());
            System.out.println("Order Date: " + order.getDateOrder());
            System.out.println("Customer Name: " + order.getCustomer().getCustomerName());
            System.out.println("Customer Address: " + order.getCustomer().getCustomerAddress());
            System.out.println("-------------------------------------------------------");
            System.out.printf("%-10s %-15s %-10s %-10s %-10s\n", "ID", "Name", "Price", "Quantity", "Subtotal");

            for (OrderDetail or : orderDetails) {
                System.out.println(or.toString());
            }

            System.out.println("-------------------------------------------------------");
            System.out.printf("                    TOTAL PAY: %.2fVND\n", order.getTotalPrice());
            System.out.println("=======================================================");
            exportInvoiceToFile(oder);
        }

    }

    public static void printOrder(Order order) {
        System.out.println();
        System.out.println("===========BILL==============");
        System.out.println("Order ID: " + order.getOrderID());
        System.out.println("Order Date: " + order.getDateOrder());
        System.out.println("Customer Name: " + order.getCustomer().getCustomerName());
        System.out.println("Customer Address: " + order.getCustomer().getCustomerAddress());
        System.out.println("-------------------------------------------------------");
        System.out.printf("%-10s %-15s %-10s %-10s %-10s\n", "ID", "Name", "Price", "Quantity", "Subtotal");

        for (OrderDetail or : order.getOrderDetails()) {
            System.out.println(or.toString());
        }

        System.out.println("-------------------------------------------------------");
        System.out.printf("                    TOTAL PAY: %.2fVND\n", order.getTotalPrice());
        System.out.println("=======================================================");
        System.out.println();
    }

    public static void printAllOrder(ArrayList<Order> OL) {
        for (Order order : OL) {
            printOrder(order);
        }
    }

    public static void updateOrder(ArrayList<Order> OL) {
        if (OL.isEmpty()) {
            System.out.println("List order is empty! Please create a new order");
            return;
        } else {
            System.out.print("Enter a order ID you wanna update: ");
            String id = Validation.checkInputString();
            if (!Validation.checkIDOrderExist(OL, id)) {
                for (Order order : OL) {
                     printOrder(order);
                     
                }
            }
        }
    }

    public static void exportInvoiceToFile(Order order) {
        String fileName = order.getOrderID() + "_invoice.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("========== INVOICE ==========\n");
            writer.write("Date create bill: " + LocalDate.now() + "\n");
            writer.write("Order ID: " + order.getOrderID() + "\n");
            writer.write("Order Date: " + order.getDateOrder() + "\n");
            writer.write("Customer Name: " + order.getCustomer().getCustomerName() + "\n");
            writer.write("Customer Address: " + order.getCustomer().getCustomerAddress() + "\n");
            writer.write("---------------------------------------------\n");
            writer.write(String.format("%-10s %-15s %-10s %-10s %-10s\n", "ID", "Name", "Price", "Qty", "Subtotal"));
            for (OrderDetail detail : order.getOrderDetails()) {
                writer.write(detail.toString() + "\n");
            }
            writer.write("---------------------------------------------\n");
            writer.write(String.format("TOTAL PAY: %.2f VND\n", order.getTotalPrice()));
            writer.write("=============================================\n");

            System.out.println("Hóa đơn đã được lưu vào file: " + fileName);
        } catch (IOException e) {
            System.out.println("Lỗi ghi file hóa đơn: " + e.getMessage());
        }
    }

}
