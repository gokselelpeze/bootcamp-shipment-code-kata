package com.trendyol.shipment;

import java.util.stream.Stream;

public enum ShipmentSize {

    SMALL,
    MEDIUM,
    LARGE,
    X_LARGE;

    public ShipmentSize getBiggerSize() {
        return Stream.of(values())
                .skip(this.ordinal() + 1)
                .findFirst()
                .orElse(X_LARGE);
    }
}
