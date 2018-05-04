package at.ac.univie.hci.bitcointracker.portfolio;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import at.ac.univie.hci.bitcointracker.R;


import java.util.List;

public class CoinAdapter extends BaseAdapter {

    private List<Coin> coinArrayList;
    private Context mContext;


    public CoinAdapter(Context mContext, List<Coin>  coinArrayList) {
        this.mContext = mContext;
        this.coinArrayList = coinArrayList;
    }

    @Override
    public int getCount() {
        return coinArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return coinArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(mContext, R.layout.coin_price_template, null);

        TextView tvName = (TextView) view.findViewById(R.id.nameTest);
        TextView tvCurrency = (TextView) view.findViewById(R.id.coinCurrencyTest);
        TextView tvAmount = (TextView) view.findViewById(R.id.coinPriceTest);
        ImageButton tvBtn = (ImageButton) view.findViewById(R.id.imageAlarmBtn);

        tvName.setText(coinArrayList.get(position).getName());
        tvCurrency.setText(coinArrayList.get(position).getCurrency());
//        String convertToString = Double.toString(convertDouble);
        tvAmount.setText(coinArrayList.get(position).getAmount());

        view.setTag(coinArrayList.get(position).getName());

        return view;
    }


    public void updateAdapter(List<Coin> tmp) {

        this.coinArrayList = tmp;

        //and call notifyDataSetChanged
        notifyDataSetChanged();
    }


}
