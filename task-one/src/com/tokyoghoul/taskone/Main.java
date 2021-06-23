package com.tokyoghoul.taskone;

import com.tokyoghoul.taskone.models.CarTransport;
import com.tokyoghoul.taskone.models.Transport;
import com.tokyoghoul.taskone.models.TransportType;
import com.tokyoghoul.taskone.models.TruckTransport;

import java.util.*;

public class Main {
    private final static String[] test = {"C100_1-100", "C200_1-120-1200", "C300_1-120-30", "C400_1-80-20",
            "C100_2-50", "C200_2-40-1000", "C300_2-200-45", "C400_2-10-20", "C100_3-10",
            "C200_3-170-1100", "C300_3-150-29", "C400_3-100-28", "C100_1-300",
            "C200_1-100-750", "C300_1-32-15"};
    public static void main(String[] args) {
        Map<TransportType, Map<Integer, Transport>> collection = new HashMap<>();
        collection.put(TransportType.CAR, new HashMap<>());
        collection.put(TransportType.TRUCK, new HashMap<>());
        collection.put(TransportType.PASSENGER, new HashMap<>());
        collection.put(TransportType.CRANE, new HashMap<>());
        Map<TransportType, Double> costSum = new HashMap<>();
        TransportStorage storage = new TransportStorage(collection, costSum);
        TransportMapper mapper = new TransportMapper();
        for (String teststr : test) {
            storage.add(mapper.map(teststr));
        }
        //по возрастанию километража легковых автомобилей
        System.out.println("по возрастанию километрожа легковых автомобилей");
        for (CarTransport car : storage.<CarTransport>getOrderedAutoInfo(CarTransport.TYPE, Comparator.comparing(x -> x.mileage))){
            System.out.println(car.toString());
        }
        //по убыванию километрожа легковых автомобилей
        System.out.println("по убыванию километрожа легковых автомобилей");
        for (CarTransport car : storage.<CarTransport>getOrderedAutoInfo(CarTransport.TYPE, Comparator.comparing(x -> -x.mileage))){
            System.out.println(car.toString());
        }
        //по возрастанию километража всех автомобилей
        System.out.println("по возрастанию километража всех автомобилей");
        for (Transport car : storage.getOrderedAutoInfo(Comparator.comparing(x -> x.mileage))){
            System.out.println(car.toString());
        }

        //по перевезенному грузу грузовых автомобилей
        System.out.println("по перевезенному грузу грузовых автомобилей");
        for (TruckTransport car : storage.<TruckTransport>getOrderedAutoInfo(TransportType.TRUCK, Comparator.comparing(x -> x.transportedVolumeInCm3))){
            System.out.println(car.toString());
        }
        //по перевезенному грузу грузовых автомобилей
        System.out.println("по перевезенному грузу грузовых автомобилей");
        for (TruckTransport car : storage.<TruckTransport>getOrderedAutoInfo(TransportType.TRUCK, Comparator.comparing(x -> -x.transportedVolumeInCm3))){
            System.out.println(car.toString());
        }

        System.out.println("Тип авто с минимальными расходами");
        System.out.println(storage.getMinCostType().toString());
        System.out.println(storage.getTypeCost(storage.getMinCostType()));

        System.out.println("Тип авто с максимальными расходами");
        System.out.println(storage.getMaxCostType().toString());
        System.out.println(storage.getTypeCost(storage.getMaxCostType()));

        System.out.println("максимальный расход");
        System.out.println(storage.getAllCost().toString());
    }
}
