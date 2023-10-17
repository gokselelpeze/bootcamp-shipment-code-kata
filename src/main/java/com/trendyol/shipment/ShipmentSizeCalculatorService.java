package com.trendyol.shipment;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ShipmentSizeCalculatorService {

    private static final int BASKET_SHIPMENT_SIZE_THRESHOLD = 3;

    public ShipmentSize calculateShipmentSize(List<Product> products) {

        if (isProductCountBelowThreshold(products)) {
            return getBiggestShipmentSize(products);
        }

        return getShipmentSizeBasedOnThreshold(products);
    }

    private boolean isProductCountBelowThreshold(List<Product> products) {
        return products.size() < BASKET_SHIPMENT_SIZE_THRESHOLD;
    }

    private ShipmentSize getBiggestShipmentSize(List<Product> products) {
        return products.stream()
                .map(Product::getSize)
                .max(Comparator.naturalOrder())
                .orElseThrow(() -> new RuntimeException("Unable to find the maximum shipment size"));
    }

    private ShipmentSize getShipmentSizeBasedOnThreshold(List<Product> products) {
        Optional<ShipmentSize> biggestShipmentSize = findBiggestShipmentSizeMeetingThreshold(products);
        return biggestShipmentSize.map(ShipmentSize::getBiggerSize)
                .orElseGet(() -> getBiggestShipmentSize(products));
    }

    private Optional<ShipmentSize> findBiggestShipmentSizeMeetingThreshold(List<Product> products) {
        return products.stream()
                .map(Product::getSize)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() >= BASKET_SHIPMENT_SIZE_THRESHOLD)
                .map(Map.Entry::getKey)
                .max(Enum::compareTo);
    }
}
