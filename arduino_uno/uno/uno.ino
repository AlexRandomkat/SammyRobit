#include <Wire.h>
int addr = 1;
int digitalPin = 0;

void setup() {
  // put your setup code here, to run once:
  Wire.begin(addr);
  Wire.onRequest(onData);
  pinMode(LED_BUILTIN, OUTPUT);
}

void onData(int bytes){
  digitalPin = Wire.read();
}

void loop() {
  // put your main code here, to run repeatedly:
  if(digitalPin != 0 ){
    if(digitalPin == 1){
      digitalWrite(LED_BUILTIN, HIGH);
    }
  }else{
     digitalWrite(LED_BUILTIN, LOW);
  }
}
