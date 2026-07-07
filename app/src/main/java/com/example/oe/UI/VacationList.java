package com.example.oe.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;

import com.example.oe.R;
import com.example.oe.database.Repository;
import com.example.oe.entities.Vacation;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class VacationList extends AppCompatActivity {
    private Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_vacation_list);

        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VacationList.this, VacationDetails.class);
                startActivity(intent);
            }
        });

        RecyclerView recyclerView=findViewById(R.id.recyclerView); //video 3, time stamp 52:04
        repository=new Repository(getApplication());
        List<Vacation> allVacations=repository.getmAllVacations();
        final VacationAdapter vacationAdapter=new VacationAdapter(this); //java class VacationAdapter linked here
        recyclerView.setAdapter(vacationAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        vacationAdapter.setVacations(allVacations); // this line is from VacationAdapter.java, line 85

//        adding search view stuff in the onCreate function
        SearchView searchView = findViewById(R.id.search_bar);
        searchView.setIconifiedByDefault(false);
        searchView.setQueryHint("Search Vacation Here");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                loadVacations(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                loadVacations(newText);
                return true;
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_vacation_list, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<Vacation> allVacations = repository.getmAllVacations();
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        final VacationAdapter vacationAdapter = new VacationAdapter(this);
        recyclerView.setAdapter(vacationAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        vacationAdapter.setVacations(allVacations);
    }

    private void loadVacations(String query) {
        List<Vacation> searchedVacations = repository.getVacationBySearch(query == null ? "" : query);
        RecyclerView recyclerView = findViewById(R.id.recyclerView); // recyclerview is the already existing list of vacations
        final VacationAdapter vacationAdapter = new VacationAdapter(this);
        recyclerView.setAdapter(vacationAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        vacationAdapter.setVacations(searchedVacations);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

//        blocking code for sample item code. Not necessary anymore. This is temporary fix

//        if(item.getItemId()==R.id.mysample) {
//            repository = new Repository(getApplication());
////            Commented out Toast when making the repository stuff in Repository.Java and VacationsDatabaseBuilder.java
////            Toast.makeText(VacationList.this,"put in sample data",Toast.LENGTH_LONG).show();
//
////            5 arg constructor for excursion testing
//                Vacation vacation = new Vacation(0, "Bahamas", 1000.0, "The Continental", "05/01/26", "05/06/26");
//                repository.insert(vacation);
//                vacation = new Vacation(0, "Bali", 2000.0, "The Royal", "04/20/26", "04/30/26");
//                repository.insert(vacation);
//                vacation = new Vacation(0, "Japan", 3000.0, "Osaka", "05/10/26", "05/17/26");
//                repository.insert(vacation);
//                Excursion excursion = new Excursion(0, "Scuba Diving", 250.0, 1, "05/02/26");
//                repository.insert(excursion);
//                excursion = new Excursion(0, "Wake Boarding", 150.0, 1, "05/04/26");
//                repository.insert(excursion);
//
//            return true;
//
//        }

        if(item.getItemId()==R.id.report) {
            Intent intent = new Intent(VacationList.this, Report.class);
            startActivity(intent);
            return true;
        }
        if(item.getItemId()==android.R.id.home){
            this.finish(); // this part makes the back arrow work to the previous page. can program it to go somewhere else
//          android.parentActivityName is what you link the back arrow to when on that page, in AndroidManifest

            return true;
        }
        return true;
    }

}