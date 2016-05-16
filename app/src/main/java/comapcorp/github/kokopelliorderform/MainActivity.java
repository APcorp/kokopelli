package comapcorp.github.kokopelliorderform;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    String name;
    String id;

    EditText etxtName;
    EditText etxtId;

    Button btnOrder;

    ArrayList<OrderedItem> cart;
    HashMap<String, Double> prices;
    ArrayList<QuantityBox> boxes;

    TextView price;
    double totalPrice;

    final double COFFEEPRICE = 1.25;
    final double CAPPUCCINOPRICE = 1.50;
    final double ICEDDRINKPRICE = 2.00;
    final double SMOOTHIEPRICE = 2.00;
    final double HOTCHOCOLATEPRICE = 1.50;
    final double APPLECIDERPRICE = 1.50;
    final double CHAITEAPRICE = 1.50;
    final double GREENTEAPRICE = 1.25;
    final double CHILLERPRICE = 1.50;
    final double MILKPRICE = 0.50;
    final double SNACKPRICE = 0.75;
    final double FRUITPRICE = 0.50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initializing the order button and the EditTexts that allow the user to input their name
        // and id
        btnOrder = (Button) findViewById(R.id.btnOrder);
        etxtName = (EditText) findViewById(R.id.etxtName);
        etxtId = (EditText) findViewById(R.id.etxtId);

        // initializes the name and ids to whatever is in the EditTexts for name and id. Usually
        // it's nothing
        name = etxtName.getText().toString();
        id = etxtId.getText().toString();

        // initializes our cart, menu of prices, and the data structure for all our quantity boxes
        cart = new ArrayList<>();
        prices = new HashMap<>();
        boxes = new ArrayList<>();

        price = (TextView) findViewById(R.id.txtPrice);

        // adds prices to the price menu

        prices.put(getString(R.string.cbx_brownie_text), SNACKPRICE);
        prices.put(getString(R.string.cbx_hot_chocolate_text), HOTCHOCOLATEPRICE);
        prices.put(getString(R.string.cbx_muffin_text), SNACKPRICE);
        prices.put(getString(R.string.cbx_regular_coffee_text), COFFEEPRICE);
        prices.put(getString(R.string.cbx_hazelnut_coffee_text), COFFEEPRICE);
        prices.put(getString(R.string.cbx_highlander_grogg_text), COFFEEPRICE);
        prices.put(getString(R.string.cbx_decaf_coffee_text), COFFEEPRICE);
        prices.put(getString(R.string.cbx_french_vanilla_cappuccino_text), CAPPUCCINOPRICE);
        prices.put(getString(R.string.cbx_caramel_macchiato_cappuccino_text), CAPPUCCINOPRICE);
        prices.put(getString(R.string.cbx_iced_chocolate_text), ICEDDRINKPRICE);
        prices.put(getString(R.string.cbx_iced_chai_text), ICEDDRINKPRICE);
        prices.put(getString(R.string.cbx_iced_caramel_text), ICEDDRINKPRICE);
        prices.put(getString(R.string.cbx_iced_vanilla_cappuccino_text), ICEDDRINKPRICE);
        prices.put(getString(R.string.cbx_strawberry_text), SMOOTHIEPRICE);
        prices.put(getString(R.string.cbx_mixed_berry_smoothie_text), SMOOTHIEPRICE);
        prices.put(getString(R.string.cbx_tropical_fruit_smoothie_text), SMOOTHIEPRICE);
        prices.put(getString(R.string.cbx_apple_cider_text), APPLECIDERPRICE);
        prices.put(getString(R.string.cbx_chai_tea_text), CHAITEAPRICE);
        prices.put(getString(R.string.cbx_green_tea_text), GREENTEAPRICE);

        // adding all the quantity boxes to an array for easy handling
        boxes.add(new QuantityBox((EditText) findViewById(R.id.etxtHotChocolate), getString(R.string.cbx_hot_chocolate_text)));
        boxes.add(new QuantityBox((EditText) findViewById(R.id.etxtRegularCoffee), getString(R.string.cbx_regular_coffee_text)));
        boxes.add(new QuantityBox((EditText) findViewById(R.id.etxtHazelnutCoffee), getString(R.string.cbx_hazelnut_coffee_text)));
        boxes.add(new QuantityBox((EditText) findViewById(R.id.etxtHighlanderGrogg), getString(R.string.cbx_highlander_grogg_text)));
        boxes.add(new QuantityBox((EditText) findViewById(R.id.etxtDecafCoffee), getString(R.string.cbx_decaf_coffee_text)));
        boxes.add(new QuantityBox((EditText) findViewById(R.id.etxtFrenchVanillaCappuccino), getString(R.string.cbx_french_vanilla_cappuccino_text)));
        boxes.add(new QuantityBox((EditText) findViewById(R.id.etxtCaramelMacchiatoCappuccino), getString(R.string.cbx_caramel_macchiato_cappuccino_text)));
        boxes.add(new QuantityBox((EditText) findViewById(R.id.etxtIcedChocolate), getString(R.string.cbx_iced_chocolate_text)));
        boxes.add(new QuantityBox((EditText) findViewById(R.id.etxtIcedChai), getString(R.string.cbx_iced_chai_text)));
        boxes.add(new QuantityBox((EditText) findViewById(R.id.etxtIcedCaramel), getString(R.string.cbx_iced_caramel_text)));
        boxes.add(new QuantityBox((EditText) findViewById(R.id.etxtIcedVanillaCappuccino), getString(R.string.cbx_iced_vanilla_cappuccino_text)));
        boxes.add(new QuantityBox((EditText) findViewById(R.id.etxtStrawberrySmoothie), getString(R.string.cbx_strawberry_text)));
        boxes.add(new QuantityBox((EditText) findViewById(R.id.etxtMixedBerrySmoothie), getString(R.string.cbx_mixed_berry_smoothie_text)));
        boxes.add(new QuantityBox((EditText) findViewById(R.id.etxtTropicalFruitSmoothie), getString(R.string.cbx_tropical_fruit_smoothie_text)));
        boxes.add(new QuantityBox((EditText) findViewById(R.id.etxtAppleCider), getString(R.string.cbx_apple_cider_text)));
        boxes.add(new QuantityBox((EditText) findViewById(R.id.etxtChaiTea), getString(R.string.cbx_chai_tea_text)));
        boxes.add(new QuantityBox((EditText) findViewById(R.id.etxtGreenTea), getString(R.string.cbx_green_tea_text)));

        // adding text changed listener for the name etxt so that when the user changes the name
        // we know, and we can reset the name String
        etxtName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                name = etxtName.getText().toString();
                //enableButton();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // adding text changed listener for the id etxt so that we can reset the id to what they
        // enter
        etxtId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                id = etxtId.getText().toString();
                // enableButton();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // adding text changed listener for every quantity box next to every single item. fingers
        // crossed, hope this works
        for (final QuantityBox i : boxes) {
            if (i.getQuantityBox() != null)

                i.getQuantityBox().setSelectAllOnFocus(true);
                i.getQuantityBox().addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        updateQuantity(s, i.getItemName());
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
        }

        /*
        EditText etxtBrownieQty = (EditText) findViewById(R.id.etxtCaramelMacchiatoCappuccino);

        if (etxtBrownieQty != null) etxtBrownieQty.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                updateQuantity(s, "Brownie");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        EditText etxtMuffin = (EditText) findViewById(R.id.etxtChaiTea);

        if (etxtMuffin != null) etxtMuffin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                updateQuantity(s, "Muffin");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        EditText etxtHotChocolate = (EditText) findViewById(R.id.etxtHotChocolate);

        if (etxtHotChocolate != null) etxtHotChocolate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                updateQuantity(s, "Hot Chocolate");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        */
    }

    /*
        private void enableButton() {
            boolean readyForCheckout = !name.equals("") && !id.equals("") && cart.size() != 0;


            btnOrder.setEnabled(readyForCheckout);

        }
    */

    /**
     * <b>summary: </b>is called when any checkbox on the activity is clicked. if the checkbox is
     * checked, then the adjacent EditText is revealed and the item is added
     * to the cart. If it is not checked, then the adjacent EditText is made hidden, and the item is
     * removed from the cart. The price is updated in either situation
     *
     * @param v default parameter
     */
    public void checkBoxClicked(View v) {
        CheckBox cbx = (CheckBox) v;

        if (cbx.isChecked()) {

            if (!cart.contains(new OrderedItem(cbx.getText().toString(), 1))) {
                EditText temp = (EditText) findViewById(cbx.getNextFocusRightId());

                if (temp != null)
                    cart.add(new OrderedItem(cbx.getText().toString(), Integer.parseInt(temp.getText().toString())));
            }


            setQuantityVisibility(cbx, View.VISIBLE, true);
        } else {

            remove(new OrderedItem(cbx.getText().toString(), 1));

            setQuantityVisibility(cbx, View.INVISIBLE, false);
        }

        calculatePrice();
        //enableButton();
    }

    /**
     * <b>summary: </b>is called when the order button on the activity is clicked. checks to make
     * sure that the name and id are entered. Also ensures that an item is ordered. If all these
     * conditions are met, the method starts a new Intent containing all the relevant information
     * on the page: name, id, and items ordered
     *
     * @param v default parameter
     */
    public void goToCheckout(View v) {
        boolean readyForCheckout = !name.equals("") && !id.equals("") && cart.size() != 0;

        if (readyForCheckout) {
            Intent intent = new Intent(getApplicationContext(), FinalActivity.class);

            intent.putExtra("Name", name);
            intent.putExtra("ID", id);

            intent.putExtra("Total", totalPrice);

            for (int i = 0; i < cart.size(); ++i) {
                System.out.println(cart.get(i).getItemName());
            }

            intent.putParcelableArrayListExtra("cart", cart);

            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "You need to enter your name and id and order an item!", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * <b>summary: </b>goes through all items in the cart, finds their prices and quantities,
     * then sums everything together. Stores the data in the global "price" variable
     */
    public void calculatePrice() {
        totalPrice = 0;

        for (OrderedItem i : cart) {
            totalPrice += prices.get(i.getItemName()) * i.getQuantity();
        }

        Formatter format = new Formatter();

        format.format("$%.2f", totalPrice);
        String priceDisplay = format.toString();
        price.setText(priceDisplay);

    }

    /**
     * <b>summary: </b>allows other methods to remove an item from the cart. Particularly useful in
     * the checkBoxClicked() method
     *
     * @param removeItem item that the client wishes to remove from the cart
     * @return true if the item was found and removed. false otherwise
     */
    private boolean remove(OrderedItem removeItem) {
        boolean found = false;

        for (int i = 0; i < cart.size() && !found; ++i) {
            if (removeItem.equals(cart.get(i))) {
                cart.remove(i);
                found = true;
            }
        }

        return found;
    }

    /**
     * <b>summary: </b>allows the client to find the index of a specific item in the cart. returns
     * -1 if the item cannot be found in the cart
     *
     * @param foundItem item that the client wishes to find
     * @return index of the item. -1 if the item cannot be found
     */
    private int find(OrderedItem foundItem) {
        int index = -1;

        for (int i = 0; i < cart.size() && index == -1; ++i) {
            if (foundItem.equals(cart.get(i))) {
                index = i;
            }
        }

        return index;
    }

    /**
     * <b>summary: </b>allows the client to set the visibility of the EditText next to each
     * checkbox. The user specifies the checkbox in question, the visibility they desire, and the
     * enabledness of the EditText
     *
     * @param cbx        checkbox next to which the client wishes to find the EditText
     * @param visibility the visibility of the EditText desired
     * @param enabled    whether the EditText is enabled or no
     */
    private void setQuantityVisibility(CheckBox cbx, int visibility, boolean enabled) {
        EditText etxtQuantity = (EditText) findViewById(cbx.getNextFocusRightId());

        if (etxtQuantity != null) {
            etxtQuantity.setVisibility(visibility);
            etxtQuantity.setEnabled(enabled);
        }
    }

    /**
     * <b>summary: </b>allows the client update the quantity of an item in the cart should the quantity
     * ever need to be changed.
     *
     * @param s        contents of the EditText
     * @param itemName name of the item in the cart
     */
    private void updateQuantity(CharSequence s, String itemName) {
        if (!s.toString().contains("-") && !s.toString().equals("")) {
            int index = find(new OrderedItem(itemName, 1));

            if (index > -1) {
                cart.get(index).setQuantity(Integer.parseInt(s.toString()));
            }
        }

        calculatePrice();
    }
}