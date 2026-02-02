import java.util.*;
abstract class Item {
protected String idCode;
    protected String title;
    protected double baseAmount;
 public Item(String idCode, String title, double baseAmount) {
        this.idCode = idCode;
        this.title = title;
        this.baseAmount = baseAmount;
    }
public abstract double discountValue();
 public double payableAmount() {
        return baseAmount - discountValue();
    }
public String getTitle() {
        return title;
    }
}
class ElectronicItem extends Item {
public ElectronicItem(String idCode, String title, double baseAmount) {
        super(idCode, title, baseAmount);
    }
@Override
    public double discountValue() {
        return baseAmount * 0.10;
    }
}
class ApparelItem extends Item {
public ApparelItem(String idCode, String title, double baseAmount) {
        super(idCode, title, baseAmount);
    }
 @Override
    public double discountValue() {
        return baseAmount * 0.20;
    }
}
class ReadingItem extends Item {
 public ReadingItem(String idCode, String title, double baseAmount) {
        super(idCode, title, baseAmount);
    }

    @Override
    public double discountValue() {
        return baseAmount * 0.05;
    }
}
class CartDetails {
 private List<Item> itemBucket = new ArrayList<>();
public void addItem(Item item) {
        itemBucket.add(item);
    }
 public List<Item> fetchItems() {
        return itemBucket;
    }

    public double totalPayable() {
        double sum = 0;
        for (Item item : itemBucket) {
            sum += item.payableAmount();
        }
        return sum;
    }
}
class Buyer {
private String buyerCode;
    private String buyerName;
    private CartDetails cartInfo;

    public Buyer(String buyerCode, String buyerName) {
        this.buyerCode = buyerCode;
        this.buyerName = buyerName;
        this.cartInfo = new CartDetails();
    }

    public CartDetails getCartInfo() {
        return cartInfo;
    }

    public String getBuyerName() {
        return buyerName;
    }
}
class PurchaseOrder {

    private String orderCode;
    private List<Item> orderedItems;
    private double finalBill;

    public PurchaseOrder(String orderCode, List<Item> orderedItems) {
        this.orderCode = orderCode;
        this.orderedItems = orderedItems;
        calculateBill();
    }

    private void calculateBill() {
        finalBill = 0;
        for (Item item : orderedItems) {
            finalBill += item.payableAmount();
        }
    }

    public void printOrderSummary() {
        System.out.println("Order No : " + orderCode);
        System.out.println("Items Purchased:");
        for (Item item : orderedItems) {
            System.out.println(item.getTitle() + " -> ₹" + item.payableAmount());
        }
        System.out.println("Total Payable : ₹" + finalBill);
    }
}
public class OnlineStoreApp {

    public static void main(String[] args) {

        Buyer user = new Buyer("U901", "Swathi");

        Item laptopItem = new ElectronicItem("EL11", "Laptop", 60000);
        Item shirtItem = new ApparelItem("AP22", "Casual Shirt", 1200);
        Item javaBook = new ReadingItem("BK33", "Java Programming", 800);

        user.getCartInfo().addItem(laptopItem);
        user.getCartInfo().addItem(shirtItem);
        user.getCartInfo().addItem(javaBook);

        PurchaseOrder order =
                new PurchaseOrder("ORD7788", user.getCartInfo().fetchItems());

        order.printOrderSummary();
    }
}