package at.ac.univie.hci.bitcointracker.portfolio;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import at.ac.univie.hci.bitcointracker.R;
import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.util.ArrayList;
import java.util.List;

public class ManagePortfolioCoinsFragment extends Fragment {

    private SwipeMenuListView listView;
    private CoinAdapter coinAdapter;
    private List<Coin> coinList;
    private ArrayList<String> cryptoList;
    private Button addCoinBtn;
    private EditText coinName;
    private EditText coinAmount;

    private PortfolioMemory portfolioMemory;

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

        portfolioMemory = new PortfolioMemory();

        coinName = (EditText) rootView.findViewById(R.id.coinNameReal);
        coinAmount = (EditText) rootView.findViewById(R.id.coinAmountReal);
        addCoinBtn = (Button) rootView.findViewById(R.id.saveCoinBtn);
        listView = (SwipeMenuListView) rootView.findViewById(R.id.listViewCoin);
        coinList = new ArrayList<>();
        cryptoList = new ArrayList<>();

        //cryptoList contains all coins, which are allowed to track from our application
        cryptoList.add("BTC");
        cryptoList.add("LTC");
        cryptoList.add("ETH");
        cryptoList.add("ABC");

        //gets the portfolio coins from sharedPreferences
        ArrayList<Coin> favorites = portfolioMemory.getFavorites(getContext());

        if (favorites != null) {
            coinList = portfolioMemory.getFavorites(getContext());
        }

        coinAdapter = new CoinAdapter(getContext(), coinList);
        listView.setAdapter(coinAdapter);

        /**
         * SwipeMenu Creator is used in order to swipe from right to left on one item from the list view and delete it
         *  or do something else
         * */
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem openItem = new SwipeMenuItem(
                        getContext());
                // set item background
                openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
                        0xCE)));
                // set item width
                openItem.setWidth(170);
                // set item title
                openItem.setTitle("Open");
                // set item title fontsize
                openItem.setTitleSize(18);
                // set item title font color
                openItem.setTitleColor(Color.WHITE);
                // add to menu
                menu.addMenuItem(openItem);

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(170);
                // set a icon
                deleteItem.setIcon(R.drawable.alarm_btn);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };

        // set creator
        listView.setMenuCreator(creator);

        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        Toast.makeText(getContext(), "ITEM CLICKED ==" + index, Toast.LENGTH_LONG).show();
                        break;
                    case 1:
                        Toast.makeText(getContext(), "ITEM CLICKED2 ==" + index, Toast.LENGTH_LONG).show();
                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });

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
                        updateCoinAmount(getCoin, getAmount);
                    } else renderCoinInput(getCoin, getAmount);
                }
            }
        });
    }


    private void updateCoinAmount(String name, String amount) {
        String updatedAmount = "";
        for (Coin aCoinList : coinList) {
            if (aCoinList.getName().equals(name)) {
                //TODO Fix SharedPreferences
                String previousAmount = aCoinList.getAmount();
                Double value = Double.parseDouble(previousAmount) + Double.parseDouble(amount);
                updatedAmount = String.valueOf(value);
                aCoinList.setAmount(updatedAmount);
                portfolioMemory.updateFavorites(getContext(), aCoinList);
                coinAdapter.updateAdapter(coinList);
            }
        }

    }

    private void renderCoinInput(String name, String amount) {
        Coin coin = new Coin();
        coin.setName(name);
        coin.setAmount(amount);
        coin.setCurrency("$");
        coinList.add(coin);
        portfolioMemory.addFavorite(getContext(), coin);
        coinAdapter.updateAdapter(coinList);
    }

}
