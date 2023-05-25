package com.example.gaboja;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;

public class activity_search extends AppCompatActivity {
    EditText edit;
    TextView text;
    XmlPullParser xpp;

    String key = "3%2B4TC9QpQ6aNNzWjsQt7EDddH92%2FXiE0N0Q3bbWowl9ALmp%2FuPcCklwlSVGTWXC5UeO6ulxI0IuJ94izJJFY9w%3D%3D";
    String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchpage);
        getSupportActionBar().setTitle("nologagga");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edit = findViewById(R.id.edit);
        text = findViewById(R.id.result);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentdetail = new Intent(activity_search.this, activity_detail2.class);
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
        switch (v.getId()) {
            case R.id.button:
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
                break;
        }
    }

    String getXmlData() {
        StringBuffer buffer = new StringBuffer();
        String str = edit.getText().toString();
        String location = URLEncoder.encode(str);
        String query = "%EC%A0%84%EB%A0%A5%EB%A1%9C";

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
                            buffer.append("\n");//줄바꿈 문자 추가
                        }

                        break;

                    case XmlPullParser.TEXT:
                        break;

                    case XmlPullParser.END_TAG:
                        tag = xpp.getName();

                        if (tag.equals("item")) {
                            buffer.append("\n"); // 첫번째 검색결과 종료..줄바꿈
                        }
                        break;
                }

                eventType = xpp.next();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        buffer.append("파싱 끝\n");
        return buffer.toString(); // StringBuffer 문자열 객체 반환
    }
}

