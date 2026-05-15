package com.pluralsight;

import java.util.regex.Pattern;

public class Vehicle {
    public int vin;
    public int year;
    public String make;
    public String model;
    public String vehicleType;
    public String color;
    public int odometer;
    public double price;

    public Vehicle(int vin, int year, String make, String model, String vehicleType, String color, int odometer, double price) {
        this.vin = vin;
        this.year = year;
        this.make = make;
        this.model = model;
        this.vehicleType = vehicleType;
        this.color = color;
        this.odometer = odometer;
        this.price = price;
    }

//    public static void praseFields(String line) {
//        String[] vehicleFields = line.split(Pattern.quote("|"));
//        Vehicle vehicle = new Vehicle(
//                Integer.parseInt(vehicleFields[0]),
//                Integer.parseInt(vehicleFields[1]),
//                vehicleFields[2],
//                vehicleFields[3],
//                vehicleFields[4],
//                vehicleFields[5],
//                Integer.parseInt(vehicleFields[6]),
//                Double.parseDouble(vehicleFields[7])
//        );
//        return vehicle;
//    }

    @Override
    public String toString() {
        return vin + " | " +
                year + " | " +
                make + " | " +
                model + " | " +
                vehicleType + " | " +
                color + " | " +
                odometer + " | $" +
                price;
    }
}
