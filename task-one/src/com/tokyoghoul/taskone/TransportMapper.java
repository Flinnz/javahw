package com.tokyoghoul.taskone;

import com.tokyoghoul.taskone.models.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class TransportMapper implements Mapper<Transport> {
    @Override
    public Transport map(String object) {
        if (object.charAt(0) != 'C')
            throw new IllegalArgumentException("String: " + object + " is not valid car object!");

        int codeEndIndex = object.indexOf('_');
        int codeCar = Integer.parseInt(object.substring(1, codeEndIndex));
        int govNumberEndIndex = object.indexOf('-');
        int govNumber = Integer.parseInt(object.substring(codeEndIndex + 1, govNumberEndIndex));
        int mileageEndIndex = object.indexOf('-', govNumberEndIndex + 1);
        int mileage = mileageEndIndex > 0
                ? Integer.parseInt(object.substring(govNumberEndIndex + 1, mileageEndIndex))
                : Integer.parseInt(object.substring(govNumberEndIndex + 1));
        int additionalParam = mileageEndIndex > 0
                ? Integer.parseInt(object.substring(mileageEndIndex + 1))
                : 0;

        switch (codeCar) {
            case 100:
                return new CarTransport(govNumber, mileage);
            case 200:
                return new TruckTransport(govNumber, mileage, additionalParam);
            case 300:
                return new PassengerTransport(govNumber, mileage, additionalParam);
            case 400:
                return new CraneTransport(govNumber, mileage, additionalParam);
            default:
                throw new NotImplementedException();
        }
    }
}
