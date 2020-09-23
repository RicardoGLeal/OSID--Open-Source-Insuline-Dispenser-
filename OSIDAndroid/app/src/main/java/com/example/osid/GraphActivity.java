package com.example.osid;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.osid.DB.DBCONTROLLER;
import com.example.osid.GLOBAL.GLOBAL;
import com.example.osid.POJOs.Glucose;
import com.example.osid.POJOs.Insuline;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class GraphActivity extends AppCompatActivity {
    LineChart lineChart;
    DBCONTROLLER dbcontroller;
    Button btn_Hoy, btn_Semana, btn_Mes;
    TextView fechaInicio, fechaFin;
    ImageButton back;

    Date fechaActual;
    Date startDate;
    Date endDate;

    Calendar auxCalendar;

    DatePickerDialog datePickerDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        auxCalendar = Calendar.getInstance();

        lineChart = findViewById(R.id.mpChart);
        dbcontroller = new DBCONTROLLER(this);

        fechaInicio = findViewById(R.id.fechaInicio);
        fechaFin = findViewById(R.id.fechaFin);

        btn_Hoy = findViewById(R.id.btn_hoy);
        btn_Mes = findViewById(R.id.btn_mes);
        btn_Semana = findViewById(R.id.btn_semana);

        back = findViewById(R.id.goBack_charts);

        fechaActual = new Date();

        btn_Hoy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowTodayData();
            }
        });

        btn_Semana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowWeekData();
            }
        });

        btn_Mes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowMonthData();
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        fechaInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChooseDate(fechaInicio, true);
                //startDate = StringToDate(fechaInicio.getText().toString(),GLOBAL.DATE_FORMAT);

            }
        });

        fechaFin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChooseDate(fechaFin, false);
                //endDate = StringToDate(fechaInicio.getText().toString(),GLOBAL.DATE_FORMAT);

            }
        });

        //TODO Borrar
        TestData();
        Initialization();
    }
    void TestData(){
        String dtStart;
        for(int i = 1; i <= 29;i++){
            dtStart = "2020-02-" + i +" 17:00:00";
            dbcontroller.InsertInsuline(new Insuline(i,StringToDate(dtStart, GLOBAL.DATE_FORMAT),new Random().nextInt((40 - 1) + 1) + 1));
            dbcontroller.InsertGlucose(new Glucose(i,StringToDate(dtStart, GLOBAL.DATE_FORMAT),new Random().nextInt((300 - 55) + 1) + 55));
            dtStart = "2020-02-" + i +" 11:00:00";
            dbcontroller.InsertGlucose(new Glucose(i,StringToDate(dtStart, GLOBAL.DATE_FORMAT),new Random().nextInt((300 - 55) + 1) + 55));
            dtStart = "2020-02-" + i +" 21:00:00";
            dbcontroller.InsertGlucose(new Glucose(i,StringToDate(dtStart, GLOBAL.DATE_FORMAT),new Random().nextInt((300 - 55) + 1) + 55));
        }
    }

    void Initialization(){
        startDate = new Date();
        endDate = new Date();
        fechaActual = new Date();
       ShowTodayData();
    }

    void ChartCalculator(Date startDate, Date endDate){
        lineChart.clear();

        List<Entry> glucosaEntries = new ArrayList<Entry>();
        List<Entry> insulinaEntries = new ArrayList<Entry>();

        List<Insuline> insulines = new ArrayList<Insuline>(dbcontroller.GetArrayInsuline());
        List<Glucose> glucoses = new ArrayList<Glucose>(dbcontroller.GetArrayGlucose());

        Collections.sort(insulines, new Comparator<Insuline>() {
            @Override
            public int compare(Insuline insuline, Insuline t1) {
                return insuline.getFecha().compareTo(t1.getFecha());
            }
        });
        Collections.sort(glucoses, new Comparator<Glucose>() {
            @Override
            public int compare(Glucose glucose, Glucose t1) {
                return glucose.getFecha().compareTo(t1.getFecha());
            }
        });

        String dateFormat = "YYYYMMdd";

        for(Insuline dataSet : insulines){
            if(IsBeforeDate(dataSet.getFecha(),endDate) && IsAfterDate(dataSet.getFecha(),startDate)){
                insulinaEntries.add(new Entry(dataSet.getFecha().getTime(),dataSet.getInsuline()));
            }
        }
        for(Glucose dataSet: glucoses){
            if(IsBeforeDate(dataSet.getFecha(),endDate) && IsAfterDate(dataSet.getFecha(),startDate)){
                glucosaEntries.add(new Entry(dataSet.getFecha().getTime(), dataSet.getGlucose()));
            }
        }

        ArrayList<LineDataSet> lines = new ArrayList<LineDataSet> ();

        LineDataSet dataSetInsulina = new LineDataSet(insulinaEntries,"Insulina");
        dataSetInsulina.setColor(getColor(R.color.blue));
        dataSetInsulina.setValueTextColor(getColor(R.color.black));
        dataSetInsulina.setFillAlpha(100);
        dataSetInsulina.setDrawFilled(true);
        dataSetInsulina.setFillColor(getColor(R.color.blue));
        lines.add(dataSetInsulina);

        LineDataSet dataSetGlucosa = new LineDataSet(glucosaEntries,"Glucosa");
        dataSetGlucosa.setColor(getColor(R.color.colorAccent));
        dataSetGlucosa.setValueTextColor(getColor(R.color.black));
        //dataSetGlucosa.setFillAlpha(100);
        //dataSetGlucosa.setDrawFilled(true);
        //dataSetGlucosa.setFillColor(getColor(R.color.colorAccent));
        lines.add(dataSetGlucosa);

        LineData lineData = new LineData();
        lineData.addDataSet(dataSetInsulina);
        lineData.addDataSet(dataSetGlucosa);
        lineChart.setData(lineData);
        lineChart.animateY(400);

        ValueFormatter valueFormatter = new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                Date date = new Date((long)value);
                //Specify the format you'd like
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM HH:mm");
                return sdf.format(date);
            }
        };
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setValueFormatter(valueFormatter);
        xAxis.setLabelCount(4);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

    }

    void ShowTodayData(){
        fechaActual = new Date();
        startDate = fechaActual;
        endDate = fechaActual;
        fechaInicio.setText(DateToString(fechaActual, "dd/MM/YYYY"));
        fechaFin.setText(DateToString(fechaActual, "dd/MM/YYYY"));

        ChartCalculator(fechaActual,fechaActual);
    }

    void ShowWeekData(){
        fechaActual = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaActual);
        calendar.add(Calendar.DAY_OF_MONTH, Calendar.SUNDAY - calendar.get(Calendar.DAY_OF_WEEK));

        startDate = calendar.getTime();

        calendar.setTime(fechaActual);
        calendar.add(Calendar.DAY_OF_MONTH, Calendar.SUNDAY +(Calendar.SATURDAY - calendar.get(Calendar.DAY_OF_WEEK) - 1));

        endDate = calendar.getTime();

        fechaInicio.setText(DateToString(startDate,"dd/MM/YYYY"));
        fechaFin.setText(DateToString(endDate,"dd/MM/YYYY"));
        ChartCalculator(startDate,endDate);
    }

    void ShowMonthData(){
        fechaActual = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaActual);
        //calendar.add(Calendar.DAY_OF_MONTH, Calendar.SUNDAY - calendar.get(Calendar.DAY_OF_WEEK));
        calendar.set(Calendar.DAY_OF_MONTH,
                calendar.getActualMinimum(Calendar.DAY_OF_MONTH));

        startDate = calendar.getTime();

        calendar.setTime(fechaActual);
        //calendar.add(Calendar.DAY_OF_MONTH, Calendar.SUNDAY +(Calendar.SATURDAY - calendar.get(Calendar.DAY_OF_WEEK) - 1));
        calendar.set(Calendar.DAY_OF_MONTH,
                calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        endDate = calendar.getTime();

        fechaInicio.setText(DateToString(startDate,"dd/MM/YYYY"));
        fechaFin.setText(DateToString(endDate,"dd/MM/YYYY"));
        ChartCalculator(startDate,endDate);
    }

    void ChooseDate(final TextView txtDate, final boolean x){
        Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);
        int year= c.get(Calendar.YEAR);

        datePickerDialog = new DatePickerDialog(GraphActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                auxCalendar = calendar;
                //TODO revisar fechas
                txtDate.setText(DateToString(calendar.getTime(), "dd/MM/YYYY"));
                //auxCalendar.set(Calendar.YEAR,year);
                //auxCalendar.set(Calendar.MONTH,month);
                //auxCalendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                if(x){
                    startDate = auxCalendar.getTime();
                    //ChartCalculator(auxCalendar.getTime(),endDate);
                }else{
                    endDate = auxCalendar.getTime();
                    //ChartCalculator(startDate,auxCalendar.getTime());
                }
                ChartCalculator(startDate,endDate);
                RevisarFechas();
            }
        },year,month,day);

        datePickerDialog.show();

    }

    public void RevisarFechas(){
        if(endDate.before(startDate)){
            fechaFin.setTextColor(getColor(R.color.red));

        }else{
            fechaFin.setTextColor(getColor(R.color.txt_color));

        }
        if(startDate.after(endDate)){
            fechaInicio.setTextColor(getColor(R.color.red));

        }else{
            fechaInicio.setTextColor(getColor(R.color.txt_color));

        }

    }

    public boolean IsAfterDate(Date date1, Date date2){
        // si la fecha 1 es despues que la fecha 2 entonces TRUE
        // FECHA1 > FECHA2 TRUE
        // FECHA1 < FECHA2 FALSE
        // FECHA1 == FECHA2 TRUE
        String format = "dd/MM/yyyy";
        String strDate1 = DateToString(date1,format);
        String strDate2 = DateToString(date2,format);

        String[] strDate1Array = strDate1.split("/");
        String[] strDate2Array = strDate2.split("/");

        if(Integer.parseInt(strDate1Array[2]) >= Integer.parseInt(strDate2Array[2])){
            if(Integer.parseInt(strDate1Array[1]) >= Integer.parseInt(strDate2Array[1])){
                if(Integer.parseInt(strDate1Array[0]) >= Integer.parseInt(strDate2Array[0])){
                    return true;
                }else{
                    return false;
                }
            }else{
                return false;
            }
        }else {
            return false;
        }
    }

    public boolean IsBeforeDate(Date date1, Date date2){
        // si la fecha 1 es antes que la fecha 2 entonces FALSE
        // FECHA1 > FECHA2 FALSE
        // FECHA1 < FECHA2 TRUE
        // FECHA1 == FECHA2 TRUE
        String format = "dd/MM/yyyy";
        String strDate1 = DateToString(date1,format);
        String strDate2 = DateToString(date2,format);

        String[] strDate1Array = strDate1.split("/");
        String[] strDate2Array = strDate2.split("/");

        if(Integer.parseInt(strDate1Array[2]) <= Integer.parseInt(strDate2Array[2])){
            if(Integer.parseInt(strDate1Array[1]) <= Integer.parseInt(strDate2Array[1])){
                if(Integer.parseInt(strDate1Array[0]) <= Integer.parseInt(strDate2Array[0])){
                    return true;
                }else{
                    return false;
                }
            }else{
                return false;
            }
        }else {
            return false;
        }
    }

    public static Date StringToDate(String dateStr, String format){
        try{
            return new SimpleDateFormat(format).parse(dateStr);
        }catch (ParseException e){
            return null;
        }
    }

    public static String DateToString(Date datet, String format){
        try {
           return new SimpleDateFormat(format).format(datet);
        } catch (Exception e) {
            return null;
        }
    }

}
