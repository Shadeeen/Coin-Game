package application;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/*in this class when the two computers play and there the strategy should both goes with using the dynamic table */
public class PlayComputer {

	static ThirdScreenComputer th = new ThirdScreenComputer();
	static int sumComputer1 = 0;
	static int sumComputer2 = 0;
	static FlowPane fp1 = new FlowPane();
	static FlowPane fp2 = new FlowPane();
	static FlowPane fp1Vbox = new FlowPane();
	static FlowPane fp2Vbox = new FlowPane();
	static int[][] dpTable;
	static boolean isComputer1Turn = true;
	static ObservableList<Node> coinss;

	// in this method all the variables return to the initial value , then create
	// the dpTable then start the game
	public static void startGame(ObservableList<Node> observableList, ThirdScreenComputer th) {

		if (FirstScreen.array.length > 0) {
			fp1.getChildren().clear();
			fp2.getChildren().clear();
			fp1Vbox.getChildren().clear();
			fp2Vbox.getChildren().clear();
			sumComputer1 = 0;
			sumComputer2 = 0;
			th.getSum1().clear();
			th.getSum2().clear();
			th.dpTabel.setDisable(true);
			th.getShowReselt1().setDisable(true);
			th.getShowReselt2().setDisable(true);
			isComputer1Turn = true;
			th.stopButton.setDisable(false);
			int n = FirstScreen.array.length;
			dpTable = new int[n][n];
			coinss = observableList;
			computeDPTable(n);
			playGame(observableList, th);
		}
	}

	private static void computeDPTable(int n) {
		// the initial value: 1) one coin which the user will take it
		// 2) if just 2 coins take the max
		for (int i = 0; i < n; i++) {
			dpTable[i][i] = FirstScreen.array[i];
			if (i + 1 < n) {
				dpTable[i][i + 1] = Math.max(FirstScreen.array[i], FirstScreen.array[i + 1]);
			}
		}
		// this for the subarrays that have more than 2 coins
		for (int lenght = 3; lenght <= n; lenght++) {
			for (int i = 0; i <= n - lenght; i++) {
				int j = i + lenght - 1;

				int chooseFirst = FirstScreen.array[i] + Math.min(dpTable[i + 2][j], dpTable[i + 1][j - 1]);
				int chooseLast = FirstScreen.array[i] + Math.min(dpTable[i + 2][j], dpTable[i + 1][j - 1]);
				dpTable[i][j] = Math.max(chooseFirst, chooseLast);

				// If the player take the first coin (at index i):
				// 1)The player adds the value of the first coin (FirstScreen.array[i]).
				// 2) After this,its turn to the anther player(second one), which tries to
				// minimize the first player next move
				// 3) The second player can leave:
				// - The subarray starting at `i+2` and ending at `j` (if they take the first
				// coin)
				// - The subarray starting at `i+1` and ending at `j-1` (if they take the last
				// coin). , so we need the minimum between the two possibility
//				int chooseFirst;
//				if (Math.min(dpTable[i + 2][j], dpTable[i + 1][j - 1]) < FirstScreen.array[j]) {
//					chooseFirst = FirstScreen.array[j] + Math.min(dpTable[i + 2][j], dpTable[i + 1][j - 1]);
//				} else if (Math.min(dpTable[i + 2][j], dpTable[i + 1][j - 1]) < FirstScreen.array[i]) {
//					chooseFirst = FirstScreen.array[i] + Math.min(dpTable[i + 2][j], dpTable[i + 1][j - 1]);
//
//				} else
//					chooseFirst = FirstScreen.array[i] + Math.min(dpTable[i + 2][j], dpTable[i + 1][j - 1]);
//
//				int chooseLast;
//				if (Math.min(dpTable[i][j - 2], dpTable[i + 1][j - 1]) < FirstScreen.array[j]) {
//					chooseLast = FirstScreen.array[j] + Math.min(dpTable[i][j - 2], dpTable[i + 1][j - 1]);
//				} else if (Math.min(dpTable[i][j - 2], dpTable[i + 1][j - 1]) < FirstScreen.array[i]) {
//					chooseLast = FirstScreen.array[i] + Math.min(dpTable[i][j - 2], dpTable[i + 1][j - 1]);
//				} else {
//					chooseLast = FirstScreen.array[i] + Math.min(dpTable[i + 2][j], dpTable[i + 1][j - 1]);
//				}
//				dpTable[i][j] = Math.max(chooseFirst, chooseLast);
			}
		}
	}

	static Timeline timeline;
	static int i = 0;
	static int j = FirstScreen.array.length - 1;

	private static void playGame(ObservableList<Node> observableList, ThirdScreenComputer th) {
		// Logical indices for DP table and array
		i = 0;
		j = FirstScreen.array.length - 1;

		timeline = new Timeline(new KeyFrame(Duration.seconds(3), e -> {
			// If the game ends
			if (i > j) {
				endGame(th);
				timeline.stop();
				return;
			}

			// the Decision depend on the DP table
			boolean firstSelected = dpTable[i][j] == (FirstScreen.array[i]
					+ Math.min(getValue(i + 2, j), getValue(i + 1, j - 1)));

			int selectedCoin;
			if (firstSelected) {
				// choose the first coin
				selectedCoin = FirstScreen.array[i];
				// Update the Flow first coin
				updateFlowPane(selectedCoin, isComputer1Turn, th);
				i++;
			} else {
				// choose the last coin
				selectedCoin = FirstScreen.array[j];
				// Update the Flow for last coin
				updateFlowPane(selectedCoin, isComputer1Turn, th);
				j--;
			}

			// Remove the selected coin from the observable list
			removeButtonByValue(observableList, selectedCoin);

			isComputer1Turn = !isComputer1Turn;
		}));

		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();
	}

	public static ScrollPane populateGridPane() {
		GridPane gridPane = new GridPane();
		gridPane.setHgap(10);
		gridPane.setVgap(10);

		for (int i = 0; i < coinss.size(); i++) {
			String coinValue = ((Button) coinss.get(i)).getText();

			Label columnHeader = new Label(coinValue);
			columnHeader.setStyle("-fx-padding: 8;-fx-font-size: 12px; -fx-font-weight: bold;");
			gridPane.add(columnHeader, i + 1, 0);

			Label rowHeader = new Label(coinValue);
			rowHeader.setStyle("-fx-padding: 8;-fx-font-size: 12px; -fx-font-weight: bold;");
			gridPane.add(rowHeader, 0, i + 1);
		}

		for (int i = 0; i < dpTable.length; i++) {
			for (int j = i; j < dpTable.length; j++) {
				Label label = new Label(dpTable[i][j] == 0 ? "-" : String.valueOf(dpTable[i][j]));
				label.setStyle("-fx-padding:7; -fx-font-size: 12px;");
				gridPane.add(label, j + 1, i + 1);
			}
		}

		gridPane.setStyle("-fx-font-family: 'Times New Roman';");
		gridPane.setAlignment(Pos.CENTER);
		ScrollPane scroll = new ScrollPane();
		scroll.setContent(gridPane);
		scroll.setStyle("-fx-background:E5D0B1;");
		scroll.setPrefSize(500, 300);
		scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
		scroll.setFitToWidth(true);
		return scroll;
	}

	private static void updateFlowPane(int coinValue, boolean isComputer1, ThirdScreenComputer th) {
		Label label = new Label(String.valueOf(coinValue) + "    ");
		label.setStyle(
				"-fx-font-size: 20px;-fx-font-weight: bold;-fx-text-fill:#3D2314;-fx-font-family: 'Times New Roman';");
		Label label2 = new Label(String.valueOf(coinValue) + "    ");
		label2.setStyle(
				"-fx-font-size: 20px;-fx-font-weight: bold;-fx-text-fill:#3D2314;-fx-font-family: 'Times New Roman';");

		if (isComputer1) {
			sumComputer1 += coinValue;
			th.getSum1().setText(String.valueOf(sumComputer1));
			fp1.getChildren().add(label);
			fp1Vbox.getChildren().add(label2);
			fp1Vbox.setAlignment(Pos.CENTER);
		} else {
			sumComputer2 += coinValue;
			th.getSum2().setText(String.valueOf(sumComputer2));
			fp2.getChildren().add(label);
			fp2Vbox.getChildren().add(label2);
			fp2Vbox.setAlignment(Pos.CENTER);
		}
	}

	private static void removeButtonByValue(ObservableList<Node> observableList, int value) {
		for (Node node : observableList) {
			if (node instanceof Button) {
				Button button = (Button) node;
				if (Integer.parseInt(button.getText()) == value) {
					observableList.remove(node);
					break;
				}
			}
		}
	}

	// this method is when the game ended , make a screen show the winner name ,
	// this method describe all the interface
	public static void endGame(ThirdScreenComputer th) {
		th.stopButton.setDisable(true);
		VBox v = new VBox(7);
		Label endLabel = new Label(" GAME IS OVER");
		endLabel.setStyle(
				"-fx-font-size:30px;-fx-font-weight: bold;-fx-text-fill:#3D2314;-fx-font-family: 'Times New Roman';");

		Label winner;
		if (sumComputer1 > sumComputer2) {
			winner = new Label("Computer 1 is the WINNER");
		} else if (sumComputer1 < sumComputer2) {
			winner = new Label("Computer 2 is the WINNER");
		} else {
			winner = new Label("It's a TIE!");
		}
		th.dpTabel.setDisable(false);
		th.getShowReselt1().setDisable(false);
		th.getShowReselt2().setDisable(false);
		winner.setStyle(
				"-fx-font-size:25px;-fx-font-weight: bold;-fx-text-fill:#3D2314;-fx-font-family: 'Times New Roman';");
		v.getChildren().addAll(endLabel, winner);
		v.setAlignment(Pos.CENTER);
		Stage finishStage = new Stage();
		Scene finishScene = new Scene(v, 400, 200);
		finishStage.setScene(finishScene);
		timeline.stop();
		finishStage.show();
		th.scaleTransition.stop();
	}

	private static int getValue(int i, int j) {
		if (i >= 0 && j < dpTable.length && i <= j) {
			return dpTable[i][j];
		}
		return 0;
	}

}
