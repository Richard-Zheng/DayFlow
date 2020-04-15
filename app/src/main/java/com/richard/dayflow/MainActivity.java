package com.richard.dayflow;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements DatePickerFragment.DateSetReturnListener {

    private SwipeRefreshLayout mSwipeLayout;
    public ImageView mImageView;
    public CalendarPage mCalendarPage;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.pick_date:
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "datePicker");
            case R.id.date_today:
                setNowDate();
                mCalendarPage.refreshImage(mImageView, this);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void setNowDate() {

        //get date
        Calendar calendar = Calendar.getInstance();
        int nowyear = calendar.get(Calendar.YEAR);
        int nowmonth = calendar.get(Calendar.MONTH) + 1;
        int nowday = calendar.get(Calendar.DAY_OF_MONTH);

        CalendarPage.year = nowyear;
        CalendarPage.month = nowmonth;
        CalendarPage.day = nowday;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSwipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_widget);
        mImageView = (ImageView) findViewById(R.id.image_view);
        mCalendarPage = new CalendarPage();

        //when refreshing
        mSwipeLayout.setColorSchemeResources(R.color.colorAccent,R.color.colorPrimary);
        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {//设置刷新监听器
            @Override
            public void onRefresh() {
                mCalendarPage.refreshImage(mImageView, MainActivity.this);
                mSwipeLayout.setRefreshing(false);
            }
        });

        //initialize calendar page
        setNowDate();
        mSwipeLayout.setRefreshing(true);
        mCalendarPage.refreshImage(mImageView, this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
            mSwipeLayout.setRefreshing(false);
            }
        },2000); // 延时1秒
    }

    @Override
    public void onDateSetReturnListener(DialogFragment dialog, int year, int month, int dayOfMonth) {

        mSwipeLayout.setRefreshing(true);
        CalendarPage.year = year;
        CalendarPage.month = month + 1;
        CalendarPage.day = dayOfMonth;
        mCalendarPage.refreshImage(mImageView, this);
        mSwipeLayout.setRefreshing(false);
    }
}
