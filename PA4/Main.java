public class Main {
    
    public void exposeGameSetup() {
        GameSetup gameSetup = new GameSetup();
        gameSetup.setupGame();
    }

    public void exposeGameStop() {
        GameStopper gameEnd = new GameStopper();
        gameEnd.stopGame();
    }
    
    public void exposeGameStart() {
        GameStarter start = new GameStarter();        
        start.startGame();
    }

    public void exposeGamePlay() {
        GamePlay game = new GamePlay();
        game.playGame();
    }
    
    public static void main(String[] args) {
        GamePlay game = new GamePlay();
        game.playGame();
    }
}