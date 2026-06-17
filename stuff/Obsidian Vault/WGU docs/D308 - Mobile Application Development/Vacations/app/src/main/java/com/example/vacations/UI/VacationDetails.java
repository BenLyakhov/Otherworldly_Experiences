package com.example.oe.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oe.R;
import com.example.oe.database.Repository;
import com.example.oe.entities.Excursion;
import com.example.oe.entities.Vacation;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class VacationDetails extends AppCompatActivity {

    String name;
    double price;
    int vacationID;
    EditText editName;
    EditText editPrice;
    Repository repository;

//    When adding any new empty view activity to the UI folder, don't forget to include the label (name) in the AndroidManifest.xml file

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_vacation_details);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            FloatingActionButton fab = findViewById(R.id.floatingActionButton2);

            editName=findViewById(R.id.titleText);
            editPrice=findViewById(R.id.priceText);
            vacationID = getIntent().getIntExtra("id", -1);
            name = getIntent().getStringExtra("name");
            price = getIntent().getDoubleExtra("price",0.0);
            editName.setText(name);
            editPrice.setText(Double.toString(price));

            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(VacationDetails.this, ExcursionDetails.class);
                    startActivity(intent);

                }

            });
//            return insets;
        RecyclerView recyclerView=findViewById(R.id.excursionRecyclerView);
        repository = new Repository(getApplication());
        final ExcursionAdapter excursionAdapter = new ExcursionAdapter(this);
        recyclerView.setAdapter(excursionAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//      The following code is for filtering the vacations based on the excursions their IDs point to
        List<Excursion> filteredExcursions = new ArrayList<>();
        for (Excursion e : repository.getmAllExcursions()) {
            if (e.getVacationID() == vacationID) filteredExcursions.add(e);
        }
//        excursionAdapter.setExcursions(repository.getmAllExcursions()); //send all excursions to the recycler view.
        excursionAdapter.setExcursions(filteredExcursions);
//      using the filtered for loop above, you can also send the filtered parts to the recycler view, as opposed to all of them.
        }

        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.menu_vacationdetails, menu);
            return true;
        }

        public boolean onOptionsItemSelected(MenuItem item) {
            if(item.getItemId()==R.id.vacationsave){
                Vacation vacation;
                if (vacationID==-1){
                    if (repository.getmAllVacations().size() == 0) vacationID = 1;
                    else vacationID = repository.getmAllVacations().get(repository.getmAllVacations().size() - 1).getVacationID() +1;
//          The above else statement is saying if the repository (ie, list of all vacations) is not 0, get the list, count its size, -1
//          because coding is 0 based, and then add one to insert the new vacation at the end of the list
                    vacation = new Vacation(vacationID, editName.getText().toString(), Double.parseDouble((editPrice.getText().toString())));
                    repository.insert(vacation);
                    this.finish(); // goes back to the list screen
                }
                else { // this else statement is for if you are modifying a vacation (time stamp video 3, 1:29:40)
                    vacation = new Vacation(vacationID, editName.getText().toString(), Double.parseDouble((editPrice.getText().toString())));
                    repository.update(vacation);
                    this.finish();
                }
            }
            return true;
        }

//        The following is the code found in VacationList.java. per video 4, this is supposed to be in
//    VacationDetail.java. I'll leave it in both areas for now, see if it breaks anything.
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

    }

