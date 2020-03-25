package com.richard.dayflow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.os.Handler;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private static final int REFRESH_COMPLETE = 0X110;
    private SwipeRefreshLayout mSwipeLayout;
    public MyImageView myImageView;

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

        mSwipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_widget);
        myImageView = (MyImageView) findViewById(R.id.image_view);

        mSwipeLayout.setColorSchemeResources(R.color.colorAccent,R.color.colorPrimary);
        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {//设置刷新监听器
            @Override
            public void onRefresh() {
                refreshImage();
                mSwipeLayout.setRefreshing(false);
                /*
                new Handler().postDelayed(new Runnable() {//模拟耗时操作
                    @Override
                    public void run() {
                        refreshImage();
                        mSwipeLayout.setRefreshing(false);//取消刷新
                    }
                },2000);

                 */
            }
        });

        mSwipeLayout.setRefreshing(true);
        refreshImage();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeLayout.setRefreshing(false);
            }
        },2000); // 延时1秒
    }
}
