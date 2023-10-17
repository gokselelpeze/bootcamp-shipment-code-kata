package com.trendyol.shipment;

import java.util.List;

public class Basket {

    private List<Product> products;
    private final ShipmentSizeCalculatorService shipmentSizeCalculatorService;

    public Basket(ShipmentSizeCalculatorService shipmentSizeCalculatorService) {
        this.shipmentSizeCalculatorService = shipmentSizeCalculatorService;
    }

    public ShipmentSize getShipmentSize() {
        return shipmentSizeCalculatorService.calculateShipmentSize(products);
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
