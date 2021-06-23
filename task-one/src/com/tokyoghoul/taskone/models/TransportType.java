package com.tokyoghoul.taskone.models;

public enum TransportType {
    //Легковой автомобиль
    CAR(100, 12.5, 46.1),
    //грузовик
    TRUCK(200, 12.0, 48.9),
    //пассажирский транспорт
    PASSENGER(300, 11.5, 47.5),
    //тяжелая техника
    CRANE(400, 20.0, 48.9);
    public final Integer code;
    public final Double fuelConsumptionPer100km;
    public final Double literOfFuelCost;

    TransportType(Integer code, Double fuelConsumptionPer100km, Double literOfFuelCost) {
        this.code = code;
        this.fuelConsumptionPer100km = fuelConsumptionPer100km;
        this.literOfFuelCost = literOfFuelCost;
    }
}
