package at.ac.univie.hci.bitcointracker.portfolio;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import at.ac.univie.hci.bitcointracker.R;

import java.util.ArrayList;
import java.util.List;

public class CoinPriceFragment extends Fragment {

    private ListView listView;
    private Button addCoin;
    private PortfolioMemory portfolioMemory;
    private PriceAdapter priceAdapter;
    private List<Coin> coinList;

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
        portfolioMemory = new PortfolioMemory();

        addCoin = (Button) rootView.findViewById(R.id.addCoinPriceBtn);
        listView = (ListView) rootView.findViewById(R.id.viewListCoinPrice);

        coinList = new ArrayList<>();
        ArrayList<Coin> favorites = portfolioMemory.getFavorites(getContext());

        if (favorites != null) {
            coinList = portfolioMemory.getFavorites(getContext());
        }

        priceAdapter = new PriceAdapter(getContext(), coinList);
        listView.setAdapter(priceAdapter);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        addCoin = (Button) getView().findViewById(
                R.id.addCoinPriceBtn);
        addCoin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                FragmentTransaction fragmentTransaction = getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container_portfolio, new ManagePortfolioCoinsFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

    }

}
