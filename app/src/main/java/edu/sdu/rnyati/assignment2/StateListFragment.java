package edu.sdu.rnyati.assignment2;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class StateListFragment extends ListFragment implements OnItemClickListener {

    int value = 0;
    ArrayAdapter adapter;
    String selectedCountry;

    public StateListFragment(){

    }

    public interface DataValuesListener{
        public void countryStateValues(String country, String state);
    }

    public void setBundle(Bundle bundle){
        this.setArguments(bundle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_state_list, container, false);
        if(getArguments() != null) {
            value = getArguments().getInt("position");
            selectedCountry = getArguments().getString("country");
        }
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        switch(value){
            case 0:
                adapter = ArrayAdapter.createFromResource(getActivity(),
                        R.array.China, android.R.layout.simple_list_item_1);
                break;
            case 1:
                adapter = ArrayAdapter.createFromResource(getActivity(),
                        R.array.India, android.R.layout.simple_list_item_1);
                break;
            case 2:
                adapter = ArrayAdapter.createFromResource(getActivity(),
                        R.array.Mexico, android.R.layout.simple_list_item_1);
                break;
            case 3:
                adapter = ArrayAdapter.createFromResource(getActivity(),
                        R.array.USA, android.R.layout.simple_list_item_1);
                break;
            default:
                break;

        }

        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
        Toast.makeText(getActivity(), "Item: " + position, Toast.LENGTH_SHORT).show();
        String selectedState = (String) getListView().getItemAtPosition(position);
        DataValuesListener listerner = (DataValuesListener) getActivity();
        listerner.countryStateValues(selectedCountry,selectedState);
    }
}
