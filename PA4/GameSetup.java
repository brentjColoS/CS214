class GameSetup {
    public Store setupGame() {
        StoreItem sword = new StoreItem("Sword", 10.0);
        StoreItem potion = new StoreItem("Health Potion", 5.0);
        StoreItem hat = new StoreItem("Hat", 11);

        Store store = new Store();
        store.addItem(sword);
        sword.addTag(0);
        store.addItem(potion);
        potion.addTag(0);
        potion.addTag(1);
        store.addItem(hat);
        hat.addTag(0);
        hat.addTag(2);

        return store;
    }
}