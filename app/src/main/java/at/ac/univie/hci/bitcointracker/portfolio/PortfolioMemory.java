package at.ac.univie.hci.bitcointracker.portfolio;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PortfolioMemory {


    public static final String PREFS_NAME = "Bitcoin_Tracker";
    public static final String COINS = "PortfolioCoins";

    public PortfolioMemory() {
        super();
    }

    // This four methods are used for maintaining favorites.
    public void saveFavorites(Context context, List<Coin> favorites) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        editor = settings.edit();

        Gson gson = new Gson();
        String jsonFavorites = gson.toJson(favorites);

        editor.putString(COINS, jsonFavorites);

        editor.commit();
    }

    public void addFavorite(Context context, Coin coin) {
        List<Coin> favorites = getFavorites(context);
        if (favorites == null)
            favorites = new ArrayList<Coin>();
        favorites.add(coin);
        saveFavorites(context, favorites);
    }

    public void removeFavorite(Context context, Coin coin) {
        ArrayList<Coin> favorites = getFavorites(context);
        if (favorites != null) {
            favorites.remove(coin);
            saveFavorites(context, favorites);
        }
    }

    public void updateFavorites(Context context, Coin coin) {
        ArrayList<Coin> favorites = getFavorites(context);
        if (favorites != null) {

            for (Coin favorite : favorites) {
                if (favorite.getName().equals(coin.getName())) {
                    favorite.setAmount(coin.getAmount());
                }
            }
            saveFavorites(context, favorites);
        }
    }

    public ArrayList<Coin> getFavorites(Context context) {
        SharedPreferences settings;
        List<Coin> favorites;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);

        if (settings.contains(COINS)) {
            String jsonFavorites = settings.getString(COINS, null);
            Gson gson = new Gson();
            Coin[] favoriteItems = gson.fromJson(jsonFavorites,
                    Coin[].class);

            favorites = Arrays.asList(favoriteItems);
            favorites = new ArrayList<Coin>(favorites);
        } else
            return null;

        return (ArrayList<Coin>) favorites;
    }

}
