package ebnrdwan.app.android.autovia.RoadAssistance;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flipboard.bottomsheet.BottomSheetLayout;

import java.util.ArrayList;

import ebnrdwan.app.android.autovia.R;


/**
 * AIzaSyCcf59B6OshfwYMkdGlt02Pndj_10BmTKc
 */
public class RoadAssistAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    GenericViewHolder genericViewHolder;
    ArrayList<RoadServiceModel> arrayList;
    Context context;
    int selectedPos = 0;
    private int focusedItem = 0;

    @Override
    public void onAttachedToRecyclerView(final RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

    }

    public RoadAssistAdapter(Context context, ArrayList<RoadServiceModel> arrayList) {
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
             genericViewHolder = (GenericViewHolder) holder;
            Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/Font-Regular.otf");
            Typeface typefacee = Typeface.createFromAsset(context.getAssets(), "fonts/Font-Bold.otf");
            genericViewHolder.name.setText(arrayList.get(position).getNameService());

            genericViewHolder.name.setTypeface(typefacee);
//            genericViewHolder.picture.setImageResource(arrayList.get(position).getImage());
           genericViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   selectedPos = position;
                   notifyDataSetChanged();
               }
           });
            if (selectedPos==position){
                ((GenericViewHolder) holder).name.setTextColor(context.getResources().getColor(R.color.white));
                ((GenericViewHolder) holder).picture.setColorFilter(context.getResources().getColor(R.color.white));
                ((GenericViewHolder) holder).wholeItem.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
            }
            else {
                ((GenericViewHolder) holder).name.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                ((GenericViewHolder) holder).picture.setColorFilter(context.getResources().getColor(R.color.colorPrimary));
                ((GenericViewHolder) holder).wholeItem.setBackgroundColor(context.getResources().getColor(R.color.light_gray));
            }


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
        LinearLayout wholeItem;
        ImageView picture;
        BottomSheetLayout bottomSheet;
        int positionadapter = getAdapterPosition();


        public GenericViewHolder(final View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.servicename);
            picture = (ImageView) itemView.findViewById(R.id.serviceImage);
            wholeItem = (LinearLayout) itemView.findViewById(R.id.whole_service_item);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    notifyItemChanged(selectedPos);
                    selectedPos=getLayoutPosition();
                    notifyItemChanged(selectedPos);
//                    genericViewHolder.picture.setColorFilter(ContextCompat.getColor(context, R.color.colorPrimary), android.graphics.PorterDuff.Mode.MULTIPLY);
//                    genericViewHolder.name.setTextColor(context.getResources().getColor(R.color.white));

                }
            });
        }


        @Override
        public void onClick(View v) {

            switch (v.getId()){
                case R.id.whole_service_item:
//                    colorSelectedItem(genericViewHolder,positionadapter);
            }

        }

        private void colorSelectedItem(GenericViewHolder viewHolder, int position){
           if (viewHolder.getAdapterPosition()==position)
            viewHolder.picture.setColorFilter(ContextCompat.getColor(context, R.color.colorPrimary), android.graphics.PorterDuff.Mode.MULTIPLY);





        }
    }


}

