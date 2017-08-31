package com.example.android.autovia.AccountSessions;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.android.autovia.CustomViews.MyEditText;
import com.example.android.autovia.CustomViews.MyTextView;
import com.example.android.autovia.MainScreen;
import com.example.android.autovia.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {


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


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        callbackManager = CallbackManager.Factory.create();
        initializeViews();
        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("email");

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
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

                break;
            case R.id.forgot_Pass:

                break;
            case R.id.create_account:

                break;
            case R.id.emailInput:

                break;
            case R.id.passwordinput:

                break;
        }
    }


    private void registFacebook() {

        String a[] = new String[]{"user_friends","email"};
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

}

