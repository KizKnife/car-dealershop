package com.pluralsight;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    private Dealership dealership;
    private Scanner scanner = new Scanner(System.in);

    public void UserInterface() {

    }

    private void init() {
        DealershipFileManager fileManager = new DealershipFileManager();

        dealership = fileManager.getDealership();
    }

    public void display() {
        init();

        while (true) {
            System.out.printf(
                    "Commands:%n" +
                    "1 - Find vehicles within a price range%n" +
                    "2 - Find vehicles by make / model%n" +
                    "3 - Find vehicles by year range%n" +
                    "4 - Find vehicles by color%n" +
                    "5 - Find vehicles by mileage range%n" +
                    "6 - Find vehicles by type (car, truck, SUV, van)%n" +
                    "7 - List ALL vehicles%n" +
                    "8 - Add a vehicle%n" +
                    "9 - Remove a vehicle%n" +
                    "99 - Quit%n" +
                    "What do you want to do: "
            );

            switch (scanner.nextLine()) {
                case "1":
                    processGetByPriceRequest();
                    break;
                case "2":
                    processGetByMakeModelRequest();
                    break;
                case "3":
                    processGetByYearRequest();
                    break;
                case "4":
                    processGetByColorRequest();
                    break;
                case "5":
                    processGetByMileageRequest();
                    break;
                case "6":
                    processGetByVehicleTypeRequest();
                    break;
                case "7":
                    processGetAllVehiclesRequest();
                    break;
                case "8":
                    processAddVehicleRequest();
                    break;
                case "9":
                    processRemoveVehicleRequest();
                    break;
                case "99":
                    System.out.printf("Goodbye%n");
                    return;
                default:
                    System.out.printf("Invalid option%n");
                    break;
            }
        }
    }

    private void displayVehicles(ArrayList<Vehicle> vehicles) {
        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle);
        }
    }

    public void processGetByPriceRequest() {
        System.out.print("Please enter min price: ");
        double min = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Enter max price: $: ");
        double max = scanner.nextDouble();
        scanner.nextLine();

        ArrayList<Vehicle> vehicles = dealership.getVehiclesByPrice(min, max);
        displayVehicles(vehicles);
    }

    public void processGetByMakeModelRequest() {
        System.out.print("Enter make: ");
        String make = scanner.nextLine().trim();

        System.out.print("Enter model: ");
        String model = scanner.nextLine().trim();

        ArrayList<Vehicle> vehicles = dealership.getVehiclesByMakeModel(make, model);
        displayVehicles(vehicles);
    }

    public void processGetByYearRequest() {
        System.out.print("Please enter min year: ");
        double min = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Please enter max year: ");
        double max = scanner.nextDouble();
        scanner.nextLine();

        ArrayList<Vehicle> vehicles = dealership.getVehiclesByYear(min, max);
        displayVehicles(vehicles);
    }

    public void processGetByColorRequest() {
        System.out.print("Please enter color: ");
        String color = scanner.nextLine();

        ArrayList<Vehicle> vehicles = dealership.getVehiclesByColor(color);
        displayVehicles(vehicles);
    }

    public void processGetByMileageRequest() {
        System.out.print("Please enter min mileage: ");
        double min = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Please enter max mileage: ");
        double max = scanner.nextDouble();
        scanner.nextLine();

        ArrayList<Vehicle> vehicles = dealership.getVehiclesByMileage(min, max);
        displayVehicles(vehicles);
    }

    public void processGetByVehicleTypeRequest() {
        System.out.print("Please enter vehicle type: ");
        String vehicleType = scanner.nextLine();

        ArrayList<Vehicle> vehicles = dealership.getVehiclesByType(vehicleType);
        displayVehicles(vehicles);
    }

    public void processGetAllVehiclesRequest() {
        ArrayList<Vehicle> vehicles = dealership.getAllVehicles();

        displayVehicles(vehicles);
    }

    public void processAddVehicleRequest() {
        System.out.println("Adding a vehicle:");
        System.out.print("Enter VIN: ");
        int vin = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter year: ");
        int year = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter make: ");
        String make = scanner.nextLine();

        System.out.print("Enter model: ");
        String model = scanner.nextLine();

        System.out.print("Enter vehicle type: ");
        String type = scanner.nextLine();

        System.out.print("Enter color: ");
        String color = scanner.nextLine();

        System.out.print("Enter odometer reading: ");
        int odometer = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter price: $");
        double price = scanner.nextDouble();
        scanner.nextLine();

        Vehicle vehicle = new Vehicle(
                vin,
                year,
                make,
                model,
                type,
                color,
                odometer,
                price
        );

        dealership.addVehicle(vehicle);

        DealershipFileManager manager =
                new DealershipFileManager();

        manager.saveDealership(dealership);

        System.out.println("Vehicle added!");
    }

    public void processRemoveVehicleRequest() {
        System.out.println("Removing a vehicle:");
        System.out.print("Enter VIN: ");
        int vin = scanner.nextInt();
        scanner.nextLine();

        Vehicle vehicleRemove = null;

        for (Vehicle vehicle : dealership.getAllVehicles()) {
            if (vehicle.getVin() == vin) {
                vehicleRemove = vehicle;
                break;
            }
        }

        if (vehicleRemove != null) {
            dealership.removeVehicle(vehicleRemove);

            DealershipFileManager manager =
                    new DealershipFileManager();

            manager.saveDealership(dealership);

            System.out.println("Vehicle removed!%n");
        } else {
            System.out.println("No vehicle found!%n");
        }
    }
}
