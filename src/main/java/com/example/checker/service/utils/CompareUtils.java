package com.example.checker.service.utils;

import org.springframework.stereotype.Service;

@Service
public class CompareUtils {

    public static boolean doublesAreEqual(double value1, double value2, double delta) {
        return doublesAreEqual(value1, value2) || Math.abs(value1 - value2) <= delta;
    }

    private static boolean doublesAreEqual(double value1, double value2) {
        return Double.doubleToLongBits(value1) == Double.doubleToLongBits(value2);
    }
}
