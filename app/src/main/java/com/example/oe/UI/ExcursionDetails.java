package com.example.oe.UI;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.oe.R;
import com.example.oe.database.Repository;
import com.example.oe.entities.Excursion;
import com.example.oe.entities.Vacation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ExcursionDetails extends AppCompatActivity {

    String name;
    double price;
    int excursionID;
    int vacationID;
    EditText editName;
    EditText editPrice;
    EditText editNote;
    TextView editDate;
    Date excDateDate;
    Date vacaStartDate;
    Date vacaEndDate;
    String vacaStartDateString;
    String vacaEndDateString;
    String excursionDateString;
    Repository repository;
    Excursion currentExcursion;
    // Datepickerdialog from video 4, timestamp 23:20
    DatePickerDialog.OnDateSetListener startDate;
    final Calendar myCalendarStart = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_excursion_details);

//      The following is in video 4, time stamp 7:02 (also found in her flamingo live code
//      modifying parts and products with excursions and vacations

        repository=new Repository(getApplication());
// suggestion: findViews first, getintents next, then set the variables
        name = getIntent().getStringExtra("name");
        editName = findViewById(R.id.excursionName);
        editName.setText(name);

        price = getIntent().getDoubleExtra("price", 0.0);
        editPrice = findViewById(R.id.excursionPrice);
        editPrice.setText(Double.toString(price));

        excursionID = getIntent().getIntExtra("id", -1); //setting it to 0 here improves performance
        vacationID = getIntent().getIntExtra("vacationID", -1);
//        vacationID is correct when creating a new vacation (increments appropriately)
//        new excursion for an existing vacation default to vacationID = -1
//        because of this, they do not get saved to that vacation. WHY?????

        vacaStartDateString = getIntent().getStringExtra("startVacationDate");
        vacaEndDateString = getIntent().getStringExtra("endVacationDate");

        editNote = findViewById(R.id.note);

        excursionDateString = getIntent().getStringExtra("excursionDate");
        editDate = findViewById(R.id.excursiondate); // ID in xml file
        editDate.setText(excursionDateString); //variable name here

//  4/3/26 adding datepicker dialogue, video 4, starting 24:27

        startDate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendarStart.set(Calendar.YEAR, year);
                myCalendarStart.set(Calendar.MONTH, month);
                myCalendarStart.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabelStart();
            }
        };

//   video 4, timestamp 1:31:49, spinner
        Spinner spinner=findViewById(R.id.spinner);
        ArrayList<Vacation> vacationArrayList=new ArrayList<>();
        vacationArrayList.addAll(repository.getmAllVacations());
        ArrayAdapter<Vacation>vacationAdapter=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,vacationArrayList);
        spinner.setAdapter(vacationAdapter);

//        Moving these 2 lines in the onDateSet method:
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

//        from video 4, timestamp starting 24:15:
//        this is the code that shows the calendar when you click on the date in the date field.
//        try putting this on the vacationDetails.java.
//        if this is too difficult to save as a date, you can simply convert to EditText field (user types in date manually)
        editDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date date;
                String info = editDate.getText().toString();
                if (info.equals("")) info="05/01/26";
                try {
                    myCalendarStart.setTime(sdf.parse(info));//sdf = date formater
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                new DatePickerDialog(ExcursionDetails.this, startDate,
                        myCalendarStart.get(Calendar.YEAR),
                        myCalendarStart.get(Calendar.MONTH),
                        myCalendarStart.get(Calendar.DAY_OF_MONTH)).show();

            }
        });
    }
//    The following is stuff I added, copying her live flamingo code, because she doesnt go over this stuff in her videos
//    This stuff is basically copying what she went over in VacationDetails.java, but modifying them
//    for ExcursionDetails here

    private void updateLabelStart() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        editDate.setText(sdf.format(myCalendarStart.getTime()));
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_excursiondetails, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }



//        Below code is testing saving the excursion date, per tasks
        if (item.getItemId() == R.id.excursionsave) {

            Excursion excursion;


//            adding in validation for excursion dates, so that it is between the vacation dates
//            if(startDateDate)
//lines 160 and 161 not needed, repository/database does this automatically. Let database decide ID
            if (excursionID == -1) {
                excursionID = 0;
            }
            excursion = new Excursion(
                    excursionID,
                    editName.getText().toString(),
                    Double.parseDouble(editPrice.getText().toString()),
                    vacationID,
                    editDate.getText().toString());

//            adding date validation here, making sure the excursion date is between the vacation start and end dates.

            String myFormat = "MM/dd/yy";
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

            try {
                excDateDate = sdf.parse(editDate.getText().toString());
                vacaStartDate = sdf.parse(vacaStartDateString);
                vacaEndDate = sdf.parse(vacaEndDateString);
            } catch (ParseException e) {
                Toast.makeText(this, "Start and End dates needs to be in valid format MM/DD/YY", Toast.LENGTH_LONG).show();
            }


//            Date validation here
            if(excDateDate.before(vacaStartDate) || excDateDate.after(vacaEndDate)){
                Toast.makeText(this, "Excursion Date must be between " + vacaStartDateString + " and " + vacaEndDateString, Toast.LENGTH_LONG).show();
                return false;
            }

            if (excursionID == 0) {
                repository.insert(excursion);
            } else {
                repository.update(excursion);
            }

            this.finish();
//            return true;
        }

        //        adding option to delete excursions
        if(item.getItemId()==R.id.excursiondelete){
            for (Excursion excur:repository.getmAllExcursions()){
                if(excur.getExcursionID()== excursionID)currentExcursion=excur;
            }
            repository.delete(currentExcursion);
            Toast.makeText(ExcursionDetails.this, currentExcursion.getExcursionName() + " was deleted", Toast.LENGTH_LONG).show();
            ExcursionDetails.this.finish(); // if delete was successful, go back to the main screen
        }

        if (item.getItemId() == R.id.excursionshare) { //sharing excursion details is not necessary for WGU task submission.
//            This part is taken care of in Task B3F, sharing all vacation details.
            Intent sentIntent= new Intent();
            sentIntent.setAction(Intent.ACTION_SEND);
            sentIntent.putExtra(Intent.EXTRA_TEXT, editName.getText().toString()+ "EXTRA_TEXT");
            sentIntent.putExtra(Intent.EXTRA_TITLE, editDate.getText().toString()+ "EXTRA_TITLE");
            sentIntent.setType("text/plain");
            Intent shareIntent=Intent.createChooser(sentIntent,null);
            startActivity(shareIntent);
            return true;
        }
        if (item.getItemId() == R.id.excursionnotify) {
            String dateFromScreen = editDate.getText().toString();
            String myFormat = "MM/dd/yy";
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
            Date myDate = null;
            try {
                myDate = sdf.parse(dateFromScreen);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Long trigger = myDate.getTime();
            Intent intent = new Intent(ExcursionDetails.this, MyReceiver.class); //need to create new java class in UI folder with this name MyReceiver
            intent.putExtra("key", getIntent().getStringExtra("name") + " is starting.");
            PendingIntent sender=PendingIntent.getBroadcast(ExcursionDetails.this,++MainActivity.numAlert, intent,PendingIntent.FLAG_IMMUTABLE);

//          The following code is to basically wake the phone/app up to a set date and time in the future (trigger, sender)
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP, trigger,sender);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}