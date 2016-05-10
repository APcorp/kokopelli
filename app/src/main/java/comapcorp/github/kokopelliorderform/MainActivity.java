package comapcorp.github.kokopelliorderform;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    String name;
    String id;
    EditText etxtName;
    EditText etxtId;
    Button btnOrder;
    HashMap<String, Integer> cart;
    HashMap<String, Double> prices;
    TextView price;
    double totalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = "";
        id = "";
        btnOrder = (Button) findViewById(R.id.btnOrder);
        etxtName = (EditText) findViewById(R.id.etxtName);
        etxtId = (EditText) findViewById(R.id.etxtId);

        cart = new HashMap<String, Integer>();
        prices = new HashMap<String, Double>();

        price = (TextView) findViewById(R.id.txtPrice);
        prices.put("Brownie", 0.75);
        prices.put("Hot Chocolate", 1.25);

        etxtName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                name = etxtName.getText().toString();
                enableButton();
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
                id = etxtName.getText().toString();
                enableButton();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void enableButton() {
        boolean readyForCheckout = !name.equals("") && !id.equals("") && cart.size() != 0;

        btnOrder.setEnabled(readyForCheckout);
        btnOrder.setClickable(readyForCheckout);
    }

    public void checkBoxClicked(View v) {
        CheckBox cbx = (CheckBox) v;

        if (cbx.getId() == R.id.cbxBrownie) {
            CheckBox cbx2 = (CheckBox) findViewById(R.id.cbxBrownie2);

            if (cbx2 != null)
                cbx2.setChecked(cbx.isChecked());
        }

        if (cbx.getId() == R.id.cbxBrownie2) {
            CheckBox cbx2 = (CheckBox) findViewById(R.id.cbxBrownie);

            if (cbx2 != null)
                cbx2.setChecked(cbx.isChecked());
        }

        if (cbx.isChecked()) {
            cbx.setChecked(true);
            if (!cart.containsKey(cbx.getText().toString())) {
                cart.put(cbx.getText().toString(), new Integer(1));

            }
        } else {
            if (cart.containsKey(cbx.getText().toString()))
                cart.remove(cbx.getText().toString());
        }
        calculatePrice();
        enableButton();
    }

    public void goToCheckout(View v) {
        startActivity(new Intent(getApplicationContext(), FinalActivity.class));
    }

    public void calculatePrice() {
        totalPrice = 0;
        for (Map.Entry<String, Integer> i : cart.entrySet()) {
            totalPrice += prices.get(i.getKey()) * i.getValue();
        }

        Formatter format = new Formatter();

        format.format("$%.2f", totalPrice);
        String priceDisplay = format.toString();
        price.setText(priceDisplay);

    }
}