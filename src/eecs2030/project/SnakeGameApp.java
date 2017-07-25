package eecs2030.project;

/**
 * EECS2030 Project
 * A simple Snake Game using MVC pattern.
 * @author Chenxing Zheng #214634901
 *
 */
public class SnakeGameApp {
	
	static Thread SnakeThread;
	public static void main(String[] args) {
		
		
		SnakeController controller = new SnakeController();
		SnakeView view = new SnakeView();
		SnakeModel model = new SnakeModel(view);
		
		controller.setModel(model);
		controller.setView(view);
	
		view.setModel(model);
		view.setController(controller);
		model.addObserver(view);
		
	    SnakeThread = new Thread(model);
		SnakeThread.start();
		
		//System.out.println(model.sBody);
	
	}
}
