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
        if (PL.isEmpty()) {
            System.out.println();
            System.out.println("Please add product to create order");
            return;

        }
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
            boolean found = false;
            for (OrderDetail or : orderDetails) {
                if (or.getProduct().getProductID().equalsIgnoreCase(productSelect.getProductID())) {
                    or.setQuantity(or.getQuantity() + quantity);
                    found = true;
                    break;
                }
            }
            if (!found) {
                orderDetails.add(new OrderDetail(productSelect, quantity));
            }

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

            order.toString() ; 

            System.out.println("-------------------------------------------------------");
            System.out.printf("                    TOTAL PAY: %.2fVND\n", order.getTotalPrice());
            System.out.println("=======================================================");
            System.out.println();
            System.out.print("Do you wanna export invoice(Y/N) : ");
            boolean ok = Validation.checkInputYN();
            if (ok) {
                exportInvoiceToFile(oder);
            } else {
                break;
            }

        }
    }

    public static void deteleOrderById(ArrayList<Order> OL) {
        if (OL.isEmpty()) {
            System.out.println("List order is empty. Please order after that can delete list order ");
            System.out.println();
        } else {
            System.out.println();
            System.out.print("Enter order ID to delete: ");
            String fID = Validation.checkInputString();
            for (Order order : OL) {
                if (order.getOrderID().equalsIgnoreCase(fID)) {
                    OL.remove(order);
                    System.out.println("===========Delete Successfully===========");
                    return;
                }
            }

            System.out.println("Can not delete order cause ID not exits!");
            System.out.println();
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
        if (OL.isEmpty()) {
            System.out.println("List order is empty. Please order after that can see list order ");
            System.out.println();
        } else {
            for (Order order : OL) {
                printOrder(order);
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

    public static void showMenuUpdate() {
        System.out.println();
        System.out.println("    1.Update by add new product");
        System.out.println("    2.Update by delete older product");
        System.out.println("    3.Update by change quantity of product");
        System.out.println("    4.Update by change customer information");
        System.out.println("    5.Exit");
        System.out.print("      Enter your choice: ");

    }

    public static void exportInvoiceByID(ArrayList<Order> OL) {
        if (OL.isEmpty()) {
            System.out.println("List order is empty! Please create a new order");
            return;
        } else {
            System.out.print("Enter a order ID you wanna export: ");
            String fID = Validation.checkInputString();
            for (Order order : OL) {
                if (order.getOrderID().equalsIgnoreCase(fID)) {
                    exportInvoiceToFile(order);
                    System.out.println("=====Export Successfully=====");
                    return;
                }
            }
            System.out.println("Can not find order ID.");
        }
    }

    public static void updateOrderByOrderID(ArrayList<Order> OL, ArrayList<Product> PL) {
        if (OL.isEmpty()) {
            System.out.println("List order is empty! Please create new order! ");
            return;
        }

        System.out.print("Enter order ID to update: ");
        String fID = Validation.checkInputString();
        Order orderToUpdate = null;

        for (Order order : OL) {
            if (order.getOrderID().equalsIgnoreCase(fID)) {
                orderToUpdate = order;
                break;
            }
        }

        if (orderToUpdate == null) {
            System.out.println("Can not find order ID");
            return;
        }

        while (true) {
            showMenuUpdate();
            int choice = Validation.checkInputInt(1, 5);
            switch (choice) {
                case 1:
                    System.out.println();
                    System.out.println("====Add product to order by select a product from below list====");
                    Collections.sort(PL);
                    System.out.println(String.format("%-15s %-15s %-15s\n", "ProductID", "Product Name", "Product Price"));
                    Collections.sort(PL);
                    for (Product product : PL) {
                        System.out.println(product.toString());
                    }

                    System.out.print("Enter id product wanna add: ");
                    String newPID = Validation.checkInputString();
                    Product selectedProduct = null;
                    for (Product product : PL) {
                        if (product.getProductID().equalsIgnoreCase(newPID)) {
                            selectedProduct = product;
                            break;
                        }
                    }

                    if (selectedProduct == null) {
                        System.out.println("Can not find product cause product ID not exist.");
                        break;
                    }

                    System.out.print("Enter quantity: ");
                    int qty = Validation.checkInputInt(1, 1000);
                    boolean found = false;
                    for (OrderDetail od : orderToUpdate.getOrderDetails()) {
                        if (od.getProduct().getProductID().equalsIgnoreCase(newPID)) {
                            od.setQuantity(od.getQuantity() + qty);
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        orderToUpdate.getOrderDetails().add(new OrderDetail(selectedProduct, qty));
                    }
                    System.out.println("=====ADD Product Successfull=====.");
                    break;

                case 2:
                    System.out.print("Enter ID product wan na delete to the order: ");
                    String delPID = Validation.checkInputString();
                    boolean removed = false;
                    for (int i = 0; i < orderToUpdate.getOrderDetails().size(); i++) {
                        OrderDetail od = orderToUpdate.getOrderDetails().get(i);
                        if (od.getProduct().getProductID().equalsIgnoreCase(delPID)) {
                            orderToUpdate.getOrderDetails().remove(i);
                            removed = true;
                            break;
                        }
                    }
                    if (removed) {
                        System.out.println("Delete product successfully.");
                    } else {
                        System.out.println("Can not find product in this order.");
                    }
                    break;

                case 3:
                    System.out.print("Enter product ID to change quantity: ");
                    String updPID = Validation.checkInputString();
                    boolean updated = false;
                    for (OrderDetail od : orderToUpdate.getOrderDetails()) {
                        if (od.getProduct().getProductID().equalsIgnoreCase(updPID)) {
                            System.out.print("Enter new quantity: ");
                            od.setQuantity(Validation.checkInputInt(1, 1000));
                            updated = true;
                            break;
                        }
                    }
                    if (updated) {
                        System.out.println("Cập nhật số lượng thành công.");
                    } else {
                        System.out.println("Không tìm thấy sản phẩm trong đơn hàng.");
                    }
                    break;

                case 4:
                    System.out.print("Enter new customer name: ");
                    orderToUpdate.getCustomer().setCustomerName(Validation.checkInputString());
                    System.out.print("Enter new customer address: ");
                    orderToUpdate.getCustomer().setCustomerAddress(Validation.checkInputString());
                    System.out.println("Update customer information successfull.");
                    break;

                case 5:
                    System.out.println("=====Exit update order menu=====");
                    return;
            }
        }
    }
}
