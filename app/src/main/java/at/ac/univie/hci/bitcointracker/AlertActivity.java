package at.ac.univie.hci.bitcointracker.portfolio;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import at.ac.univie.hci.bitcointracker.MainActivity;
import at.ac.univie.hci.bitcointracker.R;

public class AlertActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alerts);

        Spinner currencies=findViewById(R.id.currencies);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,
                getResources().getStringArray(R.array.currencies));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        currencies.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });
        currencies.setAdapter(adapter);


        final ListView listview = (ListView) findViewById(R.id.listview);

        String[] from = { "name", "purpose" };
        int[] to = { R.id.coin_label, R.id.coin_value };

        ArrayList<Map<String, String>> list = buildData();
        SimpleAdapter adapter1 = new SimpleAdapter(this,list,
                R.layout.currency_table, from, to);

        listview.setAdapter(adapter1);

    }

    private ArrayList<Map<String, String>> buildData() {
        ArrayList<Map<String, String>> list = new ArrayList<Map<String, String>>();
        list.add(putData("Bitcoin     ", "9756"));
        list.add(putData("Ethereum", "736"));
        list.add(putData("Ripple      ", "0.86"));
        list.add(putData("Litecoin   ", "178"));
        return list;
    }

    private HashMap<String, String> putData(String name, String purpose) {
        HashMap<String, String> item = new HashMap<String, String>();
        item.put("name", name);
        item.put("purpose", purpose);
        return item;
    }

   /* public void onButtonClick(View view)
    {
        if (view.getId()==R.id.addAlert)
        {
            Intent i=new Intent (MainActivity.this, AlertActivity.class);
            startActivity(i);
        }
    }*/
}

