package ebnrdwan.app.android.autovia.Requests.moreInfo;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import ebnrdwan.app.android.autovia.R;

public class MoreInfoRequest extends AppCompatActivity implements View.OnClickListener {

    Button rateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_info_request);
        rateButton = (Button) findViewById(R.id.rate_button);
        rateButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rate_button:
                Dialog dialog = new Dialog(MoreInfoRequest.this);
                dialog.setContentView(R.layout.road_dialog);
                dialog.setTitle("Request A Service");
                Button ok = (Button) dialog.findViewById(R.id.dialog_ok);
                ok.setOnClickListener(MoreInfoRequest.this);
                Button cancel = (Button) dialog.findViewById(R.id.dialog_cancel);
                cancel.setOnClickListener(MoreInfoRequest.this);


                dialog.show();
                break;
        }
    }
}