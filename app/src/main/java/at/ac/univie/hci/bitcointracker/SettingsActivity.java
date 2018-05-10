package at.ac.univie.hci.bitcointracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;


public class SettingsActivity extends AppCompatActivity{
    Switch switchButton, switchButton2, switchButton3;
    TextView textView, textView2, textView3;
    String switchOn = "Switch is ON";
    String switchOff = "Switch is OFF";

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // For first switch button
        switchButton = (Switch) findViewById(R.id.switchButton);
        textView = (TextView) findViewById(R.id.textView);

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
        switchButton2 = (Switch) findViewById(R.id.switchButton2);
        textView2 = (TextView) findViewById(R.id.textView2);

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
        // for third button
        switchButton3 = (Switch) findViewById(R.id.switchButton3);
        textView3 = (TextView) findViewById(R.id.textView3);

        switchButton3.setChecked(false);
        switchButton3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean bChecked) {
                if (bChecked) {
                    textView3.setText(switchOn);
                } else {
                    textView3.setText(switchOff);
                }
            }
        });
        if (switchButton3.isChecked()) {
            textView3.setText(switchOn);
        } else {
            textView3.setText(switchOff);
        }
    }


}

