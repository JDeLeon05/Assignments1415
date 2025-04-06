import javafx.scene.layout.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
public class TicTacToe extends Application{
    //jfx variables
    GridPane gPane = new GridPane(1,1);
    Scene mainScene = new Scene(gPane, 500, 200);
    
    Tile[][] tile = new Tile[5][5];

    Text message = new Text();
    Button restarButton = new Button("Restart");

    //non-jfx variables
    char currentTurn = 'X';
    boolean[] winCon = new boolean[5];
    boolean gameEnded = false;
    int turn = 0;
    
    public static void main(String[] args) throws Exception {
        launch(args);
    }//end main

    @Override
    public void start(Stage PrimaryStage){
        //sets stage
        PrimaryStage.setScene(mainScene);
        PrimaryStage.setTitle("5x5 Tic Tac Toe");

        //sets grid pane
        gPane.setHgap(0);
        gPane.setVgap(1);
        gPane.setAlignment(Pos.CENTER_LEFT);
        gPane.getStyleClass().add("tile");

        //sets constraints for gridPane columns
        ColumnConstraints cConstraints = new ColumnConstraints();
        cConstraints.setPercentWidth(5);
        for(int i = 0; i < 5; i++){
            gPane.getColumnConstraints().add(cConstraints);
        }//end for

        //5x5 tic-tac-toe board
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                gPane.add(tile[i][j] = new Tile(), i + 1, j);
            }//end for
        }//end for
        
        //tells players who's turn it is
        gPane.add(message, 7, 2);
        message.setText(currentTurn + "'s Turn!");

        //button restarts the game
        gPane.add(restarButton, 7, 4);
        restarButton.setOnAction(e -> Restart());

        PrimaryStage.show();
    }//end start

    //Class for tiles in board
    class Tile extends Button{
        //stores what is placed on tile(X or O)
        char token = ' ';

        //constructor
        Tile(){
            setText("  ");
            setMinHeight(10);
            setMinWidth(10);
            setOnAction(e -> whenClicked());
        }//end Tile
        
        //method 
        private void whenClicked(){
            //makes sure token is unused and that game is still going
            if(token == ' ' && gameEnded == false){
                //increments a turn everytime a player makes a mobe
                turn++;

                //sets a tile to 'X' if it is 'X' player's turn
                if(currentTurn == 'X'){

                    setText("X");
                    token = currentTurn;
                    CheckWins();
                    currentTurn = 'O';

                //sets tile to 'O' if it's 'O' player's turn
                } else if(currentTurn == 'O'){

                    setText("0");
                    token = currentTurn;
                    CheckWins();
                    currentTurn = 'X';
                }//end else if

                //changes turn if no one's one the game yes
                if(gameEnded == false){
                    message.setText(currentTurn + "'s Turn!");
                }//end if
            }//end if
        }//end WhenClicked

    }//end Tile Class
    
    //method checks to see if any win condition is met
    void CheckWins(){
        //checks for horizontal win conditions
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                //stores value in an array of booleans
                if(tile[i][j].token == currentTurn){
                    winCon[j] = true;
                } else{
                    winCon[j] = false;
                }//end if else
            }//end for
            //determines if game has ended
            gameEnded = hasWon();
        }//end for

        //checks vertical win conditions
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                //stores value in an array of booleans
                if(tile[j][i].token == currentTurn){
                    winCon[j] = true;
                } else {
                    winCon[j] = false;
                }
            }
            //determines if game has ended
            gameEnded = hasWon();
        }
        
        //checks diagonal win condition
        for(int i = 0; i < 5; i++){
            if(tile[i][i].token == currentTurn){
                winCon[i] = true;
            } else{
                winCon[i] = false;
            }
            gameEnded = hasWon();
        }

        //checks other diagonal win condition
        for(int i = 4; i >= 0; i--){
            if(tile[4-i][i].token == currentTurn){
                winCon[i] = true;
            } else{
                winCon[i] = false;
            }
            gameEnded = hasWon();
        }

        //declares tie if all tiles are filled
        if(turn == 25 && gameEnded == false){
            gameEnded = true;
            message.setText("It was a Tie!");
        }//end if
        
    }//end CheckWins()

    //method checks to see if there was a winner
    boolean hasWon(){

        //checks if all variables in array are true(which would indicate that a win condition is met)
        for(boolean win : winCon){
            //allows game to continue if at least one variable is false and previous win condition wasn't met
            if(win == false && gameEnded != true){
                return false;
            }//end if
        }//end if

        //declares winner if all variable 
        message.setText(currentTurn + " has Won!");
        return true;
    }

    //restarts game
    private void Restart(){
        //sets all variables to start value
        turn = 0;
        currentTurn = 'X';
        message.setText(currentTurn + "'s turn");
        gameEnded = false;

        //removes tokens from all tiles
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                tile[i][j].token = ' ';
                tile[i][j].setText(" ");
            }//end for
        }//end for
        
    }//end Restart()

}//end class
