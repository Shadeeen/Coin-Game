package application;

import java.util.concurrent.atomic.AtomicBoolean;
import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

/*in this class i create the last big screen which when the computers start to play*/
public class ThirdScreenComputer {

	private Stage primaryStage = new Stage();
	private BorderPane bp = new BorderPane();
	private ImageView back = new ImageView("back7.jpg");
	private VBox v1 = new VBox(15);
	private VBox v2;
	private Label name1;
	private Label name2;
	private Button showReselt1;
	private Button showReselt2;
	private TextField sum1;
	private TextField sum2;
	Button dpTabel;
	static Button settings;
	private StackPane spWhich;
	private BorderPane bpWhich;
	private Label whichLabel;
	private static RadioButton firstRadioButton;
	private static RadioButton secRadioButton;
	private ToggleGroup group;
	private Button start;
	ScaleTransition scaleTransition;
	private SettingButton settingsClass = new SettingButton();
	private Button cancelButton;
	Button stopButton;

	/*
	 * in the constructor i get the interface ready with all the nodes with calling
	 * the setting staff from the setting class
	 */
	public ThirdScreenComputer() {

		back.fitWidthProperty().bind(primaryStage.widthProperty());
		back.fitHeightProperty().bind(primaryStage.heightProperty());
		back.setOpacity(0.3);

		Label animLabel = new Label("The Game Started!!!");
		animLabel.setStyle(
				"-fx-font-size: 50px;-fx-font-weight: bold;-fx-text-fill: #3D2314;-fx-font-family: 'Times New Roman';");

		scaleTransition = new ScaleTransition(Duration.seconds(1), animLabel);
		scaleTransition.setByX(0.2);
		scaleTransition.setByY(0.2);
		scaleTransition.setCycleCount(ScaleTransition.INDEFINITE);
		scaleTransition.setAutoReverse(true);

		scaleTransition.play();

		ImageView set = new ImageView("settings.png");
		set.setFitWidth(40);
		set.setFitHeight(40);

		settings = new Button("", set);
		settings.setStyle("-fx-border-color: transparent; -fx-background-color: transparent;");
		scale(settings);
		StackPane stackPane = new StackPane();
		stackPane.getChildren().addAll(animLabel, settings);
		StackPane.setAlignment(animLabel, Pos.CENTER);
		StackPane.setAlignment(settings, Pos.TOP_RIGHT);

		bp.setTop(stackPane);

		name1 = new Label();
		name1.setStyle(
				"-fx-font-size: 30px;-fx-font-weight: bold;-fx-text-fill:#3D2314;-fx-font-family: 'Times New Roman';");

		ImageView firstPlayarImg = new ImageView("player1.png");
		firstPlayarImg.setFitWidth(150);
		firstPlayarImg.setFitHeight(150);
		sum1 = new TextField();
		sum1.setStyle(
				"-fx-background-color:#e3c099; -fx-border-color:#3D2314; -fx-background-radius: 10px;-fx-border-radius: 10px; ");
		sum1.setAlignment(Pos.CENTER);
		sum1.setMaxSize(80, 100);
		sum1.setEditable(false);

		ImageView res1 = new ImageView("result.png");
		res1.setFitWidth(70);
		res1.setFitHeight(70);
		showReselt1 = new Button("", res1);
		showReselt1.setDisable(true);
		showReselt1.setStyle("-fx-border-color: transparent; -fx-background-color: transparent;");
		scale(settings);
		scale(showReselt1);
		v1.setAlignment(Pos.CENTER);
		v1.getChildren().addAll(name1, firstPlayarImg, sum1, showReselt1);
		bp.setMargin(v1, new Insets(0, 0, 0, 0));
		bp.setAlignment(v1, Pos.CENTER);
		bp.setLeft(v1);

		v2 = new VBox(15);

		name2 = new Label();
		name2.setStyle(
				"-fx-font-size: 30px;-fx-font-weight: bold;-fx-text-fill:#3D2314;-fx-font-family: 'Times New Roman';");

		ImageView secPlayarImg = new ImageView("player2.png");
		secPlayarImg.setFitWidth(150);
		secPlayarImg.setFitHeight(150);

		sum2 = new TextField();
		sum2.setStyle(
				"-fx-background-color:#e3c099; -fx-border-color:#3D2314; -fx-background-radius: 10px;-fx-border-radius: 10px; ");
		sum2.setAlignment(Pos.CENTER);
		sum2.setMaxSize(80, 100);
		sum2.setEditable(false);

		ImageView res2 = new ImageView("result2.png");
		res2.setFitWidth(70);
		res2.setFitHeight(70);
		showReselt2 = new Button("", res2);
		showReselt2.setStyle("-fx-border-color: transparent; -fx-background-color: transparent;");
		scale(showReselt2);
		showReselt2.setDisable(true);
		v2.setAlignment(Pos.CENTER);

		v2.getChildren().addAll(name2, secPlayarImg, sum2, showReselt2);
		bp.setMargin(v2, new Insets(0, 0, 0, 0));
		bp.setAlignment(v2, Pos.CENTER);
		bp.setRight(v2);

		ImageView tabelImg = new ImageView("table.png");
		tabelImg.setFitWidth(90);
		tabelImg.setFitHeight(90);
		dpTabel = new Button("", tabelImg);
		dpTabel.setStyle("-fx-border-color: transparent; -fx-background-color: transparent;");
		scale(dpTabel);

		dpTabel.setDisable(true);

		VBox vtS = new VBox(10);
		ImageView stopImg = new ImageView("stop.png");
		stopImg.setFitWidth(70);
		stopImg.setFitHeight(70);
		stopButton = new Button("", stopImg);
		stopButton.setStyle("-fx-border-color: transparent; -fx-background-color: transparent;");

		vtS.getChildren().addAll(stopButton, dpTabel);
		vtS.setAlignment(Pos.CENTER);
		scale(stopButton);
		bp.setBottom(vtS);
		bp.setMargin(vtS, new Insets(0, 0, 100, 0));
		bp.setAlignment(vtS, Pos.CENTER);

		ImageView startImg = new ImageView("start.png");
		startImg.setFitWidth(80);
		startImg.setFitHeight(80);
		start = new Button("", startImg);

		firstRadioButton = new RadioButton();
		secRadioButton = new RadioButton();

		AtomicBoolean flag = new AtomicBoolean(true);

		stopButton.setOnAction(e -> {
			if (flag.get()) {
				PlayComputer.timeline.stop();
				scaleTransition.stop();
				flag.set(false);
			} else {
				PlayComputer.timeline.play();
				scaleTransition.play();
				flag.set(true);
			}
		});

		// set action on the setting button to show the setting screen
		settings.setOnAction(e -> {
			PlayComputer.timeline.stop();
			settingsClass.settingSp = new StackPane();
			settingsClass.settingSp.getChildren().addAll(settingsClass.settingImage, settingsClass.settingVBox);
			Scene settingScene = new Scene(settingsClass.settingSp, 300, 320);
			settingsClass.settingStage.setScene(settingScene);
			settingsClass.settingStage.show();
			scaleTransition.stop();

		});
		// set action to the backSettingButton in the setting class to back to the game
		settingsClass.backSettingButton.setOnAction(e -> {
			PlayComputer.timeline.play();
			settingsClass.settingStage.close();
			scaleTransition.play();
		});
		// set action to the exitSettingButton in the setting class to exit game
		settingsClass.exitStartGameButton.setOnAction(e -> {
			PlayComputer.timeline.stop();
			settingsClass.settingStage.close();
			primaryStage.close();
			FirstScreen.getPrimaryStage().show();
		});
		// set action to the restartButton in the setting class to restart the game
		// which should know again who start first to restart again
		settingsClass.restartButton.setOnAction(e -> {
			if (f)
				firstRadioButton.setSelected(true);
			else
				secRadioButton.setSelected(true);
			coins();
			settingsClass.settingStage.close();
			scaleTransition.play();
		});

		// set action to the showResult button to know the result to the first computer
		showReselt1.setOnAction(e -> {
			scaleTransition.stop();
			Stage s = new Stage();
			Scene sce = new Scene(
					settingsClass.result(PlayComputer.fp1, PlayComputer.sumComputer1, PlayComputer.sumComputer2), 500,
					300);
			s.setScene(sce);
			s.show();

			settingsClass.getDone().setOnAction(r -> {
				s.close();
			});
		});

		// set action to the showResult button to know the result to the second computer
		showReselt2.setOnAction(e -> {
			scaleTransition.stop();
			Stage s = new Stage();
			Scene sce = new Scene(
					settingsClass.result(PlayComputer.fp2, PlayComputer.sumComputer2, PlayComputer.sumComputer1), 500,
					300);
			s.setScene(sce);
			s.show();

			settingsClass.getDone().setOnAction(r -> {
				s.close();
			});
		});

		start();
	}

	private Stage stage = new Stage();
	private Scene scene2;

	// in this method show the whole screen of the third screen computer term
	public void show() {

		StackPane sp = new StackPane();
		Scene scene = new Scene(sp);
		sp.getChildren().addAll(back, bp);
		scene.setRoot(sp);
		primaryStage.setScene(scene);
		primaryStage.setMaximized(true);
		primaryStage.show();
		FirstScreen.getPrimaryStage().close();
	}

	// in this method i create a interface to make clear to the user which side will
	// play first
	public void which() {

		spWhich = new StackPane();
		firstRadioButton.setSelected(true);
		firstRadioButton.setStyle(
				"-fx-font-size: 20px;-fx-font-weight: bold;-fx-text-fill:#3D2314;-fx-font-family: 'Times New Roman';");
		secRadioButton.setStyle(
				"-fx-font-size: 20px;-fx-font-weight: bold;-fx-text-fill:#3D2314;-fx-font-family: 'Times New Roman';");
		firstRadioButton.setText("Computer_1");
		secRadioButton.setText("Computer_2");
		secRadioButton.setDisable(true);
		bpWhich = new BorderPane();
		HBox hbWhich = new HBox(30);
		ImageView image = new ImageView("back9.jpg");
		image.fitWidthProperty().bind(stage.widthProperty());
		image.fitHeightProperty().bind(stage.heightProperty());
		image.setOpacity(0.3);

		ImageView vs = new ImageView("vs.png");
		vs.setFitWidth(90);
		vs.setFitHeight(90);
		whichLabel = new Label("Computer_1 will always start the game...");
		whichLabel.setStyle(
				"-fx-font-size: 30px;-fx-font-weight: bold;-fx-text-fill:#3D2314;-fx-font-family: 'Times New Roman';");
		whichLabel.setAlignment(Pos.CENTER);
		bpWhich.setTop(whichLabel);

		group = new ToggleGroup();
		firstRadioButton.setToggleGroup(group);
		secRadioButton.setToggleGroup(group);

		ImageView cancelImg = new ImageView("cancel.png");
		cancelImg.setFitWidth(50);
		cancelImg.setFitHeight(50);
		cancelButton = new Button("", cancelImg);
		cancelButton.setStyle("-fx-border-color: transparent; -fx-background-color: transparent;");
		scale(cancelButton);

		hbWhich.getChildren().addAll(firstRadioButton, vs, secRadioButton);
		bpWhich.setCenter(hbWhich);
		bpWhich.setLeft(cancelButton);
		bpWhich.setAlignment(cancelButton, Pos.TOP_LEFT);
		hbWhich.setAlignment(Pos.CENTER);

		start.setStyle("-fx-border-color: transparent; -fx-background-color: transparent;");
		scale(start);
		bpWhich.setRight(start);
		bpWhich.setAlignment(start, Pos.BOTTOM_RIGHT);
		bpWhich.setAlignment(hbWhich, Pos.CENTER);
		bpWhich.setAlignment(whichLabel, Pos.CENTER);
		spWhich.getChildren().addAll(image, bpWhich);
		bpWhich.setMargin(hbWhich, new Insets(0, 0, 0, 100));
		scene2 = new Scene(spWhich, 700, 300);
		stage.setScene(scene2);
		stage.show();
		scaleTransition.stop();

		cancelButton.setOnAction(e -> {
			stage.close();
			primaryStage.close();
			FirstScreen.getPrimaryStage().show();
			SecondScreen.getStage2().show();
		});

		if (firstRadioButton.isSelected())
			f = true;
		else
			f = false;

	}

	// in this method do an action on the start buttons which start the all process
	// game
	public void start() {

		start.setOnAction(e -> {
			PlayComputer.fp1Vbox.getChildren().clear();
			PlayComputer.fp2Vbox.getChildren().clear();
			coins();
			v1.getChildren().removeIf(node -> node instanceof FlowPane);
			v2.getChildren().removeIf(node -> node instanceof FlowPane);
			v1.getChildren().add(PlayComputer.fp1Vbox);
			v2.getChildren().add(PlayComputer.fp2Vbox);
			SecondScreen.getStage2().close();
			scaleTransition.play();
			stage.close();

		});

	}

	boolean f = true;

	// this method to start the game which put all the buttons in the flow pane and
	// call the playComputer class to start
	public void coins() {

		FlowPane fp = new FlowPane(15, 15);
		DropShadow dropShadow = new DropShadow();
		dropShadow.setOffsetX(6);
		dropShadow.setOffsetY(6);
		dropShadow.setColor(Color.SADDLEBROWN);

		for (int i = 0; i < FirstScreen.array.length; i++) {

			Button button = new Button("" + FirstScreen.array[i]);

			button.setStyle(
					"-fx-background-radius: 30; -fx-min-width: 80px; -fx-min-height: 80px;-fx-max-width: 80px;-fx-max-height: 80px; -fx-background-color:derive(#94673a,50%);-fx-border-color:transparent;fx-font-size: 25px;-fx-font-weight: bold;-fx-text-fill: #3D2314 ");
			button.setEffect(dropShadow);
			button.setAlignment(Pos.CENTER);

			fp.getChildren().add(button);

		}
		PlayComputer.startGame(fp.getChildren(), this);
		fp.setAlignment(Pos.TOP_CENTER);
		bp.setAlignment(fp, Pos.TOP_CENTER);

		bp.setMargin(fp, new Insets(150, 0, 0, 0));
		bp.setCenter(fp);

		ImageView img = new ImageView("okTabel.png");
		img.setFitWidth(70);
		img.setFitHeight(70);
		BorderPane bp2 = new BorderPane();
		Button doneButton = new Button("", img);
		bp2.setStyle("-fx-background-color:E5D0B1");
		bp2.setCenter(PlayComputer.populateGridPane());
		bp.setAlignment(PlayComputer.populateGridPane(), Pos.CENTER);
		bp2.setRight(doneButton);
		scale(doneButton);
		doneButton.setStyle("-fx-border-color: transparent; -fx-background-color: transparent;");
		bp2.setAlignment(doneButton, Pos.BOTTOM_RIGHT);
		Stage s = new Stage();
		Scene se = new Scene(bp2, 600, 700);
		s.setScene(se);
		// to show the dp table
		dpTabel.setOnAction(e -> {
			s.setMaximized(true);
			s.show();
			scaleTransition.stop();

		});
		doneButton.setOnAction(e -> {
			s.close();

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

	// a setters and getters methods
	public static RadioButton getFirstRadioButton() {
		return firstRadioButton;
	}

	public static void setFirstRadioButton(RadioButton firstRadioButton) {
		ThirdScreenComputer.firstRadioButton = firstRadioButton;
	}

	public static RadioButton getSecRadioButton() {
		return secRadioButton;
	}

	public static void setSecRadioButton(RadioButton secRadioButton) {
		ThirdScreenComputer.secRadioButton = secRadioButton;
	}

	public TextField getSum1() {
		return sum1;
	}

	public void setSum1(TextField sum1) {
		this.sum1 = sum1;
	}

	public TextField getSum2() {
		return sum2;
	}

	public void setSum2(TextField sum2) {
		this.sum2 = sum2;
	}

	public Button getShowReselt1() {
		return showReselt1;
	}

	public void setShowReselt1(Button showReselt1) {
		this.showReselt1 = showReselt1;
	}

	public Button getShowReselt2() {
		return showReselt2;
	}

	public void setShowReselt2(Button showReselt2) {
		this.showReselt2 = showReselt2;
	}

	public Label getName1() {
		return name1;
	}

	public void setName1(Label name1) {
		this.name1 = name1;
	}

	public Label getName2() {
		return name2;
	}

	public void setName2(Label name2) {
		this.name2 = name2;
	}

}
