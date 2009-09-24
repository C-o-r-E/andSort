package com.example.andsort;

import android.app.Activity;
import android.os.Bundle;

import rokon.Rokon;
import rokon.Sprite;
//import rokon.Texture;
import java.util.Random;

public class andSort extends Activity 
{
	public static final int NUMPTS = 90;
    Rokon rokon;
    Random rand;
    
    Sprite[] points = new Sprite[NUMPTS];
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        rokon = Rokon.createEngine(this);
        rokon.setFullscreen();
        rokon.fixPortrait();
        rokon.init();
        
        rand = new Random();
        
        for(int i=0; i<NUMPTS; i++)
        {
        	points[i] = new Sprite(rand.nextInt(320), i*4, 2,2);
        	rokon.addSprite(points[i]);
        }
        
        for(int i=0; i<NUMPTS; i++)
        {
        	points[i].setX(10);
        }
        
        Thread gameThread = new Thread(new GameThread());
        gameThread.start();
    }
    
    public class GameThread implements Runnable {
    	public void run() {

    	}
    }
}