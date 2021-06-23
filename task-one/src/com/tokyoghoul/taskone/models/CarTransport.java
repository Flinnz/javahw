package com.tokyoghoul.taskone.models;

import java.util.StringJoiner;

public final class CarTransport extends Transport {

    public static final TransportType TYPE = TransportType.CAR;

    public CarTransport(
            Integer carGovernmentNumber,
            Integer mileage) {
        super(carGovernmentNumber, mileage);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", PassengerTransport.class.getSimpleName() + "[", "]")
                .add("carCode=" + TYPE.code)
                .add("carGovernmentNumber=" + carGovernmentNumber)
                .add("mileage=" + mileage)
                .toString();
    }

    @Override
    public TransportType getType() {
        return TYPE;
    }

    @Override
    public Integer getCode() {
        return TYPE.code;
    }

    @Override
    public Double getLiterOfFuelCost() {
        return TYPE.literOfFuelCost;
    }

    @Override
    public Double getFuelConsumptionPer100km() {
        return TYPE.fuelConsumptionPer100km;
    }
}
