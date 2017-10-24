
void setup() {
Serial.begin(250000);
}
void loop() {
    int i,val[6];
    int b=0;
    for(i=0;i<7;i++){
      if(i==1)continue;
      val[i]= digitalRead(i);
      if(val[i]==0&&b==0){
       if(i==0)
      Serial.println(i+1);
      else
      Serial.println(i);
      b=1; 
      }
    }
   // if(b==0)
   // Serial.println(0);
      
    b=0;
}
