package comapcorp.github.kokopelliorderform;

/**
 * Created by Theodore on 5/8/16.
 */
public class OrderedItem {
    private String itemName;
    private int quantity;

    public OrderedItem(String name, int quantity) {
        itemName = name;
        this.quantity = quantity;
    }

    public String getItemName() {
        return itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setItemName(String newName) {
        itemName = newName;
    }

    public void setQuantity(int newQuantity) {
        quantity = newQuantity;
    }
}
