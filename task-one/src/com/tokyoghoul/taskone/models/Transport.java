package com.tokyoghoul.taskone.models;

import java.util.Objects;

public abstract class Transport {

    public final Integer carGovernmentNumber;
    public final Integer mileage;

    protected Transport(
            Integer carGovernmentNumber,
            Integer mileage) {
        this.carGovernmentNumber = carGovernmentNumber;
        this.mileage = mileage;
    }

    public abstract TransportType getType();
    public abstract Integer getCode();
    public abstract Double getLiterOfFuelCost();
    public abstract Double getFuelConsumptionPer100km();

    public Double getCost() {
        return mileage.doubleValue()/100*getFuelConsumptionPer100km()*getLiterOfFuelCost();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transport transport = (Transport) o;
        return Objects.equals(carGovernmentNumber, transport.carGovernmentNumber) &&
                Objects.equals(mileage, transport.mileage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carGovernmentNumber, mileage);
    }
}
