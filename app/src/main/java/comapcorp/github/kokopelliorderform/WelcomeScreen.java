package comapcorp.github.kokopelliorderform;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class WelcomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_welcome_screen);
        setTitle("Welcome");


        //if (actionBar != null)
        //  actionBar.hide();


    }

    public void goToMain(View v) {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    public void goToFavorites(View v) {
        startActivity(new Intent(getApplicationContext(), FavoriteActivity.class));
    }
}
