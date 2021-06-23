package com.tokyoghoul.taskone;

import com.tokyoghoul.taskone.models.TransportType;

import java.util.Comparator;
import java.util.List;

public interface Storage<T, U> {
    Boolean add(T object);
    T get(U type, Integer number);
    T remove(U type, Integer number);

    Double getAllCost();
    Double getTypeCost(U type);

    U getMinCostType();
    U getMaxCostType();
    <V extends T> List<V> getOrderedAutoInfo(TransportType transportType, Comparator<V> comparator);
    List<T> getOrderedAutoInfo(Comparator<T> comparator);
}
