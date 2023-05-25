package com.example.gaboja;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {


    private FrameLayout frameLayout;
    ViewFlipper v_fllipper;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = findViewById(R.id.sandfestival);

        text.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intentdetail1 = new Intent(MainActivity.this, activity_detail1.class);
                startActivity(intentdetail1);
            }
        });


        int images[] = {
                R.drawable.busan ,  //첫번째 파일 이름
                R.drawable.legoland, //두번째 파일 이름
                R.drawable.alpaka,//세번째 파일 이름
                R.drawable.rose,
                R.drawable.hanriver
        };

        v_fllipper = findViewById(R.id.image_slide);

        for(int image : images) {
            fllipperImages(image);
        }
    }

    public void fllipperImages(int image) {
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(image);

        v_fllipper.addView(imageView);      // 이미지 추가
        v_fllipper.setFlipInterval(4000);       // 자동 이미지 슬라이드 딜레이시간(1000 당 1초)
        v_fllipper.setAutoStart(true);          // 자동 시작 유무 설정

        // animation
        v_fllipper.setInAnimation(this,android.R.anim.slide_in_left);
        v_fllipper.setOutAnimation(this,android.R.anim.slide_out_right);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int curld = item.getItemId();
        switch (curld) {
            case R.id.menu_filter:
               Intent intent = new Intent(getApplicationContext(), activity_filter.class);
                startActivity(intent);
                break;

            case R.id.menu_search:
                Intent intent1 = new Intent(getApplicationContext(), activity_search.class);
                startActivity(intent1);
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}