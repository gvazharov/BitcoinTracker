package at.ac.univie.hci.bitcointracker.portfolio;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import at.ac.univie.hci.bitcointracker.R;

public class PieChartFragment extends Fragment {

    private TextView pieChart;

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
        View rootView = inflater.inflate(R.layout.coin_price_fragment, container, false);
        pieChart = (TextView) rootView.findViewById(R.id.pieChart);

        return rootView;
    }


}
