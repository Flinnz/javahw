package com.tokyoghoul.taskone.models;

import java.util.Objects;
import java.util.StringJoiner;

public final class PassengerTransport extends Transport {
    public static final TransportType TYPE = TransportType.PASSENGER;

    public final Integer passengers;

    public PassengerTransport(
            Integer carGovernmentNumber,
            Integer passengers,
            Integer mileage) {
        super(carGovernmentNumber, mileage);
        this.passengers = passengers;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", PassengerTransport.class.getSimpleName() + "[", "]")
                .add("carCode=" + TYPE.code)
                .add("passengers=" + passengers)
                .add("carGovernmentNumber=" + carGovernmentNumber)
                .add("mileage=" + mileage)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PassengerTransport that = (PassengerTransport) o;
        return Objects.equals(passengers, that.passengers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), passengers);
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
