package com.example.oe.UI;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.oe.R;
import com.example.oe.*;

public class MainActivity extends AppCompatActivity {

public static int numAlert; // starts at 0

// following for email and password stuff
private EditText emailEditText;
private EditText passwordEditText;
private Button enterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        emailEditText = findViewById(R.id.editTextEmailAddress);
        passwordEditText = findViewById(R.id.editTextPassword);
        enterButton=findViewById(R.id.button);

        enterButton.setEnabled(false); // initialize to false, until email and password are entered

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                validateFields(); // function to enable the enter button
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
        };

        emailEditText.addTextChangedListener(textWatcher);
        passwordEditText.addTextChangedListener(textWatcher);

        enterButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) { // writing code to move to the next screen when the button is pressed
                Intent intent = new Intent(MainActivity.this, VacationList.class);
                intent.putExtra("test", "information sent");
                startActivity(intent);
            }
        });
    }

    private void validateFields() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        boolean isEmailValid = Patterns.EMAIL_ADDRESS.matcher(email).matches();
        boolean isPasswordNotEmpty = !password.isEmpty();

        enterButton.setEnabled(isEmailValid && isPasswordNotEmpty);
    }
}
