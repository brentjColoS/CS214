class GameStarter {
    Player player;
    Store store;

    public void startGame() {
        GameSetup gameSetup = new GameSetup();
        this.store = gameSetup.setupGame();
        this.player = new Player(100.0);
    }

    public Player getPlayer() {
        return this.player;
    }

    public Store getStore() {
        return this.store;
    }
}