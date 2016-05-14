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

        // initizlizes the name and ids to whatever is in the EditTexts for name and id. Usually
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
    }

    private void enableButton() {
        boolean readyForCheckout = !name.equals("") && !id.equals("") && cart.size() != 0;


        btnOrder.setEnabled(readyForCheckout);

    }

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
        boolean readyForCheckout = !name.equals("") && !id.equals("") && cart.size() != 0;


        if (readyForCheckout) {
            Intent intent = new Intent(getApplicationContext(), FinalActivity.class);

            intent.putExtra("Name", name);
            intent.putExtra("ID", id);

            intent.putExtra("Total", totalPrice);

            for (OrderedItem i : cart) {
                intent.putExtra(i.getItemName(), i.getQuantity());
            }

            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "You need to enter your name and id and order an item!", Toast.LENGTH_SHORT).show();

        }
    }

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

    private int find(OrderedItem foundItem) {
        int index = -1;

        for (int i = 0; i < cart.size() && index == -1; ++i) {
            if (foundItem.equals(cart.get(i))) {
                index = i;
            }
        }

        return index;
    }

    private void setQuantityVisibility(CheckBox cbx, int visibility, boolean enabled) {
        EditText etxtQuantity = (EditText) findViewById(cbx.getNextFocusRightId());

        if (etxtQuantity != null) {
            etxtQuantity.setVisibility(visibility);
            etxtQuantity.setEnabled(enabled);
        }
    }

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