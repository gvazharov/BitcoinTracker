package at.ac.univie.hci.bitcointracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class SettingsActivity extends AppCompatActivity{
    Switch switchButton, switchButton2;
    TextView textView, textView2;
    String switchOn = "Switch is ON";
    String switchOff = "Switch is OFF";

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
//        getSupportActionBar().setTitle("Settings");

        // For first switch button
        switchButton = (Switch) findViewById(R.id.switchButton_1);
        textView = (TextView) findViewById(R.id.textView_1);

        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton compoundButton, boolean bChecked) {
                if (bChecked) {
                    textView.setText(switchOn);
                } else textView.setText(switchOff);
            }
        });

        if (switchButton.isChecked()) {
            textView.setText(switchOn);
        } else {
            textView.setText(switchOff);
        }

        // for second switch button
        switchButton2 = (Switch) findViewById(R.id.switchButton_2);
        textView2 = (TextView) findViewById(R.id.textView_2);

        switchButton2.setChecked(false);
        switchButton2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean bChecked) {
                if (bChecked) {
                    textView2.setText(switchOn);
                } else {
                    textView2.setText(switchOff);
                }
            }
        });
        if (switchButton2.isChecked()) {
            textView2.setText(switchOn);
        } else {
            textView2.setText(switchOff);
        }


        Spinner currencies= (Spinner) findViewById(R.id.spinner_settings);
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
    }


}

