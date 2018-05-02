package at.ac.univie.hci.bitcointracker.portfolio;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import at.ac.univie.hci.bitcointracker.R;

public class CoinPriceFragment extends Fragment {

    private TextView test;

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
        test = (TextView) rootView.findViewById(R.id.TESTCOIN);

        return rootView;
    }

}
