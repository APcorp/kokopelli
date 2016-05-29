package comapcorp.github.kokopelliorderform;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

public class WelcomeScreen extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {

    private static final String TAG = "SignInActivity";
    private GoogleApiClient mGoogleApiClient;
    private static final int RC_SIGN_IN = 1;
    String mUsername;
    String mEmail;
    String mId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getSupportActionBar() != null)
            getSupportActionBar().hide();

        setContentView(R.layout.activity_welcome_screen);

        findViewById(R.id.btnSignIn).setOnClickListener(this);
        findViewById(R.id.btnSignOut).setOnClickListener(this);
        findViewById(R.id.btnNewOrder).setOnClickListener(this);
        findViewById(R.id.btnFavorites).setOnClickListener(this);

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();


        // Build a GoogleApiClient with access to the Google Sign-In API and the
        // options specified by gso.
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    /**
     * <b>summary: </b>handles the result generated when the SignIn API attempts to sign in with
     * the user-specified google account. If the sign-in is successful, then information is taken
     * from the account - name, id, and email. The buttons to order other things also shows up
     *
     * @param result
     */
    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();

            mUsername = acct.getDisplayName();
            mEmail = acct.getEmail();
            mId = acct.getId();

            int index = -1;

            for (int i = 0; i < mEmail.length() && index == -1; ++i) {
                if (mEmail.charAt(i) == '@')
                    index = i;
            }

            if (mEmail.substring(index + 1).equals("waunakeecsd.org") || mEmail.substring(index + 1).equals("waunakee.k12.wi.us")) {
                updateUI(true);
            } else {
                signOut();
                Toast.makeText(getApplicationContext(), "You need to use your school email! You can add " +
                        "additional accounts by clicking \"Add account\"", Toast.LENGTH_LONG).show();
            }
            // mStatusTextView.setText(getString(R.string.signed_in_fmt, acct.getDisplayName()));

        } else {
            // Signed out, show unauthenticated UI.
            updateUI(false);
        }
    }

    /**
     * <b>summary: </b>updates the UI of this Welcome screen to show the welcome message and the
     * Google Sign-In button or the list of options to start a new order and whatnot
     *
     * @param visible true if you want the options to show. False if you want the welcome message
     */
    private void updateUI(boolean visible) {
        if (visible) {
            showOptions();
        } else {
            hideOptions();
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
    }

    /**
     * <b>summary: </b>starts an Intent to go to the Main order screen. Sends the name, id, and email
     * of the user to the next screen so that the information can be displayed at the top of the
     * next Activity
     */
    private void goToMain() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);

        intent.putExtra("name", mUsername);
        intent.putExtra("id", mId);
        intent.putExtra("email", mEmail);

        startActivity(intent);
    }

    /**
     * <b>summary: </b>starts an Intent to send the user to the Favorites Activity where they can
     * select from a choice of their favorite orders
     */
    private void goToFavorites() {
        startActivity(new Intent(getApplicationContext(), FavoriteActivity.class));
    }

    /**
     * <b>summary: </b>handles the clicking of the Google sign-in button
     */
    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    /**
     * <b>summary: </b>handles the clicking of the sign-out button. Signs the user out from the
     * Google account
     */
    private void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        // ...
                    }
                });

        updateUI(false);
    }

    /**
     * <b>summary: </b>revokes the access of the phone to the account
     */
    private void revokeAccess() {
        Auth.GoogleSignInApi.revokeAccess(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        // ...
                    }
                });

        updateUI(false);
    }

    /**
     * <b>summary: </b>handles the clicking of all the buttons on this Activity. Switches between
     * the ids of the various buttons and calls the relevant method
     *
     * @param v
     */
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSignIn:
                signIn();
                break;
            case R.id.btnSignOut:
                signOut();
                break;
            case R.id.btnNewOrder:
                goToMain();
                break;
            case R.id.btnFavorites:
                goToFavorites();
                break;
        }
    }

    /**
     * <b>summary: </b>displays the LinearLayout containing the buttons with options for New Order,
     * Favorites, Sign Out, Remove Account
     */
    private void showOptions() {
        LinearLayout options = (LinearLayout) findViewById(R.id.linOptions);
        LinearLayout welcomeMessage = (LinearLayout) findViewById(R.id.linWelcomeStuff);
        TextView welcomeUser = (TextView) findViewById(R.id.txtWelcomeUser);

        String welcomeUserMessage = "Welcome, " + mUsername + "!";

        welcomeUser.setText(welcomeUserMessage);
        welcomeMessage.setVisibility(View.GONE);
        options.setVisibility(View.VISIBLE);


    }

    /**
     * <b>summary: </b>hides the LinearLayout containing the option button and shows the welcome
     * message along with the Google sign-in button
     */
    private void hideOptions() {
        LinearLayout options = (LinearLayout) findViewById(R.id.linOptions);
        LinearLayout welcomeMessage = (LinearLayout) findViewById(R.id.linWelcomeStuff);

        welcomeMessage.setVisibility(View.VISIBLE);
        options.setVisibility(View.GONE);
    }
}
