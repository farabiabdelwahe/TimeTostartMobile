/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class SplashScreen extends Canvas implements Runnable {
Image img[]=new Image[3];

int imgIndex;
public SplashScreen() {
try {
img[0]=Image.createImage("/GUI/splash1.jpg");
img[1]=Image.createImage("/GUI/splash2.jpg");
img[2]=Image.createImage("/GUI/splash3.jpg");


}catch(Exception e){}

Thread th=new Thread(this);
th.start();

}

//Display GIF image
public void paint(Graphics g) {
g.drawImage(img[imgIndex],0,0,g.TOP|g.LEFT);
}

//Handling keyEvents
protected void keyPressed(int keyCode) {

}

public void run() {
   
while(true) {
imgIndex++;
imgIndex%=3;

try {
Thread.sleep(1000);
}catch(Exception e){}
}
}


}

