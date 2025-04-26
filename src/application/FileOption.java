package application;

import javafx.animation.ScaleTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/*this class show all the fileOption process and the interface with what the action of the node
 *it has showFileScreen ,nextFileOption,fullFields,scale methods
 */
public class FileOption {

	private Label coinsNumberLabel;
	private Label coinsValueLabel;
	private BorderPane bp = new BorderPane();
	private ImageView img;
	private Button nextFileScreen;
	private static Stage primaryStage = new Stage();
	private SecondScreen s = new SecondScreen();
	private static Stage stage = new Stage();

	/*
	 * in the constructor i initialized all the panes and the nodes that i used in
	 * the interface
	 */
	public FileOption() {

		img = new ImageView("back4.jpg");
		img.fitWidthProperty().bind(primaryStage.widthProperty());
		img.fitHeightProperty().bind(primaryStage.heightProperty());
		img.setOpacity(0.3);

		coinsNumberLabel = new Label("The Coins Number:");
		coinsNumberLabel.setStyle(
				"-fx-font-size: 23px;-fx-font-weight: bold;-fx-text-fill:#3D2314;-fx-font-family: 'Times New Roman';");

		ImageView nextImg = new ImageView("next.png");
		nextImg.setFitWidth(80);
		nextImg.setFitHeight(80);
		nextFileScreen = new Button("", nextImg);
		nextFileScreen.setStyle("-fx-border-color: transparent; -fx-background-color: transparent;");

		scale(nextFileScreen);

		coinsValueLabel = new Label("The Coins Value:");
		coinsValueLabel.setStyle(
				"-fx-font-size: 23px;-fx-font-weight: bold;-fx-text-fill:#3D2314;-fx-font-family: 'Times New Roman';");

		bp.setBottom(nextFileScreen);
		bp.setAlignment(nextFileScreen, Pos.BOTTOM_RIGHT);

	}

	/*
	 * call the nextFileOption method and put the nodes in the pane then the pane in
	 * the stage to show the screen when press the fileOptionButton in the
	 * firstScreen (this method is called in the firstScreen class specially in the
	 * readFile method)
	 */
	public void showFileScreen() {
		nextFileOption();
		StackPane sp = new StackPane();
		sp.getChildren().addAll(img, bp);
		Scene scene = new Scene(sp, 700, 300);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Coins game shaden-1220169");
		fullFields();
		primaryStage.show();

	}

	/*
	 * in this method i put to the nextFileScreen button an action to go to the
	 * anther screen which i used the Pane(getCoinsPane) who in the SecondScreen
	 * class (s)
	 */
	public void nextFileOption() {
		getNextFileScreen().setOnAction(e -> {
			StackPane stack = new StackPane();
			// get the image and the CoinsPane from the secondScreen class to put them in
			// the
			// pane and shown when press on next button
			stack.getChildren().addAll(s.getImg2(), s.getCoinsPane());
			s.getImg2().fitWidthProperty().bind(stage.widthProperty());
			s.getImg2().fitHeightProperty().bind(stage.heightProperty());
			s.getImg2().setOpacity(0.3);
			Scene scene1 = new Scene(stack, 700, 300);
			stage.setScene(scene1);
			getPrimaryStage().close();
			stage.show();
		});

	}

	/*
	 * in this method i need to full the fields in the numbers that are in the array
	 * which is full from the file and put them in the Pane to use in the
	 * showFileScreen method
	 */
	public void fullFields() {
		TextField coinsNumberField = new TextField();
		coinsNumberField.setMaxSize(80, 100);
		coinsNumberField.setStyle(
				"-fx-background-color:#e3c099; -fx-border-color:#3D2314; -fx-background-radius: 10px;-fx-border-radius: 10px; ");
		coinsNumberField.setEditable(false);
		coinsNumberField.setAlignment(Pos.CENTER);

		VBox vbCoins = new VBox(15);
		vbCoins.setAlignment(Pos.CENTER);
		vbCoins.setAlignment(Pos.CENTER);

		// get the size to put in coinsNumberField
		int size = FirstScreen.size;
		coinsNumberField.setText("" + size);
		FlowPane fp = new FlowPane(5, 5);
		fp.getChildren().clear();
		// check the size and the array then go to elements one by one in the array to
		// put each one in textField
		if (size > 0 && FirstScreen.array != null) {
			vbCoins.getChildren().removeIf(node -> node instanceof FlowPane);
			for (int i = 0; i < FirstScreen.array.length; i++) {
				TextField textField = new TextField("" + FirstScreen.array[i]);
				textField.setMaxSize(80, 100);
				textField.setStyle(
						"-fx-background-color:#e3c099; -fx-border-color:#3D2314; -fx-background-radius: 10px;-fx-border-radius: 10px; ");
				textField.setEditable(false);
				textField.setAlignment(Pos.CENTER);
				fp.getChildren().add(textField);

			}
			fp.setAlignment(Pos.CENTER);
			vbCoins.getChildren().addAll(coinsNumberLabel, coinsNumberField, coinsValueLabel, fp);
			bp.setCenter(vbCoins);
		}
	}

	/*
	 * this method to make a transition on the buttons when the mouse in them (its a
	 * interface thing)
	 */
	private void scale(Button button) {

		ScaleTransition scaleUp = new ScaleTransition(Duration.millis(200), button);
		scaleUp.setToX(1.1);
		scaleUp.setToY(1.1);
		ScaleTransition scaleDown = new ScaleTransition(Duration.millis(200), button);
		scaleDown.setToX(1.0);
		scaleDown.setToY(1.0);
		button.setOnMouseEntered(e -> scaleUp.play());
		button.setOnMouseExited(e -> scaleDown.play());
	}

	// a setters and getters methods
	public Button getNextFileScreen() {
		return nextFileScreen;
	}

	public void setNextFileScreen(Button nextCOins) {
		this.nextFileScreen = nextCOins;
	}

	public static Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void setPrimaryStage(Stage primaryStage) {
		FileOption.primaryStage = primaryStage;
	}

	public static Stage getStage() {
		return stage;
	}

	public static void setStage(Stage stage) {
		FileOption.stage = stage;
	}

}
