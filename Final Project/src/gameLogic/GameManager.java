package gameLogic;

import java.util.Scanner;

import gameUI.BoardPanel;
import gameUI.Main;
import gameUI.OuterFrame;
import gameUI.Player;

public class GameManager {
    private BoardPanel _board;
    final private int sequence_of = 5;

    public GameManager(BoardPanel board) {
        _board = board;
    }

    /* Input: Row index, Column index
     * Output: True if the move is valid, false otherwise
     * Description: Check if the specified move is valid on the current board
     */
    public boolean isValidMove(int i, int j) throws InvalidMoveException {
        if (!isValidIndex(i) || !isValidIndex(j)) {
            throw new InvalidMoveException("Invalid Move, Out Of Board Bounds!");
        } else if (!_board.getSquare(i, j).isHasSoldier()) {
            throw new InvalidMoveException("Invalid Move, Already Has Soldier!");
        } else {
            return true;
        }
    }

    /* Input: N/A
     * Output: N/A
     * Description: Calculate the winner based on the current state of the board
     */
    public void winnerCulc() {
        winnerCulcHelper('C');
        winnerCulcHelper('R');
        winnerCulcHelper('D');
        winnerCulcHelper('A');

        // Check for a draw
        gameUI.Square[][] sBoard = _board.getPanels();
        int boardSize = sBoard.length;
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (sBoard[i][j].isHasSoldier()) {
                    return; // If any empty square is found, the game is not a draw yet
                }
            }
        }
        // If no empty squares are found, and no player has won, then it's a draw
        gameUI.Main.GAME_STATUS = GameStatus.draw;
    }

    /* Input: Check type (R, C, D, A)
     * Output: N/A
     * Description: Helper method to check for a winner in rows, columns, and diagonals
     */
    public void winnerCulcHelper(char checkType) {
        int count = 0, iType = 0, jType = 0;
        switch (checkType) {
            case 'R':
                iType = 0;
                jType = 1;
                break;
            case 'C':
                iType = 1;
                jType = 0;
                break;
            case 'D':
                iType = 1;
                jType = 1;
                break;
            case 'A':
                iType = -1;
                jType = 1;
                break;
            default:
                break;
        }
        gameUI.Square[][] sBoard = _board.getPanels();
        int boardSize = sBoard.length;

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                count = 0;
                for (int k = 0; k < sequence_of; k++) {
                    int rowIdx = i + k * iType;
                    int colIdx = j + k * jType;
                    if (isValidIndex(rowIdx) && isValidIndex(colIdx)) {
                        if (sBoard[rowIdx][colIdx].getPlayer() == sBoard[i][j].getPlayer()
                                && sBoard[rowIdx][colIdx].isHasSoldier()) {
                            count++;
                        } else {
                            count = 0;
                        }
                        if (count >= sequence_of) {
                        	gameUI.Main.GAME_STATUS = (gameUI.Main.currentPlayer == Player.black) ? GameStatus.black_player_win : GameStatus.white_player_win;
                            return;
                        }
                    }
                }
            }
        }
    }

    /* Input: Index
     * Output: True if the index is valid, false otherwise
     * Description: Check if the specified index is within the valid board bounds
     */
    public boolean isValidIndex(int i) {
        return (i >= 0 && i <= 14);
    }

    /* Input: N/A
     * Output: N/A
     * Description: Start the game and handle player moves until there is a winner or a draw
     */
    public void startGame(int i,int j) {
        if(gameUI.Main.GAME_STATUS == GameStatus.in_progress) {
            //i = s.nextInt();
            //j = s.nextInt();

            try {
                if (isValidMove(i, j)) {
                    //board.updateBoard((currPlayer) ? Soldier.BLACK : Soldier.WHITE, i, j);
                    winnerCulc();
                    //currPlayer = !currPlayer;
                } else {
                    System.out.println("Invalid move. Try again.");
                }
            } catch (InvalidMoveException e) {
                System.out.println(e.getMessage());
            }
        }

        switch (gameUI.Main.GAME_STATUS) {
            case black_player_win:
                System.out.println("Black player wins!");
                OuterFrame.endGameWrapper();
                break;
            case white_player_win:
                System.out.println("White player wins!");
                OuterFrame.endGameWrapper();
                break;
            case draw:
                System.out.println("It's a draw!");
                OuterFrame.endGameWrapper();
                break;
            case in_progress:
                System.out.println("Next Turn!");
                break;
            default:
                System.out.println("Unexpected game status.");
                //OuterFrame.endGame();
        }

    }
}
