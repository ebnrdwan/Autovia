package ebnrdwan.app.android.autovia.AccountSessions.Register;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

import ebnrdwan.app.android.autovia.AccountSessions.LoginActivity;
import ebnrdwan.app.android.autovia.CustomViews.MyEditText;
import ebnrdwan.app.android.autovia.CustomViews.MyTextView;
import ebnrdwan.app.android.autovia.MainScreen;
import ebnrdwan.app.android.autovia.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    MyEditText fullName, password, phone, email;
    String name, pass, phoneInput, getemail;
    MyTextView register, logi;
    ApiInterface apiInterface;
    LottieAnimationView animationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initializeViews();

    }


    private void initializeViews() {
        register = (MyTextView) findViewById(R.id.regist);
        register.setOnClickListener(this);
        logi = (MyTextView) findViewById(R.id.sign_regist);
        logi.setOnClickListener(this);
        fullName = (MyEditText) findViewById(R.id.regist_name);
        email = (MyEditText) findViewById(R.id.emailInput);
        password = (MyEditText) findViewById(R.id.passwordinput);
        password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        phone = (MyEditText) findViewById(R.id.phone_edit);
        phone.setInputType(InputType.TYPE_CLASS_NUMBER);
        animationView = (LottieAnimationView) findViewById(R.id.animation_view);
        animationView.setAnimation("anims/loading_rainbow.json");

        apiInterface = ApiClient.getApiClientInstance().create(ApiInterface.class);
    }


    private void getInputs() {
        name = fullName.getText().toString();
        getemail = email.getText().toString();
        pass = password.getText().toString();
        phoneInput = phone.getText().toString();
        if (validatedInput(name, getemail, pass, phoneInput)) {
            initializeRetroCall(name, getemail, pass, phoneInput);
        }


    }

    // validate your user input
    private boolean validatedInput(String name, String emailText, String pass, String phone) {
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(emailText) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(phone)) {
            if (TextUtils.isEmpty(name)) {
                fullName.setError("Set your name ");
            }
            if (TextUtils.isEmpty(emailText)) {
                email.setError("set your email");
            }
            if (TextUtils.isEmpty(pass)) {
                password.setError("set your password");
            }
            if (TextUtils.isEmpty(phone)) {
                this.phone.setError("Phone is Required");
            }

            return false;
        } else {
            animationView.playAnimation();
            return true;
        }
    }


    private void initializeRetroCall(String name, String email, String pass, String phone) {


        Call<User_Model> call = apiInterface.RegisterUserCall(name, email, pass, phone);
        call.enqueue(new Callback<User_Model>() {
            @Override
            public void onResponse(Call<User_Model> call, Response<User_Model> response) {

                Toast.makeText(RegisterActivity.this, "your res : " + call.toString(), Toast.LENGTH_SHORT).show();
                animationView.pauseAnimation();
                startActivity(new Intent(RegisterActivity.this, MainScreen.class));
                animationView.setVisibility(View.GONE);


                Log.d("REGIST", call.toString());
            }

            @Override
            public void onFailure(Call<User_Model> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "failed to connect"+ t.toString(), Toast.LENGTH_SHORT).show();
                Log.d("REGIST", t.toString());
            }
        });


    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.regist:
                getInputs();
                break;
            case R.id.sign_regist:
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                break;


        }
    }
}
