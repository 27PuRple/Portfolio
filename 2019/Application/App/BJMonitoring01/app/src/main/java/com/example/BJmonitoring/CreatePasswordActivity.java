package com.example.BJmonitoring;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CreatePasswordActivity extends AppCompatActivity {

    EditText editText1, editText2;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_password);

        editText1 = (EditText)findViewById(R.id.editText1);
        editText2 = (EditText)findViewById(R.id.editText2);
        button = (Button) findViewById(R.id.Previousness);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text1 = editText1.getText().toString();
                String text2 = editText2.getText().toString();

                if(text1.equals("") || text2.equals("")) {
                    //there s no password
                    Toast.makeText(CreatePasswordActivity.this, "No password entered!", Toast.LENGTH_SHORT).show();
                } else {
                    if(text1.equals(text2)) {
                        //save the password
                        SharedPreferences settings = getSharedPreferences("PREFS", 0);
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putString("password", text1);
                        editor.apply();

                        //enter the app
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        //there is no match on the passwords
                        Toast.makeText(CreatePasswordActivity.this, "Passwords doesn't match!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
