package com.example.osid;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.osid.DB.DBCONTROLLER;
import com.example.osid.GLOBAL.GLOBAL;
import com.example.osid.POJOs.Glucose;

import java.util.Date;

public class GlucometerActivity extends AppCompatActivity {
    static ProgressBar progressBar;
    static TextView waitTime_txt;
    static TextView waitingGlucometer_lbl;
    static com.example.osid.MyTextView_Roboto_Regular glucosa, insulinaRecomendada;
    static LinearLayout glucoInfoLinearLayout;
    static ImageButton back;
    static LinearLayout glucoseLayout;
    DBCONTROLLER dbcontroller;

    private StringBuilder DataStringIN = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glucometer);

        dbcontroller = new DBCONTROLLER(this);

        progressBar = findViewById(R.id.waitProgressBarID_glucometer);
        waitTime_txt = findViewById(R.id.txt1);
        waitingGlucometer_lbl = findViewById(R.id.waitingGlucometerID_glucometer);

        glucoseLayout = findViewById(R.id.layout_glucose);
        glucoInfoLinearLayout = findViewById(R.id.glucoInfoID_LinearLayout);
        glucosa = findViewById(R.id.basal1_function_ID);
        insulinaRecomendada = findViewById(R.id.insulina_recomendada);
        back = findViewById(R.id.goBackID_glucometer);

        glucoseLayout.setVisibility(View.GONE);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        MainActivity.btconection.bluetoothIn = new Handler(){
            public void handleMessage(android.os.Message msg){
                if(msg.what == MainActivity.btconection.handlerState){
                    String readMessage = (String)msg.obj;

                    DataStringIN.append(readMessage);

                    int endOfLineIndex = DataStringIN.indexOf("#");

                    if(endOfLineIndex > 0){
                        String dataInPrint = DataStringIN.substring(0, endOfLineIndex);
                        ReceiveData(dataInPrint);
                        DataStringIN.delete(0,DataStringIN.length());

                    }
                }
            }
        };
        //GLOBAL.btconection.MyConexionBT.run();
       // waiting();
    }

    public void ReceiveData(String data)
    {
        glucoseLayout.setVisibility(View.VISIBLE);

        waitingGlucometer_lbl.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        waitTime_txt.setVisibility(View.GONE);

        float voltage = Float.parseFloat(data);
        double glucose = CalculateGlucoseConcentration(voltage);
        glucosa.setText(glucose + " mg/dl");
        if(glucose> 100){
            double  insulina = 0;
            insulina = glucose - 100;
            insulina = insulina/GLOBAL.user.getPGPU();
            insulinaRecomendada.setText(insulina + " U");
        }else{
            insulinaRecomendada.setText(0 + " U");
        }
        dbcontroller.InsertGlucose(new Glucose(new Date(),(int)glucose));
    }

    public static void waiting(){
        waitingGlucometer_lbl.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        waitTime_txt.setVisibility(View.VISIBLE);
        glucoInfoLinearLayout.setVisibility(View.GONE);
    }

    public double CalculateGlucoseConcentration(float voltage)
    {
        return (Math.round(Math.pow(((2347110.773 * Math.pow((18.06302774 * 0.001), (voltage)) + (2 * (4.65 - voltage))) / (8 * voltage)), 2.2f) + (10 * voltage) + 30 - voltage)) +16;
    }
}