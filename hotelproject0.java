import java.text.SimpleDateFormat;
import java.util.*;

// ======================= DISH CLASS =======================
class Dish {
    private String name;
    private int price;

    Dish(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() { return name; }
    public int getPrice() { return price; }
}

// ======================= ORDER CLASS =======================
class Order {
    private List<Dish> items = new ArrayList<>();
    private int total = 0;

    public void addDish(Dish d, int qty) {
        for(int i = 0; i < qty; i++) {
            items.add(d);
        }
        total += d.getPrice() * qty;
    }

    public int getTotal() {
        return total;
    }

    //  FINAL BILL FORMAT
    public void showOrder() {

        int billNo = (int)(Math.random() * 10000);

        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
        String formattedDate = sdf.format(now);

        System.out.println("\n\t================= HOTEL BILL =================");
        System.out.println("\tBill No: " + billNo);
        System.out.println("\tDate: " + formattedDate);
        System.out.println("\t----------------------------------------------");

        System.out.printf("\t%-15s %-10s %-10s %-10s\n", "Item", "Qty", "Price", "Total");
        System.out.println("\t----------------------------------------------");

        Map<String, Integer> itemCount = new LinkedHashMap<>();
        Map<String, Integer> itemPrice = new HashMap<>();

        // Count items
        for(Dish d : items) {
            itemCount.put(d.getName(), itemCount.getOrDefault(d.getName(), 0) + 1);
            itemPrice.put(d.getName(), d.getPrice());
        }

        // Print table
        for(String name : itemCount.keySet()) {
            int qty = itemCount.get(name);
            int price = itemPrice.get(name);
            int totalItem = qty * price;

            System.out.printf("\t%-15s %-10d %-10d %-10d\n", name, qty, price, totalItem);
        }

        System.out.println("\t----------------------------------------------");
        System.out.printf("\t%-15s %-10s %-10s %-10d\n", "TOTAL", "", "", total);
        System.out.println("\t==============================================");

    }
}

// ======================= HOTEL CLASS =======================
class Hotel {
    List<Dish> vegMenu = new ArrayList<>();
    List<Dish> nonVegMenu = new LinkedList<>();

    Hotel() {
        vegMenu.add(new Dish("Pongal", 40));
        vegMenu.add(new Dish("Idly", 45));
        vegMenu.add(new Dish("Poori", 20));
        vegMenu.add(new Dish("Vada", 20));


        nonVegMenu.add(new Dish("Chicken Rice", 110));
        nonVegMenu.add(new Dish("Briyani", 120));
        nonVegMenu.add(new Dish("Prawn Briyani", 150));
        nonVegMenu.add(new Dish("Fish Curry", 90));
    }

    public void showMenu(List<Dish> menu) {
        int i = 1;
        for(Dish d : menu) {
            System.out.println(i + ". " + d.getName() + " - rs " + d.getPrice());
            i++;
        }
    }

    public void sortMenu(List<Dish> menu) {
        Collections.sort(menu, (a,b) -> a.getPrice() - b.getPrice());
    }
}

// ======================= MAIN CLASS =======================
public class hotelproject0 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Hotel hotel = new Hotel();
        Order order = new Order();

        try {
            Thread.sleep(1500);
            System.out.println("\n\n\t\tWELCOME TO HOTEL ");

            Thread.sleep(1000);
            System.out.println("\n1. Veg\n2. Non-Veg");
            System.out.println("( Enter 1 for veg, Enter 2 for non-veg ) ");
            int choice = sc.nextInt();

            List<Dish> menu;

            if(choice == 1) {
                menu = hotel.vegMenu;
            } else if(choice == 2) {
                menu = hotel.nonVegMenu;
            } else {
                throw new Exception("Invalid Menu Selection");
            }

            hotel.sortMenu(menu);

            int continueOrder;

            //  MULTIPLE ITEM ORDERING
            do {
                Thread.sleep(1000);
                System.out.println("\nMenu:");
                hotel.showMenu(menu);

                System.out.print("\nSelect Item: ");
                int item = sc.nextInt();

                if(item < 1 || item > menu.size()) {
                    throw new Exception("Invalid Item Selection");
                }

                System.out.print("Enter Quantity: ");
                int qty = sc.nextInt();

                Dish selected = menu.get(item - 1);
                order.addDish(selected, qty);

                System.out.println("\nItem added ");

                Thread.sleep(500);
                System.out.println("\nEnter 1 to Add More Items");
                System.out.println("Enter 2 to Checkout");
                continueOrder = sc.nextInt();

            } while(continueOrder == 1);

            // SHOW BILL
			Thread.sleep(1000);
            order.showOrder();

            // CONFIRMATION
			Thread.sleep(500);
            System.out.println("\nEnter 1 to Confirm Order");
            System.out.println("Enter 2 to Cancel");
            int confirm = sc.nextInt();

            if(confirm == 1) {
                Thread.sleep(1000);
                System.out.println("\nOrder Placed Successfully ");
            } else if(confirm == 2) {
                Thread.sleep(1000);
                System.out.println("\nOrder Cancelled ");
            } else {
                Thread.sleep(1000);
                throw new Exception("Invalid Selection");
            }

        } catch(Exception e) {
            System.out.println("\nError: " + e.getMessage());
        } finally {
            sc.close();
		
            System.out.println("\nThank you for visiting! ");
        }
    }
}