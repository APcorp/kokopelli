package comapcorp.github.kokopelliorderform;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Theodore on 5/8/16.
 */
public class OrderedItem implements Comparable<OrderedItem>, Parcelable {
    private String itemName;
    private int quantity;
    private int mData;

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

    public int compareTo(OrderedItem other) {
        return itemName.compareTo(other.getItemName());
    }

    public boolean equals(OrderedItem other) {
        return compareTo(other) == 0;
    }


    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(mData);
    }

    public static final Parcelable.Creator<OrderedItem> CREATOR = new Parcelable.Creator<OrderedItem>() {
        public OrderedItem createFromParcel(Parcel in) {
            return new OrderedItem(in);
        }

        public OrderedItem[] newArray(int size) {
            return new OrderedItem[size];
        }
    };

    private OrderedItem(Parcel in) {
        mData = in.readInt();
    }
}
