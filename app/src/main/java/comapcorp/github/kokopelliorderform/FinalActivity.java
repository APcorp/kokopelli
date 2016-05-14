package comapcorp.github.kokopelliorderform;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class FinalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        Intent intent = getIntent();

        TextView txtFinalName = (TextView) findViewById(R.id.finalName);
        TextView txtFinalId = (TextView) findViewById(R.id.finalId);

        txtFinalName.setText(intent.getStringExtra("Name"));
        txtFinalId.setText(intent.getStringExtra("ID"));

        ArrayList<OrderedItem> cart = intent.getParcelableArrayListExtra("cart");

        System.out.println(cart == null);

        LinearLayout parentLin = (LinearLayout) findViewById(R.id.linFinalActivity);

        System.out.println(cart.size());

        for (int i = 0; i < cart.size(); ++i) {
            System.out.println("Blah" + cart.get(i).getItemName());
        }

        for (int i = 0; i < cart.size(); ++i) {

            LinearLayout tempLayout = new LinearLayout(this);


            TextView itemName = new TextView(this);
            itemName.setText(cart.get(i).getItemName());
            itemName.setTextAppearance(this, android.R.style.TextAppearance_Large);
            itemName.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

            /*LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) itemName.getLayoutParams();

            lp.weight = 1;

            itemName.setLayoutParams(lp);
*/
            tempLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            tempLayout.addView(itemName);

            parentLin.addView(tempLayout);

        }

        parentLin.invalidate();

    }
}
