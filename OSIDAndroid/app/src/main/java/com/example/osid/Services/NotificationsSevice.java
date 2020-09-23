package com.example.osid.Services;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.osid.DB.DBCONTROLLER;
import com.example.osid.GraphActivity;
import com.example.osid.R;
import com.example.osid.SettingsUpdateVariables;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class NotificationsSevice extends Service {
    private static  final String TAG = "NotificacionesBackgroundService";
    DBCONTROLLER dbcontroller;
    boolean food;
    boolean insuline;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG,"onBind()");
        return null;
    }

    @Override
    public void onCreate(){
        super.onCreate();

    }

    @Override
    public int onStartCommand(Intent intencion, int flags, int idArranque) {
        String currentTime = GraphActivity.DateToString(new Date(),"HH:mm");
        dbcontroller = new DBCONTROLLER(getApplicationContext());

        food = intencion.getExtras().getBoolean("Food");
        insuline = intencion.getExtras().getBoolean("Insuline");

        if(dbcontroller.GetUser().getInsulinaRestante() <= 50 && !insuline){
            SendNotification("Te queda poca insulina!","HEY",getString(R.string.CHANNEL_REPOSITORIO_ID));
            insuline = true;
        }else{
            insuline = false;
        }

        if(currentTime.equals("10:00")){
            SendNotification("Ya es hora de desayunar!","Desayuno",getString(R.string.CHANNEL_COMIDAS_ID));
            food = true;
        }else if(currentTime.equals("15:00")){
            SendNotification("Ya es hora de Comer!","Comida",getString(R.string.CHANNEL_COMIDAS_ID));
            food = true;
        }else if(currentTime.equals("20:00")){
            SendNotification("Ya es hora de Cenar!","Cena",getString(R.string.CHANNEL_COMIDAS_ID));
            food = true;
        }else{
            food = false;
        }

        Intent serviceIntent = new Intent(this, NotificationsSevice.class);
        serviceIntent.putExtra("Food",food);
        serviceIntent.putExtra("Insuline", insuline);
        startService(serviceIntent);

        return START_STICKY;
    }

    @Override
    public void onDestroy(){

    }

    @Override
    public void onLowMemory() {

    }
    public  void SendNotification(String message, String title, String CHANNEL_ID){
        Intent intent = new Intent(this, SettingsUpdateVariables.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(1, builder.build());
    }

    private boolean checktimings(String time, String endtime) {

        String pattern = "HH:mm";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);

        try {
            Date date1 = sdf.parse(time);
            Date date2 = sdf.parse(endtime);

            if(date1.equals(date2)) {
                return true;
            } else {
                return false;
            }
        } catch (ParseException e){
            e.printStackTrace();
        }
        return false;
    }
}
