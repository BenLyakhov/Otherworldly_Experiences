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

import com.example.vacations.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class VacationList extends AppCompatActivity {

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

        System.out.println(getIntent().getStringExtra("test")); // this line makes the button work
        // the button which was added in MainActivity.java, lines 20 to 31, which is the button added in the activity_main.xml UI/simulator

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_product_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId()==R.id.mysample){
            Toast.makeText(VacationList.this,"put in sample data",Toast.LENGTH_LONG).show();
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