package com.example.osid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;

import java.util.Random;

public class OtherSettigsActivity extends AppCompatActivity {

    ImageButton back;
    Switch activeComida, activeInsulina, activeCatether;
    //TODO notificaciones -> CAMBIO DE REPOSITORIO
    //TODO notificaciones -> CAMBIO DE CATETER
    //TODO notificaciones -> HORAS DE INGERIR COMIDAS


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_settigs);

        back = findViewById(R.id.goBack_other_settings);
        activeComida = findViewById(R.id.switch_notificaciones_comida);
        activeInsulina = findViewById(R.id.switch_notificaciones_insulina);
        //activeCatether = findViewById(R.id.switch_notificaciones_cambio_catether);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               onBackPressed();
            }
        });
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
        notificationManager.notify((new Random().nextInt((40 - 1) + 1) + 1), builder.build());

    }
}
