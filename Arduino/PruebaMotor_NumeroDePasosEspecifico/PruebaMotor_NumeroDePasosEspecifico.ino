void setup() {
  // put your setup code here, to run once:
 pinMode(3, OUTPUT);
 pinMode(4, OUTPUT);
 pinMode(5, OUTPUT);
 pinMode(6, OUTPUT);

 Pasos(4400);
}

void loop() {

}

void Pasos(int number)
{
  number = number/8;
  for(int i=0; i<=number; i++)
  {
     digitalWrite(3,HIGH);
     digitalWrite(4,LOW);
     digitalWrite(5,LOW);
     digitalWrite(6,HIGH);
     delay(3);
     
     digitalWrite(3,LOW);
     digitalWrite(4,LOW);
     digitalWrite(5,LOW);
     digitalWrite(6,HIGH);
     delay(3);
    
     digitalWrite(3,LOW);
     digitalWrite(4,LOW);
     digitalWrite(5,HIGH);
     digitalWrite(6, HIGH);
     delay(3);
    
      digitalWrite(3,LOW);
     digitalWrite(4,LOW);
     digitalWrite(5,HIGH);
     digitalWrite(6, LOW);
     delay(3);
    
     digitalWrite(3,LOW);
     digitalWrite(4,HIGH);
     digitalWrite(5,HIGH);
     digitalWrite(6, LOW);
     delay(3);
    
     digitalWrite(3,LOW);
     digitalWrite(4,HIGH);
     digitalWrite(5,LOW);
     digitalWrite(6, LOW);
     delay(3);
    
     digitalWrite(3,HIGH);
     digitalWrite(4,HIGH);
     digitalWrite(5,LOW);
     digitalWrite(6, LOW);
     delay(3);
     
     digitalWrite(3,HIGH);
     digitalWrite(4,LOW);
     digitalWrite(5,LOW);
     digitalWrite(6,LOW);
     delay(3);
  }
}
