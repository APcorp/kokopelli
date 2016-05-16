package comapcorp.github.kokopelliorderform;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Formatter;

public class FinalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Review Your Order");
        setContentView(R.layout.activity_final);


        Intent intent = getIntent();

        TextView txtFinalName = (TextView) findViewById(R.id.finalName);
        TextView txtFinalId = (TextView) findViewById(R.id.finalId);


        String nameString = "Name: " + intent.getStringExtra("Name");

        if (txtFinalName != null) txtFinalName.setText(nameString);

        String idString = "ID: " + intent.getStringExtra("ID");

        if (txtFinalId != null) txtFinalId.setText(idString);

        ArrayList<OrderedItem> cart = intent.getParcelableArrayListExtra("cart");

        System.out.println(cart == null);

        LinearLayout parentLin = (LinearLayout) findViewById(R.id.linFinalActivity);

        int size = 0;

        if (cart != null) size = cart.size();


        for (int i = 0; i < size; ++i) {

            LinearLayout tempLayout = new LinearLayout(this);


            TextView itemName = new TextView(this);
            itemName.setText(cart.get(i).getItemName());
            itemName.setTextAppearance(this, android.R.style.TextAppearance_Large);
            itemName.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT));

            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) itemName.getLayoutParams();

            lp.weight = 1;

            itemName.setLayoutParams(lp);

            tempLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            tempLayout.addView(itemName);

            TextView itemQuantity = new TextView(this);
            itemQuantity.setText(String.valueOf(cart.get(i).getQuantity()));
            itemQuantity.setTextAppearance(this, android.R.style.TextAppearance_Large);
            itemQuantity.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

            tempLayout.addView(itemQuantity);
            if (parentLin != null) parentLin.addView(tempLayout);

        }


        TextView total = (TextView) findViewById(R.id.txtFinalTotal);

        Formatter format = new Formatter();

        format.format("$%.2f", intent.getDoubleExtra("Total", 0.00));

        if (total != null) total.setText(format.toString());
    }
}
