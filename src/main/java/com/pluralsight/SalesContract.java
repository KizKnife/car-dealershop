package com.pluralsight;

public class SalesContract extends Contract{
    private double salesTax = 0.05;
    private double recordingFee = 100;
    private double processingFee;
    private boolean isFinanced;

    public SalesContract(String date, String customerName, String email, Vehicle vehicleSold, double totalPrice,
                         double monthlyPayment, double salesTax, double recordingFee, double processingFee, boolean isFinanced) {
        super(date, customerName, email, vehicleSold, totalPrice, monthlyPayment);
        this.salesTax = salesTax;
        this.recordingFee = recordingFee;
        this.processingFee = processingFee;
        this.isFinanced = isFinanced;
    }

    public double getSalesTax() {
        return salesTax;
    }

    public void setSalesTax(double salesTax) {
        this.salesTax = salesTax;
    }

    public double getRecordingFee() {
        return recordingFee;
    }

    public void setRecordingFee(double recordingFee) {
        this.recordingFee = recordingFee;
    }

    public double getProcessingFee() {
        return processingFee;
    }

    public void setProcessingFee() {

        if (this.getVehicleSold().getPrice() < 10000) {
            this.processingFee = 295;
        } else {
            this.processingFee = 495;
        }
    }

    public boolean isFinanced() {
        return isFinanced;
    }

    public void setFinanced(boolean financed) {
        isFinanced = financed;
    }

    @Override
    public double getTotalPrice() {
        return 0;
    }

    @Override
    public double getMonthlyPayment() {
        if (!this.isFinanced){
            return 0;
        }
        if (this.getVehicleSold().getPrice() >= 10000) {
            return ((getVehicleSold().getPrice() * 0.0425) + getVehicleSold().getPrice()) / 48;
        }
        return ((getVehicleSold().getPrice() * 0.0525) + getVehicleSold().getPrice()) / 24;
    }
}
