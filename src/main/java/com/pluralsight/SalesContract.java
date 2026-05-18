package com.pluralsight;

public class SalesContract extends Contract{
    private double salesTax = 0.05;
    private double recordingFee = 100;
    private double processingFee;
    private boolean isFinanced;

    public SalesContract(String date, String customerName, String email, Vehicle vehicleSold, double salesTax, double recordingFee, boolean isFinanced) {
        super(date, customerName, email, vehicleSold);
        this.salesTax = salesTax;
        this.recordingFee = recordingFee;
        this.setProcessingFee();
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
        return this.recordingFee + this.processingFee + this.getVehicleSold().getPrice() + (this.getVehicleSold().getPrice() * 0.05);
    }

    @Override
    public double getMonthlyPayment() {
        if (!this.isFinanced){
            return 0;
        }
        if (this.getVehicleSold().getPrice() >= 10000) {
            return ((this.getTotalPrice() * 0.0425) + this.getTotalPrice()) / 48;
        }
        return ((this.getTotalPrice() * 0.0525) + this.getTotalPrice()) / 24;
    }
}
