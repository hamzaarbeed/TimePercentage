package com.hamza.timepercentage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    ProgressBar yearProgressBar, monthProgressBar, weekProgressBar, dayProgressBar, hourProgressBar, minuteProgressBar;
    TextView curYear, curMonth, curWeek, curDay, curHour, curMinute;
    TextView yearText,monthText,weekText,dayText,hourText,minuteText;
    Calendar time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
        refreshMinute();
    }


    private void initialize() {
        yearText = (TextView)findViewById(R.id.yearText);
        yearProgressBar = (ProgressBar) findViewById(R.id.yearProgressBar);
        curYear = (TextView) findViewById(R.id.curYear);

        monthText = (TextView)findViewById(R.id.monthText);
        monthProgressBar = (ProgressBar) findViewById(R.id.monthProgressBar);
        curMonth = (TextView) findViewById(R.id.curMonth);

        weekText = (TextView)findViewById(R.id.weekText);
        weekProgressBar = (ProgressBar) findViewById(R.id.weekProgressBar);
        curWeek = (TextView) findViewById(R.id.curWeek);

        dayText = (TextView)findViewById(R.id.dayText);
        dayProgressBar = (ProgressBar) findViewById(R.id.dayProgressBar);
        curDay = (TextView) findViewById(R.id.curDay);

        hourText = (TextView)findViewById(R.id.hourText);
        hourProgressBar = (ProgressBar) findViewById(R.id.hourProgressBar);
        curHour = (TextView) findViewById(R.id.curHour);

        minuteText = (TextView)findViewById(R.id.minuteText);
        minuteProgressBar = (ProgressBar) findViewById(R.id.minuteProgressBar);
        curMinute = (TextView) findViewById(R.id.curMinute);

        dayText.setText("Hours");
        hourText.setText("Minutes");
        minuteText.setText("Seconds");

    }

    private void refreshMinute() {
        time = Calendar.getInstance();

        minuteProgressBar.setMax(60000);
        int temp=time.get(time.SECOND);

        minuteProgressBar.setProgress(temp*1000+ time.get(time.MILLISECOND));
        curMinute.setText((new SimpleDateFormat("s")).format(time.getTime()));
        Log.v("fa",curHour.getText().toString());
        if (time.get(time.MINUTE) != Integer.parseInt(curHour.getText().toString()))
            refreshAll();
    }

    private void refreshAll() {
        time = Calendar.getInstance();
        int hour = Integer.parseInt((new SimpleDateFormat("H")).format(time.getTime()));
        int temp;

        yearProgressBar.setMax(time.getActualMaximum(time.DAY_OF_YEAR) * 24 * 60);
        temp=time.get(time.DAY_OF_YEAR);
        curYear.setText(String.valueOf(temp));
        yearProgressBar.setProgress(temp * 24 * 60 + hour * 60 + time.get(time.MINUTE));
        yearText.setText(Integer.toString(time.get(time.YEAR)));

        monthProgressBar.setMax(time.getActualMaximum(time.DAY_OF_MONTH) * 24 * 60);
        temp=time.get(time.DAY_OF_MONTH);
        curMonth.setText(String.valueOf(temp));
        monthProgressBar.setProgress(temp * 24 * 60 + hour * 60 + time.get(time.MINUTE));
        monthText.setText((new SimpleDateFormat("MMMM")).format(time.getTime()));

        weekProgressBar.setMax(7 * 24 * 60);
        temp=time.get(time.DAY_OF_WEEK);
        curWeek.setText(String.valueOf(temp));
        weekProgressBar.setProgress(temp * 24 * 60 + hour * 60 + time.get(time.MINUTE));
        weekText.setText((new SimpleDateFormat("cccc")).format(time.getTime()));

        dayProgressBar.setMax(24 * 60);
        dayProgressBar.setProgress(hour * 60 + time.get(time.MINUTE));
        curDay.setText((new SimpleDateFormat("H")).format(time.getTime()));

        hourProgressBar.setMax(60);
        temp=time.get(time.MINUTE);
        hourProgressBar.setProgress(temp);
        curHour.setText((new SimpleDateFormat("m")).format(time.getTime()));

    }

    public void refresh(View view) {
        refreshMinute();
    }
}