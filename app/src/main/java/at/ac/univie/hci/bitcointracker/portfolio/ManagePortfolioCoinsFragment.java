package at.ac.univie.hci.bitcointracker.portfolio;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import at.ac.univie.hci.bitcointracker.R;

import java.util.ArrayList;
import java.util.List;

public class ManagePortfolioCoinsFragment extends Fragment {

    private ListView listView;
    private CoinAdapter coinAdapter;
    private List<Coin> coinList;
    private ArrayList<String> cryptoList;
    private Button addCoinBtn;
    private EditText coinName;
    private EditText coinAmount;

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
        View rootView = inflater.inflate(R.layout.manage_coin_fragment, container, false);

        coinName = (EditText) rootView.findViewById(R.id.coinNameReal);
        coinAmount = (EditText) rootView.findViewById(R.id.coinAmountReal);
        addCoinBtn = (Button) rootView.findViewById(R.id.saveCoinBtn);
        listView = (ListView) rootView.findViewById(R.id.listViewCoin);
        coinList = new ArrayList<>();
        cryptoList = new ArrayList<>();

        cryptoList.add("BTC");
        cryptoList.add("LTC");
        cryptoList.add("ECA");
        cryptoList.add("ABC");

        coinList.add(new Coin("BTC", "$", "232.1"));
        coinList.add(new Coin("LTC", "$", "2312"));
        coinList.add(new Coin("ECA", "$", "23123"));


        coinAdapter = new CoinAdapter(getContext(), coinList);
        listView.setAdapter(coinAdapter);


        return rootView;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        addCoinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getCoin = coinName.getText().toString();
                String getAmount = coinAmount.getText().toString();

                ArrayList<String> savedCoins = new ArrayList<>();

                for (Coin aCoinList : coinList) {
                    savedCoins.add(aCoinList.getName());
                }

                if (getCoin == null || getCoin.trim().equals("") || coinAmount.getText().toString() == null
                        || coinAmount.getText().toString().trim().equals("")) {
                    Toast.makeText(getContext(), "One or more Input Fields are Empty", Toast.LENGTH_LONG).show();
                } else if (!(cryptoList.contains(getCoin))) {
                    Toast.makeText(getContext(), "Unknown Coin", Toast.LENGTH_LONG).show();
                } else {

                    if (savedCoins.contains(getCoin)) {
                        updateCoinAmount(getCoin,getAmount);
                    } else renderCoinInput(getCoin, getAmount);
                }
            }
        });
    }


    private String updateCoinAmount(String name, String amount) {
        String updatedAmount = "";
        for (Coin aCoinList : coinList) {
            if (aCoinList.getName().equals(name)) {
                String previousAmount = aCoinList.getAmount();
                Double value = Double.parseDouble(previousAmount) + Double.parseDouble(amount);
                updatedAmount = String.valueOf(value);
                aCoinList.setAmount(updatedAmount);
                coinAdapter.updateAdapter(coinList);
            }
        }
        return updatedAmount;
    }

    private void renderCoinInput(String name, String amount) {
        Coin coin = new Coin();
        coin.setName(name);
        coin.setAmount(amount);
        coin.setCurrency("$");
        coinList.add(coin);
        coinAdapter.updateAdapter(coinList);
    }

}
