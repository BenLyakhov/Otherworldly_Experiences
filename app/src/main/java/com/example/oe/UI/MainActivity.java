package com.example.oe.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.oe.R;
import com.example.oe.*;

public class MainActivity extends AppCompatActivity {


public static int numAlert; // starts at 0


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
//        setContentView(R.layout.activity_main);
        Button button=findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) { // writing code to move to the next screen when the button is pressed
                Intent intent = new Intent(MainActivity.this, VacationList.class);
                intent.putExtra("test", "information sent");
                startActivity(intent);
            }
        });
    }
}
