package application;

import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/*in this class i create the anther screen which include the how to play and the other one the names of the two players*/
public class SecondScreen {

	private Button playersButton;
	private Button computerButton;
	private Label choiseScreen;
	private VBox vbChoise = new VBox(10);
	private HBox hb = new HBox(100);
	private BorderPane coinsPane = new BorderPane();
	private BorderPane bpPlay;
	private Label askLabel;
	private Label firstLabel;
	private Label secondLabel;
	private TextField firstField;
	private TextField secondField;
	private Button startButton;
	private ImageView img2 = new ImageView("back5.jpg");
	private static Stage stage2 = new Stage();
	private ThirdScreen t = new ThirdScreen();
	private ThirdScreenComputer tC = new ThirdScreenComputer();
	private Button cancelButton;
	private Button cancelHow;

	/*
	 * in the constructor i initialized all the panes and the nodes that i used in
	 * the interface
	 */
	public SecondScreen() {

		choiseScreen = new Label("How Do You Like To Play?");
		choiseScreen.setStyle(
				"-fx-font-size: 30px;-fx-font-weight: bold;-fx-text-fill:#3D2314;-fx-font-family: 'Times New Roman';");

		ImageView playersImg = new ImageView("people.png");
		playersImg.setFitWidth(100);
		playersImg.setFitHeight(100);
		playersButton = new Button("", playersImg);
		playersButton.setStyle("-fx-border-color: transparent; -fx-background-color: transparent;");

		scale(playersButton);

		ImageView computerImg = new ImageView("computer.png");
		computerImg.setFitWidth(100);
		computerImg.setFitHeight(100);
		computerButton = new Button("", computerImg);
		computerButton.setStyle("-fx-border-color: transparent; -fx-background-color: transparent;");

		scale(computerButton);

		hb.getChildren().addAll(playersButton, computerButton);
		hb.setAlignment(Pos.CENTER);

		vbChoise.getChildren().addAll(choiseScreen, hb);
		vbChoise.setAlignment(Pos.CENTER);

		coinsPane.setCenter(vbChoise);

		ImageView img3 = new ImageView("back6.jpg");
		img3.fitWidthProperty().bind(stage2.widthProperty());
		img3.fitHeightProperty().bind(stage2.heightProperty());
		img3.setOpacity(0.3);

		bpPlay = new BorderPane();

		GridPane gp = new GridPane(0, 20);
		askLabel = new Label("Write The Player`s Names:");
		askLabel.setStyle(
				"-fx-font-size: 23px;-fx-font-weight: bold;-fx-text-fill:#3D2314;-fx-font-family: 'Times New Roman';");
		gp.add(askLabel, 1, 0);

		firstLabel = new Label("first Player:");
		firstLabel.setStyle(
				"-fx-font-size: 15px;-fx-font-weight: bold;-fx-text-fill:#3D2314;-fx-font-family: 'Times New Roman';");
		gp.add(firstLabel, 0, 1);

		secondLabel = new Label("second Player:");
		secondLabel.setStyle(
				"-fx-font-size: 15px;-fx-font-weight: bold;-fx-text-fill:#3D2314;-fx-font-family: 'Times New Roman';");
		gp.add(secondLabel, 0, 2);

		firstField = new TextField();
		firstField.setPromptText("Enter The Name");
		firstField.setStyle(
				"-fx-background-color:#e3c099; -fx-border-color:#3D2314; -fx-background-radius: 10px;-fx-border-radius: 10px;-fx-prompt-text-fill: #3D2314;");
		gp.add(firstField, 1, 1);

		secondField = new TextField();
		secondField.setPromptText("Enter The Name");
		secondField.setStyle(
				"-fx-background-color:#e3c099; -fx-border-color:#3D2314; -fx-background-radius: 10px;-fx-border-radius: 10px;-fx-prompt-text-fill: #3D2314; ");
		gp.add(secondField, 1, 2);

		gp.setAlignment(Pos.CENTER);
		bpPlay.setCenter(gp);

		ImageView ima = new ImageView("next4.png");
		ima.setFitWidth(80);
		ima.setFitHeight(80);

		startButton = new Button("", ima);
		startButton.setStyle("-fx-border-color: transparent; -fx-background-color: transparent;");
		scale(startButton);

		bpPlay.setBottom(startButton);
		bpPlay.setAlignment(startButton, Pos.BOTTOM_RIGHT);

		// put action in the playerButton to show the put names screen (name of 1est and
		// 2ed player)
		playersButton.setOnAction(e -> {

			StackPane stack = new StackPane();
			stack.getChildren().addAll(img3, bpPlay);
			Scene scene1 = new Scene(stack, 700, 300);
			stage2.setScene(scene1);
			FileOption.getStage().close();
			ManualOption.getStage().close();
			RandomOption.getStage().close();
			stage2.show();

		});

		ImageView cancelImg = new ImageView("cancel2.png");
		cancelImg.setFitWidth(50);
		cancelImg.setFitHeight(50);
		cancelButton = new Button("", cancelImg);
		cancelButton.setStyle("-fx-border-color: transparent; -fx-background-color: transparent;");
		scale(cancelButton);

		bpPlay.setTop(cancelButton);
		bpPlay.setAlignment(cancelButton, Pos.TOP_LEFT);

		t.startPlayer();

		cancelButton.setOnAction(e -> {
			stage2.close();
		});

		ImageView cancelHwoImg = new ImageView("cancel4.png");
		cancelHwoImg.setFitWidth(50);
		cancelHwoImg.setFitHeight(50);
		cancelHow = new Button("", cancelHwoImg);
		cancelHow.setStyle("-fx-border-color: transparent; -fx-background-color: transparent;");
		scale(cancelHow);

		coinsPane.setTop(cancelHow);
		coinsPane.setAlignment(cancelHow, Pos.TOP_LEFT);
		coinsPane.setMargin(vbChoise, new Insets(0, 0, 80, 0));

		cancelHow.setOnAction(e -> {
			FileOption.getStage().close();
			ManualOption.getStage().close();
			RandomOption.getStage().close();

		});

		startTwoPlayer();
		startComputer();

	}

	/*
	 * this method to put an action on startComputer button to open the anther
	 * screen which taken from the thirdComputrtscreen class
	 */
	public void startComputer() {
		computerButton.setOnAction(e -> {
			getStage2().close();
			RandomOption.getStage().close();
			FileOption.getStage().close();
			ManualOption.getStage().close();
			tC.show();
			tC.which();
			tC.getName1().setText("Computer_1");
			tC.getName2().setText("Computer_2");

		});
	}

	/*
	 * this method to put action on the startTwoPlayer button which check if the
	 * both names entered and go to the anther screen which is in the thirdScreen
	 * class
	 */
	public void startTwoPlayer() {
		startButton.setOnAction(e -> {
			if (firstField.getText().isEmpty()) {
				System.out.println(getFirstField().getText());
				firstField.setPromptText("The Name!!!");
				return;
			} else if (secondField.getText().isEmpty()) {
				secondField.setPromptText("The Name!!!");
				return;
			} else {
				t.getName1().setText(firstField.getText());
				t.getName2().setText(secondField.getText());
				getStage2().close();
				t.getScaleTransition().stop();
				t.show();
				t.which();
				t.getFirstRadioButton().setText(firstField.getText());
				t.getSecRadioButton().setText(secondField.getText());
				t.getFirstRadioButton().setSelected(true);
			}

		});

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

	/*
	 * this method to control the number type which entered in the textFields to
	 * less the errors , so i set the formatter text to accept only the numeric
	 * input
	 */
	public void numericTextField(TextField textField) {
		textField.setTextFormatter(new TextFormatter<>(change -> {
			String newText = change.getControlNewText();
			if (newText.matches("[0-9]*") && !newText.equals("0")) {
				return change;
			} else {
				return null;
			}
		}));
	}

	// a setters and getters methods
	public TextField getFirstField() {
		return firstField;
	}

	public void setFirstField(TextField firstField) {
		this.firstField = firstField;
	}

	public TextField getSecondField() {
		return secondField;
	}

	public void setSecondField(TextField secondField) {
		this.secondField = secondField;
	}

	public Button getStartButton() {
		return startButton;
	}

	public void setStartButton(Button startButton) {
		this.startButton = startButton;
	}

	public static Stage getStage2() {
		return stage2;
	}

	public static void setStage2(Stage stage2) {
		SecondScreen.stage2 = stage2;
	}

	public Button getComputerButton() {
		return computerButton;
	}

	public void setComputerButton(Button computerButton) {
		this.computerButton = computerButton;
	}

	public BorderPane getCoinsPane() {
		return coinsPane;
	}

	public void setCoinsPane(BorderPane coinsPane) {
		this.coinsPane = coinsPane;
	}

	public ImageView getImg2() {
		return img2;
	}

	public void setImg2(ImageView img2) {
		this.img2 = img2;
	}

}
