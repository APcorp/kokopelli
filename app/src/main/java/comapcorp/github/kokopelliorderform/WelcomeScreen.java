package comapcorp.github.kokopelliorderform;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        setTitle("Welcome");

    }

    public void goToMain(View v) {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    public void goToFavorites(View v) {
        startActivity(new Intent(getApplicationContext(), FavoriteActivity.class));
    }
}
