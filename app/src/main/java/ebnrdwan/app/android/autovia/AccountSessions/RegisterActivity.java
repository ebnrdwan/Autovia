package ebnrdwan.app.android.autovia.AccountSessions;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import ebnrdwan.app.android.autovia.CustomViews.MyTextView;
import ebnrdwan.app.android.autovia.R;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        MyTextView logi = (MyTextView) findViewById(R.id.sign_regist);
        logi.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.sign_regist:
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
        }
    }
}
