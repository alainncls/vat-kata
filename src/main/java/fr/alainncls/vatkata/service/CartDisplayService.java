package fr.alainncls.vatkata.service;

import fr.alainncls.vatkata.model.Cart;
import fr.alainncls.vatkata.model.Product;

public class CartDisplayService {

    public CartDisplayService() {
        // NOOP
    }

    public void display(Cart cart) {
        System.out.println("\n=============");
        System.out.println("| Panier #" + cart.getId() + " |");
        System.out.println("=============\n");

        cart.getProductsBatches().forEach(batch -> {
            Product product = batch.getProduct();
            System.out.printf(batch.getProductsNumber() + " " + product.getName() + (product.isImported() ? " (importé)" : "") + " à " + product.getPrice() + "€ : %.2f€ TTC", batch.getTotalPrice());
            System.out.printf("%n");
        });

        System.out.printf("\nMontant des taxes : %.2f€", cart.getTotalVat());
        System.out.printf("\nTotal : %.2f€", cart.getTotalPrice());
        System.out.printf("%n");
    }
}
