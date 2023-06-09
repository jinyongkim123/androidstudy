package com.example.practice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private fragment_a fragmentA;
    private fragment_b fragmentB;
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();

        fragmentA = new fragment_a();
        fragmentB = new fragment_b();

        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout,fragmentA).commitAllowingStateLoss();
    }

    public void clickHandler(View view){
        transaction = fragmentManager.beginTransaction();

        switch(view.getId()){
            case R.id.btn_fragmentA:
                transaction.replace(R.id.frameLayout,fragmentA).commitAllowingStateLoss();
                break;
            case R.id.btn_fragmentB:
                transaction.replace(R.id.frameLayout, fragmentB).commitAllowingStateLoss();
                break;
        }
    }
}

