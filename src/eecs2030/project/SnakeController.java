package eecs2030.project;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SnakeController implements KeyListener{
	
	private SnakeView view;
	private SnakeModel model;
	
	
	
	public void setView(SnakeView v){
		this.view = v;
	}
	
	public void setModel(SnakeModel m){
		this.model = m;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(model.running){
			
			switch(e.getKeyCode()){
			case KeyEvent.VK_UP:
				model.setDirection(model.UP);
				break;
			case KeyEvent.VK_DOWN:
				model.setDirection(model.DOWN);
				break;
			case KeyEvent.VK_LEFT:
				model.setDirection(model.LEFT);
				break;
			case KeyEvent.VK_RIGHT:
				model.setDirection(model.RIGHT);
				break;
			case KeyEvent.VK_A:
				System.out.print(" AAAAAAAAAAAAA ");
			default:
				break;
		
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	

}
