package model;

import java.awt.Color;
import java.util.Random;

import ui.Main_Agario;

public class Avatar {

	 public static final int INIT_MASS = 150;
	    public static final int MAX_RANGE_COLOR = 256;
	    private static final Color COLOR_VIRUS = Color.GREEN;
	    
	    private double centerX, centerY;
//	    private int mass;
	    
	    private Color color;
	    private static Random rand = new Random();
	    private boolean virus;
	    private int id; //PARA CUANDO SEAN VIRUS
	    
//	    private int timeCreation;
//	    private boolean principal;
	    
	    public Avatar(){
	        this.centerX = rand.nextInt(3*Main_Agario.WINDOW_WIDTH/4)+ Main_Agario.WINDOW_WIDTH/8;
	        this.centerY = rand.nextInt(3*Main_Agario.WINDOW_HEIGHT/4)+ Main_Agario.WINDOW_HEIGHT/8;
	        int r = rand.nextInt(MAX_RANGE_COLOR);
	        int g = rand.nextInt(MAX_RANGE_COLOR);
	        int b = rand.nextInt(MAX_RANGE_COLOR);
	        this.color = new Color(r,g,b);
//	        this.mass = INIT_MASS;
//	        this.timeCreation = 0;
	    }
	    
	    public Avatar(int xMax, int yMax){
	        this.centerX = rand.nextInt(3*xMax/4)+ xMax/8;
	        this.centerY = rand.nextInt(3*yMax/4)+ yMax/8;
	        int r = rand.nextInt(MAX_RANGE_COLOR);
	        int g = rand.nextInt(MAX_RANGE_COLOR);
	        int b = rand.nextInt(MAX_RANGE_COLOR);
	        this.color = new Color(r,g,b);
//	        this.mass = INIT_MASS;
//	        this.timeCreation = 0;
	    }
	    
	    public Avatar(int xMax, int yMax,boolean virus){
	        //ONLY FOR VIRUS
	        this.centerX = rand.nextInt(xMax-4)+4;
	        this.centerY = rand.nextInt(yMax-4)+4;
	        int r = rand.nextInt(MAX_RANGE_COLOR);
	        int g = rand.nextInt(MAX_RANGE_COLOR);
	        int b = rand.nextInt(MAX_RANGE_COLOR);
	        this.color = new Color(r,g,b);
//	        this.mass = INIT_MASS/2;
	        this.virus = virus;
//	        this.timeCreation = 0;
	    }

} 
