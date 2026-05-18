package com.pluralsight;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ContractFileManager {

    public void saveContract(Contract contract) {
        try {
            FileWriter fileWriter = new FileWriter("contracts.csv");

            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(contract.toString());
            bufferedWriter.newLine();

            bufferedWriter.close();

            System.out.println("Contract saved!");
        } catch (IOException e) {
            System.out.println("Could not save contract!");

            e.printStackTrace();
        }
    }
}
