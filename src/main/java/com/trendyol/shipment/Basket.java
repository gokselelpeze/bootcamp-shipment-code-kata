package com.trendyol.shipment;

import java.util.List;

public class Basket {

    private List<Product> products;
    private final ShipmentSizeService shipmentSizeService;

    public Basket(ShipmentSizeService shipmentSizeService) {
        this.shipmentSizeService = shipmentSizeService;
    }

    public ShipmentSize getShipmentSize() {
        return shipmentSizeService.calculateShipmentSize(products);
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
