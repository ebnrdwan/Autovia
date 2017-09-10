package ebnrdwan.app.android.autovia.Workshop;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.flipboard.bottomsheet.BottomSheetLayout;

import java.util.ArrayList;

import ebnrdwan.app.android.autovia.R;


/**
 * Created by Abdulrhman on 22/02/2017.
 */
public class WorkshopAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<WorkshopModel> arrayList;
    Context context;


    public WorkshopAdapter(Context context, ArrayList<WorkshopModel> arrayList) {
        this.arrayList = arrayList;
        this.context = context;



    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.service_item, parent, false);
        return new GenericViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof GenericViewHolder) {
            final GenericViewHolder genericViewHolder = (GenericViewHolder) holder;
            Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/Font-Regular.otf");
            Typeface typefacee = Typeface.createFromAsset(context.getAssets(), "fonts/Font-Bold.otf");


            genericViewHolder.name.setText(arrayList.get(position).getNameService());
            genericViewHolder.name.setTypeface(typefacee);
//            genericViewHolder.picture.setImageResource(arrayList.get(position).getImage());
        }
    }


    private boolean isPositionHeader(int position) {
        return position == 0;
    }
    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    class GenericViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name;
        TextView price;
        Button activate;
        ImageView picture;
        BottomSheetLayout bottomSheet;
        int positionadapter = getAdapterPosition();


        public GenericViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.servicename);

            picture = (ImageView) itemView.findViewById(R.id.serviceImage);
        }


        @Override
        public void onClick(View v) {

        }
    }


}

