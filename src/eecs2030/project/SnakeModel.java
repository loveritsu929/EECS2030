package eecs2030.project;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Random;

import javax.swing.JOptionPane;

public class SnakeModel extends Observable implements Runnable{

	private SnakeView view;
	private Random rd = new Random();
	boolean running = true;		// if the app is running
	private boolean[][] occupied;
	private int direction = 1;
	private int sleepTime = 200;
	private int width;
	private int height;
	private static final int INITIAL_LENGTH  = 4;
	private int poisonNum = rd.nextInt(3) + 5;
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
		
		occupied = new boolean[width][height];
		
		sBody.clear();
		poisons.clear();
		
		//create the snake
		for(int i = 0; i < INITIAL_LENGTH; i++){
			
			int x = width/2 ;
			int y = height/2 + i;
			
			sBody.add(new Node(x, y));
			occupied[x][y] = true;
		}
		
		createFood();
		createPoisons();
		
		running = true;
		
	}
	
	private void createFood(){
		int x = 0;
		int y = 0;
		
		do{
		x = rd.nextInt(width + 1);
		y = rd.nextInt(height +1);
		
		food = new Node(x, y);
		
		} while(occupied[x][y] == true );
			
		occupied[x][y] = true;
	}
	
	private void createPoisons(){
		int x = 0;
		int y = 0;
		
		for(int i = 0; i < poisonNum; i++){
			
			do{
				x = rd.nextInt(width + 1);
				y = rd.nextInt(height +1);
				
				Node p = new Node(x, y);
				poisons.add(p);
				
				} while(occupied[x][y] == true );
					
				occupied[x][y] = true;
		}
	}
	
	public void setDirection(int dir){
		//cannot turn 180 degree
		if( !(this.direction + dir == 3 || this.direction + dir ==7 )){
			this.direction = dir;
		}
	}
	
	private boolean moving(){
		boolean moving = true; // for test, it should be false initially
		//boolean moving = false;
		
		int x = sBody.get(0).x;
		int y = sBody.getFirst().y;
		
		switch(direction){
		case UP:
			y--;     // y--!!!
			break;
		case DOWN:
			y++;
			break;
		case LEFT:
			x--;
			break;
		case RIGHT:
			x++;
			break;
		}
		
		// if x & y are valid
		if(x >= 0 && x <= width && y>=0 && y<= height){
			if(occupied[x][y]){
			
				//eat food
				if(food.x == x && food.y == y){
					
					sBody.addFirst(food);
					createFood();
					return true;
				}
				//occupied by poison or its own body
				else{
					return false;
				}
			}
			// not occupied, just move
			else{
				Node tail = sBody.removeLast();
				occupied[tail.x][tail.y] = false;
				
				Node newHead = new Node(x,y);
				sBody.addFirst(newHead);
			}
		}
		
		return moving;
	}
		
	@Override
	public void run() {
		         
	        while (running) {  
	            try {  
	                Thread.sleep(sleepTime);  
	                if(moving()){
	                	//sBody.removeLast(); 	// remove the tail && add one to the head
	                	//test
	                	//Node newHead = new Node(sBody.get(0).x + 1, sBody.get(0).y);
	                	//sBody.add(0, newHead);
	                	setChanged();            
	                    notifyObservers();  
	                 }
	                else{
	                	running = false;
	                }
	                 
                    } catch (Exception e) {  
	            	
	            	break;  
	            }  
	        }

	}
	
	
	 	/**
	 	 * No accesss modifier so other classes in the same package can see it.
	 	 * @author lover
	 	 *
	 	 */
	 	class Node{
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
