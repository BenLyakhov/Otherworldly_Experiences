package com.example.oe.UI;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oe.R;
import com.example.oe.entities.Excursion;
import com.example.oe.entities.Vacation;

import java.util.List;

public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.ReportViewHolder> {

    private List<ReportRow> mRows;
    private final LayoutInflater mInflater;

    public ReportAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    public void setRows(List<ReportRow> rows) {
        mRows = rows;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ReportAdapter.ReportViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.report_list_item, parent, false);
        return new ReportViewHolder(itemView);
    } // this tells Studio what to look for, ie, what to import into the report view

    @Override
    public void onBindViewHolder(@NonNull ReportAdapter.ReportViewHolder holder, int position) {
        if(mRows != null) {
            ReportRow row = mRows.get(position);
            Vacation vacation = row.getVacation();
//            Excursion excursion = row.getExcursion();

            holder.vacationName.setText(vacation.getVacationName());
            holder.vacationHotel.setText(vacation.getHotelName());
            holder.vacationStart.setText(vacation.getStartVacaDate());
            holder.vacationEnd.setText(vacation.getEndVacaDate());
            holder.excursionCount.setText(String.valueOf(row.getExcursionCount())); //Int to string
        }
    } // this populates the report_list_item. The guts change depending on what needs to be populated. In this case, its a row of data


    @Override
    public int getItemCount() {
//        return 0;
        return mRows != null ? mRows.size() : 0;
    }

    public class ReportViewHolder extends RecyclerView.ViewHolder {
        TextView vacationName, vacationHotel, vacationStart, vacationEnd, excursionCount;

        public ReportViewHolder(@NonNull View itemView) {
            super(itemView);
            vacationName = itemView.findViewById(R.id.report_vacation_name);
            vacationHotel = itemView.findViewById(R.id.report_vacation_hotel);
            vacationStart = itemView.findViewById(R.id.report_vacation_start);
            vacationEnd = itemView.findViewById(R.id.report_vacation_end);
            excursionCount = itemView.findViewById(R.id.report_excursion_count);
        }
    }

}
