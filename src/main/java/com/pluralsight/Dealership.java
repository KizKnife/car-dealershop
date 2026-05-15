package com.pluralsight;

import java.util.ArrayList;

public class Dealership {
    private String name;
    private String address;
    private String phone;

    private ArrayList<Vehicle> inventory;

    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.inventory = new ArrayList<>();
    }

    public Dealership() {
        this.inventory = new ArrayList<>();
    }

    public ArrayList<Vehicle> getVehiclesByPrice(double min, double max) {

        ArrayList<Vehicle> results = new ArrayList<>();

        for (Vehicle vehicle : inventory) {

            double price = vehicle.getPrice();

            if (price >= min && price <= max) {
                results.add(vehicle);
            }
        }

        return results;
    }

    public ArrayList<Vehicle> getVehiclesByMakeModel(String make, String model) {

        ArrayList<Vehicle> results = new ArrayList<>();

        for (Vehicle vehicle : inventory) {

            boolean makeMatches =
                    make.isEmpty() ||
                            vehicle.getMake().equalsIgnoreCase(make);

            boolean modelMatches =
                    model.isEmpty() ||
                            vehicle.getModel().equalsIgnoreCase(model);

            if (makeMatches && modelMatches) {
                results.add(vehicle);
            }
        }

        return results;
    }

    public ArrayList<Vehicle> getVehiclesByYear(double min, double max) {

        ArrayList<Vehicle> results = new ArrayList<>();

        for (Vehicle vehicle : inventory) {

            int year = vehicle.getYear();

            if (year >= min && year <= max) {
                results.add(vehicle);
            }
        }

        return results;
    }

    public ArrayList<Vehicle> getVehiclesByColor(String color) {

        ArrayList<Vehicle> results = new ArrayList<>();

        for (Vehicle vehicle : inventory) {

            if (vehicle.getColor().equalsIgnoreCase(color)) {
                results.add(vehicle);
            }
        }

        return results;
    }

    public ArrayList<Vehicle> getVehiclesByMileage(double min, double max) {

        ArrayList<Vehicle> results = new ArrayList<>();

        for (Vehicle vehicle : inventory) {

            int odometer = vehicle.getOdometer();

            if (odometer >= min && odometer <= max) {
                results.add(vehicle);
            }
        }

        return results;
    }

    public ArrayList<Vehicle> getVehiclesByType(String vehicleType) {

        ArrayList<Vehicle> results = new ArrayList<>();

        for (Vehicle vehicle : inventory) {

            if (vehicle.getVehicleType().equalsIgnoreCase(vehicleType)) {
                results.add(vehicle);
            }
        }

        return results;
    }

    public ArrayList<Vehicle> getAllVehicles() {
        return inventory;
    }

    public void addVehicle(Vehicle vehicle) {
        inventory.add(vehicle);
    }

    public void removeVehicle(Vehicle vehicle) {
        inventory.remove(vehicle);
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "Dealership{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", inventory=" + inventory +
                '}';
    }
}
