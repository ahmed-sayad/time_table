package com.example.timetable;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class home extends AppCompatActivity {

    Button logoutbutton;
    Button oldtaskbutton;
    TextInputEditText newtaskTXT;
    TextInputEditText timeTXT;
    Button addbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        logoutbutton = findViewById(R.id.logoutbutton);
        oldtaskbutton = findViewById(R.id.oldtaskbutton);
        newtaskTXT = findViewById(R.id.newtaskTXT);
        timeTXT = findViewById(R.id.timeTXT);
        addbutton = findViewById(R.id.addbutton);



        logoutbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });

        oldtaskbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), showoldtasks.class);
                startActivity(intent);
                finish();
            }
        });

        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String newtask, time;
                newtask = String.valueOf(newtaskTXT.getText());
                time = String.valueOf(timeTXT.getText());

                if(!newtask.equals("") && !time.equals("")) {
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            String[] field = new String[2];
                            field[0] = "newtask";
                            field[1] = "time";
                            String[] data = new String[2];
                            data[0] = newtask;
                            data[1] = time;
                            PutData putData = new PutData("http://192.168.1.3/timetablePHP/newtask.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();
                                    if(result.equals("Task has been Added Successfully")){
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), showoldtasks.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else {
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(getApplicationContext(), "Task has not been added yet!!", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}