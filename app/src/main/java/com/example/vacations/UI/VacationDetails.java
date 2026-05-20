package com.example.vacations.UI;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class VacationDetails extends AppCompatActivity {

    String name;
    double price;
    int vacationID;
    static int notificationID;
    String hotel;
    String startVacaDate;
    String endVacaDate;
    EditText editName;
    EditText editPrice;
    EditText editHotel;
    TextView editStartVacaDate;
    TextView editEndVacaDate;
    Date startDate; // for date validation in vacationSave menu item
    Date endDate; // for date validation in vacationSave menu item
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
        startVacaDate = getIntent().getStringExtra("startvacadate"); //these variables need to be the same name as in the adapter
        editStartVacaDate.setText(startVacaDate); // fixed this. now the vacation dates show

        editEndVacaDate=findViewById(R.id.endvacadate);
//        endDate=findViewById(R.id.endvacadate);
        endVacaDate = getIntent().getStringExtra("endvacadate");
        editEndVacaDate.setText(endVacaDate); // fixed this. now the vacation dates show

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
                if (info.equals("")) info="05/01/26"; // setting default date to 01/01/26
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
                if (info.equals("")) info="05/01/26"; // setting default date to 01/01/26
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
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        editStartVacaDate.setText(sdf.format(myCalendarStart.getTime()));
    }

        public boolean onCreateOptionsMenu(Menu menu) { // I think inflate means add on top of the activity
            getMenuInflater().inflate(R.menu.menu_vacationdetails, menu);
            return true;
        }

    private void scheduleAlarm(AlarmManager alarmManager, long triggerTime, String message, int notificationId) {
        Intent intent = new Intent(VacationDetails.this, MyReceiver.class);
        intent.putExtra("key", message);
        intent.putExtra("notification_id", notificationId);
        PendingIntent sender = PendingIntent.getBroadcast(VacationDetails.this, notificationId, intent, PendingIntent.FLAG_IMMUTABLE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, triggerTime, sender);
    }

        public boolean onOptionsItemSelected(MenuItem item) {
            if (item.getItemId()==R.id.vacationsave){

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

//                Performing a date check, making sure the end date is after the start date.
                String myFormat = "MM/dd/yy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                try {
                    startDate = sdf.parse(editStartVacaDate.getText().toString()); //startDate is a Date variable. startVacaDate is the String variable
                } catch (ParseException e) {
                    Toast.makeText(this, "Start date needs to be in valid format MM/DD/YY", Toast.LENGTH_LONG).show();
                }

                try {
                    endDate = sdf.parse(editEndVacaDate.getText().toString()); //endDate is the Date variable. endVacaDate is the string variable.
                } catch (ParseException e) {
                    Toast.makeText(this, "End date needs to be in valid format MM/DD/YY", Toast.LENGTH_LONG).show();
                }

//              date validation here.
                if (!endDate.after(startDate)) {
                    Toast.makeText(this, "End date must be after start date.", Toast.LENGTH_LONG).show();
                    return false;
                }

                if (vacationID == 0) {
                    repository.insert(vacation);
                } else {
                    repository.update(vacation);
                }
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
                    if(excursion.getVacationID()==vacationID)++numExcursions; //increment = counter. If there are excursions
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
            return true;
            }

            if(item.getItemId()==R.id.vacationshare){

                List<Excursion> associatedExcursions = new ArrayList<>();
                for (Excursion e : repository.getmAllExcursions()) {
                    if(e.getVacationID() == vacationID) associatedExcursions.add(e);
                }

                String title = editName.getText().toString();
                String hotel = editHotel.getText().toString();
                String start = editStartVacaDate.getText().toString();
                String end = editEndVacaDate.getText().toString();

                StringBuilder associatedExcursionDetails = new StringBuilder();
                for (Excursion e : associatedExcursions) {
                    associatedExcursionDetails.append("Excursion Name: ").append(e.getExcursionName())
                            .append("\nExcursion Price: ").append(e.getPrice())
                            .append("\nExcursion Date: ").append(e.getExcursionDate()+"\n\n");

                }

                StringBuilder vacationDetailsShareText = new StringBuilder();
                vacationDetailsShareText.append("Vacation: ").append(title)
                        .append("\nHotel: ").append(hotel)
                        .append("\nStart: ").append(start)
                        .append("\nEnd: ").append(end)
                        .append("\n\nHere are the associated Excursions: \n").append(associatedExcursionDetails);

                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("text/plain");
                share.putExtra(Intent.EXTRA_TEXT, vacationDetailsShareText.toString());
                share.putExtra(Intent.EXTRA_SUBJECT, "Vacation: " + title);
                startActivity(Intent.createChooser(share, "Share vacation via"));



//                repository.getAssociatedExcursions(vacationID);


//                    if (excursions != null && !excursions.empty()) {
//                        vacationDetailsShareText.append("\n\nExcursions:");
//                        for (com.wgu.d308.entities.Excursion excursion : excursions) {
//                            vacationDetailsShareText.append("\n- ").append(excursion.getTitle())
//                                    .append(" (").append(excursion.getDate()).append(")");
//                        }
//                    }



                return true;
            }

            if(item.getItemId()==R.id.vacationnotify){
                String notifyStartDateString = editStartVacaDate.getText().toString();
                String notifyEndDateString = editEndVacaDate.getText().toString();
                String myFormat = "MM/dd/yy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                Date startDateDate = null;
                Date endDateDate = null;

                try {
                    startDateDate = sdf.parse(notifyStartDateString);
                    endDateDate = sdf.parse(notifyEndDateString);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                try {
                    AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

                    scheduleAlarm(alarmManager, startDateDate.getTime(), name + " is starting.",  notificationID++);
                    scheduleAlarm(alarmManager, endDateDate.getTime(), name + " is ending.",  notificationID++);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return true;
            }
            return super.onOptionsItemSelected(item);
        } // end public boolean onOptionsItemSelected

    public void saveVacation() { //this is to try to save the vacation dates correctly. What i've been doing in the menu function
//        is not working

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

