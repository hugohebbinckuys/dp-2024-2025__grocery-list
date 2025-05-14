package fr.anthonyquere;

import fr.anthonyquere.GroceryShopServer;

public class MainWeb {
    public static void main(String[] args) {
        GroceryShopServer server = new GroceryShopServer(new SimpleGroceryShop());
        server.start(8080);
        System.out.println("Serveur démarré sur http://localhost:8080");

        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
