package fr.alainncls.vatkata.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class Product {

    private String name;
    private ProductType productType;
    private boolean isImported;
    private BigDecimal price;

    public enum ProductType {ESSENTIAL, BOOK, OTHER}
}