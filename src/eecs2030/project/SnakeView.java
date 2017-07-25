package eecs2030.project;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import eecs2030.project.SnakeModel.Node;

public class SnakeView implements Observer {

	private SnakeController controller;
	private SnakeModel model;
	private static int width_columns = 50;
	private static int height_rows = 35 ;
	private final static int NODE_SIZE = 15;
	
	private JFrame frame;
	private JLabel scoreLabel;
	private Canvas field;
	private JPanel ctrlPane;
	private JButton startBtn;
	
	public SnakeView() {
		
		frame = new JFrame("Snake Game By Chenxing Zheng");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		Container pane = frame.getContentPane();
		
		scoreLabel = new JLabel("Score:", JLabel.CENTER);
		scoreLabel.setPreferredSize(new Dimension(100,50));
		pane.add(scoreLabel, BorderLayout.NORTH);
		
		field = new Canvas();
		field.setSize(width_columns*NODE_SIZE, height_rows*NODE_SIZE);
		pane.add(field, BorderLayout.CENTER);
		
		ctrlPane = new JPanel();
		startBtn = new JButton("Start");
		ctrlPane.add(startBtn);
		pane.add(ctrlPane, BorderLayout.SOUTH);
		
		
		//WRONG
		//the view didn't know a controller when it was just created
		
		//frame.addKeyListener(ctrl);

		frame.setSize(width_columns*NODE_SIZE, height_rows*NODE_SIZE+100 );
		frame.setResizable(false); 
		frame.setLocationRelativeTo(null);
		frame.pack();
		frame.setVisible(true);
		
		
		
	}
	
	/*public void setKeyListener(SnakeController c){
		this.frame.addKeyListener(c);
	}*/
	
	public void setModel(SnakeModel m){
		
		this.model = m;
	}
	
	public void setController(SnakeController c){
		controller = c;
		frame.addKeyListener(controller);
		startBtn.addKeyListener(controller);
		startBtn.setActionCommand("start");
		
		startBtn.addActionListener(controller);
		ctrlPane.addKeyListener(controller);
		field.addKeyListener(controller);
		frame.requestFocus();
	}
	
	public int getWidth(){
		return width_columns;
	}
	
	public int getHeight(){
		return height_rows;
	}
	 private void paint(){
		
		Graphics g = field.getGraphics();
		
		//clear the canvas
		g.setColor(Color.white);
		g.fillRect(0, 0, field.getWidth(), field.getHeight());
		
		//draw the food
		g.setColor(Color.green);
		g.fillRect(model.food.getX() * NODE_SIZE, model.food.getY() * NODE_SIZE, NODE_SIZE, NODE_SIZE);
		
		//draw the poisons
		g.setColor(Color.red);
		for(Node n:  model.poisons){
			g.fillRect(n.getX() * NODE_SIZE, n.getY() * NODE_SIZE, NODE_SIZE, NODE_SIZE);
		}
		
		//draw the snake
		g.setColor(Color.black);
		for(Node n : model.sBody){
			g.fillRect(n.getX() * NODE_SIZE, n.getY() * NODE_SIZE, NODE_SIZE, NODE_SIZE);
		}
		g.setColor(Color.gray);
		Node head = model.sBody.getFirst();
		g.fillRect(head.getX() * NODE_SIZE, head.getY() * NODE_SIZE, NODE_SIZE, NODE_SIZE);
		
		
	}
	

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 * 
	 * Called when observable SnakeModel notifyObsever
	 */
	@Override
	public void update(Observable o, Object arg) {
		
		paint();
		scoreLabel.setText("Score: " + model.getScore() + " Highest Score: " + model.getHighestScore());
	}
	
	public static void main(String[] args) {
		SnakeView testV = new SnakeView();
		SnakeController testC = new SnakeController();
		SnakeModel testM = new SnakeModel(testV);
		
		testV.setController(testC);
		testV.setModel(testM);
		
		testC.setModel(testM);
		testC.setView(testV);
		
		testV.paint();
	}

}
