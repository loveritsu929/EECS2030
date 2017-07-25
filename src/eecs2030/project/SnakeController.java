package eecs2030.project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SnakeController implements KeyListener, ActionListener{
	
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
			case KeyEvent.VK_EQUALS:
				model.setSpeed("+");
				break;
			case KeyEvent.VK_MINUS:
				model.setSpeed("-");
				//System.out.print(" ----- ");
				break;
			default:
				break;
		
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		
		//restart
		if (command.equals("start")) {
			SnakeModel newModel = new SnakeModel(view);
			
			this.setModel(newModel);
			view.setModel(newModel);

			model.deleteObserver(view);
			newModel.addObserver(view);
			
			(new Thread(newModel)).start();
		}
	
		
	}
	
	

}
