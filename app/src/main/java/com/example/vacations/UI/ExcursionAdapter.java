package com.example.vacations.UI;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vacations.R;
import com.example.vacations.entities.Excursion;

import java.util.List;

public class ExcursionAdapter extends RecyclerView.Adapter<ExcursionAdapter.ExcursionViewHolder> {

    private List<Excursion> mExcursions;
    private final Context context;
    private final LayoutInflater mInflater;
    class ExcursionViewHolder extends RecyclerView.ViewHolder {

        private final TextView excursionItemView;
        private final TextView excursionItemView2;


        private ExcursionViewHolder(View itemView) {
            super(itemView);
            excursionItemView = itemView.findViewById(R.id.textView5);
            excursionItemView2 = itemView.findViewById(R.id.textView6);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    int position = getAdapterPosition();
//                    int position=getAbsoluteAdapterPosition();
                    int position=getBindingAdapterPosition();
                    final Excursion current = mExcursions.get(position);
                    Intent intent = new Intent(context, ExcursionDetails.class);
                    intent.putExtra("id", current.getExcursionID());
                    intent.putExtra("name", current.getExcursionName());
                    intent.putExtra("price", current.getPrice());
                    intent.putExtra("vacaID", current.getVacationID());

//                  excursion not displaying date when selected. trying below line to see if it works. It doesn't
//                    intent.putExtra("date", current.getExcursionDate());
                    context.startActivity(intent);
                }
            });
        }
    }
    public ExcursionAdapter(Context context){
        mInflater=LayoutInflater.from(context);
        this.context=context;
    }

    @Override
    public ExcursionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView=mInflater.inflate(R.layout.excursion_list_item,parent,false);
        return new ExcursionViewHolder(itemView);
    }

//    @NonNull
//    @Override
//    public ExcursionAdapter.ExcursionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        return null;
//    }

    @Override
    public void onBindViewHolder(@NonNull ExcursionAdapter.ExcursionViewHolder holder, int position) {
        if(mExcursions!=null){
            Excursion current=mExcursions.get(position);
            String name=current.getExcursionName();
            int vacaID=current.getVacationID();
            holder.excursionItemView.setText(name);
            holder.excursionItemView2.setText(Integer.toString(vacaID));
        } else {
            holder.excursionItemView.setText("No excursion name");
            holder.excursionItemView.setText("No vacation ID");
        }
    }

    public void setExcursions(List<Excursion> excursions){
        mExcursions=excursions;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mExcursions!=null) return mExcursions.size();
        else return 0;
    }
}
