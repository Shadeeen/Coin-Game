package application;

import java.util.concurrent.atomic.AtomicInteger;
import javafx.animation.ScaleTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/*this class show all the randomOption process and the interface with what the action of the node
 *it has showrandomScreen ,nextrandomOption,fullFields,scale methods
 */
public class RandomOption {

	private Label coinsNumberLabel;
	private Label coinsValueLabel;
	private BorderPane bp = new BorderPane();
	private ImageView img;
	private Button nextRandomScreen;
	private static Stage primaryStage = new Stage();
	private TextField coinsNumberField = new TextField();
	private VBox vbCoins = new VBox(15);
	private SecondScreen s = new SecondScreen();
	private static Stage stage = new Stage();

	/*
	 * in the constructor i initialized all the panes and the nodes that i used in
	 * the interface
	 */
	public RandomOption() {

		img = new ImageView("back4.jpg");
		img.fitWidthProperty().bind(primaryStage.widthProperty());
		img.fitHeightProperty().bind(primaryStage.heightProperty());
		img.setOpacity(0.3);

		coinsNumberLabel = new Label("The Coins Number:");
		coinsNumberLabel.setStyle(
				"-fx-font-size: 23px;-fx-font-weight: bold;-fx-text-fill:#3D2314;-fx-font-family: 'Times New Roman';");

		ImageView nextImg = new ImageView("next12.png");
		nextImg.setFitWidth(80);
		nextImg.setFitHeight(80);
		nextRandomScreen = new Button("", nextImg);
		nextRandomScreen.setStyle("-fx-border-color: transparent; -fx-background-color: transparent;");

		scale(nextRandomScreen);

		coinsValueLabel = new Label("The Coins Value:");
		coinsValueLabel.setStyle(
				"-fx-font-size: 23px;-fx-font-weight: bold;-fx-text-fill:#3D2314;-fx-font-family: 'Times New Roman';");

		bp.setBottom(nextRandomScreen);
		bp.setAlignment(nextRandomScreen, Pos.BOTTOM_RIGHT);
		coinsNumberField.setMaxSize(80, 100);
		numericTextField(coinsNumberField);
		coinsNumberField.setStyle(
				"-fx-background-color:#e3c099; -fx-border-color:#3D2314; -fx-background-radius: 10px;-fx-border-radius: 10px; ");
		coinsNumberField.setAlignment(Pos.CENTER);

		bp.setCenter(vbCoins);
		vbCoins.setAlignment(Pos.CENTER);
		bp.setAlignment(vbCoins, Pos.CENTER);

	}

	// this flag to know if the user put the range or not
	boolean flag = true;

	/* this method make an action on nextRandomScreen button */
	public void nextRandomScreen() {
		// if the range didn't put don't play the next button
		nextRandomScreen.setOnAction(e -> {
			if (flag) {
				showAlert(AlertType.ERROR, "Empty Field", "Please fill all the fields before proceeding.");
				return;
			}
			// take the image and the coinsPane from the second screen and put them in the
			// pane to put in the stage to show and put the flag true to the next term
			StackPane stack = new StackPane();
			stack.getChildren().addAll(s.getImg2(), s.getCoinsPane());
			s.getImg2().fitWidthProperty().bind(stage.widthProperty());
			s.getImg2().fitHeightProperty().bind(stage.heightProperty());
			s.getImg2().setOpacity(0.3);
			Scene scene1 = new Scene(stack, 700, 300);
			stage.setScene(scene1);
			primaryStage.close();
			stage.show();
			flag = true;
		});
	}

	private AtomicInteger sizee = new AtomicInteger(0);
	private AtomicInteger random1 = new AtomicInteger(0);
	private AtomicInteger random2 = new AtomicInteger(0);

	/* this method to enter the range of the random numbers */
	public void range() {
		TextField from = new TextField();
		TextField to = new TextField();

		from.setStyle(
				"-fx-background-color:#e3c099; -fx-border-color:#3D2314; -fx-background-radius: 10px;-fx-border-radius: 10px; ");
		from.setAlignment(Pos.CENTER);
		from.setMaxSize(80, 100);

		to.setStyle(
				"-fx-background-color:#e3c099; -fx-border-color:#3D2314; -fx-background-radius: 10px;-fx-border-radius: 10px; ");
		to.setAlignment(Pos.CENTER);
		to.setMaxSize(80, 100);
		Label lable = new Label("->");
		lable.setStyle(
				"-fx-font-size: 23px;-fx-font-weight: bold;-fx-text-fill:#3D2314;-fx-font-family: 'Times New Roman';");
		numericTextField(to);
		numericTextField(from);

		// if the key pressed on the coinsNumberField especially in the enter key if the
		// number entered give it to the sizee if not return;
		coinsNumberField.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER) {
				if (coinsNumberField.getText().isBlank()) {
					return;
				}
				sizee.set(Integer.parseInt(coinsNumberField.getText().trim()));
				// check the size if it valid or not
				if (sizee.get() % 2 != 0) {
					showAlert(AlertType.WARNING, "Odd number", "the number of coins must be even");
					return;
				}
				if (sizee.get() > 30) {
					showAlert(AlertType.WARNING, "size", "the size is so big");
					return;
				}
				// if the size fine show the to and from fields to put the range in it
				if (e.getCode() == KeyCode.ENTER) {
					Label labeel = new Label("Range Of Coins:");
					labeel.setStyle(
							"-fx-font-size: 23px;-fx-font-weight: bold;-fx-text-fill:#3D2314;-fx-font-family: 'Times New Roman';");
					vbCoins.getChildren().clear();
					vbCoins.getChildren().addAll(labeel, from, lable, to);

				}
			}
		});

		// when put the range then the user press enter in the to field
		to.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER) {
				// check that field empty or not
				if (to.getText().isBlank() && from.getText().isBlank()) {
					return;
				}
				try {
					// if everything OK save the ranges in the random1 and random2 to use in the
					// fullRandomFields method
					random1.set(Integer.parseInt(from.getText().trim()));
					random2.set(Integer.parseInt(to.getText().trim()));
					vbCoins.getChildren().clear();
					vbCoins.getChildren().addAll(coinsNumberLabel, coinsNumberField, coinsValueLabel);
					fullRandomFields();
					flag = false;
					to.clear();
					from.clear();
				} catch (NumberFormatException r) {
					return;
				}
			}
		});

	}

	/*
	 * this method show the interface which put all the nodes in the pane its called
	 * in the firstscreen class especially in the randomOption method and in this
	 * method call range method to the fields process
	 */
	public void showRandomScreen() {
		nextRandomScreen();
		vbCoins.getChildren().clear();
		coinsNumberField.clear();
		vbCoins.getChildren().addAll(coinsNumberLabel, coinsNumberField, coinsValueLabel);
		StackPane sp = new StackPane();
		sp.getChildren().addAll(img, bp);
		Scene scene = new Scene(sp, 700, 300);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Coins game shaden-1220169");
		range();
		vbCoins.getChildren().removeIf(node -> node instanceof FlowPane);
		primaryStage.show();

	}

	/*
	 * in this method i take the sizee then check if its valid or not then full the
	 * f9elds randomly in the range (depend on random1,random2)
	 */
	public void fullRandomFields() {
		vbCoins.setAlignment(Pos.CENTER);
		int size = sizee.get();
		if (size % 2 == 0 && size != 0) {
			FirstScreen.size = size;
			vbCoins.getChildren().removeIf(node -> node instanceof FlowPane);

			FlowPane fp = new FlowPane();
			FirstScreen.array = new int[size];
			fp.getChildren().clear();
			for (int i = 0; i < size; i++) {
				TextField textField = new TextField();
				numericTextField(textField);
				textField.setMaxSize(80, 100);
				textField.setStyle(
						"-fx-background-color:#e3c099; -fx-border-color:#3D2314; -fx-background-radius: 10px;-fx-border-radius: 10px; ");
				textField.setAlignment(Pos.CENTER);
				int min = Math.min(random1.get(), random2.get());
				int max = Math.max(random1.get(), random2.get());

				int number = (int) (Math.random() * ((max - min) + 1)) + min;

				textField.setText("" + number);
				FirstScreen.array[i] = number;
				fp.getChildren().add(textField);

			}
			fp.setAlignment(Pos.CENTER);
			vbCoins.getChildren().add(fp);

		} else {
			showAlert(AlertType.WARNING, "Odd number", "the number of coins must be even");
			return;
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

	/*
	 * this method to help me to alert the user if there anything wrong
	 */
	private void showAlert(Alert.AlertType alertType, String title, String message) {
		try {
			Alert alert = new Alert(alertType);
			alert.setTitle(title);
			alert.setHeaderText(null);
			alert.setContentText(message);
			alert.showAndWait();
		} catch (Exception e) {
		}
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
	public static Stage getStage() {
		return stage;
	}

	public static void setStage(Stage stage) {
		RandomOption.stage = stage;
	}

}
