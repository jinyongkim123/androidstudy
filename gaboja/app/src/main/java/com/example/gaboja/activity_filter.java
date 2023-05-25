package com.example.gaboja;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class activity_filter extends AppCompatActivity {

    private Button resultButton;
    private TextView costSee;
    private String seekBarValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainfilter);
        getSupportActionBar().setTitle("nologagga");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        costSee = findViewById(R.id.cost_see);
        SeekBar costBar = findViewById(R.id.costbar);

        costBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                int cost = progress * 1000;
                seekBarValue = Integer.toString(cost);
                String costText = String.format("비용 0 ~ %d원", cost);
                costSee.setText(costText);
            }


            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        resultButton = findViewById(R.id.resultbutton);
        resultButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String localValue = getLocal(view);

                Intent intent = new Intent(activity_filter.this, window.class);
                intent.putExtra("age", getAge(view));
                intent.putExtra("fourseason", getFourSeason(view));
                intent.putExtra("cost", seekBarValue);
                intent.putExtra("type", getType(view));
                intent.putExtra("local", localValue);
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed(); // 뒤로가기 동작을 처리하는 메서드 호출
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public String getAge(View v) {
        RadioButton age_10_20 = findViewById(R.id.age_10_20);
        RadioButton age_30_40 = findViewById(R.id.age_30_40);
        RadioButton age_50_60 = findViewById(R.id.age_50_60);
        RadioButton age_70 = findViewById(R.id.age_70);
        String age = null;

        if (age_10_20.isChecked()) {
            age = age_10_20.getText().toString();
        } else if (age_30_40.isChecked()) {
            age = age_30_40.getText().toString();
        } else if (age_50_60.isChecked()) {
            age = age_50_60.getText().toString();
        } else if (age_70.isChecked()) {
            age = age_70.getText().toString();
        }
        return age;
    }

    public String getFourSeason(View v) {
        RadioButton spring = findViewById(R.id.spring);
        RadioButton summer = findViewById(R.id.summer);
        RadioButton autumn = findViewById(R.id.autumn);
        RadioButton winter = findViewById(R.id.winter);
        String fourSeason = null;

        if (spring.isChecked()) {
            fourSeason = spring.getText().toString();
        } else if (summer.isChecked()) {
            fourSeason = summer.getText().toString();
        } else if (autumn.isChecked()) {
            fourSeason = autumn.getText().toString();
        } else if (winter.isChecked()) {
            fourSeason = winter.getText().toString();
        }
        return fourSeason;
    }

    public String getType(View v) {
        RadioButton culture = findViewById(R.id.culture);
        RadioButton nature = findViewById(R.id.nature);
        RadioButton city = findViewById(R.id.city);
        RadioButton leisure = findViewById(R.id.leisure);
        String type = null;

        if (culture.isChecked()) {
            type = culture.getText().toString();
        } else if (nature.isChecked()) {
            type = nature.getText().toString();
        } else if (city.isChecked()) {
            type = city.getText().toString();
        } else if (leisure.isChecked()) {
            type = leisure.getText().toString();
        }
        return type;
    }

    public String getLocal(View v) {
        RadioButton ggd = findViewById(R.id.ggd);
        RadioButton gwd = findViewById(R.id.gwd);
        RadioButton ccnd = findViewById(R.id.ccnd);
        RadioButton ccbd = findViewById(R.id.ccbd);
        RadioButton jlnd = findViewById(R.id.jlnd);
        RadioButton jlbd = findViewById(R.id.jlbd);
        RadioButton gsnd = findViewById(R.id.gsnd);
        RadioButton gsbd = findViewById(R.id.gsbd);
        String local = null;

        if (ggd.isChecked()) {
            local = ggd.getText().toString();
        } else if (gwd.isChecked()) {
            local = gwd.getText().toString();
        } else if (ccnd.isChecked()) {
            local = ccnd.getText().toString();
        } else if (ccbd.isChecked()) {
            local = ccbd.getText().toString();
        } else if (jlnd.isChecked()) {
            local = jlnd.getText().toString();
        } else if (jlbd.isChecked()) {
            local = jlbd.getText().toString();
        } else if (gsnd.isChecked()) {
            local = gsnd.getText().toString();
        } else if (gsbd.isChecked()) {
            local = gsbd.getText().toString();
        }
        return local;
    }

    public void resetFilters(View view) {
        RadioGroup ageGroup = findViewById(R.id.age_group);
        RadioGroup seasonGroup = findViewById(R.id.season_group);
        SeekBar costBar = findViewById(R.id.costbar);
        RadioGroup typeGroup = findViewById(R.id.type_group);
        RadioGroup localGroup = findViewById(R.id.local_group);
        RadioGroup localGroup2 = findViewById(R.id.local_group2);

        ageGroup.clearCheck();
        seasonGroup.clearCheck();
        costBar.setProgress(0);
        typeGroup.clearCheck();
        localGroup.clearCheck();
        localGroup2.clearCheck();
    }
}

