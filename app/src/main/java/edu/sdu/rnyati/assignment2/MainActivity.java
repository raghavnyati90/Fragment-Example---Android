package edu.sdu.rnyati.assignment2;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import android.content.Intent;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int INTENT_DATE = 123;
    private static final int INTENT_COUNTRY = 124;
    public static final String PREFS_NAME = "MyPrefsFile";
    int day, month, year;
    TextView dateTextView, countryTextView, stateTextView;
    EditText fNameTextField, lNameTextField, ageTextField, emailTextField, phoneTextField;
    String country, state, fName, lName, email, date, phone, age;
    SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dateTextView = (TextView) findViewById(R.id.dateTextView);
        countryTextView = (TextView) findViewById(R.id.countryTextView);
        stateTextView = (TextView) findViewById(R.id.stateTextView);
        fNameTextField = (EditText) findViewById(R.id.fNameTextField);
        lNameTextField = (EditText) findViewById(R.id.lNameTextField);
        ageTextField = (EditText) findViewById(R.id.ageTextField);
        emailTextField = (EditText) findViewById(R.id.emailTextField);
        phoneTextField = (EditText) findViewById(R.id.phoneTextField);

        fName = fNameTextField.getText().toString();
        lName = lNameTextField.getText().toString();
        age = ageTextField.getText().toString();
        email = emailTextField.getText().toString();
        phone = phoneTextField.getText().toString();
        country = countryTextView.getText().toString();
        date = dateTextView.getText().toString();
        state = stateTextView.getText().toString();

        restoreData();
    }

    private void restoreData() {
        settings = getSharedPreferences(PREFS_NAME, 0);
        fNameTextField.setText(settings.getString("fName", fName));
        lNameTextField.setText(settings.getString("lName", lName));
        ageTextField.setText(settings.getString("age", age));
        emailTextField.setText(settings.getString("email", email));
        phoneTextField.setText(settings.getString("phone", phone));
        countryTextView.setText(settings.getString("country", country));
        stateTextView.setText(settings.getString("state", state));
        dateTextView.setText(settings.getString("date", date));
    }

    public void setDateClicked(View Button){
        Intent setDate = new Intent(this, DateActivity.class);
        startActivityForResult(setDate, INTENT_DATE);
    }

    public void setCountryStateClicked(View Button){
        Intent setCountryState = new Intent(this, CountryState.class);
        startActivityForResult(setCountryState, INTENT_COUNTRY);
    }

    public void onDoneClicked(View Button){
        saveData();
        Toast.makeText(this, "Data saved successfully.", Toast.LENGTH_SHORT).show();
    }

    private void saveData() {
        settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("fName", fNameTextField.getText().toString());
        editor.putString("lName", lNameTextField.getText().toString());
        editor.putString("email", emailTextField.getText().toString());
        editor.putString("age", ageTextField.getText().toString());
        editor.putString("phone", phoneTextField.getText().toString());
        editor.putString("date", dateTextView.getText().toString());
        editor.putString("country", countryTextView.getText().toString());
        editor.putString("state", stateTextView.getText().toString());
        editor.commit();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == INTENT_COUNTRY) {
            switch (resultCode) {
                case RESULT_OK:
                        country = data.getStringExtra("country");
                        state = data.getStringExtra("state");
                        countryTextView.setText(country);
                        stateTextView.setText(state);
                        break;
                case RESULT_CANCELED:
                    break;
            }
        } else if(requestCode == INTENT_DATE){
            switch (resultCode) {
                case RESULT_OK:
                        day = data.getIntExtra("day",-1);
                        month = data.getIntExtra("month", -1);
                        year = data.getIntExtra("year", -1);
                        date = month + "-" + day + "-" + year;
                        dateTextView.setText(date);
                    break;
                case RESULT_CANCELED:
                    break;
            }
        } else {
            return;
        }
    }

    @Override
    protected void onStop(){
        super.onStop();
        saveData();
    }
}
