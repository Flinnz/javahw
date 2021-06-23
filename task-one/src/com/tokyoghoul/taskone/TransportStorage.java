package com.tokyoghoul.taskone;

import com.tokyoghoul.taskone.models.CarTransport;
import com.tokyoghoul.taskone.models.Transport;
import com.tokyoghoul.taskone.models.TransportType;

import java.util.*;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;


public class TransportStorage implements Storage<Transport, TransportType> {
    private final Map<TransportType, Map<Integer, Transport>> collection;
    private final Map<TransportType, Double> costTable;
    private boolean needsRecalculate = true;

    private TransportType minCostType = null;
    private TransportType maxCostType = null;

    public TransportStorage(Map<TransportType, Map<Integer, Transport>> collection,
                            Map<TransportType, Double> costTable) {
        this.collection = collection;
        this.costTable = costTable;
    }


    @Override
    public Boolean add(Transport object) {
        TransportType carCode = object.getType();
        if (!collection.containsKey(carCode)) {
            return false;
        }
        Map<Integer, Transport> cars = collection.get(carCode);
        if (!cars.containsKey(object.carGovernmentNumber)) {
            cars.put(object.carGovernmentNumber, object);
            costTable.merge(carCode, object.getCost(), (oldCost, defaultCost) -> oldCost + object.getCost());
            return true;
        }
        return false;
    }

    @Override
    public Transport get(TransportType type, Integer number) {
        if (!collection.containsKey(type)) {
            return null;
        }
        return collection.get(type).get(number);
    }

    @Override
    public Transport remove(TransportType type, Integer number) {
        if (!collection.containsKey(type)) {
            return null;
        }
        Transport deleted = collection.get(type).remove(number);
        costTable.merge(type, 0.0, (oldCost, defaultCost) -> oldCost - deleted.getCost());
        return deleted;
    }

    private void recalculateCostTable() {
        for (Map.Entry<TransportType, Map<Integer, Transport>> entry: collection.entrySet()) {
            Double sum = entry
                    .getValue()
                    .values()
                    .stream()
                    .reduce(0.0, (acc, transport) -> acc + transport.getCost(), Double::sum);
            costTable.put(entry.getKey(), sum);
        }
        needsRecalculate = false;
    }

    @Override
    public Double getAllCost() {
        if (needsRecalculate) recalculateCostTable();
        return costTable.values().stream().reduce(0.0, Double::sum);
    }

    @Override
    public Double getTypeCost(TransportType type) {
        if (needsRecalculate) recalculateCostTable();
        return costTable.get(type);
    }

    private TransportType getCostType(Double initialValue, BiPredicate<Double, Double> predicate) {
        if (needsRecalculate) recalculateCostTable();
        TransportType type = TransportType.CAR;
        for (Map.Entry<TransportType, Map<Integer, Transport>> entry: collection.entrySet()) {
            Double sum = costTable.get(entry.getKey());
            if (predicate.test(initialValue, sum)) {
                initialValue = sum;
                type = entry.getKey();
            }
        }
        return type;
    }

    @Override
    public TransportType getMinCostType() {
        if (minCostType == null)
            minCostType = getCostType(Double.MAX_VALUE, (a, b) -> a > b);
        return minCostType;
    }

    @Override
    public TransportType getMaxCostType() {
        if (maxCostType == null)
            maxCostType = getCostType(Double.MIN_VALUE, (a, b) -> a < b);
        return maxCostType;
    }

    @Override
    public <V extends Transport> List<V> getOrderedAutoInfo(TransportType transportType, Comparator<V> comparator) {
        Map<Integer, Transport> transportMap = collection.get(transportType);
        return transportMap
                .values()
                .stream()
                .map(transport -> (V) transport)
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    @Override
    public List<Transport> getOrderedAutoInfo(Comparator<Transport> comparator) {
        return collection
                .values()
                .stream()
                .flatMap(t -> t.values().stream())
                .sorted(comparator)
                .collect(Collectors.toList());
    }
}
