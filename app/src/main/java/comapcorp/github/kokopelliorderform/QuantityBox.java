package comapcorp.github.kokopelliorderform;

import android.widget.EditText;

/**
 * Created by Theodore on 5/13/16.
 */
public class QuantityBox implements Comparable<QuantityBox> {

    private EditText quantityBox;
    private String itemName;

    public QuantityBox(EditText box, String itemName) {
        this.itemName = itemName;
        quantityBox = box;
    }

    public EditText getQuantityBox() {
        return quantityBox;
    }

    public String getItemName() {
        return itemName;
    }

    public void setQuantityBox(EditText newBox) {
        quantityBox = newBox;
    }

    public void setItemName(String newName) {
        itemName = newName;
    }

    public int compareTo(QuantityBox other) {
        return itemName.compareTo(other.getItemName());
    }

    public boolean equals(QuantityBox other) {
        return compareTo(other) == 0;
    }
}
