package com.example.project4_4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    TextView text1, text2;
    Switch aSwitch;
    RadioGroup rGroup1;
    RadioButton VersionQ, VersionR, VersionS;
    Button btnFIN, btnRTN;
    ImageView imgAnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("안드로이드 사진 보기(20190822/김진용)");

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.cat);

        text1 = (TextView) findViewById(R.id.Text1);
        aSwitch = (Switch) findViewById(R.id.switch1);

        text2 = (TextView) findViewById(R.id.Text2);
        rGroup1 = (RadioGroup) findViewById(R.id.Rgroup1);
        VersionQ = (RadioButton) findViewById(R.id.versionQ);
        VersionR = (RadioButton) findViewById(R.id.versionR);
        VersionS = (RadioButton) findViewById(R.id.versionS);

        btnFIN = (Button) findViewById(R.id.Btnfin);
        btnRTN = (Button) findViewById(R.id.Btnrtn);
        imgAnd = (ImageView) findViewById(R.id.ImgAnd);

//미안 ♡
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            public  void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                if(aSwitch.isChecked() == true){
                    text2.setVisibility(View.VISIBLE);
                    rGroup1.setVisibility(View.VISIBLE);
                    btnFIN.setVisibility(View.VISIBLE);
                    btnRTN.setVisibility(View.VISIBLE);
                    imgAnd.setVisibility(View.VISIBLE);
                }else {
                    text2.setVisibility(View.INVISIBLE);
                    rGroup1.setVisibility(View.INVISIBLE);
                    btnFIN.setVisibility(View.INVISIBLE);
                    btnRTN.setVisibility(View.INVISIBLE);
                    imgAnd.setVisibility(View.INVISIBLE);
                }
            }
        });

        rGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            public void onCheckedChanged(RadioGroup radioGroup, int i){
                switch(rGroup1.getCheckedRadioButtonId()){
                    case R.id.versionQ:
                        imgAnd.setImageResource(R.drawable.q10);
                        break;
                    case R.id.versionR:
                        imgAnd.setImageResource(R.drawable.r11);
                        break;
                    case R.id.versionS:
                        imgAnd.setImageResource(R.drawable.s300);
                        break;
                    default:

                }
            }
        });

        btnFIN.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                finish();
            }
        });

        btnRTN.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                aSwitch.setChecked(false);
                VersionQ.setChecked(false);
                VersionR.setChecked(false);
                VersionS.setChecked(false);
                imgAnd.setImageResource(0);

            }
        });

    }
}