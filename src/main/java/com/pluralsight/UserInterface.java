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
                    System.out.printf("1%n");
                    break;
                case "2":
                    System.out.printf("2%n");
                    break;
                case "3":
                    System.out.printf("3%n");
                    break;
                case "4":
                    System.out.printf("4%n");
                    break;
                case "5":
                    System.out.printf("5%n");
                    break;
                case "6":
                    System.out.printf("6%n");
                    break;
                case "7":
                    processGetAllVehiclesRequest();
                    break;
                case "8":
                    processAddVehicleRequest();
                    break;
                case "9":
                    System.out.printf("9%n");
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

    public void processGetByPriceRequest() {}

    public void processGetByMakeModelRequest() {}

    public void processGetByYearRequest() {}

    public void processGetByColorRequest() {}

    public void processGetByMileageRequest() {}

    public void processGetByVehicleTypeRequest() {}

    public void processGetAllVehiclesRequest() {
        ArrayList<Vehicle> vehicles = dealership.getAllVehicles();

        displayVehicles(vehicles);
    }

    public void processAddVehicleRequest() {
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

    public void processRemoveVehicleRequest() {}
}
