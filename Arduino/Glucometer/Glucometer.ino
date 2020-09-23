#include <SPI.h>
#include <nRF24L01.h>
#include <RF24.h>
#include <Wire.h>
#include "RTClib.h"

#define CE_PIN 9
#define CSN_PIN 10

//Variable con la dirección del canal por donde se va a transmitir
const byte address[6] = "00001";
RF24 radio(CE_PIN, CSN_PIN);
float datos[2];

int strip_detect = 2;
int current = 0;

// measurement starts if transimpedance amp output voltage > threshold
float threshold = 2.7; 

RTC_Millis RTC;

void setup() {
  
  Serial.begin(9600);
  // set the RTC to the date & time this sketch was compiled
  RTC.begin(DateTime(__DATE__, __TIME__));
  pinMode(strip_detect, INPUT);
  pinMode(6, OUTPUT);

  radio.begin();
  radio.openWritingPipe(address);
  radio.setPALevel(RF24_PA_MIN);
  radio.stopListening();

}

void loop() {
  while(1) {
    if(digitalRead(strip_detect) == 1) break;
    digitalWrite(6,HIGH);
  }
  digitalWrite(6,LOW);
  Serial.println("APPLY BLOOD");
  float current_voltage_0;
  float current_voltage_1;
  while(1) {
    current_voltage_0 = analogRead(0) * (5.0 / 1023.0);
    current_voltage_1 = analogRead(1) * (5.0 / 1023.0);
    Serial.print("Current 0: ");
    Serial.println(current_voltage_0);
    Serial.print("Current 1: ");
    Serial.println(current_voltage_1);
    
    if(current_voltage_0 > threshold) break;
  }
  // count down timer
  for(int i = 5; i > 0; i--) {
    delay(1000);
    Serial.print(i);
    if(i > 1) Serial.print(", ");
    else Serial.println("");

    current_voltage_0 = analogRead(0) * (5.0 / 1023.0);
    current_voltage_1 = analogRead(1) * (5.0 / 1023.0);
    Serial.print("Current 0: ");
    Serial.print(current_voltage_0);
    Serial.print(" seg: ");
    Serial.println(i);
    Serial.print("Current 1: ");
    Serial.print(current_voltage_1);
    Serial.print(" seg: ");
    Serial.println(i);
  }
  
  // compute concentration
  current_voltage_0 = analogRead(0) * (5.0 / 1023.0);
  current_voltage_1 = analogRead(1) * (5.0 / 1023.0);
  Serial.print("LAST Current 0: ");
    Serial.println(current_voltage_0);
    Serial.print("LAST Current 1: ");
    Serial.println(current_voltage_1);
  
  
  float concentration0 = 495.6 * current_voltage_0 - 1275.5;
  Serial.print(concentration0);
  Serial.println(" mg/dL 0");
  
  datos[0] = current_voltage_0;
  datos[1] = current_voltage_1;
  bool ok = false;
 do{
   ok = radio.write(datos, sizeof(datos));
  delay(1000);
  if(ok){
    Serial.println("DATO ENVIADO");
  }else{
    Serial.println("ERROR AL ENVIAR EL DATO");
  }
 }while(!ok);
  
  display_time();
  while(1) {
    if(digitalRead(strip_detect) == 0) break;
  }
  delay(1000); //debounce
}

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
