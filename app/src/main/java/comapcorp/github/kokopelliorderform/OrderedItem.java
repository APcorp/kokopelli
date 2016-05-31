package comapcorp.github.kokopelliorderform;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Theodore on 5/8/16.
 */
public class OrderedItem implements Comparable<OrderedItem>, Parcelable {
    private String mItemName;
    private int mQuantity;
    private double mUnitPrice;


    public OrderedItem(String itemName, int quantity, double unitPrice) {
        mItemName = itemName;
        mQuantity = quantity;
        mUnitPrice = unitPrice;
    }

    public String getItemName() {
        return mItemName;
    }

    public int getQuantity() {
        return mQuantity;
    }

    public double getUnitPrice() {
        return mUnitPrice;
    }

    public void setItemName(String itemName) {
        mItemName = itemName;
    }

    public void setQuantity(int quantity) {
        mQuantity = quantity;
    }

    public void setUnitPrice(double unitPrice) {
        mUnitPrice = unitPrice;
    }

    public int compareTo(OrderedItem other) {
        return mItemName.compareTo(other.getItemName());
    }

    public boolean equals(OrderedItem other) {
        return compareTo(other) == 0;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(mItemName);
        out.writeInt(mQuantity);
        out.writeDouble(mUnitPrice);
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
        mItemName = in.readString();
        mQuantity = in.readInt();
        mUnitPrice = in.readDouble();
    }
}
