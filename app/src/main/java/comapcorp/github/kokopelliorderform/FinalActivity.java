package comapcorp.github.kokopelliorderform;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Formatter;

public class FinalActivity extends AppCompatActivity implements View.OnClickListener {

    private String mName;
    private String mId;
    private String mEmail;
    private String emailBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Review Your Order");
        setContentView(R.layout.activity_final);

        // gets the Intent from the previous activity
        Intent intent = getIntent();

        // initializes the OnClickListener for the order button
        findViewById(R.id.btnFinalOrder).setOnClickListener(this);

        // initializes the TextViews that contain the user's name, id, and email
        TextView txtFinalName = (TextView) findViewById(R.id.finalName);
        //TextView txtFinalId = (TextView) findViewById(R.id.finalId);
        TextView txtFinalEmail = (TextView) findViewById(R.id.finalEmail);

        mName = intent.getStringExtra("name");
        //mId = intent.getStringExtra("id");
        mEmail = intent.getStringExtra("email");
        // sets the relevant strings on the summary with the correct name and id and stuff
        String name = "Name: " + mName;
        //String id = "ID: " + mId;
        String email = mEmail;

        txtFinalName.setText(name);
        // txtFinalId.setText(id);
        txtFinalEmail.setText(email);

        emailBody = name + "\n";

        emailBody += "Email: " + email + "\n\n";

        ArrayList<OrderedItem> cart = intent.getParcelableArrayListExtra("cart");

        System.out.println(cart == null);

        LinearLayout parentLin = (LinearLayout) findViewById(R.id.linFinalActivity);

        int size = 0;

        if (cart != null) size = cart.size();

        // automatically generates a list of everything that the user had checked on the previous
        // page

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
            parentLin.addView(tempLayout);

            emailBody += cart.get(i).getItemName() + "\t" + cart.get(i).getQuantity() + "\n";

        }



        TextView total = (TextView) findViewById(R.id.txtFinalTotal);

        Formatter format = new Formatter();

        format.format("$%.2f", intent.getDoubleExtra("Total", 0.00));

        total.setText(format.toString());

        emailBody += "\n" + "Total: " + format.toString();
    }

    /**
     * <b>summary: </b>handles the clicking of all the buttons on this page. There is only one button
     * on this activity at this point - Order
     *
     * @param v
     */
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnFinalOrder:
                sendEmail();
                break;
        }
    }

    /**
     * <b>summary: </b>sends an automatically generated email to the relevant parties - a receipt for
     * the user and an order for Kokopelli
     */
    private void sendEmail() {
        Intent info = getIntent();
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"kokopelliorderform@gmail.com"});
        intent.putExtra(Intent.EXTRA_CC, new String[]{mEmail});
        intent.putExtra(Intent.EXTRA_SUBJECT, mName + "'s " + "Order");
        intent.putExtra(Intent.EXTRA_TEXT, emailBody);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
