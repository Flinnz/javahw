package com.tokyoghoul.taskone.models;

import java.util.Objects;
import java.util.StringJoiner;

public final class TruckTransport extends Transport {
    public static final TransportType TYPE = TransportType.TRUCK;

    public final Integer transportedVolumeInCm3;

    public TruckTransport(
            Integer carGovernmentNumber,
            Integer mileage,
            Integer transportedVolumeInCm3) {
        super(carGovernmentNumber, mileage);
        this.transportedVolumeInCm3 = transportedVolumeInCm3;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TruckTransport.class.getSimpleName() + "[", "]")
                .add("carCode=" + TYPE.code)
                .add("transportedVolumeInCm3=" + transportedVolumeInCm3)
                .add("carGovernmentNumber=" + carGovernmentNumber)
                .add("mileage=" + mileage)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TruckTransport that = (TruckTransport) o;
        return Objects.equals(transportedVolumeInCm3, that.transportedVolumeInCm3);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), transportedVolumeInCm3);
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
