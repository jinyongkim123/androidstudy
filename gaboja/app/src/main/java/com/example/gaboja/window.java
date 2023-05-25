package com.example.gaboja;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;

public class window extends AppCompatActivity {

    String local, age, fourseason, type, cost;
    TextView text, text1, text2, text3, text4,text5;
    XmlPullParser xpp;

    String key = "3%2B4TC9QpQ6aNNzWjsQt7EDddH92%2FXiE0N0Q3bbWowl9ALmp%2FuPcCklwlSVGTWXC5UeO6ulxI0IuJ94izJJFY9w%3D%3D";
    String data;
    private Button filterbutton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_window);
        getSupportActionBar().setTitle("nologagga");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        if (intent != null) {
            local = intent.getStringExtra("local");
            age = intent.getStringExtra("age");
            fourseason = intent.getStringExtra("fourseason");
            type = intent.getStringExtra("type");
            cost = intent.getStringExtra("cost");
        }

        text1 = findViewById(R.id.result1);
        text2 = findViewById(R.id.result2);
        text3 = findViewById(R.id.result3);
        text4 = findViewById(R.id.result4);
        text5 = findViewById(R.id.result5);
        text = findViewById(R.id.result);

        text1.setText(local);
        text2.setText(age);
        text3.setText(fourseason);
        text4.setText(type);
        text5.setText(cost);

        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentdetail = new Intent(window.this, activity_detail.class);
                startActivity(intentdetail);
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

    public void mOnClick(View v) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                data = getXmlData();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        text.setText(data);
                    }
                });
            }
        }).start();
    }

    String getXmlData() {
        StringBuffer buffer = new StringBuffer();
        String location = URLEncoder.encode(local);

        String queryUrl = "https://apis.data.go.kr/B551011/KorService1/searchKeyword1?serviceKey=3%2B4TC9QpQ6aNNzWjsQt7EDddH92%2FXiE0N0Q3bbWowl9ALmp%2FuPcCklwlSVGTWXC5UeO6ulxI0IuJ94izJJFY9w%3D%3D&numOfRows=10000&pageNo=1&MobileOS=AND&MobileApp=AppTest&_type=xml&listYN=Y&arrange=A&keyword=" + location + "&contentTypeId=12";

        try {
            URL url = new URL(queryUrl);
            InputStream is = url.openStream();

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(new InputStreamReader(is, "UTF-8"));

            String tag;
            xpp.next();
            int eventType = xpp.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        buffer.append("파싱 시작...\n\n");
                        break;
                    case XmlPullParser.START_TAG:
                        tag = xpp.getName();

                        if (tag.equals("item")) {
                            // 첫번째 검색결과
                        } else if (tag.equals("addr1")) {
                            buffer.append("주소 : ");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        } else if (tag.equals("title")) {
                            buffer.append("이름 :");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        } else if (tag.equals("firstimage")) {
                            buffer.append("사진 :");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        }

                        break;

                    case XmlPullParser.TEXT:
                        break;

                    case XmlPullParser.END_TAG:
                        tag = xpp.getName();

                        if (tag.equals("item")) {
                            buffer.append("\n");
                        }
                        break;
                }

                eventType = xpp.next();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        buffer.append("파싱 끝\n");
        return buffer.toString();
    }
}
