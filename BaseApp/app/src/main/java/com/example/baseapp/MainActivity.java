package com.example.baseapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1;

        button1 = (Button) findViewById(R.id.button1);

        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Toast.makeText(getApplicationContext(),"버튼을 눌렀어요.", Toast.LENGTH_SHORT).show();
            }
        });

        CheckBox check1;

        check1 = (CheckBox) findViewById(R.id.check1);

        check1.setOnClickListener( new View.OnClickListener(){
            public void onClick(View v){
                Toast.makeText(getApplicationContext(),"체크박스를 누르셨네~",Toast.LENGTH_SHORT).show();
            }
        });

    }
}