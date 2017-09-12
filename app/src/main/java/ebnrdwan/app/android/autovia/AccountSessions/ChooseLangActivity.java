package ebnrdwan.app.android.autovia.AccountSessions;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import ebnrdwan.app.android.autovia.CustomViews.MyTextView;
import ebnrdwan.app.android.autovia.R;

public class ChooseLangActivity extends AppCompatActivity implements View.OnClickListener {

    MyTextView chooseArabic;
    MyTextView chooseEnglish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_lang);
        initializeViews();
    }


    private void initializeViews(){
        chooseArabic = (MyTextView) findViewById(R.id.ar_button_lang);
        chooseEnglish = (MyTextView) findViewById(R.id.en_lang_btn);
        chooseEnglish.setOnClickListener(this);
        chooseArabic.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ar_button_lang:
                startActivity(new Intent(ChooseLangActivity.this,LoginActivity.class));
                break;
            case R.id.en_lang_btn:
                startActivity(new Intent(ChooseLangActivity.this,LoginActivity.class));
        }
    }
}
