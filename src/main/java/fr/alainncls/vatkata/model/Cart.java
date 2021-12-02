package fr.alainncls.vatkata.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class Cart {

    private int id;
    private List<ProductBatch> productsBatches;
    private BigDecimal totalVat;
    private BigDecimal totalPrice;

}