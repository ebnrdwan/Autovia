package ebnrdwan.app.android.autovia.CustomViews;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import ebnrdwan.app.android.autovia.R;



public class MyEditText extends android.support.v7.widget.AppCompatEditText {

    public MyEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyEditText(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), getResources().getString(R.string.lottoFont));
            setTypeface(tf);
        }
    }

}