package edu.sdu.rnyati.assignment2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

public class DateActivity extends AppCompatActivity {

    DatePicker datePicker;
    int day;
    int month;
    int year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);

        datePicker = (DatePicker) findViewById(R.id.datePicker);
        day = datePicker.getDayOfMonth();
        month = datePicker.getMonth() + 1;
        year = datePicker.getYear();
    }

    public void back(View button){
        finish();
    }

    public void doneClicked(View Button){
        day = datePicker.getDayOfMonth();
        month = datePicker.getMonth() + 1;
        year = datePicker.getYear();

        Intent toPassBack = getIntent();
        toPassBack.putExtra("day", day);
        toPassBack.putExtra("month", month);
        toPassBack.putExtra("year", year);
        setResult(RESULT_OK, toPassBack);
        finish();
    }

    public void cancelClicked(View Button){
        finish();
    }
}
