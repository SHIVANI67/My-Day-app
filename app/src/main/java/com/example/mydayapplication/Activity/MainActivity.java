package com.example.mydayapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mydayapplication.Adapters.DaysPagerAdapter;
import com.example.mydayapplication.Interface.TaskApiService;
import com.example.mydayapplication.Pojo.TaskList;
import com.example.mydayapplication.Pojo.Tasks;
import com.example.mydayapplication.R;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ImageView leftArrow, rightArrow;
    TextView dateTv, monthTv;
    ViewPager pager;
    TabLayout tabs;
    DaysPagerAdapter adapter;
    int index = 0;
    Calendar calendar = Calendar.getInstance();
    String dayOfTheMonth, month;
    TaskList taskList;

    public static final String BASE_URL = "https://38rhabtq01.execute-api.ap-south-1.amazonaws.com/dev/schedule/";
    private static Retrofit retrofit = null;
    SharedPreferences mPrefs;
    SharedPreferences.Editor prefsEditor;
    Gson gson = new Gson();
    String json;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        leftArrow = findViewById(R.id.left_arrow);
        rightArrow = findViewById(R.id.right_arrow);
        dateTv = findViewById(R.id.date_tv);
        monthTv = findViewById(R.id.month_tv);
        mPrefs = getPreferences(MODE_PRIVATE);
        prefsEditor = mPrefs.edit();

        Date d = calendar.getTime();
        dayOfTheMonth = new SimpleDateFormat("dd").format(d);
        month = new SimpleDateFormat("MMM").format(d);

        System.out.println("day of the month: " + dayOfTheMonth + " month name: " + month);

        dateTv.setText(dayOfTheMonth);
        monthTv.setText(month);

        String returnJson = mPrefs.getString("Tasklists", "");

        System.out.println("shared preference data :" + returnJson.isEmpty());

        if(returnJson.isEmpty()){
            connectAndGetApiData();
        }

        long firstApiCallDate = mPrefs.getLong("Api first call date", 0);
        index = (int)(System.currentTimeMillis() - firstApiCallDate)/(1000*60*60*24);

        Log.e("indexvalue", "onCreate: "+ index);

        pager = findViewById(R.id.view_pager);
        tabs = findViewById(R.id.day_tabs);
        tabs.setupWithViewPager(pager);

        adapter = new DaysPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,index);
        pager.setAdapter(adapter);

        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.add(Calendar.DAY_OF_MONTH,-1);
                Date d = calendar.getTime();
                dayOfTheMonth = new SimpleDateFormat("dd").format(d);
                month = new SimpleDateFormat("MMM").format(d);
                dateTv.setText(dayOfTheMonth);
                monthTv.setText(month);

                index--;

                adapter = new DaysPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, index);
                pager.setAdapter(adapter);
            }
        });

        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.add(Calendar.DAY_OF_MONTH,1);
                Date d = calendar.getTime();
                dayOfTheMonth = new SimpleDateFormat("dd").format(d);
                month = new SimpleDateFormat("MMM").format(d);
                dateTv.setText(dayOfTheMonth);
                monthTv.setText(month);

                index++;

                adapter = new DaysPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,index);
                pager.setAdapter(adapter);
            }
        });

    }

    public void connectAndGetApiData() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        TaskApiService taskApiService = retrofit.create(TaskApiService.class);
        Call<TaskList> call = taskApiService.getTaskLists();

        call.enqueue(new Callback<TaskList>() {
            @Override
            public void onResponse(Call<TaskList> call, Response<TaskList> response) {
                if(response.isSuccessful()) {
                    taskList = response.body();

                    json = gson.toJson(taskList);
                    prefsEditor.putString("Tasklists",json);
                    prefsEditor.putLong("Api first call date", System.currentTimeMillis());
                    prefsEditor.commit();
                }
            }

            @Override
            public void onFailure(Call<TaskList> call, Throwable t) {
                System.out.println("Api response failed: " + taskList);
                t.printStackTrace();
            }
        });
    }
}

