package com.pluralsight;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class UserInterface {
    private Dealership dealership;
    private Scanner scanner = new Scanner(System.in);

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
                    "10 - Sell/Lease a vehicle%n" +
                    "99 - Quit%n" +
                    "What do you want to do: "
            );

            switch (scanner.nextLine()) {
                case "1" -> processGetByPriceRequest();
                case "2" -> processGetByMakeModelRequest();
                case "3" -> processGetByYearRequest();
                case "4" -> processGetByColorRequest();
                case "5" -> processGetByMileageRequest();
                case "6" -> processGetByVehicleTypeRequest();
                case "7" -> processGetAllVehiclesRequest();
                case "8" -> processAddVehicleRequest();
                case "9" -> processRemoveVehicleRequest();
                case "10" -> processSellOrLeaseContract();
                case "99" -> {
                    System.out.printf("Goodbye%n");
                    return;
                }
                default -> System.out.printf("Invalid option%n");
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

    public void processSellOrLeaseContract() {
        System.out.print("Enter VIN: ");
        int vin = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Customer Name: ");
        String customerName = scanner.nextLine();

        System.out.print("Enter Customer Email: ");
        String customerEmail = scanner.nextLine();

        System.out.print("Sale or Lease? (Type Sale or Lease): ");
        String saleOrLease = scanner.nextLine();

        if (!saleOrLease.equalsIgnoreCase("sale") && !saleOrLease.equalsIgnoreCase("lease")) {
            System.out.println("Please enter valid sale or lease option!");
            return;
        }

        Vehicle vehicle = dealership.getVehicleByVin(vin);

        if (vehicle == null) {
            System.out.println("Vehicle not found");
            return;
        }

        ContractFileManager contractFileManager = new ContractFileManager();

        if (saleOrLease.equalsIgnoreCase("sale")) {
            System.out.print("Do you want to finance? True Or False:");
            boolean isFinanced = scanner.nextBoolean();
            scanner.nextLine();

            Contract contract = new SalesContract(new Date().toString(), customerName, customerEmail, vehicle, isFinanced);
            contractFileManager.saveContract(contract);
        }

        if (saleOrLease.equalsIgnoreCase("lease")) {
            if (LocalDate.now().getYear() - vehicle.getYear() > 3) {
                System.out.print("You cannot lease vehicle over 3 years old");
                return;
            }

            Contract contract = new LeaseContract(new Date().toString(), customerName, customerEmail, vehicle);
            contractFileManager.saveContract(contract);
        }
    }
}
