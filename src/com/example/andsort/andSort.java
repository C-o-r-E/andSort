package com.example.andsort;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import rokon.Rokon;
import rokon.Sprite;
//import rokon.Texture;
import java.util.Random;

public class andSort extends Activity 
{
	public static final int SLEEPTIME = 100;
	public static final int NUMPTS = 120;
    Rokon rokon;
    Random rand;
    
    static Sprite[] points = new Sprite[NUMPTS];
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        rokon = Rokon.createEngine(this);
        rokon.setFullscreen();
        rokon.fixPortrait();
        rokon.init();
        
        rand = new Random();
        
        
        
        Thread gameThread = new Thread(new GameThread());
        gameThread.start();
    }
    
    public class GameThread implements Runnable {
    	public void run() 
    	{
    		while(true)
    		{
    			for(int i=0; i<NUMPTS; i++)
    	        {
    	        	points[i] = new Sprite(rand.nextInt(320), i*4, 2,2);
    	        	rokon.addSprite(points[i]);
    	        }
    			
	        	Log.v("sort", "Start");
	    		try {
					Thread.sleep(SLEEPTIME);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				Log.v("sort", "Update");
				points = mergeSort(points);
				Log.v("sort", "done");
				
				try {
					Thread.sleep(20*SLEEPTIME);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				for(int i=0; i<NUMPTS; i++)
    	        {
    	        	rokon.removeSprite(points[i]);
    	        	points[i] = null;
    	        }
				
				try {
					Thread.sleep(10*SLEEPTIME);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			
    		}
    		
    	}
    }
    
    public static void update(Sprite[] list)
    {
    	for(int i=0; i<list.length; i++)
    	{
    		list[i].setY(i*4);
    	}
    }
    
   /////////////////////////////////////////////////////

    public static Sprite[] append(Sprite[] arr, Sprite apendee)
    {
    	int len = 0;
    	
    	if(arr != null)
    	{
    		len = arr.length;
    	}
    	Sprite[] result = new Sprite[len + 1];
    	for(int i=0; i<len; i++)
    	{
    		result[i] = arr[i];
    	}
    	result[len] = apendee;
    	return result;
    }
    
    public static Sprite[] merge(Sprite[] left, Sprite[] right)
    {
    	Sprite[] result = null;
    	int numLeft = left.length;
    	int numRight = right.length;

    	for(int i=0, j=0;;)
    	{
    		if(left[i].getX() < right[j].getX())
    		{
    			result = append(result, left[i]);
    			i++;
    		}
    		else
    		{
    			result = append(result, right[j]);
    			j++;
    		}
    		
    		if(i>=numLeft)
    		{
    			for(int k=j; k<numRight; k++)
    			{
    				result = append(result, right[k]);
    			}
    			break;
    		}
    		else if(j>=numRight)
    		{
    			for(int k=i; k<numLeft; k++)
    			{
    				result = append(result, left[k]);
    			}
    			break;	
    		}
    	}
    	return result;
    }
    
    public static Sprite[] mergeSort(Sprite[] list)
    {
    	
    	Log.v("sort", "mergeSort() called, Sleeping " + SLEEPTIME + "ms");
		try {
			Thread.sleep(SLEEPTIME);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	int listSize = 0;
    	
    	if(list != null)
    		listSize = list.length;
    	
    	Sprite[] result = new Sprite[listSize];
    	Sprite[] left = null, right = null;
    	
    	if(listSize <= 1)
    		return list;
    	
    	for(int i=0; i<listSize/2; i++)
    	{
    		left = append(left, list[i]);
    	}
    	for(int i=listSize/2; i<listSize; i++)
    	{
    		right = append(right, list[i]);
    	}
    	
    	left = mergeSort(left);
    	right = mergeSort(right);
    	
    	if(left[left.length - 1].getX() > right[0].getX())
    		result = merge(left, right);
    	else
    		result = merge(right, left);
    	
    	update(result);
    	
    	return result;	
    }
}