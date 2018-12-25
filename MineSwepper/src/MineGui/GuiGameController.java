package MineGui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import mineswipper.AutoPlayer;
import mineswipper.GuiPlayer;
import mineswipper.MoveType;
import mineswipper.Player;
import mineswipper.PlayerMove;
import mineswipper.RandomAIPlayer;
import mineswipper.Square;
import mineswipper.SquareStatus;

public class GuiGameController implements Initializable {

    @FXML
    Button myNewGameButton;

    @FXML
    GridPane myGridPane;

    @FXML
    private Label ScoreLabel;

    @FXML
    private HBox winandlosehbox;

    @FXML
    private Label winandloselabel;

    Button[][] matrix;

    int length;

    int width;

    boolean loseFlag;

    boolean winFlag;

    public GuiGameController() {
        loseFlag = false;
        winFlag = false;
        width = GuiGame.guiGame.grid.getM();
        length = GuiGame.guiGame.grid.getN();
    }

    public GridPane GenerateMatrixOfButtons() {
        width = GuiGame.guiGame.grid.getM();
        length = GuiGame.guiGame.grid.getN();
        myGridPane.setPadding(new Insets(40, 0, 0, 275));
        myGridPane.setHgap(2);
        myGridPane.setVgap(2);
        matrix = new Button[length][width];
        for (int x = 0; x < length; x++) {
            for (int y = 0; y < width; y++) {
                matrix[x][y] = new Button();
                String coordinate = x + "" + ((char) (y + 65));
                matrix[x][y].setId(coordinate);
                matrix[x][y].setOnMousePressed(new EventHandler<MouseEvent>() {

                    
                    //Mark move
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        if (mouseEvent.isSecondaryButtonDown()) {
                            Button xButton = (Button) mouseEvent.getSource();
                            String move = xButton.getId();
                            PlayerMove temp = new PlayerMove();
                            Square tempsquare = new Square();
                            char[] tempchararray = move.toCharArray();
                            int x, y;
                            x = 0;
                            if (Character.isDigit(move.charAt(1))) {
                                System.out.println("AAAAAAAAAAAAAaaaa");
                                x += Character.getNumericValue(tempchararray[0]) * 10;
                                x += Character.getNumericValue(tempchararray[1]);
                                char c = tempchararray[2];
                                y = (int) c - 65;
                            } else {
                                x = Character.getNumericValue(tempchararray[0]);
                                char c = tempchararray[1];
                                y = (int) c - 65;
                            }
                            System.out.println(move + x + " " + y);
                            tempsquare.setX(x);
                            tempsquare.setY(y);
                            temp.player = GuiGame.guiGame.currentPlayer;
                            MoveType tempmovetype = new MoveType();
                            tempmovetype.type = "Mark";
                            temp.square = tempsquare;
                            temp.type = tempmovetype;
                            boolean checkmove = GuiGame.guiGame.AcceptMove(temp);
                            if (checkmove) {
                                updateGuiMatrix();
                            }
                        }
                    }

                    public void updateGuiMatrix() {
                        SquareStatus squarestatus = new SquareStatus();
                        int n = GuiGame.guiGame.grid.getN();
                        int m = GuiGame.guiGame.grid.getM();
                        for (int i = 0; i < n; i++) {
                            for (int j = 0; j < m; j++) {
                                squarestatus = GuiGame.guiGame.grid.square[i][j].getCurrentSquareStatus();
                                if (squarestatus.getStatus() == SquareStatus.status.OpenedEmpty) {
                                    matrix[i][j].getStyleClass().removeAll("buttons");
                                    matrix[i][j].getStyleClass().addAll("emptySquare");
                                }
                                if (squarestatus.getStatus() == SquareStatus.status.oneMine) {
                                    matrix[i][j].getStyleClass().removeAll("buttons");
                                    matrix[i][j].getStyleClass().addAll("oneMine");
                                }
                                if (squarestatus.getStatus() == SquareStatus.status.twoMines) {
                                    matrix[i][j].getStyleClass().removeAll("buttons");
                                    matrix[i][j].getStyleClass().addAll("twoMine");
                                }
                                if (squarestatus.getStatus() == SquareStatus.status.threeMines) {
                                    matrix[i][j].getStyleClass().removeAll("buttons");
                                    matrix[i][j].getStyleClass().addAll("threeMine");
                                }
                                if (squarestatus.getStatus() == SquareStatus.status.fourMines) {
                                    matrix[i][j].getStyleClass().removeAll("buttons");
                                    matrix[i][j].getStyleClass().addAll("fourMine");
                                }
                                if (squarestatus.getStatus() == SquareStatus.status.fiveMines) {
                                    matrix[i][j].getStyleClass().removeAll("buttons");
                                    matrix[i][j].getStyleClass().addAll("fiveMine");
                                }
                                if (squarestatus.getStatus() == SquareStatus.status.sixMines) {
                                    matrix[i][j].getStyleClass().removeAll("buttons");
                                    matrix[i][j].getStyleClass().addAll("sixMine");
                                }
                                if (squarestatus.getStatus() == SquareStatus.status.sevenMines) {
                                    matrix[i][j].getStyleClass().removeAll("buttons");
                                    matrix[i][j].getStyleClass().addAll("sevenMine");
                                }
                                if (squarestatus.getStatus() == SquareStatus.status.eightMines) {
                                    matrix[i][j].getStyleClass().removeAll("buttons");
                                    matrix[i][j].getStyleClass().addAll("eightMine");
                                }
                                if (squarestatus.getStatus() == SquareStatus.status.Marked) {
                                    matrix[i][j].getStyleClass().removeAll("buttons");
                                    matrix[i][j].getStyleClass().addAll("Marked");
                                }
                                if (squarestatus.getStatus() == SquareStatus.status.Closed) {
                                    matrix[i][j].getStyleClass().removeAll("Marked");
                                    matrix[i][j].getStyleClass().addAll("buttons");
                                }
                                if (loseFlag) {
                                    if (squarestatus.getStatus() == SquareStatus.status.OpenedMine) {
                                        matrix[i][j].getStyleClass().removeAll("buttons");
                                        matrix[i][j].getStyleClass().addAll("boMine");
                                    }
                                }
                                if (winFlag) {
                                    if (squarestatus.getStatus() == SquareStatus.status.OpenedMine) {
                                        matrix[i][j].getStyleClass().removeAll("buttons");
                                        matrix[i][j].getStyleClass().addAll("wiMine");
                                    }
                                }
                            }
                        }
                        String theScore = "";
                        for (int ii = 0; ii < GuiGame.guiGame.players.size(); ii++) {
                            int score = GuiGame.guiGame.players.get(ii).currentScore;
                            theScore += GuiGame.guiGame.players.get(ii).name + " Score : " + score + "\n";
                        }
                        theScore += "--------------\nPlayer Turn : \n" + GuiGame.guiGame.currentPlayer.name;
                        ScoreLabel.setText(theScore);
                    }
                });
                matrix[x][y].setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                        Button xButton = (Button) event.getSource();
                        String move = xButton.getId();
                        PlayerMove temp = new PlayerMove();
                        Square tempsquare = new Square();
                        char[] tempchararray = move.toCharArray();
                        int x, y;
                        x = 0;
                        if (Character.isDigit(move.charAt(1))) {
                           // System.out.println("AAAAAAAAAAAAAaaaa");
                            x += Character.getNumericValue(tempchararray[0]) * 10;
                            x += Character.getNumericValue(tempchararray[1]);
                            char c = tempchararray[2];
                            y = (int) c - 65;
                        } else {
                            x = Character.getNumericValue(tempchararray[0]);
                            char c = tempchararray[1];
                            y = (int) c - 65;
                        }
                        System.out.println(move + x + " " + y);
                        tempsquare.setX(x);
                        tempsquare.setY(y);
                        temp.player = GuiGame.guiGame.currentPlayer;
                        MoveType tempmovetype = new MoveType();
                        tempmovetype.type = "Reveal";
                        temp.square = tempsquare;
                        temp.type = tempmovetype;
                        boolean checkmove = GuiGame.guiGame.AcceptMove(temp);
                        if (checkmove) {
                            if (GuiGame.guiGame.checkifwin()) {
                                winandloselabel.setText(GuiGame.guiGame.currentPlayer.name + " is Winner");
                                GuiGame.guiGame.grid.printGrid();
                                winandlosehbox.setVisible(true);
                                System.out.println(GuiGame.guiGame.currentPlayer.name + " Score : " + GuiGame.guiGame.currentPlayer.currentScore);
                                winFlag = true;
                                myGridPane.setDisable(winFlag);
                                updateGuiMatrix();
                                return;
                            }
                            if (GuiGame.guiGame.grid.square[x][y].isMine()) {
                                loseFlag = true;
                                myGridPane.setDisable(loseFlag);
                                winandlosehbox.setVisible(loseFlag);
                                winandloselabel.setText(GuiGame.guiGame.currentPlayer.name + " GameOver !");
                                SquareStatus tempSquarestatus = new SquareStatus();
                                tempSquarestatus.setStatus(SquareStatus.status.OpenedMine);
                                GuiGame.guiGame.grid.square[x][y].setCurrentSquareStatus(tempSquarestatus);
                                updateGuiMatrix();
                                return;
                            }
                            GuiGame.guiGame.grid.printGrid();
                            if (!(GuiGame.guiGame.grid.square[x][y].isMine()))
                                GuiGame.guiGame.currentPlayer = GuiGame.guiGame.currentRules.DecideNextPlayer(GuiGame.guiGame.moves);
                            updateGuiMatrix();
                            
                            if (GuiGame.guiGame.currentPlayer instanceof AutoPlayer) {
                                checkmove = false;
                                while (!checkmove) {
                                    temp = GuiGame.guiGame.currentPlayer.GetPlayerMove();
                                    checkmove = GuiGame.guiGame.AcceptMove(temp);
                                    if (checkmove) {
                                        if (GuiGame.guiGame.checkifwin()) {
                                            winandloselabel.setText(GuiGame.guiGame.currentPlayer.name + " is Winner");
                                            GuiGame.guiGame.grid.printGrid();
                                            winandlosehbox.setVisible(true);
                                            System.out.println(GuiGame.guiGame.currentPlayer.name + " Score : " + GuiGame.guiGame.currentPlayer.currentScore);
                                            winFlag = true;
                                            myGridPane.setDisable(winFlag);
                                            updateGuiMatrix();
                                            return;
                                        }
                                        if (GuiGame.guiGame.grid.square[temp.square.getX()][temp.square.getY()].isMine()) {
                                            loseFlag = true;
                                            myGridPane.setDisable(loseFlag);
                                            winandlosehbox.setVisible(loseFlag);
                                            winandloselabel.setText(GuiGame.guiGame.currentPlayer.name + " GameOver !");
                                            SquareStatus tempSquarestatus = new SquareStatus();
                                            tempSquarestatus.setStatus(SquareStatus.status.OpenedMine);
                                            GuiGame.guiGame.grid.square[x][y].setCurrentSquareStatus(tempSquarestatus);
                                            updateGuiMatrix();
                                            return;
                                        }
                                        GuiGame.guiGame.grid.printGrid();
                                    }
                                }
                                updateGuiMatrix();
                                GuiGame.guiGame.currentPlayer = GuiGame.guiGame.currentRules.DecideNextPlayer(GuiGame.guiGame.moves);
                            }
                        }
                    }

                    public void updateGuiMatrix() {
                        SquareStatus squarestatus = new SquareStatus();
                        int n = GuiGame.guiGame.grid.getN();
                        int m = GuiGame.guiGame.grid.getM();
                        for (int i = 0; i < n; i++) {
                            for (int j = 0; j < m; j++) {
                                squarestatus = GuiGame.guiGame.grid.square[i][j].getCurrentSquareStatus();
                                if (squarestatus.getStatus() == SquareStatus.status.OpenedEmpty) {
                                    matrix[i][j].getStyleClass().removeAll("buttons");
                                    matrix[i][j].getStyleClass().addAll("emptySquare");
                                }
                                if (squarestatus.getStatus() == SquareStatus.status.oneMine) {
                                    matrix[i][j].getStyleClass().removeAll("buttons");
                                    matrix[i][j].getStyleClass().addAll("oneMine");
                                }
                                if (squarestatus.getStatus() == SquareStatus.status.twoMines) {
                                    matrix[i][j].getStyleClass().removeAll("buttons");
                                    matrix[i][j].getStyleClass().addAll("twoMine");
                                }
                                if (squarestatus.getStatus() == SquareStatus.status.threeMines) {
                                    matrix[i][j].getStyleClass().removeAll("buttons");
                                    matrix[i][j].getStyleClass().addAll("threeMine");
                                }
                                if (squarestatus.getStatus() == SquareStatus.status.fourMines) {
                                    matrix[i][j].getStyleClass().removeAll("buttons");
                                    matrix[i][j].getStyleClass().addAll("fourMine");
                                }
                                if (squarestatus.getStatus() == SquareStatus.status.fiveMines) {
                                    matrix[i][j].getStyleClass().removeAll("buttons");
                                    matrix[i][j].getStyleClass().addAll("fiveMine");
                                }
                                if (squarestatus.getStatus() == SquareStatus.status.sixMines) {
                                    matrix[i][j].getStyleClass().removeAll("buttons");
                                    matrix[i][j].getStyleClass().addAll("sixMine");
                                }
                                if (squarestatus.getStatus() == SquareStatus.status.sevenMines) {
                                    matrix[i][j].getStyleClass().removeAll("buttons");
                                    matrix[i][j].getStyleClass().addAll("sevenMine");
                                }
                                if (squarestatus.getStatus() == SquareStatus.status.eightMines) {
                                    matrix[i][j].getStyleClass().removeAll("buttons");
                                    matrix[i][j].getStyleClass().addAll("eightMine");
                                }
                                if (squarestatus.getStatus() == SquareStatus.status.Marked) {
                                    matrix[i][j].getStyleClass().removeAll("buttons");
                                    matrix[i][j].getStyleClass().addAll("Marked");
                                }
                                if (squarestatus.getStatus() == SquareStatus.status.Closed) {
                                    matrix[i][j].getStyleClass().removeAll("Marked");
                                    matrix[i][j].getStyleClass().addAll("buttons");
                                }
                                if (loseFlag) {
                                    if (squarestatus.getStatus() == SquareStatus.status.OpenedMine) {
                                        matrix[i][j].getStyleClass().removeAll("buttons");
                                        matrix[i][j].getStyleClass().addAll("boMine");
                                    }
                                }
                                if (winFlag) {
                                    if (squarestatus.getStatus() == SquareStatus.status.OpenedMine) {
                                        matrix[i][j].getStyleClass().removeAll("buttons");
                                        matrix[i][j].getStyleClass().addAll("wiMine");
                                    }
                                }
                            }
                        }
                        String theScore = "";
                        for (int ii = 0; ii < GuiGame.guiGame.players.size(); ii++) {
                            int score = GuiGame.guiGame.players.get(ii).currentScore;
                            theScore += GuiGame.guiGame.players.get(ii).name + " Score : " + score + "\n";
                        }
                        theScore += "--------------\nPlayer Turn : \n" + GuiGame.guiGame.currentPlayer.name;
                        ScoreLabel.setText(theScore);
                    }
                });
                matrix[x][y].getStyleClass().addAll("buttons");
                myGridPane.add(matrix[x][y], y, x);
            }
        }
        return myGridPane;
    }

    @FXML
    private void newGameButton(ActionEvent event) throws IOException {
        try {
            GuiGame.guiGame.players.clear();
            Player human1 = new GuiPlayer("Taleb");
            Player human2 = new GuiPlayer("Majd");
            GuiGame.guiGame.players.add(human1);
            GuiGame.guiGame.players.add(human2);
            Stage primaryStage = (Stage) myNewGameButton.getScene().getWindow();
            Parent newRoot = FXMLLoader.load(getClass().getResource("MainGame.fxml"));
            primaryStage.getScene().setRoot(newRoot);
            GuiGame.guiGame.initGame();
            primaryStage.show();
        } catch (Exception e) {
        } finally {
        }
    }

    @FXML
    private void playvspc(ActionEvent event) throws IOException {
        try {
            GuiGame.guiGame.players.clear();
            Player human1 = new GuiPlayer("Taleb");
            AutoPlayer autoPlayer = new RandomAIPlayer(GuiGame.guiGame.grid.getN(), GuiGame.guiGame.grid.getM());
            GuiGame.guiGame.players.add(human1);
            GuiGame.guiGame.players.add(autoPlayer);
            Stage primaryStage = (Stage) myNewGameButton.getScene().getWindow();
            Parent newRoot = FXMLLoader.load(getClass().getResource("MainGame.fxml"));
            primaryStage.getScene().setRoot(newRoot);
            GuiGame.guiGame.initGame();
            primaryStage.show();
        } catch (Exception e) {
        } finally {
        }
    }

    @FXML
    private void playAgainButton(ActionEvent event) throws IOException {
        try {
            Stage primaryStage = (Stage) matrix[0][0].getScene().getWindow();
            Parent newRoot = FXMLLoader.load(getClass().getResource("GuiGame.fxml"));
            primaryStage.getScene().setRoot(newRoot);
            primaryStage.show();
            GuiGame.guiGame.initGame();
            primaryStage.getScene().setRoot(newRoot);
        } catch (Exception e) {
        } finally {
        }
    }
       @FXML
    private void SinglePlayer(ActionEvent event) throws IOException {
            try {
            GuiGame.guiGame.players.clear();
            Player human1 = new GuiPlayer("Taleb");
            GuiGame.guiGame.players.add(human1);
            Stage primaryStage = (Stage) myNewGameButton.getScene().getWindow();
            Parent newRoot = FXMLLoader.load(getClass().getResource("MainGame.fxml"));
            primaryStage.getScene().setRoot(newRoot);
            GuiGame.guiGame.initGame();
            primaryStage.show();
        } catch (Exception e) {
        } finally {
        }
    }

    @FXML
    private void OptionMenu(ActionEvent event) throws IOException {
        try {
            Stage primaryStage = (Stage) myNewGameButton.getScene().getWindow();
            Parent newRoot = FXMLLoader.load(getClass().getResource("OprtionMenu.fxml"));
            primaryStage.getScene().setRoot(newRoot);
            primaryStage.show();
            primaryStage.getScene().setRoot(newRoot);
        } catch (Exception e) {
        } finally {
        }
    }

    public void updatescores() {
        String theScore = "";
        for (int ii = 0; ii < GuiGame.guiGame.players.size(); ii++) theScore += GuiGame.guiGame.players.get(ii).name + " Score : " + GuiGame.guiGame.players.get(ii).currentScore + "\n";
        theScore += "--------------\nPlayer Turn : \n" + GuiGame.guiGame.currentPlayer.name;
        ScoreLabel.setText(theScore);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (myGridPane != null) {
            GenerateMatrixOfButtons();
        }
        if (ScoreLabel != null) {
            updatescores();
        }
    }
}
