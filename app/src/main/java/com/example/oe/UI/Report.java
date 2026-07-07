package com.example.oe.UI;

import android.os.Bundle;
import android.util.Log;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oe.R;
import com.example.oe.database.Repository;
import com.example.oe.entities.Excursion;
import com.example.oe.entities.Vacation;

import java.util.ArrayList;
import java.util.List;

public class Report extends AppCompatActivity {
    private ReportAdapter reportAdapter;
    private Repository repository;
    private String vacationQuery;
    private String excursionQuery;
    private boolean isReady = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        repository = new Repository(getApplication());

        RecyclerView recyclerView = findViewById(R.id.report_recycler_view);
        reportAdapter = new ReportAdapter(this);
        recyclerView.setAdapter(reportAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


//        pulling in info to make the vacation search bar work
        SearchView vacationSearch = findViewById(R.id.report_search_vacation);
        vacationSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String query) {
                if(!isReady) return true;
                vacationQuery = query == null ? "" : query;
                loadReport();
                return true;
            }

            @Override
            public boolean onQueryTextSubmit(String newText) {
                if(!isReady) return true;
                vacationQuery = newText == null ? "" : newText;
                loadReport();
                return true;
            }
        });

        //        Same thing for excursion search bar
        SearchView excursionSearch = findViewById(R.id.report_search_excursion);
        excursionSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String query) {
                if(!isReady) return true;
                excursionQuery = query == null ? "" : query;
                loadReport();
                return true;
            }

            @Override
            public boolean onQueryTextSubmit(String newText) {
                if(!isReady) return true;
                excursionQuery = newText == null ? "" : newText;
                loadReport();
                return true;
            }
        });

        loadReport();
        isReady = true;

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }

        private void loadReport() {
            if (vacationQuery == null) vacationQuery = "";
            if (excursionQuery == null) excursionQuery = "";

            List<Vacation> vacations = repository.getVacationBySearch(vacationQuery);
            List<Excursion> excursions = repository.getExcursionBySearch(excursionQuery);

            Log.d("report debug", "Vacation count returned: " + (vacations != null ?vacations.size() : "null"));
            if (vacations != null) {
                for (Vacation vacation : vacations) {
                    Log.d("report debug", "Vacation Found: " + vacation.getVacationName());
                }
            }
            List<ReportRow> rows = new ArrayList<>();

            for (Vacation vacation : vacations) { // getting excursions for specific vacation, searching by excursion

                int count = 0;
                for (Excursion excursion : excursions) {
                    if(excursion.getVacationID() == vacation.getVacationID()) {
//                        associated.add(excursion);
                        count++;
                    }
                }
                if (!excursionQuery.isEmpty() && count == 0) continue;

                rows.add(new ReportRow(vacation, count));
            }

            Log.d("report debug", "Row count sent to adapter: " + rows.size());
            for(ReportRow row : rows) {
                Log.d("report debug", "Row: " + row.getVacation().getVacationName());
            }
            reportAdapter.setRows(rows);
    }
}
