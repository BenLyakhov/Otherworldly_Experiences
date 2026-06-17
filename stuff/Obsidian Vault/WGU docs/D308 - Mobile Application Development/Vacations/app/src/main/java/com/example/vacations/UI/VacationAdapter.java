package com.example.oe.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oe.R;
import com.example.oe.entities.Vacation;

import java.util.List;

public class VacationAdapter extends RecyclerView.Adapter<VacationAdapter.VacationViewHolder> {
//    mVacations is needed for the method on line 55, setVacations.
//    this method is needed for getAbsoluteAdapterPosition(), line 32
    private List<Vacation>  mVacations;
    private final Context context;
    private final LayoutInflater mInflater;
    public VacationAdapter(Context context){
        mInflater= LayoutInflater.from(context);
        this.context=context;
    }
    public class VacationViewHolder extends RecyclerView.ViewHolder {

        private final TextView vacationItemView;

        public VacationViewHolder(@NonNull View itemView) {
            super(itemView);
            vacationItemView=itemView.findViewById(R.id.textView4); //textView4 is the text view ID in vacation_list_item.xml
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//              Panopto video 3 has getAdapterPosition(). (time marker 40:20) This is depreciated in my current version of Android studio.
//              I will try instead to use what was suggested by the IDE, getAbsoluteAdapterPosition();
//              if this doesn't work, try the other suggestion, getBindingAdapterPosition();
//                    int position=getAbsoluteAdapterPosition();
                    int position=getBindingAdapterPosition();
                    final Vacation current=mVacations.get(position);
                    Intent intent=new Intent(context,VacationDetails.class);
                    intent.putExtra("id", current.getVacationID());
                    intent.putExtra("name", current.getVacationName());
                    intent.putExtra("price", current.getPrice());
                    context.startActivity(intent); // 1/22/26: I was missing this line. maybe it'll work now
                }
            });
        }
    }

    @NonNull
    @Override
    public VacationAdapter.VacationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView=mInflater.inflate(R.layout.vacation_list_item,parent,false);
        return new VacationViewHolder(itemView);

    }

    @Override
//    onBindViewHolder is what we display on the recyclerview. The recyclerview is an element in the android app that displays the list of items that you
//    want to show
    public void onBindViewHolder(@NonNull VacationAdapter.VacationViewHolder holder, int position) {
        if(mVacations!=null){
            Vacation current=mVacations.get(position);
            String name= current.getVacationName();
            holder.vacationItemView.setText(name);
        } else {
            holder.vacationItemView.setText("No Vacation name");
        }
    }

    @Override
    public int getItemCount() {
//        return 0; // default, dangerous
//        instead want to return the number of products (vacations) you have.
        if (mVacations!=null){
            return mVacations.size();
        } else return 0;
    }

//    need the following method for the getAbsoluteAdapterPosition(), line 27
    public void setVacations(List<Vacation> vacations){
        mVacations=vacations;
        notifyDataSetChanged();
    }

}
