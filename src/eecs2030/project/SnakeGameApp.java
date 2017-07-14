package eecs2030.project;

public class SnakeGameApp {
	public static void main(String[] args) {
		
		
		SnakeController controller = new SnakeController();
		SnakeView view = new SnakeView();
		SnakeModel model = new SnakeModel(view);
		
		controller.setModel(model);
		controller.setView(view);
		
		view.setModel(model);
		view.setController(controller);
		
	    model.addObserver(view);
		
		(new Thread(model)).start();
	
	}
}
