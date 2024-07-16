import java.util.Scanner;

public class WingStopOrderApp {
    public static int SIZE = 99;
    public static int count = 0;
    private static int idNum = 1010;

        public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Scanner inLine = new Scanner(System.in);
    
        WingStopOrder[] orders = new WingStopOrder[SIZE];
    
        while (true) {
            userType();
    
            System.out.print("\nUser Type [A|C|E]: "); // a-admin, c-cashier, e-exit
            String u = inLine.nextLine();
    
            if (u.equalsIgnoreCase("A")) {
                adminMenu();
    
                System.out.print("\nEnter choice (A|S|C|V|E|X): ");
                String c = inLine.nextLine();
    
                if (c.equalsIgnoreCase("A")) {
                    calculateAvgSales(orders);
                    promptEnterKey();
                } else if (c.equalsIgnoreCase("S")) {
                    calculateTotalPayments(orders);
                    promptEnterKey();
                } else if (c.equalsIgnoreCase("C")) {
                    totalCustByPaymentType(orders);
                    promptEnterKey();
                } else if (c.equalsIgnoreCase("V")) {
                    System.out.print("\nEnter order ID to view receipt: ");
                    while (!in.hasNextInt()) {
                        System.out.println("Invalid input. Please enter a valid order ID.");
                        in.next(); 
                    }
                    int orderId = in.nextInt();
                    viewReceipt(orders, orderId);
                    promptEnterKey();
                } else if (c.equalsIgnoreCase("E")) {
                    System.out.println("Exiting the system. Goodbye!");
                    break;
                } else {
                    System.out.println("Invalid choice. Please try again.");
                }
            } else if (u.equalsIgnoreCase("C")) {
                cashierMenu();
    
                System.out.print("\nEnter choice (A|R|U|E|X): ");
                String c = inLine.nextLine();
    
                if (c.equalsIgnoreCase("A")) {
                    addOrder(orders, in, inLine);
                    promptEnterKey();
                } else if (c.equalsIgnoreCase("R")) {
                    System.out.print("Enter order ID to remove: ");
                    while (!in.hasNextInt()) {
                        System.out.println("Invalid input. Please enter a valid order ID.");
                        in.next(); 
                    }
                    int removeId = in.nextInt();
    
                    System.out.print("Are you sure you want to remove this order? (Y|N): ");
                    String confirmation = in.next();
    
                    if (confirmation.equalsIgnoreCase("Y")) {
                        removeOrder(orders, removeId);
                    } else {
                        System.out.println("Removal canceled.");
                    }
                    promptEnterKey();
                } else if (c.equalsIgnoreCase("U")) {
                    System.out.print("Enter order ID to update: ");
                    while (!in.hasNextInt()) {
                        System.out.println("Invalid input. Please enter a valid order ID.");
                        in.next(); 
                    }
                    int updateId = in.nextInt();
                    updateOrder(orders, updateId, in, inLine);
                    promptEnterKey();
                } else if (c.equalsIgnoreCase("E")) {
                    System.out.println("Exiting the system. Goodbye!");
                    break;
                } else {
                    System.out.println("Invalid choice. Please try again.");
                }
            } else if (u.equalsIgnoreCase("E")) {
                System.out.println("Exiting the system. Goodbye!");
                break;
            } else {
                System.out.println("Invalid user type. Please try again.");
            }
        }
    
        in.close();
        inLine.close();
    }

    public static void userType() {
        System.out.println("\u000C");
        System.out.println("**************************************");
        System.out.println("*      Wingstop User Type:           *");
        System.out.println("**************************************");
        System.out.println("| A - Admin                          |");
        System.out.println("| C - Cashier                        |");
        System.out.println("| E - Exit                           |");
        System.out.println("**************************************");
    }

    
    public static void adminMenu() {
        System.out.println("\u000C");
        System.out.println("**************************************");
        System.out.println("*      Admin Menu:                   *");
        System.out.println("**************************************");
        System.out.println("| A - Calculate Average Sales        |");
        System.out.println("| S - Sum Payments                   |");
        System.out.println("| C - Count Customers by Payment Type|");
        System.out.println("| V - View Receipt                   |");
        System.out.println("| E - Exit                           |");
        System.out.println("| X - Back                           |");
        System.out.println("**************************************");
    }

    
    public static void cashierMenu() {
        System.out.println("\u000C");
        System.out.println("**************************************");
        System.out.println("*      Cashier Menu:                 *");
        System.out.println("**************************************");
        System.out.println("| A - Add Order                      |");
        System.out.println("| R - Remove Order                   |");
        System.out.println("| U - Update Order                   |");
        System.out.println("| E - Exit                           |");
        System.out.println("| X - Back                           |");
        System.out.println("**************************************");
    }

    
    public static void displayOrderMenu() {
        System.out.println("\u000C");
        System.out.println("**************************************");
        System.out.println("*      Wingstop Menu:                *");
        System.out.println("**************************************");
        System.out.println("| Set A - RM29.99                    |");
        System.out.println("|       - 20 pcs wings               |");
        System.out.println("|       - 2 bing fries               |");
        System.out.println("|       - Sprite 1L                  |");
        System.out.println("--------------------------------------");
        System.out.println("| Set B - RM39.99                    |");
        System.out.println("|       - 10 pcs wings               |");
        System.out.println("|       - 3 bing fries               |");
        System.out.println("|       - Ice Lemon Tea 1L           |");
        System.out.println("--------------------------------------");
        System.out.println("| Set C - RM59.99                    |");
        System.out.println("|       - 10 pcs wings               |");
        System.out.println("|       - 5 pcs thighs               |");
        System.out.println("|       - 10 pcs chicken tenders     |");
        System.out.println("|       - 4 bing fries               |");
        System.out.println("|       - Coca Cola 2L               |");
        System.out.println("--------------------------------------");
    }

    
    public static void displayPaymentMenu() {
        System.out.println("\u000C");
        System.out.println("**************************************");
        System.out.println("|      Payment Menu:                 |");
        System.out.println("**************************************");
        System.out.println("| O - Online Payment                 |");
        System.out.println("| C - Cash on Delivery               |");
        System.out.println("**************************************");
    }

    
    public static void displayPickupMenu() {
        System.out.println("\u000C");
        System.out.println("**************************************");
        System.out.println("|      Pickup Menu:                  |");
        System.out.println("**************************************");
        System.out.println("| 1 - Pickup at Store                |");
        System.out.println("| 2 - Home Delivery                  |");
        System.out.println("**************************************");
    }

    
    /*** ------------------------------- add order ------------------------------------------***/
    public static void addOrder(WingStopOrder[] orders, Scanner in, Scanner inLine) {
        int orderID = idNum++;
        
        System.out.print("\u000C");
        System.out.println("**************************************");
        System.out.println("*   Add New Order                    *");
        System.out.println("**************************************");

        System.out.print("Customer Name: ");
        String custName = inLine.nextLine();

        System.out.print("Customer Phone Number: ");
        String custPhone = inLine.nextLine();

        displayOrderMenu();

        System.out.print("Order Set [A|B|C]: ");
        char orderCode = inLine.nextLine().toUpperCase().charAt(0);
        while (orderCode != 'A' && orderCode != 'B' && orderCode != 'C') {
            System.out.println("Invalid input. Please enter A, B, or C.");
            System.out.print("Order Set [A|B|C]: ");
            orderCode = inLine.nextLine().toUpperCase().charAt(0);
        }

        System.out.print("Quantity: ");
        while (!in.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid quantity.");
            in.next(); 
        }
        int orderQty = in.nextInt();

        displayPaymentMenu();

        System.out.print("Payment Type [O|C]: ");
        char paymentType = inLine.nextLine().toUpperCase().charAt(0);
        while (paymentType != 'O' && paymentType != 'C') {
            System.out.println("Invalid input. Please enter O or C.");
            System.out.print("Payment Type [O|C]: ");
            paymentType = inLine.nextLine().toUpperCase().charAt(0);
        }

        displayPickupMenu();

        System.out.print("Pickup [1|2]: ");
        while (!in.hasNextInt()) {
            System.out.println("Invalid input. Please enter 1 or 2.");
            in.next(); 
        }
        int pickup = in.nextInt();
        while (pickup != 1 && pickup != 2) {
            System.out.println("Invalid input. Please enter 1 or 2.");
            System.out.print("Pickup [1|2]: ");
            pickup = in.nextInt();
        }

        String custAddress = "N/A"; // assume pickup == 1 (yes)
        if (pickup == 2) { // no
            System.out.print("Customer Address: ");
            custAddress = inLine.nextLine();
        }

        orders[count++] = new WingStopOrder(orderID, custName, custPhone, orderCode, orderQty, paymentType, pickup, custAddress);

        System.out.println("\n\nOrder added successfully for order ID: " + orderID);
        promptEnterKey();

        printFullReceipt(orders[count - 1]); // print receipt after adding new order
    }

    
    /*** ------------------------------- print receipt ------------------------------------------***/
    public static void printFullReceipt(WingStopOrder order) {
        double totalPrice = 0.0;
    
        System.out.print("\u000C");
        System.out.print("\n**************************************\n");
        System.out.print("*           Order Receipt            *\n");
        System.out.print("**************************************\n");
        order.printOrderDetails();
    
        // calculate total price based on order set and quantity
        switch (Character.toUpperCase(order.getOrderCode())) {
            case 'A':
                totalPrice = 29.99 * order.getOrderQty();
                break;
            case 'B':
                totalPrice = 39.99 * order.getOrderQty();
                break;
            case 'C':
                totalPrice = 59.99 * order.getOrderQty();
                break;
            default:
                break;
        }
    
        System.out.printf("Total Price: RM %.2f%n", totalPrice);
        System.out.println("****************************************\n");
    }

    
    /*** ------------------------------- remove order ------------------------------------------***/
    public static void removeOrder(WingStopOrder[] orders, int orderID) {
        for (int i = 0; i < orders.length; i++) {
            if (orders[i] != null && orders[i].getOrderID() == orderID) {
                orders[i] = null;
                System.out.println("Order removed successfully!");
                return;
            }
        }
        System.out.println("Order ID " + orderID + " not found.");
    }

        
        /*** ------------------------------- update order ------------------------------------------***/
        public static void updateOrder(WingStopOrder[] orders, int orderID, Scanner in, Scanner inLine) {
        for (int i = 0; i < orders.length; i++) {
            if (orders[i] != null && orders[i].getOrderID() == orderID) {
                System.out.println("Update Options:");
                System.out.println("1 - Customer Name");
                System.out.println("2 - Customer Phone Number");
                System.out.println("3 - Order Set");
                System.out.println("4 - Quantity");
                System.out.println("5 - Payment Type");
                System.out.println("6 - Pickup");
                System.out.println("7 - Customer Address");
    
                System.out.print("Enter your choice: ");
                while (!in.hasNextInt()) {
                    System.out.println("Invalid input. Please enter a valid choice.");
                    in.next(); 
                }
                int choice = in.nextInt();
    
                switch (choice) {
                    case 1:
                        System.out.print("New Customer Name: ");
                        String custName = inLine.nextLine();
                        orders[i].setCustName(custName);
                        break;
                    case 2:
                        System.out.print("New Customer Phone Number: ");
                        String custPhone = inLine.nextLine();
                        orders[i].setCustPhone(custPhone);
                        break;
                    case 3:
                        displayOrderMenu();
                        System.out.print("New Order Set [A|B|C]: ");
                        char orderCode = inLine.nextLine().charAt(0);
                        orders[i].setOrderCode(orderCode);
                        break;
                    case 4:
                        System.out.print("New Quantity: ");
                        while (!in.hasNextInt()) {
                            System.out.println("Invalid input. Please enter a valid quantity.");
                            in.next();
                        }
                        int orderQty = in.nextInt();
                        orders[i].setOrderQty(orderQty);
                        break;
                    case 5:
                        displayPaymentMenu();
                        System.out.print("New Payment Type [O|C]: ");
                        char paymentType = inLine.nextLine().charAt(0);
                        orders[i].setPaymentType(paymentType);
                        break;
                    case 6:
                        displayPickupMenu();
                        System.out.print("New Pickup [1|2]: ");
                        while (!in.hasNextInt()) {
                            System.out.println("Invalid input. Please enter a valid pickup type.");
                            in.next(); 
                        }
                        int pickup = in.nextInt();
                        orders[i].setPickup(pickup);
                        if (pickup == 2) {
                            System.out.print("New Customer Address: ");
                            String custAddress = inLine.nextLine();
                            orders[i].setCustAddress(custAddress);
                        } else {
                            orders[i].setCustAddress("N/A");
                        }
                        break;
                    case 7:
                        System.out.print("New Customer Address: ");
                        String custAddress = inLine.nextLine();
                        orders[i].setCustAddress(custAddress);
                        break;
                    default:
                        System.out.println("Invalid choice.");
                        return;
                }
                System.out.println("Order ID " + orderID + " has been updated.");
                return;
            }
        }
        System.out.println("Order ID " + orderID + " not found.");
    }
    
    
        
    /*** ------------------------------- calc avg ------------------------------------------***/
    public static void calculateAvgSales(WingStopOrder[] orders) {
        double totalSalesPickup = 0.0;
        double totalSalesDelivery = 0.0;
        int countPickup = 0;
        int countDelivery = 0;
    
        for (WingStopOrder order : orders) {
            if (order != null) {
                double pricePerUnit = 0.0;
                switch (Character.toUpperCase(order.getOrderCode())) {
                    case 'A':
                        pricePerUnit = 29.99;
                        break;
                    case 'B':
                        pricePerUnit = 39.99;
                        break;
                    case 'C':
                        pricePerUnit = 59.99;
                        break;
                    default:
                        break;
                }
                double orderTotal = pricePerUnit * order.getOrderQty();
                if (order.getPickup() == 1) {
                    totalSalesPickup += orderTotal;
                    countPickup++;
                } else if (order.getPickup() == 2) {
                    totalSalesDelivery += orderTotal;
                    countDelivery++;
                }
            }
        }
    
        if (countPickup > 0) {
            double avgSalesPickup = totalSalesPickup / countPickup;
            System.out.printf("Average sales for Pickup is RM %.2f%n", avgSalesPickup);
        } else {
            System.out.println("No sales for Pickup");
        }
        
        if (countDelivery > 0) {
            double avgSalesDelivery = totalSalesDelivery / countDelivery;
            System.out.printf("Average sales for Delivery is RM %.2f%n", avgSalesDelivery);
        } else {
            System.out.println("No sales for Delivery");
        }
    }

    
    /*** ------------------------------- sum payment ------------------------------------------***/
    public static void calculateTotalPayments(WingStopOrder[] orders) {
        double totalPayments = 0.0;
        int numCustomers = 0;

        for (WingStopOrder order : orders) {
            if (order != null) {
                double price = 0.0;
                switch (Character.toUpperCase(order.getOrderCode())) {
                    case 'A':
                        price = 29.90;
                        break;
                    case 'B':
                        price = 39.90;
                        break;
                    case 'C':
                        price = 59.90;
                        break;
                }
                totalPayments += price * order.getOrderQty();
                numCustomers++;
            }
        }

        System.out.println("Total number of customers: " + numCustomers);
        System.out.printf("Total payments from all orders: RM %.2f%n", totalPayments);
    }

    
    /*** ------------------------------- count customer ------------------------------------------***/
    public static void totalCustByPaymentType(WingStopOrder[] orders) {
        int onlineCount = 0;
        int cashCount = 0;
    
        for (WingStopOrder order : orders) {
            if (order != null) {
                if (Character.toUpperCase(order.getPaymentType()) == 'O') {
                    onlineCount++;
                } else if (Character.toUpperCase(order.getPaymentType()) == 'C') {
                    cashCount++;
                }
            }
        }
    
        System.out.println("Number of customers who paid Online: " + onlineCount);
        System.out.println("Number of customers who paid Cash: " + cashCount);
    }


    /*** ------------------------------- view receipt ------------------------------------------***/
    public static void viewReceipt(WingStopOrder[] orders, int orderId) {
        boolean found = false;
        for (WingStopOrder order : orders) {
            if (order != null && order.getOrderID() == orderId) {
                printFullReceipt(order);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Receipt not found for order ID " + orderId);
        }
    }

    
    public static void promptEnterKey() {
        System.out.println("\nPress \"Enter\" to continue...");
        try {
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}