package comapcorp.github.kokopelliorderform;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    String mName;
    String mId;
    String mEmail;
    TextView txtName;
    TextView txtId;
    TextView txtEmail;

    // Button btnOrder;

    ArrayList<OrderedItem> cart;
    HashMap<String, Double> prices;
    ArrayList<QuantityBox> boxes;

    TextView total;
    double price;

    final double SMOOTHIEPRICE = 2.00;
    final double ICEDDRINKPRICE = 2.00;
    final double HOTMIXEDDRINKPRICE = 1.50;
    final double CHILLERPRICE = 1.50;
    final double HOTDRINKPRICE = 1.25;
    final double TREATPRICE = 0.75;
    final double FRUITPRICE = 0.50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // getting the intent from the Welcome Activity that contains the user's name, id, and email
        Intent intent = getIntent();

        // initializing the order button and the TextViews that display the user's name, id, and email
        txtName = (TextView) findViewById(R.id.txtName);
        txtId = (TextView) findViewById(R.id.txtId);
        txtEmail = (TextView) findViewById(R.id.txtEmail);

        // loading the information from the Intent into the corresponding strings
        mName = intent.getStringExtra("name");
        mId = intent.getStringExtra("id");
        mEmail = intent.getStringExtra("email");

        // initializes the name and ids to whatever was sent from the previous activity in the Intent.
        // The information is retrieved from the user's Google account
        String name = "Name: " + mName;
        String id = "ID: " + mId;
        String email = mEmail;

        txtName.setText(name);
        txtId.setText(id);
        txtEmail.setText(email);

        // sets OnClickListener for the buttons in this Activity - Order and Favorite - so that when
        // they are clicked, the correct action is done
        findViewById(R.id.btnOrder).setOnClickListener(this);
        findViewById(R.id.btnFavOrder).setOnClickListener(this);


        // initializes our cart, menu of prices, and the data structure for all our quantity boxes
        cart = new ArrayList<>();
        prices = new HashMap<>();
        boxes = new ArrayList<>();

        total = (TextView) findViewById(R.id.txtPrice);

        // adds prices to the price menu
        // smoothies
        prices.put(getString(R.string.strawberry_smoothie), SMOOTHIEPRICE);
        prices.put(getString(R.string.mixed_berry_smoothie), SMOOTHIEPRICE);
        prices.put(getString(R.string.tropical_fruit_smoothie), SMOOTHIEPRICE);

        // iced drinks
        prices.put(getString(R.string.iced_chocolate), ICEDDRINKPRICE);
        prices.put(getString(R.string.iced_chai), ICEDDRINKPRICE);
        prices.put(getString(R.string.iced_caramel), ICEDDRINKPRICE);
        prices.put(getString(R.string.iced_vanilla_cappuccino), ICEDDRINKPRICE);

        // chillers
        prices.put(getString(R.string.french_vanilla_chiller_short), CHILLERPRICE);
        prices.put(getString(R.string.caramel_chiller_short), CHILLERPRICE);

        // hot mixed drinks
        prices.put(getString(R.string.french_vanilla_cappuccino), HOTMIXEDDRINKPRICE);
        prices.put(getString(R.string.caramel_macchiato_cappuccino), HOTMIXEDDRINKPRICE);
        prices.put(getString(R.string.hot_chocolate), HOTMIXEDDRINKPRICE);
        prices.put(getString(R.string.chai_tea), HOTMIXEDDRINKPRICE);
        prices.put(getString(R.string.apple_cider), HOTMIXEDDRINKPRICE);

        // hot drinks
        prices.put(getString(R.string.regular_coffee), HOTDRINKPRICE);
        prices.put(getString(R.string.hazelnut_coffee), HOTDRINKPRICE);
        prices.put(getString(R.string.highlander_grogg), HOTDRINKPRICE);
        prices.put(getString(R.string.decaf_coffee), HOTDRINKPRICE);
        prices.put(getString(R.string.green_tea), HOTDRINKPRICE);

        // treats
        prices.put(getString(R.string.brownie), TREATPRICE);
        prices.put(getString(R.string.blueberry_muffin), TREATPRICE);
        prices.put(getString(R.string.lemon_poppy_seed_muffin), TREATPRICE);
        prices.put(getString(R.string.chocolate_chip_muffin), TREATPRICE);
        prices.put(getString(R.string.cinnamon_streusel_muffin), TREATPRICE);

        // fruit
        prices.put(getString(R.string.apple), FRUITPRICE);
        prices.put(getString(R.string.banana), FRUITPRICE);
        prices.put(getString(R.string.orange), FRUITPRICE);

        // adding all the quantity boxes to an array for easy handling
        // smoothies
        boxes.add(new QuantityBox((EditText) findViewById(R.id.etxtStrawberrySmoothie), getString(R.string.strawberry_smoothie)));
        boxes.add(new QuantityBox((EditText) findViewById(R.id.etxtMixedBerrySmoothie), getString(R.string.mixed_berry_smoothie)));
        boxes.add(new QuantityBox((EditText) findViewById(R.id.etxtTropicalFruitSmoothie), getString(R.string.tropical_fruit_smoothie)));

        // iced drinks
        boxes.add(new QuantityBox((EditText) findViewById(R.id.etxtIcedCaramel), getString(R.string.iced_caramel)));
        boxes.add(new QuantityBox((EditText) findViewById(R.id.etxtIcedChocolate), getString(R.string.iced_chocolate)));
        boxes.add(new QuantityBox((EditText) findViewById(R.id.etxtIcedVanillaCappuccino), getString(R.string.iced_vanilla_cappuccino)));
        boxes.add(new QuantityBox((EditText) findViewById(R.id.etxtIcedChai), getString(R.string.iced_chai)));

        // chillers
        boxes.add(new QuantityBox((EditText) findViewById(R.id.etxtFrenchVanillaChiller), getString(R.string.french_vanilla_chiller_short)));
        boxes.add(new QuantityBox((EditText) findViewById(R.id.etxtCaramelChiller), getString(R.string.caramel_chiller_short)));

        // hot mixed drinks
        boxes.add(new QuantityBox((EditText) findViewById(R.id.etxtFrenchVanillaCappuccino), getString(R.string.french_vanilla_cappuccino)));
        boxes.add(new QuantityBox((EditText) findViewById(R.id.etxtCaramelMacchiatoCappuccino), getString(R.string.caramel_macchiato_cappuccino)));
        boxes.add(new QuantityBox((EditText) findViewById(R.id.etxtHotChocolate), getString(R.string.hot_chocolate)));
        boxes.add(new QuantityBox((EditText) findViewById(R.id.etxtChaiTea), getString(R.string.chai_tea)));
        boxes.add(new QuantityBox((EditText) findViewById(R.id.etxtAppleCider), getString(R.string.apple_cider)));

        // hot drinks
        boxes.add(new QuantityBox((EditText) findViewById(R.id.etxtRegularCoffee), getString(R.string.regular_coffee)));
        boxes.add(new QuantityBox((EditText) findViewById(R.id.etxtHazelnutCoffee), getString(R.string.hazelnut_coffee)));
        boxes.add(new QuantityBox((EditText) findViewById(R.id.etxtHighlanderGrogg), getString(R.string.highlander_grogg)));
        boxes.add(new QuantityBox((EditText) findViewById(R.id.etxtDecafCoffee), getString(R.string.decaf_coffee)));
        boxes.add(new QuantityBox((EditText) findViewById(R.id.etxtGreenTea), getString(R.string.green_tea)));

        // treats
        boxes.add(new QuantityBox((EditText) findViewById(R.id.etxtBrownie), getString(R.string.brownie)));
        boxes.add(new QuantityBox((EditText) findViewById(R.id.etxtBlueberryMuffin), getString(R.string.blueberry_muffin)));
        boxes.add(new QuantityBox((EditText) findViewById(R.id.etxtLemonPoppySeedMuffin), getString(R.string.lemon_poppy_seed_muffin)));
        boxes.add(new QuantityBox((EditText) findViewById(R.id.etxtChocolateChipMuffin), getString(R.string.chocolate_chip_muffin)));
        boxes.add(new QuantityBox((EditText) findViewById(R.id.etxtCinnamonStreuselMuffin), getString(R.string.cinnamon_streusel_muffin)));

        // fruit
        boxes.add(new QuantityBox((EditText) findViewById(R.id.etxtApple), getString(R.string.apple)));
        boxes.add(new QuantityBox((EditText) findViewById(R.id.etxtBanana), getString(R.string.banana)));
        boxes.add(new QuantityBox((EditText) findViewById(R.id.etxtOrange), getString(R.string.orange)));

        /*
        Commenting this out for now since the name should be immutable
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
        */

        /* commenting this out for now since the id is immutable
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
        */

        // adding text changed listener for every quantity box next to every single item. fingers
        // crossed, hope this works
        for (final QuantityBox i : boxes) {
            if (i.getQuantityBox() != null) {
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


    public void goToCheckout(View v) {

    }

    /**
     * <b>summary: </b>goes through all items in the cart, finds their prices and quantities,
     * then sums everything together. Stores the data in the global "price" variable
     */
    public void calculatePrice() {
        price = 0;

        for (OrderedItem i : cart) {
            price += prices.get(i.getItemName()) * i.getQuantity();
        }

        Formatter format = new Formatter();

        format.format("$%.2f", price);
        String priceDisplay = format.toString();
        total.setText(priceDisplay);

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

    /**
     * <b>summary: </b>handles the clicking of all the buttons in this activity. There are only two:
     * Order and Add Favorite
     *
     * @param v
     */
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnOrder:
                goToCheckout();
                break;
            case R.id.btnFavOrder:
                storeFavorite();
                break;
        }
    }

    /**
     * <b>summary: </b>is called when the order button on the activity is clicked. checks to make
     * sure that the name and id are entered. Also ensures that an item is ordered. If all these
     * conditions are met, the method starts a new Intent containing all the relevant information
     * on the page: name, id, and items ordered
     */
    private void goToCheckout() {
        boolean nameEntered = !mName.trim().equals("");
        boolean idEntered = !mId.equals("");
        boolean cartReady = cart.size() != 0;


        boolean readyForCheckout = nameEntered && idEntered && cartReady;

        if (readyForCheckout) {
            Intent intent = new Intent(getApplicationContext(), FinalActivity.class);

            intent.putExtra("name", mName);
            intent.putExtra("id", mId);
            intent.putExtra("email", mEmail);

            intent.putExtra("Total", price);

            for (int i = 0; i < cart.size(); ++i) {
                System.out.println(cart.get(i).getItemName());
            }

            intent.putParcelableArrayListExtra("cart", cart);

            startActivity(intent);
        } else {
            if (!(nameEntered || idEntered || cartReady))
                Toast.makeText(getApplicationContext(), "You need to enter your name and id and order an item!", Toast.LENGTH_SHORT).show();
            else {
                if (!(nameEntered || idEntered))
                    Toast.makeText(getApplicationContext(), "You need to enter your name and id!", Toast.LENGTH_SHORT).show();
                else if (!(nameEntered || cartReady))
                    Toast.makeText(getApplicationContext(), "You need to enter your name and order an item!", Toast.LENGTH_SHORT).show();
                else if (!(idEntered || cartReady))
                    Toast.makeText(getApplicationContext(), "You need to enter your id and order an item!", Toast.LENGTH_SHORT).show();
                else {
                    if (!nameEntered)
                        Toast.makeText(getApplicationContext(), "You need to enter your name!", Toast.LENGTH_SHORT).show();
                    else if (!idEntered)
                        Toast.makeText(getApplicationContext(), "You need to enter you id!", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getApplicationContext(), "You need to order an item!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void storeFavorite() {

    }
}