package com.richard.dayflow;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class CalendarPage {

    public int year;
    public int month;
    public int day;

    public String formatYMD(int y, int m, int d) {
        String fyear = y + "";

        String fmonth = "";
        if (m < 10) {
            fmonth = "0" + m;
        } else {
            fmonth = m + "";
        }

        String fday = "";
        if (d < 10) {
            fday = "0" + d;
        } else {
            fday = d + "";
        }

        return fyear + "/" + fmonth + fday;
    }

    public void refreshImage(ImageView mImageView, Context context) {
        Glide.with(context).load("https://img.owspace.com/Public/uploads/Download/"
                + formatYMD(year, month, day) + ".jpg").into(mImageView);
    }

    public void setDate(int y, int m, int d) {
        year = y;
        month = m;
        day = d;
    }
}
