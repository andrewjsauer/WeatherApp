package com.example.andrewsauer.weatherapp.UI;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.andrewsauer.weatherapp.Adapters.DayAdapter;
import com.example.andrewsauer.weatherapp.R;
import com.example.andrewsauer.weatherapp.weather.Current;
import com.example.andrewsauer.weatherapp.weather.Day;
import com.example.andrewsauer.weatherapp.weather.Forecast;

import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DailyForecastActivity extends AppCompatActivity {


    private Day[] mDays;

    @Bind(android.R.id.list) ListView mListView;
    @Bind(android.R.id.empty) TextView mTextView;
    @Bind(R.id.locationText) TextView mLocationText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_forecast);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        Parcelable[] parcelables = intent.getParcelableArrayExtra(MainActivity.DAILY_FORECAST);
        mDays = Arrays.copyOf(parcelables, parcelables.length, Day[].class);

        DayAdapter adapter = new DayAdapter(this, mDays);
        mListView.setAdapter(adapter);
        mListView.setEmptyView(mTextView);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String dayOfTheWeek = mDays[position].getDayOfTheWeek();
                String conditions = mDays[position].getSummary();
                String highTemperature = mDays[position].getTemperatureMax() + "";

                String message = String.format("On %s, the high will be %s and it will be %s", dayOfTheWeek,
                        highTemperature, conditions);

                Toast.makeText(DailyForecastActivity.this, message, Toast.LENGTH_LONG).show();
            }
        });


//        String[] daysOfTheWeek = {"Sunday", "Monday", "Tuesday",
//                "Wednesday", "Thursday", "Friday", "Saturday" };
//
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_1, daysOfTheWeek);
//
//        setListAdapter(adapter);

    }
}
