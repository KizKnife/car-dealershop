package com.pluralsight;

import java.io.*;

public class DealershipFileManager {
    public Dealership getDealership() {
        Dealership dealership = null;

        try {
            FileReader fileReader = new FileReader("inventory.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            line = bufferedReader.readLine();

            // dealership
            String[] dealershipFields = line.split("\\|");
            dealership = new Dealership(dealershipFields[0], dealershipFields[1], dealershipFields[2]);

            // vehicles
            while ((line = bufferedReader.readLine()) != null) {
                String[] vehicleFields = line.split("\\|");

                Vehicle vehicle = new Vehicle(
                        Integer.parseInt(vehicleFields[0]),
                        Integer.parseInt(vehicleFields[1]),
                        vehicleFields[2],
                        vehicleFields[3],
                        vehicleFields[4],
                        vehicleFields[5],
                        Integer.parseInt(vehicleFields[6]),
                        Double.parseDouble(vehicleFields[7])
                );

                dealership.addVehicle(vehicle);
            }

            bufferedReader.close();

        } catch (IOException e) {
            System.out.println(
                    "The inventory file could not be read." +
                    "Please make sure the file is available and not locked and try again.");
            e.printStackTrace();
        }

        return dealership;
    }

    public void saveDealership(Dealership dealership) {
        try {
            FileWriter fileWriter = new FileWriter("inventory.csv");

            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // dealership
            bufferedWriter.write(
                    dealership.getName() + "|" +
                        dealership.getAddress() + "|" +
                        dealership.getPhone()
            );

            bufferedWriter.newLine();

            // vehicles
            for (Vehicle vehicle : dealership.getAllVehicles()) {
                bufferedWriter.write(
                        vehicle.vin + "|" +
                            vehicle.year + "|" +
                            vehicle.make + "|" +
                            vehicle.model + "|" +
                            vehicle.vehicleType + "|" +
                            vehicle.color + "|" +
                            vehicle.odometer + "|" +
                            vehicle.price
                );

                bufferedWriter.newLine();
            }

            bufferedWriter.close();

            System.out.println("Dealership saved!");
        } catch (IOException e) {
            System.out.println("Could not save dealership!");

            e.printStackTrace();
        }
    }
}
