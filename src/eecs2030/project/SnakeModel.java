package eecs2030.project;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Random;

import javax.swing.JOptionPane;

public class SnakeModel extends Observable implements Runnable{

	private SnakeView view;
	private Random rd = new Random();
	boolean running;
	private boolean[][] occupied;
	private int direction;
	
	private int width;
	private int height;
	private static final int INITIAL_LENGTH  = 4;
    static final int UP = 1;
    static final int DOWN = 2;
    static final int LEFT = 3;
    static final int RIGHT = 4;
	
	
	Node food;
	ArrayList<Node> poisons = new ArrayList<Node>();
	LinkedList<Node> sBody = new LinkedList<Node>(); // has addFirst & addLast
	
	
	public SnakeModel(SnakeView v){
		this.view = v;
		this.width  = v.getWidth();
		this.height = v.getHeight();
		
		sBody.clear();
		poisons.clear();
		
		//creat the snake
		for(int i = 0; i < INITIAL_LENGTH; i++){
			int x = width/2 ;
			int y = height/2 + i;
			
			sBody.add(new Node(x, y));
			occupied[x][y] = true;
		}
		
		creatFood();
		
		running = true;
		
	}
	
	public void creatFood(){
		int x = 0;
		int y = 0;
		
		x = rd.nextInt(width + 1);
		y = rd.nextInt(height +1);
		
		food = new Node(x, y);
		occupied[x][y] = true;
	}
		
	@Override
	public void run() {
		         
	        while (running) {  
	            try {  
	                Thread.sleep(500);  
	                
	                setChanged();            
                    notifyObservers();  
                    
	            } catch (Exception e) {  
	            	
	            	break;  
	            }  
	        		}

			}
	
	
	private static class Node{
		private int x;
		private int y;
		
		private Node(int x, int y){
			this.x = x;
			this.y = y;
		}
		
		public int getX(){
			return this.x;
		}
		
		public int getY(){
			return this.y;
		}
		
		public void setX(int i){
			this.x = i;
		}
		
		public void setY(int i){
			this.y = i;
		}
	}
}
