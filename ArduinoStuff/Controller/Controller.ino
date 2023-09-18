/*
  Blink
  Turns on an LED on for one second, then off for one second, repeatedly.

  Most Arduinos have an on-board LED you can control. On the UNO, MEGA and ZERO
  it is attached to digital pin 13, on MKR1000 on pin 6. LED_BUILTIN is set to
  the correct LED pin independent of which board is used.
  If you want to know what pin the on-board LED is connected to on your Arduino model, check
  the Technical Specs of your board  at https://www.arduino.cc/en/Main/Products

  This example code is in the public domain.

  modified 8 May 2014
  by Scott Fitzgerald

  modified 2 Sep 2016
  by Arturo Guadalupi

  modified 8 Sep 2016
  by Colby Newman
*/
uint8_t allianceColor = 0;
uint8_t matchStatus = 0;
bool oldHasGear=false;
long gotGearTime=0;
int hasGearCount=0;
long lastHasGearTime=0;
bool hasGear = false;
bool side=false;
bool secondFlash=false;
bool hazardOn=false;
bool flashState = false;
bool flashStateRgb=false;
bool gearWant = false;
bool wombo = false;
bool kittDir = true;
bool rft = false;
uint8_t strip[3];
uint8_t kittPos = 0;
bool modeArray[7];
bool redBlue=false;
bool hasGearOn=true;
long t = 0;
long oldTimeRgb = 0;
long lastHazardTime=0;
long oldTimeAddr = 0;
long oldTimeKitt = 0;
uint8_t gHue = 0;
byte mode = 0;
String inputString = "";         // a string to hold incoming data
boolean stringComplete = false;
String currentString = "";
#include <FastLED.h>
CRGBPalette16 gPal;
//Number of LEDS and each data pin used (has to be defined, check API for format
#define NUM_LEDS 64
#define DATA_PIN 2
//Array initialized set to the number of LED's
CRGB leds[NUM_LEDS];
// the setup function runs once when you press reset or power the board
void setup() {
  pinMode(4, OUTPUT); //blue
  pinMode(5, OUTPUT); //red
  pinMode(6, OUTPUT); //green
  pinMode(2, OUTPUT);//addr

  //Does stuff with FastLED, insert
  FastLED.addLeds<WS2812B, 2, GRB>(leds, NUM_LEDS);
  FastLED.setBrightness(30);

  pinMode(13, OUTPUT);
  // initialize serial:
  Serial.begin(9600);
  // reserve 200 bytes for the inputString:
  inputString.reserve(200);
  digitalWrite(13, LOW);
  for(int i=8;i<NUM_LEDS;i++){
    setPixel(i,0,0,0x50);
  }
  showStrip();
}

#define COOLING  75
#define SPARKING 120

byte colors[3][3] = { {0xff, 0, 0},
  {0xff, 0xff, 0xff},
  {0   , 0   , 0xff}
};

bool gReverseDirection = false;

void loop() {
  updateMode();
  if(allianceColor==0&&matchStatus==0){
    rainbowAddr(20);
    rainbowRgb(20);
  }else if((allianceColor==0&&matchStatus==1)||(allianceColor>0&&matchStatus==0)){
    flashAddr(0,0xff,0,2,50);
    rainbowRgb(20);
  }else if(matchStatus==1){
    setAll(0,0xff,0);
    heartbeatAllianceColorRgb(10);
  }else if(matchStatus==2){
    autonFlash();
    setStrip(modeArray[1],0,modeArray[0]);
  }else if(matchStatus==3){
    if(wombo){
      //TODO wombo
    }else if(hasGear){
      setAll(0,0xff,0);
      if(!oldHasGear){
        if(millis()-100>lastHasGearTime){
          if(hasGearOn){
            setStrip(0,0,0);
            hasGearOn=false;
          }else{
            setStrip(modeArray[1],0,modeArray[0]);
            hasGearOn=true;
            hasGearCount++;
          }
          if(hasGearCount>4){
            oldHasGear=true;
          }
          lastHasGearTime=millis();
        }
      }
    }else if(gearWant){
      kittScanner();
      setStrip(modeArray[1],0,modeArray[0]);
    }else{
      hazardFlash();
      setStrip(modeArray[1],0,modeArray[0]);
    }
    if(rft){
      flashRgb(modeArray[1],0,modeArray[0],2,50);
    }
  }
  if(allianceColor==3){
    alternateRedBlue(100);
  }
}

void autonFlash() {
  flashAddr(0xff, 0xdf, 0, 1000, 75);
}
void kittScanner() {
  kitt(50, 30);
}
void heartbeatAllianceColorRgb(int period) {
  if (allianceColor == 1) {
    fadingRgb(0xff, 0, 0, period);
  }
  else {
    fadingRgb(0, 0, 0xff, period);
  }

}
void alternateRedBlue(int period){
  long tOn=period/ 100;
  if ((millis() % period) < tOn && redBlue == false) {
    setStrip(0xff, 0, 0);
    redBlue = true;
  } else if ((millis() % period) > tOn && redBlue == true) {
    setStrip(0, 0, 0xff);
    redBlue = false;
  }
}
void hazardFlash() {
  if (millis()-50>lastHazardTime) {
    lastHazardTime=millis();
    if (side == false) {
      if (secondFlash == false && hazardOn == false) {
        for (int i = 0; i < NUM_LEDS / 2; i++) {
          setPixel(i, 0xff, 0x88, 0);
          hazardOn = true;
        }
      } else if (secondFlash == false && hazardOn == true) {
        for (int i = 0; i < NUM_LEDS; i++) {
          setPixel(i, 0, 0, 0);
          hazardOn = false;
          secondFlash = true;
        }
      } else if (hazardOn == false) {
        for (int i = 0; i < NUM_LEDS / 2; i++) {
          setPixel(i, 0xff, 0x88, 0);
          hazardOn = true;
        }
      } else if (hazardOn == true) {
        for (int i = 0; i < NUM_LEDS; i++) {
          setPixel(i, 0, 0, 0);
          hazardOn = false;
          secondFlash = false;
          side = true;
        }
       lastHazardTime+=200;
      }
    } else {
      if (secondFlash == false && hazardOn == false) {
        for (int i = NUM_LEDS / 2; i < NUM_LEDS; i++) {
          setPixel(i, 0xff, 0x88, 0);
          hazardOn = true;
        }
      } else if (secondFlash == false && hazardOn == true) {
        for (int i = 0; i < NUM_LEDS; i++) {
          setPixel(i, 0, 0, 0);
          hazardOn = false;
          secondFlash = true;
        }
      } else if (hazardOn == false) {
        for (int i = NUM_LEDS / 2; i < NUM_LEDS; i++) {
          setPixel(i, 0xff, 0x88, 0);
          hazardOn = true;
        }
      } else if (hazardOn == true) {
        for (int i = 0; i < NUM_LEDS; i++) {
          setPixel(i, 0, 0, 0);
          hazardOn = false;
          secondFlash = false;
          side = false;
        }
        lastHazardTime+=200;
      }
    }
    
    showStrip();
  }
}
void flashAddr(byte r, byte g, byte b, long period, long dutyCycle) {
  long tOn = (dutyCycle * period) / 100;
  if ((millis() % period) < tOn && flashState == false) {
    setAll(r, g, b);
    flashState = true;
  } else if ((millis() % period) > tOn && flashState == true) {
    setAll(0, 0, 0);
    flashState = false;
  }
}
void flashRgb(byte r, byte g, byte b, long period, long dutyCycle) {
  long tOn = (dutyCycle * period) / 100;
  if ((millis() % period) < tOn && flashStateRgb == false) {
    setStrip(r, g, b);
    flashStateRgb = true;
  } else if ((millis() % period) > tOn && flashStateRgb == true) {
    setStrip(0, 0, 0);
    flashStateRgb = false;
  }
}
void rainbowAddr(int period) {
  runningLights(0xff, 0xff, 0xff, period);
}

void rainbowRgb(int period) {
  fadingRgb(0xff, 0xff, 0xff, period);
}

void updateMode() {
  while (Serial.available() > 1) {
        mode = Serial.read();
    parseMode();
  }
}

void parseMode() {
  for (int i = 0; i < 8; i++) {
    modeArray[i] = bitRead(mode, i);
     Serial.print(modeArray[i]);
  }
  Serial.print(' ');
  Serial.print(calcPair(modeArray[2],modeArray[3]));
  Serial.println();
  allianceColor = calcPair(modeArray[0],modeArray[1]);
  matchStatus = calcPair(modeArray[2],modeArray[3]);
  hasGear = modeArray[4];
  gearWant = modeArray[5];
  wombo = modeArray[6];
  rft = modeArray[7];
}
uint8_t calcPair(bool first, bool second) {
  if (first & second) {
    return 3;
  } else if (first & !second) {
    return 2;
  } else if (second & !first) {
    return 1;
  } else {
    return 0;
  }
}
//Similar to runningLights
void fadingRgb(uint8_t red, uint8_t green, uint8_t blue, int Period) {
  if ((millis() - oldTimeRgb) > Period) {
    uint8_t rgbx = (millis() / Period) % 255;
    setStrip((sin8(rgbx)*red) >> 8, (sin8(rgbx + 64)*green) >> 8, (sin8(rgbx + 128)*blue) >> 8);
    oldTimeRgb = millis();
  }
}

void runningLights(byte red, byte green, byte blue, int Period) {
  if ((millis() - oldTimeAddr) > Period) {
    for (int i = 0; i < NUM_LEDS; i++) {
      uint8_t addrx = (millis() / Period + 2 * i) % 255;
      setPixel(i, (sin8(addrx)*red) >> 8, (sin8(addrx + 64)*green) >> 8, (sin8(addrx + 128)*blue) >> 8);
    }
    oldTimeAddr = millis();
    showStrip();
  }
}
void kitt(byte width, byte period) {
  if ((millis() - oldTimeKitt) > period) {
    uint8_t lowLimit = max((int)kittPos - ((int)width / 2), 0);
    uint8_t highLimit = min((int)kittPos + ((int)width / 2), NUM_LEDS);
    for (uint8_t i = 0; i < NUM_LEDS; i++) {
      if (lowLimit <= i && i < highLimit) {
        int difference = abs((int)i - (int)kittPos);
        setPixel(i, max(0xff - 2 * difference * difference / 2, 0), 0, 0);
      } else {
        setPixel(i, 0, 0, 0);
      }

    }
    oldTimeKitt = millis();
    showStrip();
    if (kittPos == 64) {
      kittDir = false;
    } else if (kittPos == 0) {
      kittDir = true;
    }
    if (kittDir) {
      kittPos++;
    } else {
      kittPos--;
    }
  }
}
void setStrip(uint8_t r, uint8_t g, uint8_t b) {
  analogWrite(5, r);
  analogWrite(3, g);
  analogWrite(4, b);
}
void showStrip() {
#ifdef ADAFRUIT_NEOPIXEL_H
  // NeoPixel
  strip.show();
#endif
#ifndef ADAFRUIT_NEOPIXEL_H

  // FastLED
  FastLED.show();
#endif
}

void setPixel(int Pixel, byte red, byte green, byte blue) {
  // FastLED
  leds[Pixel].r = red;
  leds[Pixel].g = green;
  leds[Pixel].b = blue;
}

void setAll(byte red, byte green, byte blue) {
  for (int i = 0; i < NUM_LEDS; i++ ) {
    setPixel(i, red, green, blue);
  }
  showStrip();
}

