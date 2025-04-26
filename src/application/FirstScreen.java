package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;
import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

/* in this class i start to build the interface of the first screen , which include the instruction 
 * of the game and give the user the options to choice how the data will entered 
 * and in this class i read the file by creating the readFile method and make the checkFile method
 * to check if the file is valid or not 
 * and in this class there a fileOption , manualOption , randoOption,scale , showAlert,readFiel , checkFile methods 
 */

public class FirstScreen {

	private ImageView backGround;
	private ImageView logoImage;
	private Button fileButton;
	private Button randomButton;
	private Button manualButton;
	private Button howPlayButton;
	private Button exit;
	private FileChooser fileChooser;
	private File selectedFile;
	private File file;
	private Scene scene;
	private static Stage primaryStage = new Stage();
	private HBox hbTop = new HBox(20);
	private VBox vbButtons = new VBox();
	private VBox vbCenter = new VBox(5);
	private Stage stage = new Stage();
	static int size;
	static int[] array;
	private Button okButton;

	/*
	 * in the constructor i initialized all the panes and the nodes that i used in
	 * the interface
	 */
	public FirstScreen() {
		logoImage = new ImageView("logo.png");
		logoImage.setFitWidth(70);
		logoImage.setFitHeight(70);

		Label lable = new Label("Coins Game");
		lable.setStyle(
				"-fx-font-size: 70px;-fx-font-weight: bold;-fx-text-fill: #3D2314;-fx-font-family: 'Times New Roman';");

		hbTop.getChildren().addAll(lable, logoImage);
		hbTop.setAlignment(Pos.CENTER);

		HBox hb = new HBox();
		ImageView fileImage = new ImageView("moneyBag.png");
		fileImage.setFitWidth(170);
		fileImage.setFitHeight(170);
		fileButton = new Button("", fileImage);
		fileButton.setStyle("-fx-border-color: transparent; -fx-background-color: transparent;");
		scale(fileButton);

		ImageView randomImag = new ImageView("moneyBag2.png");
		randomImag.setFitWidth(170);
		randomImag.setFitHeight(170);
		randomButton = new Button("", randomImag);
		randomButton.setStyle("-fx-border-color: transparent; -fx-background-color: transparent;");
		scale(randomButton);

		hb.getChildren().addAll(fileButton, randomButton);
		hb.setAlignment(Pos.BOTTOM_RIGHT);

		ImageView manualImag = new ImageView("moneyBag3.png");
		manualImag.setFitWidth(170);
		manualImag.setFitHeight(170);
		manualButton = new Button("", manualImag);
		manualButton.setStyle("-fx-border-color: transparent; -fx-background-color: transparent;");

		scale(manualButton);

		vbButtons.getChildren().addAll(manualButton, hb);
		vbButtons.setAlignment(Pos.BOTTOM_RIGHT);
		vbButtons.setMargin(hb, new Insets(0, 30, 30, 30));
		vbButtons.setMargin(manualButton, new Insets(0, 110, 0, 0));

		howPlayButton = new Button("How To Play ?");
		howPlayButton.setStyle(
				"-fx-border-color: transparent; -fx-background-color: transparent;-fx-font-size: 40px;-fx-font-weight: bold;-fx-text-fill: #3D2314;-fx-font-family: 'Times New Roman';");

		scale(howPlayButton);

		HBox hbCenter = new HBox();
		ImageView coinImg = new ImageView("dollar.png");
		coinImg.setFitWidth(50);
		coinImg.setFitHeight(50);
		Label howLable = new Label("", coinImg);
		hbCenter.getChildren().addAll(howPlayButton, coinImg);
		hbCenter.setAlignment(Pos.CENTER);

		exit = new Button("Exit...");
		exit.setStyle(
				"-fx-border-color: transparent; -fx-background-color: transparent;-fx-font-size: 40px;-fx-font-weight: bold;-fx-text-fill: #3D2314;-fx-font-family: 'Times New Roman';");

		scale(exit);

		vbCenter.getChildren().addAll(hbCenter, exit);
		vbCenter.setAlignment(Pos.CENTER);

		vbCenter.setMargin(hbCenter, new Insets(150, 0, 0, 0));

		backGround = new ImageView("back.jpg");
		backGround.fitWidthProperty().bind(primaryStage.widthProperty());
		backGround.fitHeightProperty().bind(primaryStage.heightProperty());
		backGround.setOpacity(0.5);

		ImageView howImgBack = new ImageView("back2.jpg");
		howImgBack.fitWidthProperty().bind(primaryStage.widthProperty());
		howImgBack.fitHeightProperty().bind(primaryStage.heightProperty());
		howImgBack.setOpacity(0.3);

		StackPane pane = new StackPane();
		BorderPane bpInstuction = new BorderPane();
		Label instractions = new Label(
				"This is a two-player game. There are even number of coins arranged in a row(you should select the number of coins).\r\n There will be"
						+ "alternate turns. In each turn, a player can either select the first coin in the row or the last coin in\r\n"
						+ "the row and keep it with him. you win if you have the maximium amount of money");
		instractions.setStyle(
				"-fx-font-size:20px;-fx-font-weight: bold;-fx-text-fill: #3D2314;-fx-font-family: 'Times New Roman';");

		ImageView okImg = new ImageView("okButton.png");
		okImg.setFitWidth(50);
		okImg.setFitHeight(50);
		okButton = new Button("", okImg);
		okButton.setStyle("-fx-border-color: transparent; -fx-background-color: transparent;");

		scale(okButton);

		bpInstuction.setCenter(instractions);
		bpInstuction.setBottom(okButton);

		pane.getChildren().addAll(howImgBack, bpInstuction);
		Stage howStage = new Stage();
		Scene howScene = new Scene(pane, 1050, 200);

		howStage.setScene(howScene);

		// do the howPlayButton,okButton actions to open and close the instruction stage
		howPlayButton.setOnAction(e -> {
			howStage.show();
		});

		okButton.setOnAction(e -> {
			howStage.close();
		});

		// do the exit button action to exit from all the game
		exit.setOnAction(e -> {
			primaryStage.close();
		});

		// call the three method
		fileOption();
		manualOption();
		randomOption();

	}

	/*
	 * in this method i collected all the nodes in the borderPane and do the scene
	 * and stage to be the first screen in the game this method called in the main
	 * class
	 */
	public void show() {
		StackPane sp = new StackPane();
		BorderPane bp = new BorderPane();
		bp.setAlignment(okButton, Pos.BOTTOM_RIGHT);
		bp.setTop(hbTop);
		bp.setBottom(vbButtons);
		bp.setCenter(vbCenter);
		sp.getChildren().addAll(backGround, bp);
		scene = new Scene(sp);
		primaryStage.setScene(scene);
		primaryStage.setMaximized(true);
		primaryStage.setTitle("Coins game shaden-1220169");
		primaryStage.show();
	}

	/*
	 * in this method i set the file process and the file chooser and call the
	 * readFIle method to read the file
	 */
	public void fileOption() {
		stage = new Stage();
		fileChooser = new FileChooser();
		ImageView img = new ImageView("form.jpg");
		img.setFitWidth(100);
		img.setFitHeight(100);
		// do the fileButton action and put the file chooser to make the user choose the
		// file (can be txt or csv)
		fileButton.setOnAction(e -> {
			showAlert(Alert.AlertType.WARNING, "File Required",
					"the file you select must have the number of coins in the first row (in even number) then the coins values ,sth like the photo",
					img);

			fileChooser.setInitialDirectory(new File("C:\\"));
			fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
			fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("csv file", "*.csv"));

			selectedFile = fileChooser.showOpenDialog(stage);

			try {
				file = new File(selectedFile.getPath());

				try {
					// call the readFile method to read the file
					readFile();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}

			} catch (NullPointerException r) {
				showAlert(Alert.AlertType.ERROR, "File Required",
						"you must select a file!!! or choose anther way to give the coins.", null);
			} catch (InputMismatchException | NumberFormatException er) {
				showAlert(Alert.AlertType.ERROR, "File Required",
						"the file you selected dose not follow the specified form", null);
			}
		});

	}

	/*
	 * in this method i read the file and make sure that it valid by calling the
	 * checkFile method, and in this method i call the showFileScreen method who is
	 * in the fileOption class to show the required screen (go to the class to know
	 * the details)
	 */
	public void readFile() throws FileNotFoundException {
		FileOption f = new FileOption();
		Scanner scan = new Scanner(file);
		// call the checkFile method to check the file
		if (checkFile()) {
			scan.nextInt();
			int i = 0;
			// if everything OK start to read the file line by line (check also if the line
			// is empty or not) then full the array
			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				String a[] = line.split(" ");
				if (!line.isBlank() && a.length > 0 && i < array.length) {
					int number = Integer.parseInt(a[0]);
					array[i] = number;
					i++;
				}

			}
			// to go to the file stage
			f.showFileScreen();

		}

	}

	/*
	 * in this method i checked the file to see if its valid or not , by seeing if
	 * the size is there or not , if the coins is odd or even ,and if the coins
	 * negative or positive
	 */
	public boolean checkFile() throws FileNotFoundException {

		// check if there a file or not
		if (file == null) {
			showAlert(Alert.AlertType.ERROR, "file", "the file not founded", null);
			return false;
		}

		size = 0;
		Scanner scan = new Scanner(file);

		// check if the first thing is integer or not if not show the alert to the user
		if (!scan.hasNextInt()) {
			showAlert(Alert.AlertType.ERROR, "File Error", "The file format is incorrect. Size is missing or invalid.",
					null);
			return false;
		}

		// give the size the first number in the file then initialize the array
		size = scan.nextInt();
		array = new int[size];
		if (size > 30) {
			showAlert(Alert.AlertType.ERROR, "size", "the size is so big", null);
			return false;
		}
		// check the size if it odd or not or if its equal 0
		if (size % 2 != 0 || size == 0) {
			showAlert(Alert.AlertType.ERROR, "number type", "the coins number is odd it should be even", null);
			return false;
		} else {

			// then check the coins values if there valid or not(+or-) and count the lines
			int n = 0;
			while (scan.hasNextInt()) {
				int coin = scan.nextInt();
				if (coin <= 0) {
					showAlert(Alert.AlertType.ERROR, "Coin Value Error", "All coin values must be greater than 0.",
							null);
					return false;
				}
				n++;
			}

			// check if the lines greater or smaller than the size
			if (size % 2 == 0 && size != 0) {
				if (n > size)
					return true;
			} else {
				showAlert(Alert.AlertType.ERROR, "number type", "the coins number is odd it should be even", null);
				return false;
			}
			return true;
		}

	}

	/*
	 * in this method there the manualButton action which i call in it the
	 * numberField method to do full field process and the showManual method to show
	 * the interface both methods are in the ManualOption class(go to the class to
	 * know the details)
	 */
	public void manualOption() {
		ManualOption m = new ManualOption();
		manualButton.setOnAction(e -> {
			m.numberField();
			m.showManual();
		});

	}

	/*
	 * in this method there the randomButton action which i call in it the
	 * showRandomScreen method to show the interface which is in the RandomOption
	 * class(go to the class to know the details)
	 */
	public void randomOption() {
		RandomOption r = new RandomOption();
		randomButton.setOnAction(e -> {
			r.showRandomScreen();
		});

	}

	/*
	 * this method to help me to alert the user if there anything wrong i put the
	 * image to use it in the checkFile method to give the user a example how the
	 * file form should be
	 */
	private void showAlert(Alert.AlertType alertType, String title, String message, ImageView img) {
		try {
			Alert alert = new Alert(alertType);
			alert.setTitle(title);
			alert.setHeaderText(null);
			alert.setContentText(message);
			if (img != null)
				alert.setGraphic(img);
			alert.showAndWait();

		} catch (Exception e) {
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
	public Button getFileButton() {
		return fileButton;
	}

	public void setFileButton(Button fileButton) {
		this.fileButton = fileButton;
	}

	public Button getRandomButton() {
		return randomButton;
	}

	public void setRandomButton(Button randomButton) {
		this.randomButton = randomButton;
	}

	public Button getManualButton() {
		return manualButton;
	}

	public void setManualButton(Button manualButton) {
		this.manualButton = manualButton;
	}

	public Button getHowPlayButton() {
		return howPlayButton;
	}

	public void setHowPlayButton(Button howPlayButton) {
		this.howPlayButton = howPlayButton;
	}

	public Button getExit() {
		return exit;
	}

	public void setExit(Button exit) {
		this.exit = exit;
	}

	public FileChooser getFileChooser() {
		return fileChooser;
	}

	public void setFileChooser(FileChooser fileChooser) {
		this.fileChooser = fileChooser;
	}

	public File getSelectedFile() {
		return selectedFile;
	}

	public void setSelectedFile(File selectedFile) {
		this.selectedFile = selectedFile;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public static int[] getArray() {
		return array;
	}

	public static void setArray(int[] array) {
		FirstScreen.array = array;
	}

	public static Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void setPrimaryStage(Stage primaryStage) {
		FirstScreen.primaryStage = primaryStage;
	}

}