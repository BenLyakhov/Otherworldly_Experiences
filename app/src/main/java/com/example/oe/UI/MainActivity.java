package com.example.oe.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.oe.R;

public class MainActivity extends AppCompatActivity {

//  from video 4, time stamp 52:06, creating numAlert for ExcursionDetails.java, line 176
//  this is to create a notification/calendar alert

public static int numAlert; // starts at 0

//  The assignment calls for alerts for both the vacations and excursions
//  You don't need numAlertVaca and numAlertExcur because the numAlert needs to be a different number, regardless of the
//    class. meaning, if numAlertVaca and numAlertExcur are both 1, it will cause problems.
//    setting numAlert and incrementing it by one regardless of which class uses it works just fine.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        Button button=findViewById(R.id.button); // the 'button' here is referring to activity_main.xml, when
        // i added the button to the phone UI/simulator, under the component tree on the left side of the window.
        // as soon as I added the button, this error went away.

        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) { // writing code to move to the next screen when the button is pressed
                Intent intent = new Intent(MainActivity.this, VacationList.class);
                intent.putExtra("test", "information sent");
                startActivity(intent);
            }
        });

// ------ the below code is missing from the panopto video part 1. ------------------------
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
    }
}
