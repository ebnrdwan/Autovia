package ebnrdwan.app.android.autovia.AccountSessions;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.Arrays;

import ebnrdwan.app.android.autovia.AccountSessions.Register.RegisterActivity;
import ebnrdwan.app.android.autovia.CustomViews.MyEditText;
import ebnrdwan.app.android.autovia.CustomViews.MyTextView;
import ebnrdwan.app.android.autovia.MainScreen;
import ebnrdwan.app.android.autovia.R;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {


    private static final int RC_SIGN_IN = 99;
    MyTextView login;
    ImageView facebookLogin;
    ImageView googleLogin;
    MyTextView forgetPass;
    MyTextView createAccount;
    MyEditText email;
    MyEditText pass;
    LoginButton loginButton;
    FacebookSdk facebookSdk;
    AppEventsLogger appEventsLogger;
    CallbackManager callbackManager;
    GoogleApiClient mGoogleApiClient;
    GoogleSignInOptions gso;
    private FirebaseAuth mAuth;
// ...


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        callbackManager = CallbackManager.Factory.create();
        mAuth = FirebaseAuth.getInstance();
        initializeViews();
        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("email");
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
//                .requestIdToken(getResources().getString(R.string.firebase_api_key))
                .requestEmail()
                .build();

// Build a GoogleApiClient with access to GoogleSignIn.API and the options above.
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case RC_SIGN_IN:

                GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
                if (result.isSuccess()) {
                    // Google Sign In was successful, authenticate with Firebase
                    GoogleSignInAccount account = result.getSignInAccount();
                    firebaseAuthWithGoogle(account);

                } else {
                    // Google Sign In failed, update UI appropriately

                }
        }


    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.signin1:
                startActivity(new Intent(LoginActivity.this, MainScreen.class));
                break;
            case R.id.facebook_icon:
                registFacebook();

                break;
            case R.id.googleicon:
                registGoogle();

                break;
            case R.id.forgot_Pass:

                break;
            case R.id.create_account:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
            case R.id.emailInput:

                break;
            case R.id.passwordinput:

                break;
        }
    }


    //initialize Views
    private void initializeViews() {

        login = (MyTextView) findViewById(R.id.signin1);
        login.setOnClickListener(this);
        facebookLogin = (ImageView) findViewById(R.id.facebook_icon);
        facebookLogin.setOnClickListener(this);
        googleLogin = (ImageView) findViewById(R.id.googleicon);
        googleLogin.setOnClickListener(this);
        forgetPass = (MyTextView) findViewById(R.id.forgot_Pass);
        forgetPass.setOnClickListener(this);
        createAccount = (MyTextView) findViewById(R.id.create_account);
        createAccount.setOnClickListener(this);
        email = (MyEditText) findViewById(R.id.emailInput);
        pass = (MyEditText) findViewById(R.id.passwordinput);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


    // Registeration By Facebook Method
    private void registFacebook() {

        String a[] = new String[]{"user_friends", "email"};
        LoginManager.getInstance().logInWithReadPermissions(LoginActivity.this, Arrays.asList(a));
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                Snackbar.make(findViewById(R.id.loginActivity), "Logined with : " + loginResult.getAccessToken().toString(), Snackbar.LENGTH_SHORT);
                startActivity(new Intent(LoginActivity.this, MainScreen.class));
            }

            @Override
            public void onCancel() {
                // App code

                Snackbar.make(findViewById(R.id.loginActivity), "Facebook Login Canceled ", Snackbar.LENGTH_SHORT);
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                Snackbar.make(findViewById(R.id.loginActivity), "Error Facebook ", Snackbar.LENGTH_SHORT);
            }
        });
        callbackManager.onActivityResult(33, 33, null);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
    }


    // Registeration By Google
    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void registGoogle() {

        signIn();

    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
//        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
//                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(LoginActivity.this, "wow success authentication mr." + user.getDisplayName(),
                                    Toast.LENGTH_SHORT).show();

                            startActivity(new Intent(LoginActivity.this, MainScreen.class));

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "signInWithCredential:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }
}

