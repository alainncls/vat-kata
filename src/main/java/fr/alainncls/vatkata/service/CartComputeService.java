package fr.alainncls.vatkata.service;

import fr.alainncls.vatkata.model.Cart;
import fr.alainncls.vatkata.model.Product;
import fr.alainncls.vatkata.model.ProductBatch;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class CartComputeService {

    public CartComputeService() {
        // NOOP
    }

    public void compute(Cart cart) {
        List<ProductBatch> productBatches = cart.getProductsBatches();

        productBatches.forEach(batch -> {
            Product product = batch.getProduct();
            BigDecimal vat = computeVat(product);
            batch.setVat(vat);
            batch.setTotalPrice(product.getPrice().add(vat).multiply(BigDecimal.valueOf(batch.getProductsNumber())));
        });

        cart.setTotalVat(computeTotalVat(productBatches));
        cart.setTotalPrice(computeTotalPrice(productBatches));
    }

    private BigDecimal computeTotalVat(List<ProductBatch> productBatches) {
        return productBatches.stream().map(batch -> batch.getVat().multiply(BigDecimal.valueOf(batch.getProductsNumber()))).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal computeTotalPrice(List<ProductBatch> productBatches) {
        return productBatches.stream().map(batch -> batch.getProduct().getPrice().add(batch.getVat()).multiply(BigDecimal.valueOf(batch.getProductsNumber()))).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal computeVat(Product product) {
        BigDecimal vatRate = getVatRate(product.getProductType(), product.isImported());
        BigDecimal totalVat = product.getPrice().multiply(vatRate);
        return totalVat.setScale(2, RoundingMode.UP);
    }

    private BigDecimal getVatRate(Product.ProductType productType, boolean isImported) {
        double rawVat;
        switch (productType) {
            case BOOK:
                rawVat = 0.1;
                break;
            case OTHER:
                rawVat = 0.2;
                break;
            default:
                rawVat = 0;
        }

        return isImported ? (BigDecimal.valueOf(rawVat)).add(BigDecimal.valueOf(0.05)) : BigDecimal.valueOf(rawVat);
    }
}
