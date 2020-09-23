package com.example.osid.GLOBAL;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Handler;
import android.widget.Toast;


import com.example.osid.GlucometerActivity;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;


public class BluetoothVerifyConnection {

    public boolean isSuccessfull = false;
    public Handler bluetoothIn;

    public StringBuilder DataStringIN = new StringBuilder();
    public final int handlerState = 0;
    public BluetoothAdapter bluetoothAdapter= null;
    public BluetoothSocket bluetoothSocket= null;
    public ConnectedThread MyConexionBT;
    public static final UUID BTMODULEUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    String receivedText;


    public void VerifyConnection()
    {
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        BluetoothDevice device = bluetoothAdapter.getRemoteDevice(GLOBAL.bluetoothDevice.getMAC());
        try {
            bluetoothSocket = createBluetoothSocket(device);
        }
        catch (IOException e)
        {}

        try{
            bluetoothSocket.connect();
        }
        catch (IOException e)
        {
            try{
                bluetoothSocket.close();
                isSuccessfull = false;
                return;
               // isSuccessfull = false;
            }
            catch(IOException e2)
            {}
        }

        MyConexionBT = new ConnectedThread(bluetoothSocket);
        MyConexionBT.start();
    }

    private BluetoothSocket createBluetoothSocket(BluetoothDevice device) throws  IOException{
        isSuccessfull = true;
        return device.createRfcommSocketToServiceRecord(BTMODULEUUID);
    }


    //Lo que hace connected thread
    public class ConnectedThread extends Thread
    {
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;
        public String dato;
        public ConnectedThread(BluetoothSocket socket)
        {
            InputStream tmpIn = null;
            OutputStream tmpOut = null;
            try
            {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) { }
            mmInStream = tmpIn;
            mmOutStream = tmpOut;

        }

        public void run()
        {
            byte[] buffer = new byte[256];
            int bytes;
            // Se mantiene en modo escucha para determinar el ingreso de datos
            while (true) {
                try {
                    bytes = mmInStream.read(buffer);
                     String readMessage = new String(buffer, 0, bytes);
                    // Envia los datos obtenidos hacia el evento via handler
                    bluetoothIn.obtainMessage(handlerState, bytes, -1, readMessage).sendToTarget();
                } catch (IOException e) {
                    //break;
                }
            }

        }
        //Envio de trama
        public void write(String input)
        {
            try {
                mmOutStream.write(input.getBytes());
            }
            catch (IOException e)
            {

            }
        }


        /*public String ReadData()
        {
            bluetoothIn = new Handler() {
                public void handleMessage(android.os.Message msg) {
                    if (msg.what == handlerState) {
                        String readMessage = (String) msg.obj;
                        DataStringIN.append(readMessage);

                        int endOfLineIndex = DataStringIN.indexOf("#");

                        if (endOfLineIndex > 0) {
                            receivedText = DataStringIN.substring(0, endOfLineIndex);
                            DataStringIN.delete(0, DataStringIN.length());
                        }
                    }
                }
            };
            GlucometerActivity.ReceiveData(receivedText);
            return receivedText;
        }*/
    }

    public void TerminarBluetooth()
    {
        try {
            bluetoothSocket.close();
        }
        catch (IOException e2)
        {}
    }


    /*public String ReadData()
    {
        bluetoothIn = new Handler() {
            public void handleMessage(android.os.Message msg) {
                if (msg.what == handlerState) {
                    String readMessage = (String) msg.obj;
                    DataStringIN.append(readMessage);

                    int endOfLineIndex = DataStringIN.indexOf("#");

                    if (endOfLineIndex > 0) {
                        receivedText = DataStringIN.substring(0, endOfLineIndex);
                        DataStringIN.delete(0, DataStringIN.length());
                    }
                }
            }
        };
        return receivedText;
<<<<<<< HEAD
=======
    }


    /*public String WriteData()
    {
        try {
            mmOutStream.write(input.getBytes());
        }
        catch (IOException e)
        {
            //si no es posible enviar datos se cierra la conexión
            Toast.makeText(getBaseContext(), "La Conexión fallo", Toast.LENGTH_LONG).show();
            finish();
        }
>>>>>>> 24c5b5238eb8181a4267d6eb97efade2636723b7
    }*/
}
