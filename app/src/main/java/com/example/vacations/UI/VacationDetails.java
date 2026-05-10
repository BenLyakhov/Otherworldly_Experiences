package com.example.vacations.UI;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vacations.R;
import com.example.vacations.database.Repository;
import com.example.vacations.entities.Excursion;
import com.example.vacations.entities.Vacation;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class VacationDetails extends AppCompatActivity {

    String name;
    double price;
    int vacationID;
    String hotel;
    String startVacaDate;
    String endVacaDate;
    EditText editName;
    EditText editPrice;
    EditText editHotel;
    TextView editStartVacaDate;
    TextView editEndVacaDate;
    DatePickerDialog.OnDateSetListener startVacationDate;
    DatePickerDialog.OnDateSetListener endVacationDate;
    Repository repository;
    Vacation currentVacation;
    RecyclerView recyclerView;
    final Calendar myCalendarStart = Calendar.getInstance();
    final Calendar myCalendarEnd = Calendar.getInstance();
    int numExcursions;
//    When adding any new empty view activity to the UI folder, don't forget to include the label (name) in the AndroidManifest.xml file

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_vacation_details);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

        FloatingActionButton fab = findViewById(R.id.floatingActionButton2); // fab2 is the "add new excursion" button

        vacationID = getIntent().getIntExtra("id", -1);

        editName=findViewById(R.id.titleText);
        name = getIntent().getStringExtra("name");
        editName.setText(name);

        editPrice=findViewById(R.id.priceText);
        price = getIntent().getDoubleExtra("price",0.0);
        editPrice.setText(Double.toString(price));

        editStartVacaDate=findViewById(R.id.startvacadate);
        startVacaDate = getIntent().getStringExtra("startVacaDate");
        editStartVacaDate =findViewById(R.id.startvacadate);

        editEndVacaDate=findViewById(R.id.endvacadate);
        endVacaDate = getIntent().getStringExtra("endVacaDate");
        editEndVacaDate =findViewById(R.id.endvacadate);

        editHotel=findViewById(R.id.hotelName);
        hotel = getIntent().getStringExtra("hotelName");
        editHotel.setText(hotel);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VacationDetails.this, ExcursionDetails.class);
//                    need to pass vacationID here in the intent
                intent.putExtra("vacationID", vacationID);
                intent.putExtra("startVacationDate", startVacaDate);
                intent.putExtra("endVacationDate", endVacaDate);
                startActivity(intent);
            }

        });

        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);


        startVacationDate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendarStart.set(Calendar.YEAR, year);
                myCalendarStart.set(Calendar.MONTH, month);
                myCalendarStart.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabelStart();
            }
        };

        editStartVacaDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date date;
                String info = editStartVacaDate.getText().toString();
                if (info.equals("")) info="01/01/26"; // setting default date to 01/01/26
                try {
                    myCalendarStart.setTime(sdf.parse(info));//sdf = date formater
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                new DatePickerDialog(VacationDetails.this, startVacationDate,
                        myCalendarStart.get(Calendar.YEAR),
                        myCalendarStart.get(Calendar.MONTH),
                        myCalendarStart.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

        endVacationDate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendarEnd.set(Calendar.YEAR, year);
                myCalendarEnd.set(Calendar.MONTH, month);
                myCalendarEnd.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabelEnd();
            }
        };

        editEndVacaDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date date;
                String info = editEndVacaDate.getText().toString();
                if (info.equals("")) info="01/01/26"; // setting default date to 01/01/26
                try {
                    myCalendarStart.setTime(sdf.parse(info));//sdf = date formater
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                new DatePickerDialog(VacationDetails.this, endVacationDate,
                        myCalendarEnd.get(Calendar.YEAR),
                        myCalendarEnd.get(Calendar.MONTH),
                        myCalendarEnd.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

//            return insets;
        recyclerView =findViewById(R.id.excursionRecyclerView); //DONT PUT RecyclerView before recyclerView. It breaks the app
        repository = new Repository(getApplication());
        final ExcursionAdapter excursionAdapter = new ExcursionAdapter(this, startVacaDate, endVacaDate);
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

    private void updateLabelEnd() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        editEndVacaDate.setText(sdf.format(myCalendarEnd.getTime()));
    }

    private void updateLabelStart() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        editStartVacaDate.setText(sdf.format(myCalendarStart.getTime()));
    }

        public boolean onCreateOptionsMenu(Menu menu) { // I think inflate means add on top of the activity
            getMenuInflater().inflate(R.menu.menu_vacationdetails, menu);
            return true;
        }

        public boolean onOptionsItemSelected(MenuItem item) {
            if(item.getItemId()==R.id.vacationsave){
                Vacation vacation;
                if (vacationID==-1){
                    vacationID = 0;
                }

                vacation = new Vacation(
                        vacationID,
                        editName.getText().toString(),
                        Double.parseDouble((editPrice.getText().toString())),
                        editHotel.getText().toString(),
                        editStartVacaDate.getText().toString(),
                        editEndVacaDate.getText().toString()
                );

                if (vacationID == 0) {
                    repository.insert(vacation);
                } else {
                    repository.update(vacation);
                }
//                    if (repository.getmAllVacations().size() == 0) vacationID = 1;
//                    else vacationID = repository.getmAllVacations().get(repository.getmAllVacations().size() - 1).getVacationID() +1;
////          The above else statement is saying if the repository (ie, list of all vacations) is not 0, get the list, count its size, -1
////          because coding is 0 based, and then add one to insert the new vacation at the end of the list
//                    vacation = new Vacation(vacationID, editName.getText().toString(), Double.parseDouble((editPrice.getText().toString())));
//                    repository.insert(vacation);
//                    this.finish(); // goes back to the list screen
//                }
//                else { // this else statement is for if you are modifying a vacation (time stamp video 3, 1:29:40)
//                    vacation = new Vacation(vacationID, editName.getText().toString(), Double.parseDouble((editPrice.getText().toString())));
//                    repository.update(vacation);
//                    this.finish();
//                }
                this.finish();
            }
// video 4, 1:17:14, adding the delete vacation function
            if(item.getItemId()==R.id.vacationdelete){
                for (Vacation vaca:repository.getmAllVacations()){
                    if(vaca.getVacationID()==vacationID)currentVacation=vaca;
                }
                numExcursions = 0;
//                the following for loop checks to see if there are any excursions associated with the vacation before deleting it
                for(Excursion excursion:repository.getmAllExcursions()){
                    if(excursion.getExcursionID()==vacationID)++numExcursions; //increment = counter. If there are excursions
                }

//                after the above for loop, if there are still no excursions attached, delete the vacation.
                if(numExcursions==0) {
                    repository.delete(currentVacation);
                    Toast.makeText(VacationDetails.this, currentVacation.getVacationName() + " was deleted", Toast.LENGTH_LONG).show();
                    VacationDetails.this.finish(); // if delete was successful, go back to the main screen
                }
                else {
                    Toast.makeText(VacationDetails.this, "Can't delete a vacation with excursions", Toast.LENGTH_LONG).show();
                }
            }
            return true;
        }

//        The following is the code found in VacationList.java. per video 4, this is supposed to be in
//    VacationDetail.java. I'll leave it in both areas for now, see if it breaks anything.
    @Override
    protected void onResume() {
        super.onResume();

        repository = new Repository(getApplication());
        final ExcursionAdapter excursionAdapter = new ExcursionAdapter(this, startVacaDate, endVacaDate);
        recyclerView.setAdapter(excursionAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//      The following code is for filtering the vacations based on the excursions their IDs point to
        List<Excursion> filteredExcursions = new ArrayList<>();
        for (Excursion e : repository.getmAllExcursions()) {
            if (e.getVacationID() == vacationID) filteredExcursions.add(e);
        }
//        excursionAdapter.setExcursions(repository.getmAllExcursions()); //send all excursions to the recycler view.
        excursionAdapter.setExcursions(filteredExcursions);

//        List<Vacation> allVacations = repository.getmAllVacations();
//        RecyclerView recyclerView = findViewById(R.id.recyclerView);
//        final VacationAdapter vacationAdapter = new VacationAdapter(this);
//        recyclerView.setAdapter(vacationAdapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        vacationAdapter.setVacations(allVacations);
    }

    }

