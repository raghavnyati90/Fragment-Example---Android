package edu.sdu.rnyati.assignment2;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class CountryState extends FragmentActivity implements StateListFragment.DataValuesListener{

    String selectedCountry;
    String selectedState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_state);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_holder, new CountryListFragment(), "My_Fragment")
                .commit();
    }

    public void countryStateValues(String country, String state){
        Log.i("country", country);
        Log.i("state", state);
        selectedCountry = country;
        selectedState = state;
    }

    public void cancelClicked(View button){
        finish();
    }

    public void doneClicked(View button){
        if(selectedCountry !=null && selectedState !=null) {
            Intent toPassBack = getIntent();
            toPassBack.putExtra("country", selectedCountry);
            toPassBack.putExtra("state", selectedState);
            setResult(RESULT_OK, toPassBack);
            finish();
        }
        else{
            Toast.makeText(this, "Select Country and State.", Toast.LENGTH_SHORT).show();
        }
    }
}
