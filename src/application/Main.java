package application;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			
			// make a firstScreen object and call the show method to show the first Screen
			// in the game
			FirstScreen f = new FirstScreen();
			f.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
