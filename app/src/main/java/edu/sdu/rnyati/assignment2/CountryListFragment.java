package edu.sdu.rnyati.assignment2;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Toast;


public class CountryListFragment extends ListFragment implements OnItemClickListener {

    public CountryListFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_country_list, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.countries, android.R.layout.simple_list_item_1);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
        Toast.makeText(getActivity(), "Item: " + position, Toast.LENGTH_SHORT).show();
        String selectedFromList = (String) getListView().getItemAtPosition(position);
        switch(position){
            case 0:
                Log.i("position","0");
                openFragment(0, getFragmentManager(), selectedFromList);
                break;
            case 1:
                Log.i("position","1");
                openFragment(1, getFragmentManager(), selectedFromList);
                break;
            case 2:
                Log.i("position","2");
                openFragment(2, getFragmentManager(), selectedFromList);
                break;
            case 3:
                Log.i("position","3");
                openFragment(3, getFragmentManager(), selectedFromList);
                break;
            default:
                break;
        }
    }

    public static void openFragment(int i, FragmentManager fragments, String selectedFromList){
        FragmentTransaction fragmentTransaction = fragments.beginTransaction();
        StateListFragment fragment = new StateListFragment();
        final Bundle bundle = new Bundle();
        bundle.putInt("position", i);
        bundle.putString("country", selectedFromList);
        fragment.setBundle(bundle);
        fragmentTransaction.replace(R.id.fragment_holder, fragment);
        fragmentTransaction.remove(new CountryListFragment());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}
