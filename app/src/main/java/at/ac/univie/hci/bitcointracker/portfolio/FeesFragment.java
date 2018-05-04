package at.ac.univie.hci.bitcointracker.portfolio;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import at.ac.univie.hci.bitcointracker.R;

public class FeesFragment extends Fragment {


    /**
     * onCreateView is invoked when this fragment is created and here I am initialising my TextViews
     * need for my layout
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fees_fragment, container, false);
        Spinner feesMarkets = (Spinner) rootView.findViewById(R.id.marketFees);
        final TextView sellPercent = (TextView) rootView.findViewById(R.id.sellPercent);
        final TextView buyPercent = (TextView) rootView.findViewById(R.id.buyPercent);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_expandable_list_item_1,
                getResources().getStringArray(R.array.coinMarkets));

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        feesMarkets.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

                if (position == 0) {
                    buyPercent.setText("     " + "1,49%");
                    sellPercent.setText("     " + "1,49%");
                } else if (position == 1) {
                    buyPercent.setText("     " + "0,43%");
                    sellPercent.setText("     " + "0,43%");
                } else if(position == 2){
                    buyPercent.setText("     " + "0,1%");
                    sellPercent.setText("     " + "0,1");
                } else if (position == 3){
                    buyPercent.setText("     " + "0%");
                    sellPercent.setText("     " + "0,25%");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // Apply the adapter to the spinner
        feesMarkets.setAdapter(adapter);


        return rootView;
    }


}
