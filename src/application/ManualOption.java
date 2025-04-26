package application;

import java.util.ArrayList;
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

/*this class show all the manualOption process and the interface with what the action of the node
 *it has showManuaScreen ,fullManualFields,scale methods
 */
public class ManualOption {

	private BorderPane bp = new BorderPane();
	private ImageView img;
	private Button nextManualScreen;
	private static Stage primaryStage = new Stage();
	private ArrayList<TextField> textArray = new ArrayList<>();
	private SecondScreen s = new SecondScreen();
	private VBox vbCoins = new VBox(15);
	private static Stage stage = new Stage();

	/*
	 * in the constructor i initialized all the panes and the nodes that i used in
	 * the interface
	 */
	public ManualOption() {

		img = new ImageView("back12.jpg");
		img.fitWidthProperty().bind(primaryStage.widthProperty());
		img.fitHeightProperty().bind(primaryStage.heightProperty());
		img.setOpacity(0.3);
		ImageView nextImg = new ImageView("manulNext.png");
		nextImg.setFitWidth(80);
		nextImg.setFitHeight(80);
		nextManualScreen = new Button("", nextImg);
		nextManualScreen.setStyle("-fx-border-color: transparent; -fx-background-color: transparent;");
		scale(nextManualScreen);
		bp.setBottom(nextManualScreen);
		bp.setAlignment(nextManualScreen, Pos.BOTTOM_RIGHT);
		vbCoins.getChildren().clear();
		bp.setCenter(vbCoins);
		vbCoins.setAlignment(Pos.CENTER);
		bp.setAlignment(vbCoins, Pos.CENTER);

	}

	/*
	 * in this method i put the enter action in the coinsNumberField to take the
	 * size i use the AtomicInteger to use it in the lambda
	 */
	public void numberField() {

		vbCoins.getChildren().clear();
		TextField coinsNumberField = new TextField();
		Label coinsNumberLabel = new Label("The Coins Number:");
		coinsNumberLabel.setStyle(
				"-fx-font-size: 23px;-fx-font-weight: bold;-fx-text-fill:#3D2314;-fx-font-family: 'Times New Roman';");
		Label coinsValueLabel = new Label("The Coins Value:");
		coinsValueLabel.setStyle(
				"-fx-font-size: 23px;-fx-font-weight: bold;-fx-text-fill:#3D2314;-fx-font-family: 'Times New Roman';");

		vbCoins.getChildren().addAll(coinsNumberLabel, coinsNumberField, coinsValueLabel);

		coinsNumberField.setMaxSize(80, 100);
		numericTextField(coinsNumberField);
		coinsNumberField.setStyle(
				"-fx-background-color:#e3c099; -fx-border-color:#3D2314; -fx-background-radius: 10px;-fx-border-radius: 10px; ");

		coinsNumberField.setAlignment(Pos.CENTER);
		// if the key pressed on the coinsNumberField especially in the enter key if the
		// number entered give it to the sizee if not return;
		coinsNumberField.setOnKeyPressed(e -> {
			coinsNumberField.setDisable(false);
			if (e.getCode() == KeyCode.ENTER) {
				if (coinsNumberField.getText().isBlank()) {
					return;
				}
				sizee.set(Integer.parseInt(coinsNumberField.getText().trim()));
				// check the size if it valid or not then call the coinsNumberField method to
				// fill the fields manually
				if (sizee.get() > 30) {
					showAlert(AlertType.WARNING, "size", "the size is so big");
					return;
				}
				if (sizee.get() % 2 == 0)
					coinsNumberField.setDisable(true);
				fullManualFields();

			}
		});
	}

	/*
	 * this method show the interface which put all the nodes in the pane its called
	 * in the firstscreen class especially in the manualOption method
	 */
	public void showManual() {
		nextManualScreen();
		StackPane sp = new StackPane();
		sp.getChildren().addAll(img, bp);
		Scene scene = new Scene(sp, 700, 300);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Coins game shaden-1220169");
		vbCoins.getChildren().removeIf(node -> node instanceof FlowPane);
		primaryStage.show();

	}

	/*
	 * this method do the action on the nextManualScreen button that check if the
	 * all fields is full to continue if not give the user alert
	 */
	public void nextManualScreen() {
		nextManualScreen.setOnAction(e -> {
			boolean allFieldsFilled = true;
			int size = sizee.get();
			if (size > 0) {
				// check the fields if full or not
				for (int i = 0; i < size; i++) {
					if (textArray.get(i).getText().isBlank()) {
						allFieldsFilled = false;
						showAlert(AlertType.ERROR, "Empty Field", "Please fill all the fields before proceeding.");
						break;
					}
				}
				// Initialize the array and put all the numbers which in the fields in it
				if (allFieldsFilled) {
					FirstScreen.array = new int[size];
					for (int i = 0; i < FirstScreen.array.length; i++) {
						FirstScreen.array[i] = Integer.parseInt(textArray.get(i).getText().trim());
					}
					// take the image and the coinsPane from the second screen and put them in the
					// pane to put in the stage to show
					StackPane stack = new StackPane();
					stack.getChildren().addAll(s.getImg2(), s.getCoinsPane());
					s.getImg2().fitWidthProperty().bind(stage.widthProperty());
					s.getImg2().fitHeightProperty().bind(stage.heightProperty());
					s.getImg2().setOpacity(0.3);
					Scene scene1 = new Scene(stack, 700, 300);
					stage.setScene(scene1);
					primaryStage.close();
					stage.show();
					allFieldsFilled = false;
				}
			}
		});
	}

	private AtomicInteger sizee = new AtomicInteger(0);

	/* this method to put the fields depends on the sizee */
	public void fullManualFields() {

		vbCoins.setAlignment(Pos.CENTER);

		int size = sizee.get();
		if (size % 2 == 0 && size != 0) {

			FirstScreen.size = size;
			vbCoins.getChildren().removeIf(node -> node instanceof FlowPane);
			FlowPane fp = new FlowPane(5, 5);
			fp.getChildren().clear();
			textArray.clear();
			// put number of fields depends on the size
			for (int i = 0; i < size; i++) {

				TextField textField = new TextField();
				numericTextField(textField);
				textArray.add(textField);
				textField.setMaxSize(80, 100);
				textField.setStyle(
						"-fx-background-color:#e3c099; -fx-border-color:#3D2314; -fx-background-radius: 10px;-fx-border-radius: 10px; ");
				textField.setAlignment(Pos.CENTER);
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
			// The input should be numeric characters only (digits 0-9), and the zero is not
			// acceptable
			if (newText.matches("[0-9]*") && !newText.equals("0")) {
				return change;
			} else {
				return null;
			}
		}));
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
	public static Stage getStage() {
		return stage;
	}

	public static void setStage(Stage stage) {
		ManualOption.stage = stage;
	}

}
