package com.example.osid;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.osid.DB.DBCONTROLLER;
import com.example.osid.GLOBAL.GLOBAL;

import java.util.ArrayList;
import java.util.Set;

public class SettingsActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    private static final int REQUEST_ENABLE_BT = 0;
    //private static final int REQUEST_DISCOVER_BT = 1;

    ImageButton Bluetooth, Otros, back;
    EditText currentInsuline;

    DBCONTROLLER dbcontroller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        dbcontroller = new DBCONTROLLER(this);

        Bluetooth = findViewById(R.id.BluetoothSettings);
        Otros = findViewById(R.id.CualquierOtro);
        back = findViewById(R.id.goBack_settings);
        currentInsuline = findViewById(R.id.actualInsuline_txt_temp);

        currentInsuline.setText(dbcontroller.GetUser().getInsulinaRestante() +"");

        currentInsuline.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if((keyEvent.getAction() == KeyEvent.ACTION_DOWN) && (i == KeyEvent.KEYCODE_ENTER)){
                    if(currentInsuline.getText().toString().length()<=0){
                        currentInsuline.setText("0");
                    }
                    if(currentInsuline.getText().toString().length()>=300){
                        currentInsuline.setText("300");
                    }
                    GLOBAL.user.setInsulinaRestante(Float.parseFloat(currentInsuline.getText().toString()));
                    dbcontroller.ActualizarUser(GLOBAL.user);

                    //basal.setSelected(false);
                    currentInsuline.clearFocus();
                    hideSoftKeyboard(getWindow().getDecorView().findViewById(android.R.id.content));
                    return true;
                }
                return false;
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

    }

    public void ConfigurarBluetooth(View view) {
        Intent i = new Intent(this, BluetoothConfigurarActivity.class);
        startActivity(i);
    }


    public void OtrosAjustes(View view) {
        Intent i = new Intent(this, OtherSettigsActivity.class);
        startActivity(i);
    }
    public static void hideSoftKeyboard(View view) {
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (inputManager != null) {
                inputManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }
}