package com.richard.dayflow;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    MyImageView myImageView;

    public void refreshImage() {
        Calendar calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        String fyear = year + "";
        String fmonth = "";
        if (month < 10) {
            fmonth = "0" + month;
        } else {
            fmonth = month + "";
        }
        String fday = "";
        if (day < 10) {
            fday = "0" + day;
        } else {
            fday = day + "";
        }

        myImageView.setImageURL("https://img.owspace.com/Public/uploads/Download/" +
                fyear + "/" + fmonth + fday + ".jpg");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myImageView = (MyImageView) findViewById(R.id.image_view);
        //myImageView.setImageURL("https://img.owspace.com/Public/uploads/Download/2020/0303.jpg");
        refreshImage();
    }
}
