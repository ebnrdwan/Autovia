package ebnrdwan.app.android.autovia.Requests.CarCareRequest;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import ebnrdwan.app.android.autovia.CustomViews.CircleImageView;
import ebnrdwan.app.android.autovia.CustomViews.MyTextView;
import ebnrdwan.app.android.autovia.R;

/**
 * Created by Abdulrhman on 09/09/2017.
 */

public class Care_Request_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    ArrayList<Care_Request_Model> arrayList;
    Context context;


    public Care_Request_Adapter(Context context, ArrayList<Care_Request_Model> arrayList) {
        this.arrayList = arrayList;
        this.context = context;


    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_care_request, parent, false);
        return new GenericViewHolder(v);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof GenericViewHolder) {
            final GenericViewHolder genericViewHolder = (GenericViewHolder) holder;
            Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/Font-Regular.otf");
            Typeface typefacee = Typeface.createFromAsset(context.getAssets(), "fonts/Font-Bold.otf");

            Care_Request_Model model = arrayList.get(position);
            genericViewHolder.request_number.setText(model.getRequestNumber());
            genericViewHolder.request_type.setText(model.getRequestType());
            genericViewHolder.request_date.setText(model.getRequestDate());
            int Request_state_code = model.getRequestState();
            genericViewHolder.setRequestState(Request_state_code,model.getMessageInfo());

        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    class GenericViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView request_number;
        TextView request_type;
        TextView request_date;
        TextView request_info;
        MyTextView request_state;

        CircleImageView r_state_pending;
        CircleImageView r_state_accepted;
        LinearLayout whole_item;


        public GenericViewHolder(View itemView) {
            super(itemView);

          request_info = (TextView) itemView.findViewById(R.id.req_info);
            request_number = (TextView) itemView.findViewById(R.id.r_request_number);
            request_type = (TextView) itemView.findViewById(R.id.r_request_type);
            request_date = (TextView) itemView.findViewById(R.id.r_request_date);
            request_state = (MyTextView) itemView.findViewById(R.id.r_request_state_text);

            r_state_pending = (CircleImageView) itemView.findViewById(R.id.r_request_state_p);
            r_state_accepted = (CircleImageView) itemView.findViewById(R.id.r_request_state_a);
            whole_item = (LinearLayout) itemView.findViewById(R.id.whole_item);


        }


        @Override
        public void onClick(View v) {

            int id = v.getId();
            switch (id) {

                case R.id.whole_item:
                Toast.makeText(context,"you clicked on item",Toast.LENGTH_SHORT).show();
                    break;
            }
        }


        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        private void setRequestState(int i,@Nullable String message) {
            switch (i) {

                case 1:
                    r_state_pending.setImageResource(R.drawable.success_pend);
                    r_state_accepted.setImageResource(R.drawable.req_go_drawable);
                    request_state.setText("Request Received");


                    break;
                case 2:
                    r_state_accepted.setImageResource(R.drawable.success_go);
                    r_state_pending.setImageResource(R.drawable.success_pend);
                    request_info.setText(message);
                    request_info.setTextColor(context.getResources().getColor(R.color.green));
                    request_state.setText("Request Accepted");

                    break;
                case 3:
                    r_state_pending.setBackground(context.getResources().getDrawable(R.drawable.req_rec_drawable));
                    r_state_pending.setImageResource(R.drawable.wrong);
                    r_state_accepted.setImageResource(R.drawable.req_go_drawable);
                    request_info.setText(message);
                    request_info.setTextColor(context.getResources().getColor(R.color.red));
                    request_state.setText("Request Refused");
                    break;
            }
//            if (!message.equals(null))

        }
    }


}

