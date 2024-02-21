package gameUI;

public class Main {
    public static gameLogic.GameStatus GAME_STATUS = gameLogic.GameStatus.in_progress;
    public static Player currentPlayer = Player.black;

    public static Player togglePlayer() {
        currentPlayer = (currentPlayer == Player.black) ? Player.white : Player.black;
        OuterFrame.updateButton();
        OuterFrame.turnTimerRestart();
        return currentPlayer;
    }

    public static void main(String[] args) {
        StartPageFrame startPage = new StartPageFrame();
        //EndPageFrame endPage = new EndPageFrame();
    }
}
