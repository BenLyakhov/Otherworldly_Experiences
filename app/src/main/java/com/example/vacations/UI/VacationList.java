package com.example.vacations.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Delete;

import com.example.vacations.R;
import com.example.vacations.database.Repository;
import com.example.vacations.entities.Excursion;
import com.example.vacations.entities.Vacation;
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
                // Product list is where you are at, ProductDetails is where you want to go
//                intent.putExtra("test", "information sent");
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

//        System.out.println(getIntent().getStringExtra("test")); // this line makes the button work
//        Commented out the above system.out line when I put in the recycler view (see video 3, at 51:56)
        // the button which was added in MainActivity.java, lines 20 to 31, which is the button added in the activity_main.xml UI/simulator

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId()==R.id.mysample) {
            repository = new Repository(getApplication());
//            Commented out Toast when making the repository stuff in Repository.Java and VacationsDatabaseBuilder.java
//            Toast.makeText(VacationList.this,"put in sample data",Toast.LENGTH_LONG).show();

//          inputting 4 things into the app to see if they work
//          once you click on the three dots in the vacation list page, the database will pop up in android studio


//              Below is 4 arg contructor
//                Vacation vacation = new Vacation(0, "Bahamas", 100.0);
//                repository.insert(vacation);
//                vacation = new Vacation(0, "Bali", 200.0);
//                repository.insert(vacation);
//                vacation = new Vacation(0, "Japan", 200.0); // initializing one vacation without excursions attached
//                Excursion excursion = new Excursion(0, "Scuba Diving", 250.0, 1, );
//                repository.insert(excursion);
//                excursion = new Excursion(0, "wake boarding", 150.0, 1);
//                repository.insert(excursion);

//            5 arg constructor for excursion testing
                Vacation vacation = new Vacation(0, "Bahamas", 100.0);
                repository.insert(vacation);
                vacation = new Vacation(0, "Bali", 200.0);
                repository.insert(vacation);
                vacation = new Vacation(0, "Japan", 200.0); // initializing one vacation without excursions attached
                Excursion excursion = new Excursion(0, "Scuba Diving", 250.0, 1, "01/01/00");
                repository.insert(excursion);
                excursion = new Excursion(0, "wake boarding", 150.0, 1, "01/01/00");
                repository.insert(excursion);


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