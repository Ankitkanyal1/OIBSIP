package com.example.unitconverterapplication.helper;

public class ConversionHelper {

    public static double convert(double value, String from, String to) {
        boolean fromIsLength = from.equals("Centimeter") || from.equals("Meter") || from.equals("Kilometer");
        boolean toIsLength = to.equals("Centimeter") || to.equals("Meter") || to.equals("Kilometer");

        boolean fromIsWeight = from.equals("Gram") || from.equals("Kilogram");
        boolean toIsWeight = to.equals("Gram") || to.equals("Kilogram");

        // If trying to convert between different categories (length <-> weight), return -1
        if ((fromIsLength && toIsWeight) || (fromIsWeight && toIsLength)) {
            return -1.0;
        }

        double baseValue;

        // Length conversion
        if (fromIsLength && toIsLength) {
            if (from.equals("Centimeter")) baseValue = value / 100;
            else if (from.equals("Kilometer")) baseValue = value * 1000;
            else baseValue = value; // Meter

            if (to.equals("Centimeter")) return baseValue * 100;
            else if (to.equals("Kilometer")) return baseValue / 1000;
            else return baseValue; // Meter
        }

        // Weight conversion
        if (fromIsWeight && toIsWeight) {
            if (from.equals("Gram")) baseValue = value / 1000;
            else baseValue = value; // Kilogram

            if (to.equals("Gram")) return baseValue * 1000;
            else return baseValue; // Kilogram
        }

        return -1.0; // Invalid or unsupported conversion
    }
}
