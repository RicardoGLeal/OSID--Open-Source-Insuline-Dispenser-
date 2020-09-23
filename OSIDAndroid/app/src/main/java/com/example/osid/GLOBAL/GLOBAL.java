package com.example.osid.GLOBAL;

import com.example.osid.POJOs.Bluetooth;
import com.example.osid.POJOs.Glucose;
import com.example.osid.POJOs.Insuline;
import com.example.osid.POJOs.User;

import java.util.ArrayList;

public class GLOBAL {
    public final static String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public final static User user = new User();
    public final static ArrayList<Glucose> GLUCOSE_ARRAY_LIST = new ArrayList<Glucose>();
    public final static ArrayList<Insuline> INSULINE_ARRAY_LIST = new ArrayList<Insuline>();
    public final static Bluetooth bluetoothDevice = new Bluetooth();






   /* public static boolean VerifyBTModuleConnection() {

        if(GLOBAL.bluetoothDevice.getMAC() != null)
        {
            GLOBAL.btconection.VerifyConnection();

            if(GLOBAL.btconection.isSuccessfull)
                return true;
            else
                return false;
        }
        else
            return false;
    }*/
}
