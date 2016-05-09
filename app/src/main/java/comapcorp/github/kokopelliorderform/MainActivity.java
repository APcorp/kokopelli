package comapcorp.github.kokopelliorderform;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    boolean nameEntered;
    boolean idEntered;
    boolean itemSelected;
    EditText etxtName;
    EditText etxtId;
    Button btnOrder;
    ArrayList<OrderedItem> cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameEntered = false;
        idEntered = false;
        itemSelected = false;
        btnOrder = (Button) findViewById(R.id.btnOrder);
        etxtName = (EditText) findViewById(R.id.etxtName);
        etxtId = (EditText) findViewById(R.id.etxtId);

        cart = new ArrayList<OrderedItem>();

        btnOrder.setEnabled(false);

        etxtName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                nameEntered = !etxtName.getText().toString().equals("");
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
                idEntered = !etxtName.getText().toString().equals("");
                enableButton();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void enableButton() {
        if (nameEntered && idEntered && itemSelected)
            btnOrder.setEnabled(true);
        else
            btnOrder.setEnabled(false);
    }
}
