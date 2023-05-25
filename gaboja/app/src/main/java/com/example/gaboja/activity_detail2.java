package com.example.gaboja;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.gaboja.Fragment1;
import com.example.gaboja.Fragment2;
import com.example.gaboja.Fragment3;
import com.example.gaboja.R;
import com.google.android.material.tabs.TabLayout;

public class activity_detail2 extends AppCompatActivity {


    Fragment7 fragment7;
    Fragment8 fragment8;
    Fragment9 fragment9;

    ViewFlipper viewFlipper;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed(); // 뒤로가기 동작을 처리하는 메서드 호출
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailpage);
        getSupportActionBar().setTitle("nologagga");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView titleText = findViewById(R.id.titleText);

        titleText.setText("롯데월드 어드벤처");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);

        fragment7 = new Fragment7();
        fragment8 = new Fragment8();
        fragment9 = new Fragment9();

        int[] images = {
                R.drawable.lotte,
                R.drawable.lotte1,
                R.drawable.lotte2
        };

        viewFlipper = findViewById(R.id.image_slide);

        for (int image : images) {
            flipperImages(image);
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment7).commit();

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("설명"));
        tabs.addTab(tabs.newTab().setText("상세정보"));
        tabs.addTab(tabs.newTab().setText("리뷰"));

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                Log.d("MainActivity", "선택된 탭: " + position);

                Fragment selected = null;
                if (position == 0) {
                    selected = fragment7;
                } else if (position == 1) {
                    selected = fragment8;
                } else if (position == 2) {
                    selected = fragment9;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.container, selected).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    public void flipperImages(int image) {
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(image);

        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(4000);
        viewFlipper.setAutoStart(true);

        viewFlipper.setInAnimation(this, android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(this, android.R.anim.slide_out_right);
    }
}
