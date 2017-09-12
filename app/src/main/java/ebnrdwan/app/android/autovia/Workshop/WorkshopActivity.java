package ebnrdwan.app.android.autovia.Workshop;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.flipboard.bottomsheet.BottomSheetLayout;

import ebnrdwan.app.android.autovia.R;

public class WorkshopActivity extends AppCompatActivity implements View.OnClickListener {
    BottomSheetLayout bottomSheet;
    Button request_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workshop_);
        intializeViews();


    }

    private void intializeViews() {
        request_btn = (Button) findViewById(R.id.care_request_btn);
        request_btn.setOnClickListener(this);
        bottomSheet = (BottomSheetLayout) findViewById(R.id.bottomsheet);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.care_request_btn:
                bottomSheet.showWithSheetView(LayoutInflater.from(WorkshopActivity.this).inflate(R.layout.care_request_customview, bottomSheet, false));

        }
    }

}

