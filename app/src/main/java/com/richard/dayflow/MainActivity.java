package com.richard.dayflow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private static final int REFRESH_COMPLETE = 0X110;
    private SwipeRefreshLayout mSwipeLayout;
    public ImageView mImageView;
    public CalendarPage mCalendarPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSwipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_widget);
        mImageView = (ImageView) findViewById(R.id.image_view);
        mCalendarPage = new CalendarPage();

        Calendar calendar = Calendar.getInstance();
        int nowyear = calendar.get(Calendar.YEAR); //now year
        int nowmonth = calendar.get(Calendar.MONTH) + 1; //now month
        int nowday = calendar.get(Calendar.DAY_OF_MONTH); //now day

        mSwipeLayout.setColorSchemeResources(R.color.colorAccent,R.color.colorPrimary);
        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {//设置刷新监听器
            @Override
            public void onRefresh() {
            mCalendarPage.refreshImage(mImageView, MainActivity.this);
            mSwipeLayout.setRefreshing(false);
            }
        });

        mSwipeLayout.setRefreshing(true);
        //initialize calendar page
        mCalendarPage.setDate(nowyear, nowmonth, nowday);
        mCalendarPage.refreshImage(mImageView, this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
            mSwipeLayout.setRefreshing(false);
            }
        },2000); // 延时1秒
    }
}
