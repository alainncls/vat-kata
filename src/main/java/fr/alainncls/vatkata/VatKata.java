package fr.alainncls.vatkata;

import fr.alainncls.vatkata.model.Cart;
import fr.alainncls.vatkata.model.Product;
import fr.alainncls.vatkata.model.ProductBatch;
import fr.alainncls.vatkata.service.CartComputeService;
import fr.alainncls.vatkata.service.CartDisplayService;

import java.math.BigDecimal;
import java.util.List;

public class VatKata {

    public static void main(String[] args) {
        CartComputeService cartComputeService = new CartComputeService();
        CartDisplayService cartDisplayService = new CartDisplayService();

        /* CART 1 */
        Product product1 = Product.builder().name("livres").productType(Product.ProductType.BOOK).isImported(false).price(BigDecimal.valueOf(12.49)).build();
        Product product2 = Product.builder().name("CD musical").productType(Product.ProductType.OTHER).isImported(false).price(BigDecimal.valueOf(14.99)).build();
        Product product3 = Product.builder().name("barres de chocolat").productType(Product.ProductType.ESSENTIAL).isImported(false).price(BigDecimal.valueOf(0.85)).build();

        Cart cart1 = Cart.builder().id(1).productsBatches(List.of(
                        ProductBatch.builder().product(product1).productsNumber(2).build(),
                        ProductBatch.builder().product(product2).productsNumber(1).build(),
                        ProductBatch.builder().product(product3).productsNumber(3).build()))
                .build();

        cartComputeService.compute(cart1);
        cartDisplayService.display(cart1);

        /* CART 2 */
        Product product4 = Product.builder().name("boîtes de chocolat").productType(Product.ProductType.ESSENTIAL).isImported(true).price(new BigDecimal(10)).build();
        Product product5 = Product.builder().name("flacons de parfum").productType(Product.ProductType.OTHER).isImported(true).price(BigDecimal.valueOf(47.50)).build();

        Cart cart2 = Cart.builder().id(2).productsBatches(List.of(
                ProductBatch.builder().product(product4).productsNumber(2).build(),
                ProductBatch.builder().product(product5).productsNumber(3).build())).build();

        cartComputeService.compute(cart2);
        cartDisplayService.display(cart2);

        /* CART 3 */
        Product product6 = Product.builder().name("flacons de parfum").productType(Product.ProductType.OTHER).isImported(true).price(BigDecimal.valueOf(27.99)).build();
        Product product7 = Product.builder().name("flacon de parfum").productType(Product.ProductType.OTHER).isImported(false).price(BigDecimal.valueOf(18.99)).build();
        Product product8 = Product.builder().name("boîtes de pilules contre la migraine").productType(Product.ProductType.ESSENTIAL).isImported(false).price(BigDecimal.valueOf(9.75)).build();
        Product product9 = Product.builder().name("boîtes de chocolats").productType(Product.ProductType.ESSENTIAL).isImported(true).price(BigDecimal.valueOf(11.25)).build();

        Cart cart3 = Cart.builder().id(3).productsBatches(List.of(
                        ProductBatch.builder().product(product6).productsNumber(2).build(),
                        ProductBatch.builder().product(product7).productsNumber(1).build(),
                        ProductBatch.builder().product(product8).productsNumber(3).build(),
                        ProductBatch.builder().product(product9).productsNumber(2).build()))
                .build();

        cartComputeService.compute(cart3);
        cartDisplayService.display(cart3);
    }
}
