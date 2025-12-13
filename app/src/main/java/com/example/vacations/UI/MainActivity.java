package com.example.vacations.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.vacations.R;

public class MainActivity extends AppCompatActivity {

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
                Intent intent = new Intent(MainActivity.this,ProductList.class);
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
