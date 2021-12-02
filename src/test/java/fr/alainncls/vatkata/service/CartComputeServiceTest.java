package fr.alainncls.vatkata.service;

import fr.alainncls.vatkata.model.Cart;
import fr.alainncls.vatkata.model.Product;
import fr.alainncls.vatkata.model.ProductBatch;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CartComputeServiceTest {

    private final CartComputeService cartComputeService = new CartComputeService();

    @Test
    void computeTest() {
        Product product1 = Product.builder().name("livres").productType(Product.ProductType.BOOK).isImported(false).price(BigDecimal.valueOf(12.49)).build();
        Product product2 = Product.builder().name("CD musical").productType(Product.ProductType.OTHER).isImported(false).price(BigDecimal.valueOf(14.99)).build();
        Product product3 = Product.builder().name("barres de chocolat").productType(Product.ProductType.ESSENTIAL).isImported(false).price(BigDecimal.valueOf(0.85)).build();

        Cart cart = Cart.builder().id(1).productsBatches(List.of(
                        ProductBatch.builder().product(product1).productsNumber(2).build(),
                        ProductBatch.builder().product(product2).productsNumber(1).build(),
                        ProductBatch.builder().product(product3).productsNumber(3).build()))
                .build();

        Cart expectedCart = Cart.builder().id(1)
                .productsBatches(List.of(
                        ProductBatch.builder().product(product1).productsNumber(2).vat(BigDecimal.valueOf(1.25)).totalPrice(BigDecimal.valueOf(27.48)).build(),
                        ProductBatch.builder().product(product2).productsNumber(1).vat(BigDecimal.valueOf(3)).totalPrice(BigDecimal.valueOf(17.99)).build(),
                        ProductBatch.builder().product(product3).productsNumber(3).vat(BigDecimal.valueOf(0)).totalPrice(BigDecimal.valueOf(2.55)).build()))
                .totalVat(BigDecimal.valueOf(5.50))
                .totalPrice(BigDecimal.valueOf(48.02))
                .build();

        cartComputeService.compute(cart);

        assertEquals(expectedCart.getId(), cart.getId());
        assertThat(expectedCart.getTotalVat(), Matchers.comparesEqualTo(cart.getTotalVat()));
        assertThat(expectedCart.getTotalPrice(), Matchers.comparesEqualTo(cart.getTotalPrice()));

        assertThat(expectedCart.getProductsBatches().get(0).getVat(), Matchers.comparesEqualTo(cart.getProductsBatches().get(0).getVat()));
        assertThat(expectedCart.getProductsBatches().get(0).getTotalPrice(), Matchers.comparesEqualTo(cart.getProductsBatches().get(0).getTotalPrice()));

        assertThat(expectedCart.getProductsBatches().get(1).getVat(), Matchers.comparesEqualTo(cart.getProductsBatches().get(1).getVat()));
        assertThat(expectedCart.getProductsBatches().get(1).getTotalPrice(), Matchers.comparesEqualTo(cart.getProductsBatches().get(1).getTotalPrice()));

        assertThat(expectedCart.getProductsBatches().get(2).getVat(), Matchers.comparesEqualTo(cart.getProductsBatches().get(2).getVat()));
        assertThat(expectedCart.getProductsBatches().get(2).getTotalPrice(), Matchers.comparesEqualTo(cart.getProductsBatches().get(2).getTotalPrice()));
    }
}
