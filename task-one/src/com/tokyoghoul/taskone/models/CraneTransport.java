package com.tokyoghoul.taskone.models;

import java.util.Objects;
import java.util.StringJoiner;

public final class CraneTransport extends Transport {
    public static final TransportType TYPE = TransportType.CRANE;

    public final Integer liftedWeightsInTon;

    public CraneTransport(
            Integer carGovernmentNumber,
            Integer liftedWeightsInTon,
            Integer mileage) {
        super(carGovernmentNumber, mileage);
        this.liftedWeightsInTon = liftedWeightsInTon;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CraneTransport.class.getSimpleName() + "[", "]")
                .add("carCode=" + TYPE.code)
                .add("liftedWeightsInTon=" + liftedWeightsInTon)
                .add("carGovernmentNumber=" + carGovernmentNumber)
                .add("mileage=" + mileage)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CraneTransport that = (CraneTransport) o;
        return Objects.equals(liftedWeightsInTon, that.liftedWeightsInTon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), liftedWeightsInTon);
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
