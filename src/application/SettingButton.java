package application;

import javafx.animation.ScaleTransition;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/*in this class i get the setting screen(interface) ready to use in the thirdScreen class 
 * and also get the finish game screen to appear when the game end
 */
public class SettingButton {

	Stage settingStage = new Stage();
	private Label settingLabel;
	Button backSettingButton;
	Button restartButton;
	Button exitStartGameButton;
	ImageView settingImage;
	StackPane settingSp;
	VBox settingVBox;
	private Button done;

	/*
	 * in the constructor i called the setting method which i do all the process in
	 */
	public SettingButton() {
		setting();
	}

	/*
	 * in this method i create all the interface to the setting screen with all the
	 * needed nodes
	 */
	public void setting() {
		settingVBox = new VBox();

		settingImage = new ImageView("back10.jpg");

		settingLabel = new Label("Coins Game Settings");
		settingLabel.setStyle(
				"-fx-font-size: 30px;-fx-font-weight: bold;-fx-text-fill: #3D2314;-fx-font-family: 'Times New Roman';");

		settingImage.fitWidthProperty().bind(settingStage.widthProperty());
		settingImage.fitHeightProperty().bind(settingStage.heightProperty());
		settingImage.setOpacity(0.3);

		ImageView restartImg = new ImageView("restart.png");
		restartImg.setFitWidth(50);
		restartImg.setFitHeight(50);

		restartButton = new Button("Restart", restartImg);
		restartButton.setStyle(
				"-fx-border-color: transparent; -fx-background-color: transparent;-fx-font-size: 20px;-fx-font-weight: bold;-fx-text-fill:#3D2314;-fx-font-family: 'Times New Roman';");

		ImageView bakeImg = new ImageView("backGame.png");
		bakeImg.setFitWidth(50);
		bakeImg.setFitHeight(50);

		backSettingButton = new Button("Back", bakeImg);
		backSettingButton.setStyle(
				"-fx-border-color: transparent; -fx-background-color: transparent;-fx-font-size: 20px;-fx-font-weight: bold;-fx-text-fill:#3D2314;-fx-font-family: 'Times New Roman';");

		ImageView exitImg = new ImageView("exitGame.png");
		exitImg.setFitWidth(70);
		exitImg.setFitHeight(70);

		exitStartGameButton = new Button("Exit", exitImg);
		exitStartGameButton.setStyle(
				"-fx-border-color: transparent; -fx-background-color: transparent;-fx-font-size: 20px;-fx-font-weight: bold;-fx-text-fill:#3D2314;-fx-font-family: 'Times New Roman';");

		scale(backSettingButton);
		scale(restartButton);
		scale(exitStartGameButton);

		settingVBox.getChildren().addAll(settingLabel, backSettingButton, restartButton, exitStartGameButton);
		settingVBox.setAlignment(Pos.CENTER);

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
	 * in this method i create the result screen which include the result to each
	 * player (name,score ,status,coins)
	 */
	public VBox result(FlowPane fp, int sum1, int sum2) {
		VBox vb = new VBox(5);
		ImageView m = new ImageView("win.png");
		ImageView m2 = new ImageView("loser.png");
		ImageView m3 = new ImageView("tie.png");

		m.setFitWidth(50);
		m.setFitHeight(50);

		m2.setFitWidth(50);
		m2.setFitHeight(50);

		m3.setFitWidth(50);
		m3.setFitHeight(50);
		Label Status;
		if (sum1 > sum2) {
			Status = new Label("THE WINNER", m);
		} else if (sum1 == sum2) {
			Status = new Label("TIE", m3);
		} else
			Status = new Label("THE LOSER", m2);

		Status.setStyle(
				"-fx-font-size: 35px;-fx-font-weight: bold;-fx-text-fill:#3D2314;-fx-font-family: 'Times New Roman';");

		Label scoreLabel = new Label("THE SCORE:\n" + "          " + sum1);
		scoreLabel.setStyle(
				"-fx-font-size: 25px;-fx-font-weight: bold;-fx-text-fill:#3D2314;-fx-font-family: 'Times New Roman';");
		Label coinsLabel = new Label("THE COINS:");
		coinsLabel.setStyle(
				"-fx-font-size: 25px;-fx-font-weight: bold;-fx-text-fill:#3D2314;-fx-font-family: 'Times New Roman';");
		ImageView doneim = new ImageView("done.png");
		doneim.setFitWidth(100);
		doneim.setFitHeight(100);
		done = new Button("", doneim);
		scale(done);
		done.setStyle("-fx-border-color: transparent; -fx-background-color: transparent;");
		fp.setAlignment(Pos.CENTER);
		vb.getChildren().addAll(Status, scoreLabel, coinsLabel, fp, done);
		vb.setAlignment(Pos.CENTER);

		return vb;
	}

	// a setters and getters methods
	public Button getDone() {
		return done;
	}

	public void setDone(Button done) {
		this.done = done;
	}

}
