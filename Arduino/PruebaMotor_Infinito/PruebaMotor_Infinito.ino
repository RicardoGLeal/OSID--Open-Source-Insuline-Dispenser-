#include <Stepper.h>
double counter = 0;
bool puedecontinuar = true;
char incomingByte;
void setup() {
  pinMode(3, OUTPUT);
  pinMode(4, OUTPUT);
  pinMode(5, OUTPUT);
  pinMode(6, OUTPUT);
  Serial.begin(9600);
  // put your setup code here, to run once:

}

void loop() {

  if (Serial.available() > 0) {
    incomingByte = Serial.read();
    puedecontinuar = false;
  }
  else{
    if(puedecontinuar){
    // put your main code here, to run repeatedly:
    digitalWrite(3, HIGH);
    digitalWrite(4, LOW);
    digitalWrite(5, LOW);
    digitalWrite(6, HIGH);
    delay(10);
    counter++;
    Serial.print(counter);
    Serial.println();
  
    digitalWrite(3, LOW);
    digitalWrite(4, LOW);
    digitalWrite(5, LOW);
    digitalWrite(6, HIGH);
    delay(10);
    counter++;
    Serial.print(counter);
    Serial.println();
  
    digitalWrite(3, LOW);
    digitalWrite(4, LOW);
    digitalWrite(5, HIGH);
    digitalWrite(6, HIGH);
    delay(10);
    counter++;
    Serial.print(counter);
    Serial.println();
  
    digitalWrite(3, LOW);
    digitalWrite(4, LOW);
    digitalWrite(5, HIGH);
    digitalWrite(6, LOW);
    delay(10);
    counter++;
    Serial.print(counter);
    Serial.println();
  
    digitalWrite(3, LOW);
    digitalWrite(4, HIGH);
    digitalWrite(5, HIGH);
    digitalWrite(6, LOW);
    delay(10);
    counter++;
    Serial.print(counter);
    Serial.println();
  
    digitalWrite(3, LOW);
    digitalWrite(4, HIGH);
    digitalWrite(5, LOW);
    digitalWrite(6, LOW);
    delay(10);
    counter++;
    Serial.print(counter);
    Serial.println();
  
    digitalWrite(3, HIGH);
    digitalWrite(4, HIGH);
    digitalWrite(5, LOW);
    digitalWrite(6, LOW);
    delay(10);
    counter++;
    Serial.print(counter);
    Serial.println();
  
    digitalWrite(3, HIGH);
    digitalWrite(4, LOW);
    digitalWrite(5, LOW);
    digitalWrite(6, LOW);
    delay(10);
    counter++;
    Serial.print(counter);
    Serial.println();
    }
  }
}
