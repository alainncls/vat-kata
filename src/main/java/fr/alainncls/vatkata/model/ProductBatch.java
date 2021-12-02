package fr.alainncls.vatkata.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProductBatch {

    private Product product;
    private int productsNumber;
    private BigDecimal vat;
    private BigDecimal totalPrice;

}