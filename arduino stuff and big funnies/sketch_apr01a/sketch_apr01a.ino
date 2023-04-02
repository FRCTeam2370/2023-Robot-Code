

#include <Wire.h>
int ic2id = 0;
int howmanybyteswewant = 0;
int datafromcolorsensor[7];
int cubergb[] = {0,0,0};
int cubeir = 0;
int conergb[] = {0,0,0};
int coneir = 0;
int conepin = 0;
int cubepin = 0;
int nopin = 0;
void setup() {
  // put your setup code here, to run once:
Wire.begin();
Wire.begin(9600);
pinMode(conepin, OUTPUT);
pinMode(cubepin, OUTPUT);
pinMode(nopin, OUTPUT);
}

void loop() {
  Wire.requestFrom(howmanybyteswewant, ic2id);

  while(Wire.available()){
    for(int i = 0; i<= howmanybyteswewant; i++){
      datafromcolorsensor[i] = Wire.read();
    }
  if(cubergb[0] == datafromcolorsensor[0] && cubergb[1] == datafromcolorsensor[1] &&cubergb[2] == datafromcolorsensor[2] && cubeir ==datafromcolorsensor[3]){
    digitalWrite(cubepin, HIGH);
    digitalWrite(conepin, LOW);
    digitalWrite(nopin, LOW);
  }
  else if(conergb[0] == datafromcolorsensor[0] && conergb[1] == datafromcolorsensor[1] && conergb[2] == datafromcolorsensor[2] && coneir == datafromcolorsensor[4]){
        digitalWrite(cubepin, LOW);
    digitalWrite(conepin, HIGH);
    digitalWrite(nopin, LOW);    
  }
  else{
        digitalWrite(cubepin, LOW);
    digitalWrite(conepin, LOW);
    digitalWrite(nopin, HIGH);
  }
  }
  // put your main code here, to run repeatedly:

}
