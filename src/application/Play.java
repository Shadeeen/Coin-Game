package application;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/*in this class when the two players play which each one try to win by calculate the biggest sum*/
public class Play {

	public Play() {
	}

	static int sum1 = 0;
	static int sum2 = 0;

	static int i = 0;
	static Label score1 = new Label();
	static Label score2 = new Label();

	// in this method all the variables return to the initial value and then start
	// the game
	public static void playarsSum(ObservableList<Node> observableList, ThirdScreen th) {

		if (observableList.size() > 0) {
			fp1.getChildren().clear();
			fp2.getChildren().clear();
			sum1 = 0;
			sum2 = 0;
			th.getSum1().clear();
			th.getSum2().clear();
			th.getShowReselt1().setDisable(true);
			th.getShowReselt2().setDisable(true);
			buttonDisable(observableList, th);

		}
	}

	static FlowPane fp1 = new FlowPane();
	static FlowPane fp2 = new FlowPane();

	// in this method its describe when the player choice the first or last coins
	private static void buttonClick(ObservableList<Node> observableList, int i, ThirdScreen th) {

		if (i < 0 || i >= observableList.size())
			return;

		// check which player should start first then calculate his own sum
		if (th.getFirstRadioButton().isSelected()) {
			Label label1 = new Label();
			label1.setText((((Button) observableList.get(i)).getText()) + " - ");
			sum1 += Integer.parseInt(((Button) observableList.get(i)).getText());
			th.getSum1().setText("" + sum1);
			fp1.getChildren().add(label1);
			label1.setStyle(
					"-fx-font-size: 20px;-fx-font-weight: bold;-fx-text-fill:#3D2314;-fx-font-family: 'Times New Roman';");

		} else {
			Label label2 = new Label();
			label2.setText((((Button) observableList.get(i)).getText()) + " - ");
			sum2 += Integer.parseInt(((Button) observableList.get(i)).getText());
			th.getSum2().setText("" + sum2);
			fp2.getChildren().add(label2);
			label2.setStyle(
					"-fx-font-size: 20px;-fx-font-weight: bold;-fx-text-fill:#3D2314;-fx-font-family: 'Times New Roman';");

		}

		// remove the chosen button from the list
		observableList.remove(i);

		// switch between the players
		if (th.getFirstRadioButton().isSelected()) {
			th.getSecRadioButton().setSelected(true);
		} else {
			th.getFirstRadioButton().setSelected(true);
		}

		// call this method to check which buttons should click and which is clicked
		buttonDisable(observableList, th);
	}

	// in this method its disable all the button except the first and last by
	// calling the enableFirstAndLast method
	public static void buttonDisable(ObservableList<Node> observableList, ThirdScreen th) {

		for (Node node : observableList) {
			node.setDisable(true);
		}

		if (observableList.size() > 0) {
			enableFirstAndLast(observableList, th);
		}

		// if the list is empty that mean the game is end
		if (observableList.size() == 0) {
			score1.setText("The score" + sum1);
			score2.setText("The score" + sum2);
			th.getShowReselt1().setDisable(false);
			th.getShowReselt2().setDisable(false);
			endGame(th);

		}

	}

	// in this method it take the first and last button and make them able to click
	// then check which one is clicked to call the buttonClick method
	private static void enableFirstAndLast(ObservableList<Node> observableList, ThirdScreen th) {
		if (observableList.size() > 0) {
			Button firstButton = (Button) observableList.get(0);
			Button lastButton = (Button) observableList.get(observableList.size() - 1);

			firstButton.setDisable(false);
			lastButton.setDisable(false);

			firstButton.setOnAction(e -> {
				buttonClick(observableList, 0, th);
			});

			lastButton.setOnAction(e -> {
				buttonClick(observableList, observableList.size() - 1, th);
			});
		}
	}

	// this method is when the game ended , make a screen show the winner name ,
	// this method describe all the interface
	public static void endGame(ThirdScreen th) {
		VBox v = new VBox(7);
		Label endLabel = new Label(" GAME IS OVER");
		endLabel.setStyle(
				"-fx-font-size:30px;-fx-font-weight: bold;-fx-text-fill:#3D2314;-fx-font-family: 'Times New Roman';");

		Label winner;
		if (sum1 > sum2)
			winner = new Label(th.getFirstRadioButton().getText() + " is the WINNER");
		else if (sum1 < sum2)
			winner = new Label(th.getSecRadioButton().getText() + " is the WINNER");
		else
			winner = new Label("NO ONE WIN :( ");
		winner.setStyle(
				"-fx-font-size:25px;-fx-font-weight: bold;-fx-text-fill:#3D2314;-fx-font-family: 'Times New Roman';");
		v.getChildren().addAll(endLabel, winner);
		v.setAlignment(Pos.CENTER);
		Stage finishStage = new Stage();
		Scene finishScene = new Scene(v, 400, 200);
		finishStage.setScene(finishScene);
		finishStage.show();

	}

}
