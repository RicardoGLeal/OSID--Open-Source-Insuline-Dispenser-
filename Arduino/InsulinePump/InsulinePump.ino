#include <SPI.h>
#include <nRF24L01.h>
#include <RF24.h>
#include "RTClib.h"

#include <SoftwareSerial.h> 
SoftwareSerial ModBluetooth(0, 1); // RX | TX 
#include <String.h>

//Declaremos los pines CE y el CSN
//#define CE_PIN 7
//#define CSN_PIN 8
#define CE_PIN 9
#define CSN_PIN 10

//---------------INYECCION-----------------------//
unsigned long startMillis;
unsigned long currentMillis;
const unsigned long SECOND = 1000;
const unsigned long HOUR = 10*SECOND;//3600*SECOND//;
float unidades = 5;
double counter = 0;
RTC_Millis RTC;

//-----------------------------------------------//
const byte address[6] = "00001";

RF24 radio(CE_PIN, CSN_PIN);
float datos[2];
bool sendData;


void setup() {
  Serial.begin(9600);
  ModBluetooth.begin(9600);
  ModBluetooth.println("MODULO CONECTADO");  
  ModBluetooth.print("#");  
  
  startMillis = millis();

  //------GLUCOMETRO-----------//
  radio.begin();
  radio.openReadingPipe(0, address);
  radio.setPALevel(RF24_PA_MIN);
  radio.startListening();
  sendData = false;
  datos[0] = 0;
  datos[1] = 0;
  
  //---------------------------//
  pinMode(3, OUTPUT);
  pinMode(4, OUTPUT);
  pinMode(5, OUTPUT);
  pinMode(6, OUTPUT);

}

void loop() {
  ReadDataFromApp();
  uint8_t numero_canal;
  if(radio.available()){

    radio.read(datos,sizeof(datos));

    /*Serial.print("Dato0= ");
    Serial.print(datos[0]);
    Serial.println(" V");
    Serial.print("Dato1= ");
    Serial.print(datos[1]);
    Serial.println(" V");*/

   // delay(100);
    float f = datos[1];
    String fstring = String(f)+"#";
    writeString(fstring);
    delay(500);

  }

  /*if(!sendData && datos[1] != 0){
    float f = datos[1];
    String fstring = String(f);
    writeString(fstring);
    sendData = true;  
  }*/


  /*
  currentMillis = millis();

  //-----------------------------------------------------------------------//
    if (currentMillis - startMillis >= HOUR)
    {
      display_time();//Se muestra la fecha y hora del inicio de la inyeccion
      if(unidades!=0)
      {
        Serial.print("Inyeccion iniciada: ");
        Serial.println();
        display_time();
        InyectarInsulina(unidades); //Inyeccion
        Serial.print("Inyeccion terminada: ");
        display_time();
      }
      startMillis = millis(); //Reinicia el contador de tiempo
    }
    //------RECIBE EL NUMERO DE UNIDADES A INYECTAR DESDE LA APP----------//
    
    //--------------------------------------------------------------------//
*/
}

void ReadDataFromApp()
{
    if (ModBluetooth.available())  //Si ModBluetooth recebe algun dato...
    { 
        String tempString = "", newString = "";
        tempString = ModBluetooth.readString();  //tempString guarda el dato.
        Serial.println(tempString);
        Serial.println("aa");
        if(tempString[0] == 'B')//Basal 
        {
          for(int i = 1; i < tempString.length(); i++)
            newString+= tempString[i];
          unidades = newString.toFloat();
        }

        
        else if (tempString[0] == 'I')//Insulina
        {
          for(int i = 1; i < tempString.length(); i++)
          newString+= tempString[i];
          InyectarInsulina(newString.toFloat());
            //ModBluetooth.begin(9600);
            //Serial.flush();
            //ModBluetooth.flush();
        }
    } 
}



void InyectarInsulina(float unidades)
{
  int pasos = unidades * 220;
  int vueltas = pasos / 8;

  for (int i = 0; i <= vueltas; i++)
  {
    digitalWrite(3, HIGH);
    digitalWrite(4, LOW);
    digitalWrite(5, LOW);
    digitalWrite(6, HIGH);
    delay(3);

    digitalWrite(3, LOW);
    digitalWrite(4, LOW);
    digitalWrite(5, LOW);
    digitalWrite(6, HIGH);
    delay(3);

    digitalWrite(3, LOW);
    digitalWrite(4, LOW);
    digitalWrite(5, HIGH);
    digitalWrite(6, HIGH);
    delay(3);

    digitalWrite(3, LOW);
    digitalWrite(4, LOW);
    digitalWrite(5, HIGH);
    digitalWrite(6, LOW);
    delay(3);

    digitalWrite(3, LOW);
    digitalWrite(4, HIGH);
    digitalWrite(5, HIGH);
    digitalWrite(6, LOW);
    delay(3);

    digitalWrite(3, LOW);
    digitalWrite(4, HIGH);
    digitalWrite(5, LOW);
    digitalWrite(6, LOW);
    delay(3);

    digitalWrite(3, HIGH);
    digitalWrite(4, HIGH);
    digitalWrite(5, LOW);
    digitalWrite(6, LOW);
    delay(3);

    digitalWrite(3, HIGH);
    digitalWrite(4, LOW);
    digitalWrite(5, LOW);
    digitalWrite(6, LOW);
    delay(3);

    counter += 1;
    Serial.print(counter);
    Serial.println();
  }
}



void writeString(String stringData) { // Used to serially push out a String with Serial.write()
  Serial.flush();
  for (int i = 0; i < stringData.length(); i++)
  {
    Serial.write(stringData[i]);   // Push each char 1 by 1 on each loop pass
  }



  
}// end writeString






void display_time() {
  DateTime now = RTC.now();
  Serial.print(now.year(), DEC);
  Serial.print('-');
  Serial.print(now.month(), DEC);
  Serial.print('-');
  Serial.print(now.day(), DEC);
  Serial.print(' ');
  Serial.print(now.hour(), DEC);
  Serial.print(':');
  Serial.print(now.minute(), DEC);
  Serial.print(':');
  Serial.print(now.second(), DEC);
  Serial.println();
}
